$ErrorActionPreference = "Stop"
$ceMod = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java" -Raw
$ceODM = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\inventory\OreDictManager.java" -Raw
$ceLang = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\resources\assets\hbm\lang\en_us.lang" -Raw
$pf = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java"
$pt = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\creativetabs\ModCreativeTabs.java"
$pl = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\resources\assets\hbm\lang\en_us.json"

# CE item fields: fieldName -> (regName, type)
$ceFields = @{}
foreach ($x in [regex]::Matches($ceMod, 'public static final Item (\w+) = new (\w+)\(\s*"([a-z0-9_]+)"')) {
    $ceFields[$x.Groups[1].Value] = @($x.Groups[3].Value, $x.Groups[2].Value)
}

# registerOres body
$start = $ceODM.IndexOf("public static void registerOres() {")
$brace = $ceODM.IndexOf("{", $start)
$depth = 0; $i = $brace; $inStr = $false
while ($i -lt $ceODM.Length) {
    $c = $ceODM[$i]
    if ($inStr) { if ($c -eq '"') { $inStr = $false } }
    else { if ($c -eq '"') { $inStr = $true } elseif ($c -eq '{') { $depth++ } elseif ($c -eq '}') { $depth--; if ($depth -eq 0) { break } } }
    $i++
}
$body = $ceODM.Substring($brace, $i - $brace)

$used = @{}
foreach ($x in [regex]::Matches($body, '\b([a-z][a-z0-9_]*)\b')) { if ($ceFields.ContainsKey($x.Groups[1].Value)) { $used[$x.Groups[1].Value] = $true } }

$portContent = Get-Content $pf -Raw
$missing = @()
foreach ($n in $used.Keys | Sort-Object) {
    if ($portContent -notmatch ('ITEMS\.register\(\s*"' + [regex]::Escape($ceFields[$n][0]) + '"')) { $missing += $n }
}
Write-Output ("missing: {0}" -f $missing.Count)

# CE lang values for regName
$langMap = @{}
foreach ($x in [regex]::Matches($ceLang, '(?m)^item\.([a-z0-9_]+)\.name=(.*)$')) { $langMap[$x.Groups[1].Value] = $x.Groups[2].Value }

function To-FieldName([string]$regName) {
    $parts = $regName -split '_'
    return (($parts | ForEach-Object { $_.ToUpperInvariant() }) -join '_')
}

$decls = @(); $accepts = @(); $langs = @()
foreach ($n in $missing) {
    $reg = $ceFields[$n][0]; $type = $ceFields[$n][1]
    $field = To-FieldName $reg
    $decls += "    // TAB: partsTab (P3.2 registerOres batch; CE type $type, ItemBase placeholder, P8 replacement)"
    $decls += "    public static final DeferredItem<Item> $field = ITEMS.register(`"$reg`","
    $decls += "            () -> new ItemBase(new Item.Properties()));"
    $accepts += "                output.accept(ModItems.$field.get());"
    $val = if ($langMap.ContainsKey($reg)) { $langMap[$reg] } else { ($reg -replace '_', ' ') }
    $langs += "  `"item.hbm.$reg`": `"$val`","
}

# 1. ModItems: insert after INGOT_ZIRCONIUM block
$mi = Get-Content $pf -Raw
$mk = "    public static final DeferredItem<Item> INGOT_ZIRCONIUM = ITEMS.register(`"ingot_zirconium`",`r`n            () -> new ItemBase(new Item.Properties()));"
if (-not $mi.Contains($mk)) { throw "ModItems marker not found" }
$mi = $mi.Replace($mk, $mk + "`r`n" + ($decls -join "`r`n"))
[System.IO.File]::WriteAllText($pf, $mi, [System.Text.UTF8Encoding]::new($false))

# 2. Tabs: insert before CAN_EMPTY accept
$mt = Get-Content $pt -Raw
$tm = "                output.accept(ModItems.CAN_EMPTY.get());"
if (-not $mt.Contains($tm)) { throw "Tabs marker not found" }
$mt = $mt.Replace($tm, ($accepts -join "`r`n") + "`r`n" + $tm)
[System.IO.File]::WriteAllText($pt, $mt, [System.Text.UTF8Encoding]::new($false))

# 3. en_us: insert before final scraps line, keep JSON valid
$lj = Get-Content $pl -Raw -Encoding UTF8
$lm = '  "item.hbm.scraps": "Foundry Scraps"'
if (-not $lj.Contains($lm)) { throw "Lang marker not found" }
$langs[-1] = $langs[-1] -replace ',$', ''
$lj = $lj.Replace($lm, $lm + "," + "`r`n" + ($langs -join "`r`n"))
[System.IO.File]::WriteAllText($pl, $lj, [System.Text.UTF8Encoding]::new($false))

Write-Output ("inserted decls={0} accepts={1} lang={2}" -f $decls.Count, $accepts.Count, $langs.Count)
$j = Get-Content $pl -Raw -Encoding UTF8 | ConvertFrom-Json
Write-Output ("JSON ok")
