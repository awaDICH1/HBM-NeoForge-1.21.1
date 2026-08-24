$ErrorActionPreference = "Stop"
$ceLang = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\resources\assets\hbm\lang\en_us.lang" -Raw
$pf = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java"
$pt = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\creativetabs\ModCreativeTabs.java"
$pl = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\resources\assets\hbm\lang\en_us.json"

# name -> (regName, tab, factorySuffix)
$specials = [ordered]@{
    "bedrock_ore_fragment" = @("bedrock_ore_fragment", "partsTab", 'new ItemAutogen(MaterialShapes.FRAGMENT, "bedrock_ore_fragment").aot(Mats.MAT_BISMUTH, "bedrock_ore_fragment_bismuth")')
    "bolt"                 = @("bolt", "partsTab", 'new ItemAutogen(MaterialShapes.BOLT, "bolt").oun("boltntm")')
    "heavy_component"      = @("heavy_component", "partsTab", 'new ItemAutogen(MaterialShapes.HEAVY_COMPONENT, "heavy_component")')
    "ingot_raw"            = @("ingot_raw", "partsTab", 'new ItemAutogen(MaterialShapes.INGOT, "ingot_raw")')
    "part_barrel_heavy"    = @("part_barrel_heavy", "partsTab", 'new ItemAutogen(MaterialShapes.HEAVYBARREL, "part_barrel_heavy")')
    "part_barrel_light"    = @("part_barrel_light", "partsTab", 'new ItemAutogen(MaterialShapes.LIGHTBARREL, "part_barrel_light")')
    "part_grip"            = @("part_grip", "partsTab", 'new ItemAutogen(MaterialShapes.GRIP, "part_grip")')
    "part_mechanism"       = @("part_mechanism", "partsTab", 'new ItemAutogen(MaterialShapes.MECHANISM, "part_mechanism")')
    "part_receiver_heavy"  = @("part_receiver_heavy", "partsTab", 'new ItemAutogen(MaterialShapes.HEAVYRECEIVER, "part_receiver_heavy")')
    "part_receiver_light"  = @("part_receiver_light", "partsTab", 'new ItemAutogen(MaterialShapes.LIGHTRECEIVER, "part_receiver_light")')
    "part_stock"           = @("part_stock", "partsTab", 'new ItemAutogen(MaterialShapes.STOCK, "part_stock")')
    "pipe"                 = @("pipe", "partsTab", 'new ItemAutogen(MaterialShapes.PIPE, "pipe")')
    "plate_cast"           = @("plate_cast", "partsTab", 'new ItemAutogen(MaterialShapes.CASTPLATE, "plate_cast").aot(Mats.MAT_BISMUTH, "plate_cast_bismuth")')
    "plate_welded"         = @("plate_welded", "partsTab", 'new ItemAutogen(MaterialShapes.WELDEDPLATE, "plate_welded")')
    "shell"                = @("shell", "partsTab", 'new ItemAutogen(MaterialShapes.SHELL, "shell")')
    "wire_dense"           = @("wire_dense", "partsTab", 'new ItemAutogen(MaterialShapes.DENSEWIRE, "wire_dense")')
    "wire_fine"            = @("wire_fine", "partsTab", 'new ItemAutogen(MaterialShapes.WIRE, "wire_fine")')
    "briquette"            = @("briquette", "partsTab", 'new ItemBase(new Item.Properties()) // P8: ItemEnumMulti<EnumBriquetteType>')
    "coke"                 = @("coke", "partsTab", 'new ItemBase(new Item.Properties()) // P8: ItemEnumMulti<EnumCokeType>')
    "chunk_ore"            = @("chunk_ore", "partsTab", 'new ItemBase(new Item.Properties()) // P8: ItemEnumMulti<EnumChunkType>')
    "oil_tar"              = @("oil_tar", "partsTab", 'new ItemBase(new Item.Properties()) // P8: ItemEnumMulti<EnumTarType>')
    "glyphid_meat"         = @("glyphid_meat", "consumableTab", 'new ItemBase(new Item.Properties()) // P8: ItemLemon')
    "glyphid_meat_grilled" = @("glyphid_meat_grilled", "consumableTab", 'new ItemBase(new Item.Properties()) // P8: ItemLemon')
    "ingot_semtex"         = @("ingot_semtex", "partsTab", 'new ItemBase(new Item.Properties()) // P8: ItemLemon')
    "nugget"               = @("nugget", "consumableTab", 'new ItemBase(new Item.Properties()) // P8: ItemLemon')
    "hand_drill"           = @("hand_drill", "partsTab", 'new ItemBase(new Item.Properties()) // P8: ItemTooling(HAND_DRILL)')
    "hand_drill_desh"      = @("hand_drill_desh", "partsTab", 'new ItemBase(new Item.Properties()) // P8: ItemTooling(HAND_DRILL)')
    "screwdriver"          = @("screwdriver", "partsTab", 'new ItemBase(new Item.Properties()) // P8: ItemTooling(SCREWDRIVER)')
    "screwdriver_desh"     = @("screwdriver_desh", "partsTab", 'new ItemBase(new Item.Properties()) // P8: ItemTooling(SCREWDRIVER)')
}

