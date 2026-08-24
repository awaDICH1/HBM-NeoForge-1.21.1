package com.hbm.hazard;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.hbm.capability.HbmLivingProps;
import com.hbm.config.GeneralConfig;
import com.hbm.config.RadiationConfig;
import com.hbm.config.ServerConfig;
import com.hbm.hazard.modifier.IHazardModifier;
import com.hbm.hazard.transformer.IHazardTransformer;
import com.hbm.hazard.type.IHazardType;
import com.hbm.main.HBM;
import com.hbm.util.ComparableStack;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * 迁移自 1.12.2 com.hbm.hazard.HazardSystem。
 *
 * ⚠️ 阻塞依赖的迁移方式（详见 P3-MIGRATION.md）：
 *   - RecipesCommon.ComparableStack → com.hbm.util.ComparableStack（已提取 ✓）
 *   - ItemStackUtil.comparableStackFrom → 内联 new ComparableStack(stack).makeSingular()
 *   - ContaminationUtil.getNeutronRads/contaminate/getNoNeutronPlayerRads/neutronActivateInventory → TODO P5
 *   - OreDictionary.getOreIDs/getOreName → 1.21.1 tag（stack.getTags()）；oreMap 键名映射待 P4 OreDictManager
 *   - ForgeRegistries.ITEMS → BuiltInRegistries.ITEM；Tuple → commons Pair
 *   - inventoryContainer → containerMenu；inventorySlots → slots；detectAndSendChanges → broadcastChanges
 *   - NTM_NEUTRON_NBT_KEY 由 ContaminationUtil 静态导入 → 本地常量（P5 恢复静态导入）
 */
public class HazardSystem {

    private static final String NTM_NEUTRON_NBT_KEY = "ntmNeutron";   // TODO P5: 原 static import ContaminationUtil.NTM_NEUTRON_NBT_KEY

    /**
     * Map for OreDict entries, always evaluated first. Avoid registering HazardData with 'doesOverride', as internal order is based on the item's
     * ore dict keys.
     */
    public static final HashMap<String, HazardData> oreMap = new HashMap<>();
    /**
     * Map for items, either with wildcard meta or stuff that's expected to have a variety of damage values, like tools.
     */
    public static final HashMap<Item, HazardData> itemMap = new HashMap<>();
    /**
     * Very specific stacks with item and meta matching. ComparableStack does not support NBT matching, to scale hazards with NBT please use
     * HazardModifiers.
     */
    public static final HashMap<ComparableStack, HazardData> stackMap = new HashMap<>();
    /**
     * For items that should, for whichever reason, be completely exempt from the hazard system.
     */
    public static final HashSet<ComparableStack> stackBlacklist = new HashSet<>();
    public static final HashSet<String> dictBlacklist = new HashSet<>();

    /**
     * For items from outside of that mod that require registration right at end of fml loading
     */
    public static final List<Pair<ResourceLocation, HazardData>> locationRateRegisterList = new CopyOnWriteArrayList<>();
    /**
     * List of hazard transformers, called in order before and after unrolling all the HazardEntries.
     */
    public static final List<IHazardTransformer> trafos = new ArrayList<>();
    private static final int VOLATILITY_THRESHOLD = 16;
    private static final int VOLATILITY_WINDOW_SECONDS = 30;
    private static final int FINAL_HAZARD_CACHE_SIZE = 2048;
    private static final ConcurrentHashMap<ComparableStack, List<HazardData>> hazardDataChronologyCache = new ConcurrentHashMap<>();
    private static final Cache<NbtSensitiveCacheKey, List<HazardEntry>> finalHazardEntryCache =
            CacheBuilder.newBuilder().maximumSize(FINAL_HAZARD_CACHE_SIZE).build();
    private static final Cache<ComparableStack, AtomicInteger> volatilityTracker =
            CacheBuilder.newBuilder().expireAfterWrite(VOLATILITY_WINDOW_SECONDS, TimeUnit.SECONDS).build();
    private static final Set<ComparableStack> volatileItemsBlacklist = ConcurrentHashMap.newKeySet();
    private static final ConcurrentHashMap<UUID, PlayerHazardData> playerHazardDataMap = new ConcurrentHashMap<>();
    private static final Queue<InventoryDelta> inventoryDeltas = new ConcurrentLinkedQueue<>();
    private static final Set<UUID> playersToUpdate = ConcurrentHashMap.newKeySet();
    private static final double minRadRate = 0.000005D;
    private static volatile CompletableFuture<Void> scanFuture = CompletableFuture.completedFuture(null);
    private static long tickCounter = 0;

