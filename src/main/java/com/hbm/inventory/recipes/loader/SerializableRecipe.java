package com.hbm.inventory.recipes.loader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import com.hbm.api.recipe.IRecipeRegisterListener;
import com.hbm.inventory.RecipesCommon;
import com.hbm.inventory.fluid.FluidStack;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.ModItems;
import com.hbm.main.HBM;
import com.hbm.util.AStack;
import com.hbm.util.ComparableStack;
import com.hbm.util.ItemStackUtil;
import com.hbm.util.Tuple;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.loading.FMLPaths;

import java.io.*;
import java.util.*;

/**
 * 自研 JSON 配方加载器（P4.1 批次C 迁移版）。
 *
 * 迁移自 1.12.2 com.hbm.inventory.recipes.loader.SerializableRecipe（494 行）。
 * 变更：
 *  - Item.REGISTRY.getObject/NameForObject → BuiltInRegistries.ITEM.get/getKey；
 *  - new ItemStack(item, size, meta)（1.12 meta 构造器）→ setDamageValue；
 *  - hasTagCompound/getTagCompound/getItemDamage → hasTag/getTag/getDamageValue；
 *  - MainRegistry.configDir → FMLPaths.CONFIGDIR/hbmConfig；MainRegistry.logger → HBM.LOGGER；
 *  - ItemStackUtil.addNBTFromString 骨架（TODO P5 完整 JSON→NBT）；
 *  - registerAllHandlers() 桩化（TODO P4.1 配方批：依赖 ~40 个配方类 + CraftingManager 1572 行 + ModItems 全量）
 *
 * 注意：HBM 配方体系是自研 JSON 序列化（非原版 Recipe<T>/RecipeType/RecipeSerializer 数据包系统），
 * 无需 RecipeType/RecipeSerializer 注册（用户简报 C3 不适用）。
 */
public abstract class SerializableRecipe {
    public static final Gson gson = new Gson();
    public static List<SerializableRecipe> recipeHandlers = new ArrayList<>();
    public static List<IRecipeRegisterListener> additionalListeners = new ArrayList<>();
    public static final Map<String, InputStream> recipeSyncHandlers = new HashMap<>();

    public boolean modified = false;

    /*
     * INIT
     */

    public static void registerAllHandlers() {
        // TODO P4.1 配方批: 恢复 ~40 个配方类注册（PressRecipes/BlastFurnaceRecipes/.../AnvilRecipes/MatDistribution/
        //  AssemblyMachineRecipes/ChemicalPlantRecipes/PUREXRecipes/FusionRecipes/PrecAssRecipes/...）
        // 依赖：CraftingManager（1572 行，ModItems/ModBlocks 全量）+ 各机器配方类
    }

    public static void initialize() {
        File recDir = new File(FMLPaths.CONFIGDIR.get().resolve("hbmConfig").toFile().getAbsolutePath() + File.separatorChar + "hbmRecipes");

        if (!recDir.exists()) {
            if (!recDir.mkdir()) {
                throw new IllegalStateException("Unable to make recipe directory " + recDir.getAbsolutePath());
            }
        }

        File info =
                new File(recDir.getAbsolutePath() + File.separatorChar + "REMOVE UNDERSCORE TO ENABLE RECIPE LOADING - RECIPES WILL RESET TO " +
                        "DEFAULT OTHERWISE");
        try {
            info.createNewFile();
        } catch (IOException ignored) {
        }

        HBM.LOGGER.info("Starting recipe init!");

        // TODO P4.1 配方批: GenericRecipes.clearPools() —— GenericRecipe/GenericRecipes 未迁移（依赖 CraftingManager）

        for (SerializableRecipe recipe : recipeHandlers) {

            recipe.deleteRecipes();

            File recFile = new File(recDir.getAbsolutePath() + File.separatorChar + recipe.getFileName());
            if (recipeSyncHandlers.containsKey(recipe.getFileName())) {
                HBM.LOGGER.info("Reading synced recipe file {}", recipe.getFileName());
                InputStream stream = recipeSyncHandlers.get(recipe.getFileName());

                try {
                    stream.reset();
                    Reader reader = new InputStreamReader(stream);
                    recipe.readRecipeStream(reader);
                    recipe.modified = true;
                } catch (Throwable ex) {
                    HBM.LOGGER.error("Failed to reset synced recipe stream", ex);
                }
            } else if (recFile.exists() && recFile.isFile()) {
                HBM.LOGGER.info("Reading recipe file " + recFile.getName());
                recipe.readRecipeFile(recFile);
                recipe.modified = true;
            } else {
                HBM.LOGGER.info("No recipe file found, registering defaults for {}", recipe.getFileName());
                recipe.registerDefaults();

                for (IRecipeRegisterListener listener : additionalListeners) {
                    listener.onRecipeLoad(recipe.getClass().getSimpleName());
                }

                File recTemplate = new File(recDir.getAbsolutePath() + File.separatorChar + "_" + recipe.getFileName());
                HBM.LOGGER.info("Writing template file {}", recTemplate.getName());
                recipe.writeTemplateFile(recTemplate);
                recipe.modified = false;
            }

            recipe.registerPost();
        }

        HBM.LOGGER.info("Finished recipe init!");
    }