$langMap = @{}
foreach ($x in [regex]::Matches($ceLang, '(?m)^item\.([a-z0-9_]+)\.name=(.*)$')) { $langMap[$x.Groups[1].Value] = $x.Groups[2].Value }

function To-FieldName([string]$regName) { return (($regName -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_') }

$decls = @(); $accepts = @(); $langs = @()
foreach ($e in $specials.GetEnumerator()) {
    $reg = $e.Value[0]; $tab = $e.Value[1]; $factory = $e.Value[2]
    $field = To-FieldName $reg
    $decls += "    // TAB: $tab (P3.2 special; CE $factory)"
    $decls += "    public static final DeferredItem<Item> $field = ITEMS.register(`"$reg`","
    $decls += "            () -> $factory);"
    $accepts += "                output.accept(ModItems.$field.get()); // $tab"
    $val = if ($langMap.ContainsKey($reg)) { $langMap[$reg] } else { ($reg -replace '_', ' ') }
    $langs += "  `"item.hbm.$reg`": `"$val`","
}

# 1. ModItems insert after INGOT_ZIRCONIUM (which is followed by the 157-batch; use SCRAPS block as anchor instead)
$mi = Get-Content $pf -Raw
$mk = '    public static final DeferredItem<Item> SCRAPS = ITEMS.register("scraps",'
if (-not $mi.Contains($mk)) { throw "ModItems marker not found" }
$idx = $mi.IndexOf($mk)
$lineEnd = $mi.IndexOf("`n", $idx)
$mi = $mi.Insert($lineEnd + 1, ($decls -join "`r`n") + "`r`n")
[System.IO.File]::WriteAllText($pf, $mi, [System.Text.UTF8Encoding]::new($false))

# 2. Tabs: split partsTab/consumableTab - insert parts before TELEPAD, consumable before CAN_EMPTY
$mt = Get-Content $pt -Raw
$partsAccepts = ($accepts | Where-Object { $_ -match '// partsTab' }) -join "`r`n"
$consAccepts = ($accepts | Where-Object { $_ -match '// consumableTab' }) -join "`r`n"
$tm = "                output.accept(ModItems.TELEPAD.get());"
if (-not $mt.Contains($tm)) { throw "Tabs TELEPAD marker not found" }
$mt = $mt.Replace($tm, $partsAccepts + "`r`n" + $tm)
$cm = "                output.accept(ModItems.CAN_EMPTY.get());"
if (-not $mt.Contains($cm)) { throw "Tabs CAN_EMPTY marker not found" }
$mt = $mt.Replace($cm, $consAccepts + "`r`n" + $cm)
[System.IO.File]::WriteAllText($pt, $mt, [System.Text.UTF8Encoding]::new($false))

# 3. en_us
$lj = Get-Content $pl -Raw -Encoding UTF8
$lm = '  "item.hbm.scraps": "Foundry Scraps"'
if (-not $lj.Contains($lm)) { throw "Lang marker not found" }
$langs[-1] = $langs[-1] -replace ',$', ''
$lj = $lj.Replace($lm, $lm + "," + "`r`n" + ($langs -join "`r`n"))
[System.IO.File]::WriteAllText($pl, $lj, [System.Text.UTF8Encoding]::new($false))

Write-Output ("inserted fields={0} partsAccepts={1} consAccepts={2} lang={3}" -f $specials.Count, ($accepts | Where-Object { $_ -match '// partsTab' }).Count, ($accepts | Where-Object { $_ -match '// consumableTab' }).Count, $langs.Count)
$j = Get-Content $pl -Raw -Encoding UTF8 | ConvertFrom-Json
Write-Output ("JSON ok")
