$ErrorActionPreference = "Stop"
$f = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\inventory\OreDictManager.java"
$content = [System.IO.File]::ReadAllText($f, [System.Text.Encoding]::UTF8)

# 1. registerOres() 方法体桩化：大括号平衡，跳过 "..." 字符串字面量
$marker = "public static void registerOres() {"
$idx = $content.IndexOf($marker)
if ($idx -lt 0) { throw "registerOres marker not found" }
$start = $content.IndexOf("{", $idx) + 1
$depth = 1
$i = $start
$inStr = $false
$len = $content.Length
while ($i -lt $len -and $depth -gt 0) {
    $c = $content[$i]
    if ($inStr) {
        if ($c -eq '\') { $i += 2; continue }
        if ($c -eq '"') { $inStr = $false }
    } else {
        if ($c -eq '"') { $inStr = $true }
        elseif ($c -eq '{') { $depth++ }
        elseif ($c -eq '}') { $depth-- }
    }
    $i++
}
$end = $i
$stub = @"
public static void registerOres() {
        /*
         * P4.2 TODO: registerOres() 全量注册块（CE 原文约 430 行）已整体桩化。
         * 依赖（未迁移）：ModItems/ModBlocks 全量字段、BlockEnums/ItemEnums、HazardRegistry 放射乘数
         * （乘数常量已就绪）、CraftingManager（P4 配方）、OreDictionary.registerOre（1.21 tag 注册）。
         * 当前桩：KEY_*/DictFrame/DictGroup 常量与框架已就绪；registerGroups()/getReflector()/
         * compensateMojangSpaghettiBullshit() 已保留。P3 物品全量 + P4 配方 + tag 系统就绪后恢复本方法。
         */
    }
"@
$content = $content.Substring(0, $idx) + $stub + $content.Substring($end)

# 2. RecipesCommon.ComparableStack -> com.hbm.util.ComparableStack
$content = $content.Replace("RecipesCommon.ComparableStack", "com.hbm.util.ComparableStack")

# 3. fromOne/fromAll 的 meta 构造器 -> setDamageValue
$content = $content.Replace("return new ItemStack(item, 1, en.ordinal());", "ItemStack s = new ItemStack(item, 1); s.setDamageValue(en.ordinal()); return s;")
$content = $content.Replace("return new ItemStack(block, 1, en.ordinal());", "ItemStack s = new ItemStack(block, 1); s.setDamageValue(en.ordinal()); return s;")
$content = $content.Replace("return new ItemStack(item, stacksize, en.ordinal());", "ItemStack s = new ItemStack(item, stacksize); s.setDamageValue(en.ordinal()); return s;")
$content = $content.Replace("return new ItemStack(block, stacksize, en.ordinal());", "ItemStack s = new ItemStack(block, stacksize); s.setDamageValue(en.ordinal()); return s;")
$content = $content.Replace("stacks[i] = new ItemStack(item, 1, vals[i].ordinal());", "stacks[i] = new ItemStack(item, 1); stacks[i].setDamageValue(vals[i].ordinal());")
$content = $content.Replace("stacks[i] = new ItemStack(block, 1, vals[i].ordinal());", "stacks[i] = new ItemStack(block, 1); stacks[i].setDamageValue(vals[i].ordinal());")

# 4. registerStack 内 OreDictionary.registerOre 行 -> 注释（TODO P4.2）
$content = $content.Replace("OreDictionary.registerOre(tag + mat, stack);", "// TODO P4.2: OreDictionary.registerOre(tag + mat, stack) -> 1.21 tag 注册")
$content = $content.Replace("OreDictionary.registerOre(mat, stack);", "// TODO P4.2: OreDictionary.registerOre(mat, stack) -> 1.21 tag 注册")

[System.IO.File]::WriteAllText($f, $content, [System.Text.UTF8Encoding]::new($false))
"done. file now " + ((Get-Content $f).Count) + " lines"