    public static void receiveRecipes(String filename, byte[] data) {
        recipeSyncHandlers.put(filename, new ByteArrayInputStream(data));
    }

    public static void clearReceivedRecipes() {
        boolean hasCleared = !recipeSyncHandlers.isEmpty();
        recipeSyncHandlers.clear();

        if (hasCleared) initialize();
    }

    /*
     * ABSTRACT
     */

    /**
     * The machine's (or process') name used for the recipe file
     */
    public abstract String getFileName();

    /**
     * Return the list object holding all the recipes, usually an ArrayList or HashMap
     */
    public abstract Object getRecipeObject();

    /**
     * Will use the supplied JsonElement (usually casts to JsonArray) from the over arching recipe
     * array and adds the recipe to the recipe list object
     */
    public abstract void readRecipe(JsonElement recipe);

    /**
     * Is given a single recipe from the recipe list object (a wrapper, Tuple, array, HashMap Entry,
     * etc) and writes it to the current ongoing GSON stream
     *
     * @throws IOException
     */
    public abstract void writeRecipe(Object recipe, JsonWriter writer) throws IOException;

    /**
     * Registers the default recipes
     */
    public abstract void registerDefaults();

    /**
     * Deletes all existing recipes, currenly unused
     */
    public abstract void deleteRecipes();

    /**
     * A routine called after registering all recipes, whether it's a template or not. Good for IMC
     * functionality.
     */
    public void registerPost() {
    }

    /**
     * Returns a string to be printed as info at the top of the JSON file
     */
    public String getComment() {
        return null;
    }

    /*
     * JSON R/W WRAPPERS
     */

    public void writeTemplateFile(File template) {

        try {
            /* Get the recipe list object */
            Object recipeObject = this.getRecipeObject();
            List<Object> recipeList = new ArrayList<>();

            /* Try to pry all recipes from our list */
            if (recipeObject instanceof Collection) {
                recipeList.addAll((Collection<?>) recipeObject);

            } else if (recipeObject instanceof HashMap) {
                recipeList.addAll(((HashMap<?, ?>) recipeObject).entrySet());
            }

            if (recipeList.isEmpty() && !allowEmptyRecipeList())
                throw new IllegalStateException("Error while writing recipes for " + this.getClass().getSimpleName() + ": Recipe list is either " + "empty or in an unsupported format!");

            JsonWriter writer = new JsonWriter(new FileWriter(template));
            writer.setIndent("  "); // pretty formatting
            writer.beginObject(); // initial '{'

            if (this.getComment() != null) {
                writer.name("comment").value(this.getComment());
            }

            writer.name("recipes").beginArray(); // all recipes are stored in an array called "recipes"

            for (Object recipe : recipeList) {
                writer.beginObject(); // begin object for a single recipe
                this.writeRecipe(recipe, writer); // serialize here
                writer.endObject(); // end recipe object
            }

            writer.endArray(); // end recipe array
            writer.endObject(); // final '}'
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean allowEmptyRecipeList() { return false; }

    public void readRecipeFile(File file) {
        try {
            readRecipeStream(new FileReader(file));
        } catch (FileNotFoundException ignored) {
        }
    }

    public void readRecipeStream(Reader reader) {
        JsonObject json = gson.fromJson(reader, JsonObject.class);
        JsonArray recipes = json.get("recipes").getAsJsonArray();
        for (JsonElement recipe : recipes) {
            if (recipe != null) this.readRecipe(recipe);
        }
    }

    /*
     * JSON IO UTIL
     */

    public static AStack readAStack(JsonArray array) {
        try {
            String type = array.get(0).getAsString();
            int stacksize = array.size() > 2 ? array.get(2).getAsInt() : 1;
            if ("nbt".equals(type)) {
                Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(array.get(1).getAsString()));
                //mlbv: upstream hardcodes meta 0 here, which drops the meta its own writer emits; we read it back
                int meta = array.size() > 3 ? array.get(3).getAsInt() : 0;
                ItemStack stack = new ItemStack(item, stacksize);
                stack.setDamageValue(meta);
                if (array.size() > 4) ItemStackUtil.addNBTFromString(stack, array.get(4).getAsString());
                return new RecipesCommon.NbtComparableStack(stack);
            }
            if ("item".equals(type)) {
                Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(array.get(1).getAsString()));
                int meta = array.size() > 3 ? array.get(3).getAsInt() : 0;
                return new ComparableStack(item, stacksize, meta);
            }
            if ("dict".equals(type)) {
                String dict = array.get(1).getAsString();
                return new RecipesCommon.OreDictStack(dict, stacksize);
            }
        } catch (Exception ignored) {
        }
        HBM.LOGGER.error("Error reading stack array {}", array.toString());
        return new ComparableStack(ModItems.NOTHING.get());
    }