    /**
     * Schedules a full rescan for a player.
     *
     * @param player The player whose inventory has changed.
     */
    public static void schedulePlayerUpdate(Player player) {
        playersToUpdate.add(player.getUUID());
    }

    /**
     * Records a delta for a single slot in the player's container.
     *
     * @apiNote hazard lookup count-insensitive; effects may be count-sensitive via modifiers; neutron handling delegated to ContaminationUtil
     */
    public static void onInventoryDelta(Player player, int serverSlotIndex, ItemStack oldStack, ItemStack newStack) {
        inventoryDeltas.add(new InventoryDelta(player.getUUID(), serverSlotIndex, oldStack.copy(), newStack.copy()));
    }

    /**
     * Main entry point, called from ServerTickEvent.
     */
    public static CompletableFuture<Void> onServerTickAsync(Executor backgroundExecutor) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();   // 原 FMLCommonHandler.instance().getMinecraftServerInstance()
        if (server == null) return CompletableFuture.completedFuture(null);
        tickCounter++;
        if (tickCounter % RadiationConfig.hazardRate == 0) {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (player.isRemoved()) continue;   // 原 isDead
                PlayerHazardData phd = playerHazardDataMap.computeIfAbsent(player.getUUID(), uuid -> new PlayerHazardData(player));
                if (phd.player != player) {
                    if (GeneralConfig.enableExtendedLogging)
                        HBM.LOGGER.debug("Player {} entity instance changed, re-initializing.", player.getName().getString());
                    phd.updatePlayerReference(player);
                }
                phd.applyActiveHazards();
            }
        }
        CompletableFuture<Void> cur = scanFuture;
        if (!cur.isDone()) return cur;
        if (playersToUpdate.isEmpty() && inventoryDeltas.isEmpty()) return CompletableFuture.completedFuture(null);
        final List<Player> playersForFullScan = new ArrayList<>();
        if (!playersToUpdate.isEmpty()) {
            for (UUID uuid : playersToUpdate) {
                Player p = server.getPlayerList().getPlayer(uuid);   // 原 getPlayerByUUID → getPlayer
                if (p != null && !p.isRemoved()) playersForFullScan.add(p);
            }
            playersToUpdate.clear();
        }
        final List<InventoryDelta> deltasForProcessing = new ArrayList<>();
        InventoryDelta delta;
        while ((delta = inventoryDeltas.poll()) != null) {
            deltasForProcessing.add(delta);
        }
        if (playersForFullScan.isEmpty() && deltasForProcessing.isEmpty()) {
            return CompletableFuture.completedFuture(null);
        }
        scanFuture = processHazardsAsync(playersForFullScan, deltasForProcessing, backgroundExecutor).thenAccept(HazardSystem::applyUpdateResult);
        return scanFuture;
    }

    private static void applyUpdateResult(HazardUpdateResult results) {
        results.fullScanResults.forEach((uuid, result) -> {
            PlayerHazardData phd = playerHazardDataMap.get(uuid);
            if (phd != null) phd.setScanResult(result);
        });
        results.deltaResults.forEach((uuid, result) -> {
            PlayerHazardData phd = playerHazardDataMap.get(uuid);
            if (phd != null) phd.applyDeltaResult(result);
        });
    }

    private static CompletableFuture<HazardUpdateResult> processHazardsAsync(List<Player> playersForFullScan, List<InventoryDelta> deltas,
                                                                             Executor executor) {
        final HashMap<UUID, CompletableFuture<PlayerHazardData.HazardScanResult>> fullScanFutures = new HashMap<>();
        for (Player p : playersForFullScan) {
            if (p == null || p.isRemoved()) continue;
            UUID uuid = p.getUUID();
            fullScanFutures.put(uuid, CompletableFuture.supplyAsync(() -> PlayerHazardData.calculateHazardScanForPlayer(p), executor));
        }
        final CompletableFuture<Void> fullBarrier = CompletableFuture.allOf(fullScanFutures.values().toArray(new CompletableFuture[0]));
        return fullBarrier.thenCompose(ignored -> {
            final HashMap<UUID, PlayerHazardData.HazardScanResult> fullScanResults = new HashMap<>(fullScanFutures.size() * 2);
            for (Map.Entry<UUID, CompletableFuture<PlayerHazardData.HazardScanResult>> e : fullScanFutures.entrySet()) {
                fullScanResults.put(e.getKey(), e.getValue().join());
            }
            final HashMap<UUID, ArrayList<InventoryDelta>> deltasByPlayer = new HashMap<>();
            for (InventoryDelta d : deltas) {
                if (fullScanResults.containsKey(d.playerUUID())) continue;
                deltasByPlayer.computeIfAbsent(d.playerUUID(), uuid -> new ArrayList<>()).add(d);
            }
            final HashMap<UUID, CompletableFuture<PlayerDeltaResult>> deltaFutures = new HashMap<>();
            for (Map.Entry<UUID, ArrayList<InventoryDelta>> e : deltasByPlayer.entrySet()) {
                UUID uuid = e.getKey();
                ArrayList<InventoryDelta> list = e.getValue();
                deltaFutures.put(uuid, CompletableFuture.supplyAsync(() -> computeDeltaForPlayer(list), executor));
            }
            final CompletableFuture<Void> deltaBarrier = CompletableFuture.allOf(deltaFutures.values().toArray(new CompletableFuture[0]));
            return deltaBarrier.thenApply(res -> {
                final HashMap<UUID, PlayerDeltaResult> deltaResults = new HashMap<>(deltaFutures.size() * 2);
                for (Map.Entry<UUID, CompletableFuture<PlayerDeltaResult>> e : deltaFutures.entrySet()) {
                    deltaResults.put(e.getKey(), e.getValue().join());
                }
                return new HazardUpdateResult(Collections.unmodifiableMap(fullScanResults), Collections.unmodifiableMap(deltaResults));
            });
        });
    }

    private static PlayerDeltaResult computeDeltaForPlayer(List<InventoryDelta> deltas) {
        float totalNeutronDelta = 0f;
        Map<Integer, Optional<Consumer<Player>>> finalApplicators = new HashMap<>(Math.max(16, deltas.size() * 2));

        for (InventoryDelta delta : deltas) {
            DeltaUpdate update = calculateDeltaUpdate(delta);
            totalNeutronDelta += update.neutronRadsDelta();
            finalApplicators.put(delta.serverSlotIndex(), update.applicator());
        }
        return new PlayerDeltaResult(Collections.unmodifiableMap(finalApplicators), totalNeutronDelta);
    }

    /**
     * Calculates the change for a single slot. Runs on a background thread.
     *
     * @apiNote hazard presence comparison count-insensitive; applicator effects may be count-sensitive; neutron delta delegated to ContaminationUtil
     */
    private static DeltaUpdate calculateDeltaUpdate(InventoryDelta delta) {
        ItemStack oldStack = delta.oldStack();
        ItemStack newStack = delta.newStack();
        boolean isOldStackHazardous = isStackHazardous(oldStack);
        boolean isNewStackHazardous = isStackHazardous(newStack);

        float neutronDelta = 0;
        if (RadiationConfig.neutronActivation) {
            // TODO P5: 原 neutronDelta -= ContaminationUtil.getNeutronRads(oldStack);
            // TODO P5: 原 neutronDelta += ContaminationUtil.getNeutronRads(newStack);
        }

        if (!isNewStackHazardous) {
            return new DeltaUpdate(Optional.empty(), neutronDelta);
        }

        final int slotIndex = delta.serverSlotIndex();
        Consumer<Player> applicator = p -> {
            if (p.containerMenu == null || slotIndex >= p.containerMenu.slots.size()) return;
            ItemStack liveStack = p.containerMenu.getSlot(slotIndex).getItem();
            applyHazards(liveStack, p);
        };
        return new DeltaUpdate(Optional.of(applicator), neutronDelta);
    }

    public static void onPlayerLogout(Player player) {
        UUID uuid = player.getUUID();
        playersToUpdate.remove(uuid);
        playerHazardDataMap.remove(uuid);
        inventoryDeltas.removeIf(delta -> delta.playerUUID().equals(uuid));
    }

    /**
     * Call when doing hot reload.
     */
    public static void clearCaches() {
        HBM.LOGGER.info("Clearing HBM hazard calculation caches.");
        hazardDataChronologyCache.clear();
        finalHazardEntryCache.invalidateAll();
        volatilityTracker.invalidateAll();
        volatileItemsBlacklist.clear();
    }

    /**
     * @return {@code true} if there exists any applicable {@link HazardEntry} for the stack.
     *
     * @apiNote count insensitive
     */
    public static boolean isStackHazardous(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        return !getHazardsFromStack(stack).isEmpty();
    }

    public static void register(final String oreName, final HazardData data) {
        oreMap.put(oreName, data);
    }

    public static void register(final Item item, final HazardData data) {
        itemMap.put(item, data);
    }

    public static void register(final ResourceLocation loc, final HazardData data) {
        retriveAndRegister(loc, data);
    }

    public static void register(final Block block, final HazardData data) {
        itemMap.put(block.asItem(), data);   // 原 Item.getItemFromBlock
    }

    public static void register(final ItemStack stack, final HazardData data) {
        stackMap.put(new ComparableStack(stack).makeSingular(), data);   // 原 ItemStackUtil.comparableStackFrom
    }

    public static void register(final ComparableStack comp, final HazardData data) {
        stackMap.put(comp, data);
    }

    public static void register(final Object o, final HazardData data) {
        if (o instanceof String s) {
            register(s, data);
            return;
        }
        if (o instanceof Item i) {
            register(i, data);
            return;
        }
        if (o instanceof ResourceLocation rl) {
            register(rl, data);
            return;
        }
        if (o instanceof Block b) {
            register(b, data);
            return;
        }
        if (o instanceof ItemStack is) {
            register(is, data);
            return;
        }
        if (o instanceof ComparableStack cs) {
            register(cs, data);
            return;
        }
        throw new IllegalArgumentException("Unsupported key type for register: " + (o == null ? "null" : o.getClass().getName()));
    }

    public static boolean unregister(final String oreName) {
        return oreMap.remove(oreName) != null;
    }

    public static boolean unregister(final Item item) {
        return itemMap.remove(item) != null;
    }

    public static boolean unregister(final ResourceLocation loc) {
        return removeResourceLocation(loc);
    }

    public static boolean unregister(final Block block) {
        Item item = block.asItem();
        return item != Items.AIR && itemMap.remove(item) != null;
    }

    public static boolean unregister(final ItemStack stack) {
        return stackMap.remove(new ComparableStack(stack).makeSingular()) != null;
    }

    public static boolean unregister(final ComparableStack comp) {
        return stackMap.remove(comp) != null;
    }

    public static boolean unregister(final Object o) {
        if (o instanceof Collection<?> c) {
            boolean removed = false;
            for (Object element : c) {
                removed |= unregister(element);
            }
            return removed;
        }
        if (o == null) return false;

        if (o instanceof String s) return unregister(s);
        if (o instanceof Item i) return unregister(i);
        if (o instanceof ResourceLocation rl) return unregister(rl);
        if (o instanceof Block b) return unregister(b);
        if (o instanceof ItemStack is) return unregister(is);
        if (o instanceof ComparableStack cs) return unregister(cs);
        if (o.getClass().isArray()) {
            boolean removed = false;
            int length = Array.getLength(o);
            for (int i = 0; i < length; i++) {
                Object element = Array.get(o, i);
                removed |= unregister(element);
            }
            return removed;
        }
        throw new IllegalArgumentException("Unsupported key type for unregister: " + o.getClass().getName());
    }

    /**
     * Attempts to retrieve and append an item onto the map from resource location, helpful for groovy users
     */
    private static void retriveAndRegister(ResourceLocation loc, HazardData data) {
        if (BuiltInRegistries.ITEM.containsKey(loc))   // 原 ForgeRegistries.ITEMS
            itemMap.put(BuiltInRegistries.ITEM.get(loc), data);
        else
            locationRateRegisterList.add(Pair.of(loc, data));   // 原 Tuple
    }

    private static boolean removeResourceLocation(ResourceLocation loc) {
        boolean removed = false;
        if (BuiltInRegistries.ITEM.containsKey(loc)) {
            Item item = BuiltInRegistries.ITEM.get(loc);
            if (item != null) {
                removed = itemMap.remove(item) != null;
            }
        }
        removed = removed || locationRateRegisterList.removeIf(pair -> loc.equals(pair.getLeft()));
        return removed;
    }

    public static void blacklist(final ItemStack stack) {
        stackBlacklist.add(new ComparableStack(stack).makeSingular());
    }

    public static void blacklist(final String oreName) {
        dictBlacklist.add(oreName);
    }

    public static void blacklist(final ComparableStack comp) {
        stackBlacklist.add(comp.makeSingular());
    }

    public static void blacklist(final Object o) {
        if (o instanceof ItemStack is) {
            blacklist(is);
            return;
        }
        if (o instanceof String s) {
            blacklist(s);
            return;
        }
        if (o instanceof ComparableStack cs) {
            blacklist(cs);
            return;
        }
        throw new IllegalArgumentException("Unsupported key type for blacklist: " + (o == null ? "null" : o.getClass().getName()));
    }

    public static boolean unblacklist(final ItemStack stack) {
        return stackBlacklist.remove(new ComparableStack(stack).makeSingular());
    }

    public static boolean unblacklist(final String oreName) {
        return dictBlacklist.remove(oreName);
    }

    public static boolean unblacklist(final ComparableStack comp) {
        return stackBlacklist.remove(comp.makeSingular());
    }

    public static boolean unblacklist(final Object o) {
        if (o instanceof Collection<?> c) {
            boolean removed = false;
            for (Object element : c) {
                removed |= unblacklist(element);
            }
            return removed;
        }
        if (o == null) return false;

        if (o instanceof ItemStack is) return unblacklist(is);
        if (o instanceof String s) return unblacklist(s);
        if (o instanceof ComparableStack cs) return unblacklist(cs);
        if (o.getClass().isArray()) {
            boolean removed = false;
            int length = Array.getLength(o);
            for (int i = 0; i < length; i++) {
                Object element = Array.get(o, i);
                removed |= unblacklist(element);
            }
            return removed;
        }
        throw new IllegalArgumentException("Unsupported key type for unblacklist: " + o.getClass().getName());
    }

    /**
     * Checks whether the given stack is blacklisted by exact (item,meta) or by ore dictionary.
     *
     * @apiNote count insensitive
     */
    public static boolean isItemBlacklisted(final ItemStack stack) {
        if (stackBlacklist.contains(new ComparableStack(stack).makeSingular())) return true;
        // TODO P4: 原 OreDictionary 黑名单（dictBlacklist，getOreIDs/getOreName）→ 1.21.1 tag 映射
        return false;
    }

    /**
     * Will return a full list of applicable HazardEntries for this stack.
     * <br><br>ORDER:
     * <ol>
     * <li>ore dict (if multiple keys, in order of the ore dict keys for this stack)
     * <li>item
     * <li>item stack
     * </ol>
     *
     * @apiNote count insensitive (matching uses ComparableStack.makeSingular; NBT sensitivity handled via sanitized hash; neutron NBT ignored)
     */
    public static List<HazardEntry> getHazardsFromStack(final ItemStack stack) {
        if (stack.isEmpty() || isItemBlacklisted(stack)) {
            return Collections.emptyList();
        }

        final ComparableStack compStack = new ComparableStack(stack).makeSingular();

        if (volatileItemsBlacklist.contains(compStack)) {
            return computeHazards(stack, compStack);
        }

        // 1.21.1 ItemStack 无 getTag()：NBT 存于 DataComponents.CUSTOM_DATA（CustomData.copyTag()）
        CompoundTag stackTag = stack.get(DataComponents.CUSTOM_DATA) == null ? null : stack.get(DataComponents.CUSTOM_DATA).copyTag();
        int nbtHash = getSanitizedNbtHash(stackTag);

        final NbtSensitiveCacheKey nbtKey = new NbtSensitiveCacheKey(compStack, nbtHash);

        try {
            return finalHazardEntryCache.get(nbtKey, () -> {
                AtomicInteger missCount = volatilityTracker.get(compStack, AtomicInteger::new);
                if (missCount.incrementAndGet() > VOLATILITY_THRESHOLD) {
                    volatileItemsBlacklist.add(compStack);
                    volatilityTracker.invalidate(compStack);
                }
                return computeHazards(stack, compStack);
            });
        } catch (ExecutionException e) {
            throw new RuntimeException("Error calculating hazard entries for stack: " + stack, e.getCause());
        }
    }

    private static int getSanitizedNbtHash(@Nullable CompoundTag tag) {
        if (tag == null || tag.isEmpty()) {
            return 0;
        }
        if (!tag.contains(NTM_NEUTRON_NBT_KEY)) {
            return tag.hashCode();
        }

        int mapHash = 0;
        boolean hasRemainingEntries = false;
        for (String key : tag.getAllKeys()) {   // 原 keySet → getAllKeys
            if (NTM_NEUTRON_NBT_KEY.equals(key)) {
                continue;
            }
            Tag value = tag.get(key);
            mapHash += key.hashCode() ^ value.hashCode();
            hasRemainingEntries = true;
        }
        return hasRemainingEntries ? Tag.TAG_COMPOUND ^ mapHash : 0;   // 原 Constants.NBT.TAG_COMPOUND
    }

    /**
     * Builds the final, NBT-aware list of hazard entries for a stack.
     *
     * @apiNote count insensitive (chronology keyed by ComparableStack without count; modifiers/types may read count at application time)
     */
    private static List<HazardEntry> computeHazards(ItemStack stack, ComparableStack compStack) {
        if (stack.isEmpty() || compStack.isEmpty()) {
            HBM.LOGGER.debug("HazardSystem.computeHazards got an empty stack or compStack(ItemStack: {}, ComparableStack: {}). " +
                    "This is not supposed to happen, please check for mod incompatibilities.", stack, compStack);
            return Collections.emptyList();
        }
        // Get NBT-agnostic base data
        List<HazardData> chronological = hazardDataChronologyCache.computeIfAbsent(compStack, cs -> {
            final List<HazardData> data = new ArrayList<>();
            ItemStack probe = new ItemStack(cs.item, 1);
            probe.setDamageValue(cs.meta);
            // TODO P4: 原 OreDictionary.getOreIDs/getOreName → 1.21.1 tag；oreMap 键名映射待 OreDictManager
            for (TagKey<Item> tagKey : probe.getTags().toList()) {
                final String name = tagKey.location().toString();
                final HazardData hazardData = oreMap.get(name);
                if (hazardData != null) data.add(hazardData);
            }
            final HazardData itemHazardData = itemMap.get(cs.item);
            if (itemHazardData != null) data.add(itemHazardData);
            final HazardData stackHazardData = stackMap.get(cs);
            if (stackHazardData != null) data.add(stackHazardData);
            return Collections.unmodifiableList(data);
        });

        if (chronological.isEmpty() && trafos.isEmpty()) {
            return Collections.emptyList();
        }

        // Apply NBT-sensitive transformers and build the final list
        final List<HazardEntry> entries = new ArrayList<>();
        for (final IHazardTransformer trafo : trafos) {
            trafo.transformPre(stack, entries);
        }

        int mutex = 0;
        for (final HazardData data : chronological) {
            if (data.doesOverride) entries.clear();
            if ((data.getMutex() & mutex) == 0) {
                entries.addAll(data.entries);
                mutex |= data.getMutex();
            }
        }

        for (final IHazardTransformer trafo : trafos) {
            trafo.transformPost(stack, entries);
        }

        return Collections.unmodifiableList(entries);
    }

    /**
     * Computes the effective level for a specific hazard type from the stack.
     *
     * @apiNote lookup count insensitive; result may be count-sensitive via modifiers
     */
    public static double getHazardLevelFromStack(ItemStack stack, IHazardType hazard) {
        double totalLevel = 0.0;
        for (HazardEntry entry : getHazardsFromStack(stack)) {
            if (entry.type == hazard) {
                totalLevel += IHazardModifier.evalAllModifiers(stack, null, entry.baseLevel, entry.mods);
            }
        }
        return totalLevel;
    }

    public static double getRawRadsFromBlock(Block b) {
        return getHazardLevelFromStack(new ItemStack(b.asItem()), HazardRegistry.RADIATION);
    }

    /**
     * Radiation from configured entries (pre-contamination).
     *
     * @apiNote lookup count insensitive; value may be count-sensitive via modifiers
     */
    public static double getRawRadsFromStack(ItemStack stack) {
        return getHazardLevelFromStack(stack, HazardRegistry.RADIATION);
    }

    /**
     * Total radiation = configured radiation + neutron contamination.
     *
     * @apiNote configured part may be count-sensitive via modifiers; neutron part delegated to ContaminationUtil
     */
    public static double getTotalRadsFromStack(ItemStack stack) {
        // TODO P5: 原 + ContaminationUtil.getNeutronRads(stack)
        return getHazardLevelFromStack(stack, HazardRegistry.RADIATION);
    }

    public static void applyHazards(Block b, LivingEntity entity) {
        applyHazards(new ItemStack(b.asItem()), entity);
    }

    /**
     * Will grab and iterate through all assigned hazards of the given stack and apply their effects to the holder.
     *
     * @apiNote entry selection count insensitive; effect application may be count-sensitive via modifiers/types
     */
    public static void applyHazards(ItemStack stack, LivingEntity entity) {
        if (stack.isEmpty()) return;
        List<HazardEntry> hazards = getHazardsFromStack(stack);
        for (HazardEntry hazard : hazards) {
            hazard.applyHazard(stack, entity);
        }
    }

    /**
     * Updates hazards emitted by a dropped {@link ItemEntity}.
     *
     * @apiNote entry selection count insensitive; evaluated level may be count-sensitive via modifiers
     */
    @SuppressWarnings("unused") // called by asm hook
    public static void updateDroppedItem(ItemEntity entity) {
        if (entity.level().isClientSide || entity.isRemoved()) return;
        ItemStack stack = entity.getItem();
        if (stack.isEmpty()) return;
        int tickrate = Math.max(1, ServerConfig.ITEM_HAZARD_DROP_TICKRATE.get());
        if (entity.level().getGameTime() % tickrate == 0) {
            for (HazardEntry entry : getHazardsFromStack(stack)) {
                entry.type.updateEntity(entity, IHazardModifier.evalAllModifiers(stack, null, entry.baseLevel, entry.mods));
            }
        }
    }

    /**
     * Adds hazard tooltip info.
     *
     * @apiNote entry selection count insensitive; display content may be count-sensitive inside type/modifiers
     */
    public static void addHazardInfo(ItemStack stack, Player player, List<String> list, TooltipFlag flagIn) {
        for (HazardEntry hazard : getHazardsFromStack(stack)) {
            hazard.type.addHazardInformation(player, list, hazard.baseLevel, stack, hazard.mods);
        }
    }

    private static class PlayerHazardData {

        private final Map<Integer, Consumer<Player>> activeApplicators = new ConcurrentHashMap<>();
        private Player player;
        private float totalNeutronRads = 0f;

        PlayerHazardData(Player player) {
            this.player = player;
            schedulePlayerUpdate(player);
        }

        /**
         * Performs a full scan of the player's inventory to build per-slot applicators, and aggregates neutron rads for non-hazardous stacks.
         *
         * @apiNote applicator presence count insensitive; neutron accumulation delegated to ContaminationUtil
         */
        static HazardScanResult calculateHazardScanForPlayer(Player player) {
            Map<Integer, Consumer<Player>> applicators = new HashMap<>();
            float totalNeutronRads = 0f;

            if (player.containerMenu == null) {
                return new HazardScanResult(Collections.emptyMap(), 0f);
            }

            for (int i = 0; i < player.containerMenu.slots.size(); i++) {
                Slot slot = player.containerMenu.getSlot(i);
                if (slot.container != player.getInventory()) continue;   // 原 slot.inventory != player.inventory

                ItemStack stack = slot.getItem();
                if (stack.isEmpty()) continue;

                List<HazardEntry> hazards = getHazardsFromStack(stack);
                if (!hazards.isEmpty()) {
                    final int slotIndex = i;
                    applicators.put(slotIndex, p -> {
                        if (p.containerMenu == null || slotIndex >= p.containerMenu.slots.size()) return;
                        ItemStack liveStack = p.containerMenu.getSlot(slotIndex).getItem();
                        applyHazards(liveStack, p);
                    });
                }
                if (RadiationConfig.neutronActivation && hazards.isEmpty()) {
                    // TODO P5: 原 totalNeutronRads += ContaminationUtil.getNeutronRads(stack);
                }
            }
            return new HazardScanResult(Collections.unmodifiableMap(applicators), totalNeutronRads);
        }

        void updatePlayerReference(Player player) {
            this.player = player;
            schedulePlayerUpdate(player);
        }

        void setScanResult(HazardScanResult result) {
            this.activeApplicators.clear();
            this.activeApplicators.putAll(result.applicatorMap);
            this.totalNeutronRads = Math.max(0f, result.totalNeutronRads);
        }

        void applyDeltaResult(PlayerDeltaResult result) {
            for (Map.Entry<Integer, Optional<Consumer<Player>>> entry : result.finalApplicators.entrySet()) {
                Optional<Consumer<Player>> applicatorOptional = entry.getValue();
                Integer slotIndex = entry.getKey();
                if (applicatorOptional.isPresent()) {
                    activeApplicators.put(slotIndex, applicatorOptional.get());
                } else {
                    activeApplicators.remove(slotIndex);
                }
            }
            this.totalNeutronRads += result.totalNeutronDelta;
            if (this.totalNeutronRads < 0) this.totalNeutronRads = 0;
        }

        void applyActiveHazards() {
            if (player.isRemoved()) return;
            boolean sync = false;

            if (!activeApplicators.isEmpty()) {
                activeApplicators.values().forEach(applier -> applier.accept(this.player));
                sync = true;
            }
            HbmLivingProps.setNeutron(player, 0);

            // 1:1 moved from RadiationSystemNT, but now scales with RadiationConfig.hazardRate
            if (RadiationConfig.neutronActivation) {
                // TODO P5: 原 ContaminationUtil.contaminate(player, HazardType.NEUTRON, ContaminationType.CREATIVE,
                //                 totalNeutronRads * 0.05F * RadiationConfig.hazardRate);
                if (!player.isCreative() && !player.isSpectator()) {
                    // TODO P5: 原 double activationRate = ContaminationUtil.getNoNeutronPlayerRads(player) * 0.00004D - ...;
                    // TODO P5: 原 if (activationRate > minRadRate) { ... ContaminationUtil.neutronActivateInventory(...) ... }
                    double activationRate = 0D;
                    if (activationRate > minRadRate) {
                        float totalActivationAmount = (float) activationRate * RadiationConfig.hazardRate;
                        // TODO P5: 原 if (ContaminationUtil.neutronActivateInventory(player, totalActivationAmount, 1.0F)) { ... }
                        if (false) {
                            schedulePlayerUpdate(this.player);
                            sync = true;
                        }
                    }
                }
            }

            if (sync && this.player.containerMenu != null) {
                this.player.containerMenu.broadcastChanges();   // 原 detectAndSendChanges
            }
        }

        record HazardScanResult(Map<Integer, Consumer<Player>> applicatorMap, float totalNeutronRads) {
        }
    }

    private record NbtSensitiveCacheKey(ComparableStack stack, int nbtHash) {
    }

    private record InventoryDelta(UUID playerUUID, int serverSlotIndex, ItemStack oldStack, ItemStack newStack) {
    }

    private record DeltaUpdate(Optional<Consumer<Player>> applicator, float neutronRadsDelta) {
    }

    private record PlayerDeltaResult(Map<Integer, Optional<Consumer<Player>>> finalApplicators, float totalNeutronDelta) {
    }

    private record HazardUpdateResult(Map<UUID, PlayerHazardData.HazardScanResult> fullScanResults, Map<UUID, PlayerDeltaResult> deltaResults) {
    }
}
