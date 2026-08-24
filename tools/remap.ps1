# ============================================================
# remap.ps1 — 1.12.2 MCP 名称 → 1.21.1 Mojang 官方名 批量替换脚本
#
# 用途：对复制进 HBM-NeoForge 的 CE 源文件做机械映射替换。
# 用法：
#   powershell -ExecutionPolicy Bypass -File tools/remap.ps1 -Path src/main/java/com/hbm/util
#   powershell -ExecutionPolicy Bypass -File tools/remap.ps1 -Path src/main/java/com/hbm -DryRun   # 仅统计
#
# ⚠️ 规则按"更具体优先"排序；脚本只做机械替换，仍有下列情况必须人工复核：
#   - TE 字段 world→level、pos→worldPosition（字段重命名无法正则安全覆盖）
#   - getEntityData()/getDataManager() 两条规则顺序敏感（脚本已处理）
#   - 泛型/重载/签名变化（如 setBlockToAir→removeBlock(pos,false)、getTagList 签名、Capability 系统）
#   - 被删除的 Forge 专有 API（IUnlistedProperty、CapabilityManager、FluidRegistry、ForgeChunkManager…）
# ============================================================

param(
    [string]$Path = (Join-Path $PSScriptRoot '..\src\main\java'),
    [switch]$DryRun
)