    public static AStack[] readAStackArray(JsonArray array) {
        try {
            AStack[] items = new AStack[array.size()];
            for (int i = 0; i < items.length; i++) {
                items[i] = readAStack((JsonArray) array.get(i));
            }
            return items;
        } catch (Exception ignored) {
        }
        HBM.LOGGER.error("Error reading stack array {}", array.toString());
        return new AStack[0];
    }

    public static void writeAStack(AStack astack, JsonWriter writer) throws IOException {
        writer.beginArray();
        writer.setIndent("");
        if (astack instanceof RecipesCommon.NbtComparableStack comp) {
            ItemStack stack = comp.getStack();
            boolean hasNbt = stack.has(DataComponents.CUSTOM_DATA);
            writer.value(hasNbt ? "nbt" : "item"); // NBT  identifier
            writer.value(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(stack.getItem())).toString()); // item name
            if (comp.stacksize != 1 || comp.meta > 0 || hasNbt) writer.value(comp.stacksize); // stack size
            if (comp.meta > 0 || hasNbt) writer.value(comp.meta); // metadata
            if (hasNbt) writer.value(stack.get(DataComponents.CUSTOM_DATA).copyTag().toString()); // NBT
        } else if (astack instanceof ComparableStack comp) {
            writer.value("item"); // ITEM  identifier
            writer.value(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(comp.toStack().getItem())).toString()); // item name
            if (comp.stacksize != 1 || comp.meta > 0) writer.value(comp.stacksize); // stack size
            if (comp.meta > 0) writer.value(comp.meta); // metadata
        } else if (astack instanceof RecipesCommon.OreDictStack ore) {
            writer.value("dict"); // DICT identifier
            writer.value(ore.name); // dict name
            if (ore.stacksize != 1) writer.value(ore.stacksize); // stacksize
        }
        writer.endArray();
        writer.setIndent("  ");
    }

    public static ItemStack readItemStack(JsonArray array) {
        try {
            Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(array.get(0).getAsString()));
            int stacksize = array.size() > 1 ? array.get(1).getAsInt() : 1;
            int meta = array.size() > 2 ? array.get(2).getAsInt() : 0;
            if (item != null) {
                ItemStack stack = new ItemStack(item, stacksize);
                stack.setDamageValue(meta);
                if (array.size() > 3) ItemStackUtil.addNBTFromString(stack, array.get(3).getAsString());
                return stack;
            }
        } catch (Exception ignored) {
        }
        HBM.LOGGER.error("Error reading stack array {} - defaulting to NOTHING item!", array.toString());
        return new ItemStack(ModItems.NOTHING.get());
    }

    public static Tuple.Pair<ItemStack, Float> readItemStackChance(JsonArray array) {
        try {
            Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(array.get(0).getAsString()));
            int stacksize = array.size() > 2 ? array.get(1).getAsInt() : 1;
            int meta = array.size() > 3 ? array.get(2).getAsInt() : 0;
            if (item != null) {
                ItemStack stack = new ItemStack(item, stacksize);
                stack.setDamageValue(meta);
                if (array.size() > 4) ItemStackUtil.addNBTFromString(stack, array.get(3).getAsString());
                float chance = array.get(array.size() - 1).getAsFloat();
                return new Tuple.Pair<>(stack, chance);
            }
        } catch (Exception ignored) {
        }
        HBM.LOGGER.error("Error reading stack array {} - defaulting to NOTHING item!", array.toString());
        return new Tuple.Pair<>(new ItemStack(ModItems.NOTHING.get()), 1F);
    }

    public static ItemStack[] readItemStackArray(JsonArray array) {
        try {
            ItemStack[] items = new ItemStack[array.size()];
            for (int i = 0; i < items.length; i++) {
                items[i] = readItemStack((JsonArray) array.get(i));
            }
            return items;
        } catch (Exception ignored) {
        }
        HBM.LOGGER.error("Error reading stack array " + array.toString());
        return new ItemStack[0];
    }

    public static Tuple.Pair<ItemStack, Float>[] readItemStackArrayChance(JsonArray array) {
        try {
            Tuple.Pair<ItemStack, Float>[] items = new Tuple.Pair[array.size()];
            for (int i = 0; i < items.length; i++) {
                items[i] = readItemStackChance((JsonArray) array.get(i));
            }
            return items;
        } catch (Exception ignored) {
        }
        HBM.LOGGER.error("Error reading stack array " + array.toString());
        return new Tuple.Pair[0];
    }

    public static void writeItemStack(ItemStack stack, JsonWriter writer) throws IOException {
        writer.beginArray();
        writer.setIndent("");
        writer.value(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(stack.getItem())).toString()); // item name
        boolean hasNbt = stack.has(DataComponents.CUSTOM_DATA);
        if (stack.getCount() != 1 || stack.getDamageValue() != 0 || hasNbt) writer.value(stack.getCount()); // stack size
        if (stack.getDamageValue() != 0 || hasNbt) writer.value(stack.getDamageValue()); // metadata
        if (hasNbt) writer.value(stack.get(DataComponents.CUSTOM_DATA).copyTag().toString()); // nbt
        writer.endArray();
        writer.setIndent("  ");
    }

    public static void writeItemStackChance(Tuple.Pair<ItemStack, Float> stack, JsonWriter writer) throws IOException {
        writer.beginArray();
        writer.setIndent("");
        writer.value(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(stack.getKey().getItem())).toString()); // item name
        ItemStack key = stack.getKey();
        boolean hasNbt = key.has(DataComponents.CUSTOM_DATA);
        if (key.getCount() != 1 || key.getDamageValue() != 0 || hasNbt) writer.value(key.getCount()); // stack size
        if (key.getDamageValue() != 0 || hasNbt) writer.value(key.getDamageValue()); // metadata
        if (hasNbt) writer.value(key.get(DataComponents.CUSTOM_DATA).copyTag().toString()); // nbt
        writer.value(stack.getValue()); // chance
        writer.endArray();
        writer.setIndent("  ");
    }

    public static FluidStack readFluidStack(JsonArray array) {
        try {
            FluidType type = Fluids.fromName(array.get(0).getAsString());
            int fill = array.get(1).getAsInt();
            int pressure = array.size() < 3 ? 0 : array.get(2).getAsInt();
            return new FluidStack(type, fill, pressure);
        } catch (Exception ignored) {
        }
        HBM.LOGGER.error("Error reading fluid array {}", array.toString());
        return new FluidStack(Fluids.NONE, 0);
    }

    public static FluidStack[] readFluidArray(JsonArray array) {
        try {
            FluidStack[] fluids = new FluidStack[array.size()];
            for (int i = 0; i < fluids.length; i++) {
                fluids[i] = readFluidStack((JsonArray) array.get(i));
            }
            return fluids;
        } catch (Exception ignored) {
        }
        HBM.LOGGER.error("Error reading fluid array {}", array.toString());
        return new FluidStack[0];
    }

    public static void writeFluidStack(FluidStack stack, JsonWriter writer) throws IOException {
        writer.beginArray();
        writer.setIndent("");
        writer.value(stack.type.getName()); // fluid type
        writer.value(stack.fill); // amount in mB
        if (stack.pressure != 0) writer.value(stack.pressure);
        writer.endArray();
        writer.setIndent("  ");
    }

    public static boolean matchesIngredients(ItemStack[] inputs, AStack[] recipe) {

        List<AStack> recipeList = new ArrayList<>();
        Collections.addAll(recipeList, recipe);

        for (ItemStack inputStack : inputs) {
            if (!inputStack.isEmpty()) {
                boolean hasMatch = false;

                for (AStack recipeStack : recipeList) {
                    if (recipeStack.matchesRecipe(inputStack, true) && inputStack.getCount() >= recipeStack.stacksize) {
                        hasMatch = true;
                        recipeList.remove(recipeStack);
                        break;
                    }
                }
                if (!hasMatch) {
                    return false;
                }
            }
        }
        return recipeList.isEmpty();
    }
}
