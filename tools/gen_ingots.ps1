$ErrorActionPreference = "Stop"
$ceMod = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java" -Raw
$ceLang = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\resources\assets\hbm\lang\en_us.lang" -Raw
$pf = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java"
$pt = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\creativetabs\ModCreativeTabs.java"
$pl = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\resources\assets\hbm\lang\en_us.json"

$ceNames = @{}
foreach ($x in [regex]::Matches($ceMod, 'new (Item\w+)\(\s*"(ingot_[a-z0-9_]+)"')) { $ceNames[$x.Groups[2].Value] = $x.Groups[1].Value }
$portContent = Get-Content $pf -Raw
$missing = @()
foreach ($n in $ceNames.Keys | Sort-Object) {
    if ($portContent -notmatch ('ITEMS\.register\(\s*"' + [regex]::Escape($n) + '"')) { $missing += $n }
}

$langMap = @{}
foreach ($x in [regex]::Matches($ceLang, '(?m)^item\.(ingot_[a-z0-9_]+)\.name=(.*)$')) { $langMap[$x.Groups[1].Value] = $x.Groups[2].Value }

$decls = @()
$accepts = @()
$langs = @()
foreach ($n in $missing) {
    $field = ($n -replace '^ingot_', 'INGOT_').ToUpperInvariant() -replace 'INGOT_', 'INGOT_'
    $decls += "    // TAB: partsTab (P4.2 ingot batch; CE type $($ceNames[$n]), ItemBase placeholder, P8 replacement)"
    $decls += "    public static final DeferredItem<Item> $field = ITEMS.register(`"$n`","
    $decls += "            () -> new ItemBase(new Item.Properties()));"
    $accepts += "                output.accept(ModItems.$field.get());"
    $val = if ($langMap.ContainsKey($n)) { $langMap[$n] } else { ($n -replace '^ingot_', 'Ingot of ' -replace '_', ' ') }
    $langs += "  `"item.hbm.$n`": `"$val`","
}

# 1. ModItems.java: insert before final }
$mi = Get-Content $pf -Raw
$marker = "    public static final DeferredItem<Item> GEM_VOLCANIC = ITEMS.register(`"gem_volcanic`",`r`n            () -> new ItemBase(new Item.Properties()));"
if (-not $mi.Contains($marker)) { throw "ModItems marker not found" }
$insert = "`r`n" + ($decls -join "`r`n") + "`r`n"
$mi = $mi.Replace($marker, $marker + $insert)
[System.IO.File]::WriteAllText($pf, $mi, [System.Text.UTF8Encoding]::new($false))

# 2. ModCreativeTabs.java: insert after GEM_VOLCANIC accept
$mt = Get-Content $pt -Raw
$tm = "                output.accept(ModItems.GEM_VOLCANIC.get());"
if (-not $mt.Contains($tm)) { throw "Tabs marker not found" }
$tins = "`r`n" + ($accepts -join "`r`n")
$mt = $mt.Replace($tm, $tm + $tins)
[System.IO.File]::WriteAllText($pt, $mt, [System.Text.UTF8Encoding]::new($false))

# 3. en_us.json: insert before final }
$lj = Get-Content $pl -Raw -Encoding UTF8
$lm = '  "item.hbm.gem_volcanic": "Volcanic Gem"'
if (-not $lj.Contains($lm)) { throw "Lang marker not found" }
# last lang line keeps no trailing comma; our generated lines end with comma, drop last comma
$langs[-1] = $langs[-1] -replace ',$', ''
$lins = "`r`n" + ($langs -join "`r`n")
$lj = $lj.Replace($lm, $lm + "," + $lins)
[System.IO.File]::WriteAllText($pl, $lj, [System.Text.UTF8Encoding]::new($false))

Write-Output ("inserted {0} fields / {1} accepts / {2} lang keys" -f $decls.Count/3, $accepts.Count, $langs.Count)
# validate JSON
$j = Get-Content $pl -Raw -Encoding UTF8 | ConvertFrom-Json
Write-Output ("JSON valid, total keys: " + ($j.PSObject.Properties.Count))