$rules = @(
    # ================= 导入路径（精确字符串替换，最安全） =================
    @('net.minecraft.util.math.BlockPos', 'net.minecraft.core.BlockPos'),
    @('net.minecraft.util.math.Vec3d', 'net.minecraft.world.phys.Vec3'),
    @('net.minecraft.util.math.Vec3i', 'net.minecraft.core.Vec3i'),
    @('net.minecraft.util.math.Vec2f', 'net.minecraft.world.phys.Vec2'),
    @('net.minecraft.util.math.AxisAlignedBB', 'net.minecraft.world.phys.AABB'),
    @('net.minecraft.util.math.MathHelper', 'net.minecraft.util.Mth'),
    @('net.minecraft.util.math.RayTraceResult', 'net.minecraft.world.phys.HitResult'),
    @('net.minecraft.util.math.RayTraceResult.Type', 'net.minecraft.world.phys.HitResult.Type'),
    @('net.minecraft.util.EnumFacing', 'net.minecraft.core.Direction'),
    @('net.minecraft.util.Rotation', 'net.minecraft.Rotation'),
    @('net.minecraft.util.text.TextFormatting', 'net.minecraft.ChatFormatting'),
    @('net.minecraft.util.text.TextComponentTranslation', 'net.minecraft.network.chat.Component'),
    @('net.minecraft.util.text.TextComponentString', 'net.minecraft.network.chat.Component'),
    @('net.minecraft.util.text.ITextComponent', 'net.minecraft.network.chat.Component'),
    @('net.minecraft.tileentity.TileEntitySpecialRenderer', 'net.neoforged.neoforge.client.extensions.common.IClientBlockExtensions'), # 仅占位，实际需逐个改写
    @('net.minecraft.tileentity.TileEntityType', 'net.minecraft.world.level.block.entity.BlockEntityType'),
    @('net.minecraft.tileentity.TileEntity', 'net.minecraft.world.level.block.entity.BlockEntity'),
    @('net.minecraft.entity.player.EntityPlayerMP', 'net.minecraft.server.level.ServerPlayer'),
    @('net.minecraft.entity.player.EntityPlayerSP', 'net.minecraft.client.player.LocalPlayer'),
    @('net.minecraft.entity.player.EntityPlayer', 'net.minecraft.world.entity.player.Player'),
    @('net.minecraft.entity.EntityLivingBase', 'net.minecraft.world.entity.LivingEntity'),
    @('net.minecraft.entity.EntityLiving', 'net.minecraft.world.entity.Mob'),
    @('net.minecraft.entity.EnumCreatureType', 'net.minecraft.world.entity.MobCategory'),
    @('net.minecraft.entity.Entity', 'net.minecraft.world.entity.Entity'),
    @('net.minecraft.item.ItemStack', 'net.minecraft.world.item.ItemStack'),
    @('net.minecraft.item.Item', 'net.minecraft.world.item.Item'),
    @('net.minecraft.item.ItemBlock', 'net.minecraft.world.item.BlockItem'),
    @('net.minecraft.nbt.NBTTagIntArray', 'net.minecraft.nbt.IntArrayTag'),
    @('net.minecraft.nbt.NBTTagLongArray', 'net.minecraft.nbt.LongArrayTag'),
    @('net.minecraft.nbt.NBTTagByteArray', 'net.minecraft.nbt.ByteArrayTag'),
    @('net.minecraft.nbt.NBTTagCompound', 'net.minecraft.nbt.CompoundTag'),
    @('net.minecraft.nbt.NBTTagList', 'net.minecraft.nbt.ListTag'),
    @('net.minecraft.nbt.NBTTagString', 'net.minecraft.nbt.StringTag'),
    @('net.minecraft.nbt.NBTTagInt', 'net.minecraft.nbt.IntTag'),
    @('net.minecraft.nbt.NBTTagLong', 'net.minecraft.nbt.LongTag'),
    @('net.minecraft.nbt.NBTTagByte', 'net.minecraft.nbt.ByteTag'),
    @('net.minecraft.nbt.NBTTagDouble', 'net.minecraft.nbt.DoubleTag'),
    @('net.minecraft.nbt.NBTTagFloat', 'net.minecraft.nbt.FloatTag'),
    @('net.minecraft.nbt.NBTTagShort', 'net.minecraft.nbt.ShortTag'),
    @('net.minecraft.nbt.NBTBase', 'net.minecraft.nbt.Tag'),
    @('net.minecraft.util.EnumHand', 'net.minecraft.world.InteractionHand'),
    @('net.minecraft.util.EnumActionResult', 'net.minecraft.world.InteractionResult'),
    @('net.minecraft.util.NonNullList', 'net.minecraft.core.NonNullList'),
    @('net.minecraft.util.ResourceLocation', 'net.minecraft.resources.ResourceLocation'),
    @('net.minecraft.util.math.BlockPos', 'net.minecraft.core.BlockPos'), # 重复无害
    @('net.minecraft.block.state.IBlockState', 'net.minecraft.world.level.block.state.BlockState'),
    @('net.minecraft.block.Block', 'net.minecraft.world.level.block.Block'),
    @('net.minecraft.world.WorldServer', 'net.minecraft.server.level.ServerLevel'),
    @('net.minecraft.world.World', 'net.minecraft.world.level.Level'),
    @('net.minecraft.world.IBlockAccess', 'net.minecraft.world.level.BlockGetter'),
    @('net.minecraftforge\.fml\.relauncher\.Side\b', 'net.neoforged.api.distmarker.Dist'),
    @('net.minecraftforge.fml.relauncher.SideOnly', 'net.neoforged.api.distmarker.OnlyIn'),
    @('net.minecraftforge.fml.common.eventhandler.SubscribeEvent', 'net.neoforged.bus.api.SubscribeEvent'),
    @('net.minecraftforge.common.MinecraftForge', 'net.neoforged.neoforge.common.NeoForge'),
    @('net.minecraft.client.resources.I18n', 'net.minecraft.client.resources.language.I18n'),
    @('net.minecraft.client.gui.FontRenderer', 'net.minecraft.client.gui.Font'),
    @('net.minecraft.client.renderer.GlStateManager', 'net.minecraft.client.renderer.RenderSystem'),
    @('net.minecraft.util.math.MathHelper', 'net.minecraft.util.Mth'),

    # ================= 标识符/类型（词边界，更长者优先） =================
    @('WorldServer', 'ServerLevel'),
    @('\bWorld\b', 'Level'),
    @('EntityPlayerMP', 'ServerPlayer'),
    @('\bEntityPlayer\b', 'Player'),
    @('IBlockState', 'BlockState'),
    @('NBTTagIntArray', 'IntArrayTag'),
    @('NBTTagLongArray', 'LongArrayTag'),
    @('NBTTagByteArray', 'ByteArrayTag'),
    @('NBTTagCompound', 'CompoundTag'),
    @('NBTTagList', 'ListTag'),
    @('NBTTagString', 'StringTag'),
    @('NBTTagInt\b', 'IntTag'),
    @('NBTTagLong\b', 'LongTag'),
    @('NBTTagByte\b', 'ByteTag'),
    @('NBTTagDouble', 'DoubleTag'),
    @('NBTTagFloat', 'FloatTag'),
    @('NBTTagShort', 'ShortTag'),
    @('NBTBase', 'Tag'),
    @('MathHelper', 'Mth'),
    @('EnumFacing', 'Direction'),
    @('AxisAlignedBB', 'AABB'),
    @('\bVec3d\b', 'Vec3'),
    @('TextFormatting', 'ChatFormatting'),
    @('TileEntitySpecialRenderer', 'BlockEntityRenderer'),
    @('TileEntityType', 'BlockEntityType'),
    @('\bTileEntity\b', 'BlockEntity'),
    @('EntityLivingBase', 'LivingEntity'),
    @('EntityLiving\b', 'Mob'),
    @('EnumHand', 'InteractionHand'),
    @('EnumActionResult', 'InteractionResult'),
    @('EnumCreatureType', 'MobCategory'),
    @('RayTraceResult', 'HitResult'),
    @('\bIBlockAccess\b', 'BlockGetter'),
    @('ForgeRegistries\.(BLOCKS|ITEMS|ENTITIES|TILE_ENTITIES)', 'ModBlocks.BLOCKS'), # 占位：注册系统 P3 手工重写

    # ================= 注解 =================
    @('@SideOnly\(Side\.CLIENT\)', '@OnlyIn(Dist.CLIENT)'),
    @('@SideOnly\(Side\.SERVER\)', '@OnlyIn(Dist.DEDICATED_SERVER)'),

    # ================= World/Level 方法 =================
    @('\.isRemote\b', '.isClientSide'),
    @('\.getTileEntity\(', '.getBlockEntity('),
    @('\.getTotalWorldTime\(', '.getGameTime('),
    @('\.getEntityWorld\(\)', '.level()'),
    @('\.getWorld\(\)', '.getLevel()'),
    @('\.getPos\(\)', '.getBlockPos()'),
    @('\.markDirty\(\)', '.setChanged()'),
    @('\.spawnEntity\(', '.addFreshEntity('),
    @('\.playerEntities\b', '.players()'),
    @('\.getChunkFromBlockCoords\(', '.getChunkAt('),
    @('\.isBlockLoaded\(', '.hasChunkAt('),
    @('\.setBlockState\(', '.setBlock('),
    @('\.getClosestPlayerToEntity\(', '.getNearestPlayer('),
    @('\.getStrongPower\(', '.getBestNeighborSignal('),   # 签名可能不同，需复核
    @('\.getRedstonePower\(', '.getBestNeighborSignal('), # 需复核
    @('\.scheduleUpdate\(', '.scheduleTick('),            # 需复核重载

    # ================= 实体方法 =================
    @('\.isDead\b', '.isRemoved()'),
    @('\.setDead\(\)', '.discard()'),
    @('\.getEntityId\(\)', '.getId()'),
    @('\.getDistanceSq\(', '.distanceToSqr('),
    @('\.getDistance\(', '.distanceTo('),
    @('\.getHeldItem\(', '.getItemInHand('),
    @('\.getHeldItemMainhand\(\)', '.getMainHandItem()'),
    @('\.getHeldItemOffhand\(\)', '.getOffhandItem()'),
    @('\.swingArm\(', '.swing('),
    @('\.attackEntityFrom\(', '.hurt('),
    @('\.getEntityData\(\)', '.getPersistentData()'), # 必须先于 getDataManager 规则
    @('\.getDataManager\(\)', '.getEntityData()'),
    @('\.addVelocity\(', '.push('),
    @('\.getRidingEntity\(\)', '.getVehicle()'),
    @('\.isRiding\(\)', '.isPassenger()'),
    @('\.getPositionEyes\(', '.getEyePosition('),
    @('\.getLook\(', '.getLookAngle('),
    @('\.getEntityBoundingBox\(\)', '.getBoundingBox()'),
    @('\.onUpdate\(\)', '.tick()'),
    @('\.getAttributeMap\(\)', '.getAttributes()'),
    @('\.getEntityAttribute\(', '.getAttribute('),
    @('\.rotationYawHead\b', '.getYHeadRot()'),
    @('\.rotationYaw\b', '.getYRot()'),
    @('\.rotationPitch\b', '.getXRot()'),
    @('\.posX\b', '.getX()'),
    @('\.posY\b', '.getY()'),
    @('\.posZ\b', '.getZ()'),

    # ================= ItemStack =================
    @('\.getItemDamage\(\)', '.getDamageValue()'),
    @('\.setItemDamage\(', '.setDamageValue('),
    @('\.splitStack\(', '.split('),
    @('\.setStackDisplayName\(', '.setHoverName('),
    @('\.getDisplayName\(\)', '.getHoverName()'),
    @('\.hasTagCompound\(\)', '.hasTag()'),
    @('\.getTagCompound\(\)', '.getTag()'),
    @('\.setTagCompound\(', '.setTag('),
    @('\.getOrCreateSubCompound\(', '.getOrCreateTagElement('),
    @('\.getSubCompound\(', '.getTagElement('),
    @('\.getUnlocalizedName\(\)', '.getDescriptionId()'),

    # ================= NBT 方法 =================
    @('\.hasKey\(', '.contains('),
    @('\.getCompoundTag\(', '.getCompound('),

    # ================= 客户端 =================
    @('Minecraft\.getMinecraft\(\)', 'Minecraft.getInstance()'),
    @('\.getStringWidth\(', '.width('),

    # ================= 构造器 =================
    @('new ResourceLocation\(\s*"([^"]+)",\s*"([^"]+)"\s*\)', 'ResourceLocation.fromNamespaceAndPath("$1", "$2")'),
    @('new ResourceLocation\(', 'ResourceLocation.parse('),
    @('new TextComponentTranslation\(', 'Component.translatable('),
    @('new TextComponentString\(', 'Component.literal('),

    # ================= 杂项 =================
    @('EnumFacing\.VALUES', 'Direction.values()'),
    @('player\.world\b', 'player.level()'),
    @('entity\.world\b', 'entity.level()'),
    @('this\.world\b', 'this.level')
)

$files = Get-ChildItem -Path $Path -Recurse -Filter *.java
$changed = 0
foreach ($file in $files) {
    $content = Get-Content -Path $file.FullName -Raw -Encoding UTF8
    $original = $content
    $fileChanged = 0
    foreach ($rule in $rules) {
        $pattern = $rule[0]
        $replacement = $rule[1]
        if ($content -match $pattern) {
            $content = [regex]::Replace($content, $pattern, $replacement)
            $fileChanged++
        }
    }
    if ($fileChanged -gt 0) {
        $changed++
        if ($DryRun) {
            Write-Host ("[DRY-RUN] {0} : {1} 条规则命中" -f $file.FullName.Replace($Path, ''), $fileChanged)
        } else {
            [System.IO.File]::WriteAllText($file.FullName, $content, [System.Text.UTF8Encoding]::new($false))
            Write-Host ("[OK] {0} : {1} 条规则命中" -f $file.FullName.Replace($Path, ''), $fileChanged)
        }
    }
}
Write-Host ("处理完成：{0} 个文件被修改（共 {1} 个文件）" -f $changed, $files.Count)
