# gen_recipe.ps1 — 配方类生成器模板
# 生成两种配方类骨架：
#   A) 静态集合类（CE 惯例：如 RBMKFuelRecipes/GasCentrifugeRecipes——不 extends SerializableRecipe）
#   B) SerializableRecipe 子类（JSON 序列化配方：如 MatDistribution）
# 参数（编辑本文件顶部）：$NAME（类名，如 "SILEXRecipes"）、$TYPE（"static" 或 "serializable"）
# usage: Get-Content gen_recipe.ps1 -Raw -Encoding UTF8 | Invoke-Expression

$NAME = "SILEXRecipes"
$TYPE = "static"

if ($TYPE -eq "serializable") {
$code = @"
package com.hbm.inventory.recipes;

import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;
import com.hbm.inventory.recipes.loader.SerializableRecipe;

import java.io.IOException;

/**
 * $NAME（SerializableRecipe 子类模板）。
 * 实现 6 个抽象方法：getFileName/getRecipeObject/readRecipe/writeRecipe/registerDefaults/deleteRecipes。
 */
public class $NAME extends SerializableRecipe {

    @Override public String getFileName() { return "$NAME.json"; }
    @Override public Object getRecipeObject() { return null; }
    @Override public void readRecipe(JsonElement recipe) { }
    @Override public void writeRecipe(Object recipe, JsonWriter writer) throws IOException { }
    @Override public void registerDefaults() { }
    @Override public void deleteRecipes() { }
}
"@
} else {
$code = @"
package com.hbm.inventory.recipes;

import java.util.HashMap;

/**
 * $NAME（静态集合类模板——CE 惯例，不 extends SerializableRecipe）。
 * register() 内填充静态配方集合；具体逻辑按 CE 原版适配。
 */
public class $NAME {

    public static void register() {
        // 配方注册
    }
}
"@
}

$base = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\inventory\recipes"
[System.IO.File]::WriteAllText("$base\$NAME.java", $code, [System.Text.UTF8Encoding]::new($false))
Write-Output ("Generated: $NAME.java ($TYPE)")
Write-Output "NOTE: 仅骨架——必须按 CE 原版逻辑适配（含配方数据与 API 映射）"
