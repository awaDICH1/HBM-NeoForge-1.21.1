$ErrorActionPreference = "Stop"
$ceMod = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java" -Raw
$ceRec = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\inventory\recipes\RBMKFuelRecipes.java" -Raw
$pf = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java"
$prf = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\inventory\recipes\RBMKFuelRecipes.java"
$pgc = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\inventory\recipes\GasCentrifugeRecipes.java"

# 1. extract (field, regName) for rbmk fuel rods from CE
$pairs = @{}
foreach ($m in [regex]::Matches($ceMod, 'public static final ItemRBMKRod (\w+) = new ItemRBMKRod\([^,]+,\s*"([a-z0-9_]+)"')) {
    $pairs[$m.Groups[1].Value] = $m.Groups[2].Value
}
# also hep239 style: field name differs from reg name - ensure via direct grep of RBMKFuelRecipes used fields
$used = @()
foreach ($m in [regex]::Matches($ceRec, 'addRod\(ModItems\.(\w+)\)')) { $used += $m.Groups[1].Value }
$used = $used | Sort-Object -Unique
Write-Output ("RBMKFuelRecipes uses: {0} fields" -f $used.Count)
Write-Output ("CE pairs found: {0}" -f $pairs.Count)

$mi = Get-Content $pf -Raw
$decls = @()
foreach ($f in $used) {
    $reg = if ($pairs.ContainsKey($f)) { $pairs[$f] } else { ($f -replace '_', '_') }
    $up = (($f -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_')
    if ($mi -match ('ITEMS\.register\(\s*"' + [regex]::Escape($reg) + '"')) { Write-Output ("skip existing: {0} ({1})" -f $f, $reg); continue }
    $decls += "    // P5.2 recipe-line placeholder (CE: ItemRBMKRod $f)"
    $decls += "    public static final DeferredItem<ItemRBMKRod> $up = ITEMS.register(`"$reg`","
    $decls += "            () -> new ItemRBMKRod(new Item.Properties()));"
}
Write-Output ("new rbmk decls: {0}" -f ($decls.Count / 3))

# 2. misc fields: nugget_uranium_fuel + nuclear_waste_tiny (ItemBase)
$misc = @()
foreach ($n in @("nugget_uranium_fuel", "nuclear_waste_tiny")) {
    if ($mi -notmatch ('ITEMS\.register\(\s*"' + [regex]::Escape($n) + '"')) {
        $up = (($n -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_')
        $decls += "    // P5.2 recipe-line placeholder"
        $decls += "    public static final DeferredItem<Item> $up = ITEMS.register(`"$n`","
        $decls += "            () -> new ItemBase(new Item.Properties()));"
    }
}

# 3. insert into ModItems (before final })
if ($decls.Count -gt 0) {
    $mk = "    public static final DeferredItem<Item> GEM_VOLCANIC"
    $idx = $mi.LastIndexOf("    public static final DeferredItem<Item> GEM_VOLCANIC")
    if ($idx -lt 0) { throw "ModItems anchor not found" }
    $lineEnd = $mi.IndexOf("`n", $mi.IndexOf("`n", $idx) + 1)
    $mi = $mi.Insert($lineEnd + 1, ($decls -join "`r`n") + "`r`n")
    [System.IO.File]::WriteAllText($pf, $mi, [System.Text.UTF8Encoding]::new($false))
}
Write-Output ("ModItems updated, total fields: " + (Select-String -Path $pf -Pattern "public static final DeferredItem<[^>]+> (\w+) = ITEMS\.register").Count)

# 4. adapt RBMKFuelRecipes: lowercase field -> UPPER.get()
$rf = Get-Content $prf -Raw -Encoding UTF8
foreach ($f in $used) {
    $up = (($f -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_')
    $rf = $rf.Replace('ModItems.' + $f, 'ModItems.' + $up + '.get()')
}
[System.IO.File]::WriteAllText($prf, $rf, [System.Text.UTF8Encoding]::new($false))
Write-Output ("RBMKFuelRecipes adapted")

# 5. adapt GasCentrifugeRecipes
$gc = Get-Content $pgc -Raw -Encoding UTF8
# ModItems.xxx -> ModItems.XXX.get()
foreach ($m in [regex]::Matches($gc, 'ModItems\.(\w+)')) {
    $up = (($m.Groups[1].Value -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_')
    $gc = $gc.Replace('ModItems.' + $m.Groups[1].Value, 'ModItems.' + $up + '.get()')
}
[System.IO.File]::WriteAllText($pgc, $gc, [System.Text.UTF8Encoding]::new($false))
Write-Output ("GasCentrifugeRecipes adapted")
