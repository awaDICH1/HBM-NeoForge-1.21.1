# ============================================================
# gen_items.ps1 — ModItems.java ItemBase 字段 → DeferredItem 代码生成器（P3 物品批）
#
# 用法:
#   powershell -ExecutionPolicy Bypass -File tools/gen_items.ps1
# 输入: CE ModItems.java（硬编码路径，可改 $cePath）
# 输出: tools/generated/items_base.txt（DeferredItem 声明）
#       tools/generated/items_tabs.txt（按创造栏分组的字段清单）
#
# 处理规则（ItemBase 链）:
#   setCreativeTab(MainRegistry.X)  → 记录 tab，代码中删除（ModCreativeTabs.displayItems）
#   setMaxStackSize(N)              → Item.Properties.stacksTo(N)
#   setMaxDamage(N)                 → Item.Properties.durability(N)
#   setContainerItem(ModItems.X)    → Item.Properties.craftRemainder(ModItems.X_UPPER.get())
#   setFull3D()                     → 独立 TODO 注释（1.21.1 无对应属性，模型 json 处理）
# ============================================================

$cePath = "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java"
$genDir = Join-Path $PSScriptRoot "generated"
New-Item -ItemType Directory -Force -Path $genDir | Out-Null

$lines = Get-Content $cePath -Encoding UTF8
$out = New-Object System.Collections.ArrayList
$tabs = @{}
$cnt = 0
$craftRemainderRefs = @()

foreach ($line in $lines) {
    if ($line -notmatch 'public static final Item (\w+) = new (ItemBase|EffectItem)\((.*?)\)(.*);$') { continue }
    $field = $Matches[1]
    $ctorClass = $Matches[2]
    $args = $Matches[3]
    $chain = $Matches[4]

    $reg = "MISSING"
    if ($args -match '"((?:[^"\\]|\\.)*)"') { $reg = $Matches[1] }

    $tab = 'null'
    if ($chain -match 'setCreativeTab\(MainRegistry\.(\w+)\)') { $tab = $Matches[1] }

    $props = @()
    $todoNotes = @()
    if ($chain -match 'setMaxStackSize\((\d+)\)') { $props += ".stacksTo($($Matches[1]))" }
    if ($chain -match 'setMaxDamage\((\d+)\)') { $props += ".durability($($Matches[1]))" }
    if ($chain -match 'setContainerItem\(ModItems\.(\w+)\)') {
        $ref = $Matches[1].ToUpper()
        $props += ".craftRemainder(ModItems.$ref.get())"
        $craftRemainderRefs += $Matches[1]
    }
    if ($chain -match 'setFull3D') {
        $todoNotes += "    // TODO: setFull3D — 1.21.1 no property, handled in model json"
    }

    $fieldUpper = $field.ToUpper()
    $p = if ($props.Count -gt 0) { "new Item.Properties()" + [string]::Join("", $props) } else { "new Item.Properties()" }

    [void]$out.Add("    // TAB: $tab")
    foreach ($n in $todoNotes) { [void]$out.Add($n) }
    [void]$out.Add("    public static final DeferredItem<Item> $fieldUpper = ITEMS.register(`"$reg`",")
    [void]$out.Add("            () -> new $ctorClass($p));")
    [void]$out.Add("")

    if (-not $tabs.ContainsKey($tab)) { $tabs[$tab] = New-Object System.Collections.ArrayList }
    [void]$tabs[$tab].Add($fieldUpper)
    $cnt++
}

[System.IO.File]::WriteAllLines("$genDir\items_base.txt", $out, [System.Text.UTF8Encoding]::new($false))

$tabLines = New-Object System.Collections.ArrayList
foreach ($t in $tabs.Keys | Sort-Object) {
    [void]$tabLines.Add("--- $t ---")
    [void]$tabLines.Add(($tabs[$t] -join ', '))
    [void]$tabLines.Add("")
}
[System.IO.File]::WriteAllLines("$genDir\items_tabs.txt", $tabLines, [System.Text.UTF8Encoding]::new($false))

Write-Host "ItemBase fields: $cnt, tabs: $($tabs.Count)"
Write-Host "craftRemainder refs: $($craftRemainderRefs -join ', ')"
