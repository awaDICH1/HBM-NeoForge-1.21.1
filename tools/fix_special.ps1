$ErrorActionPreference = "Stop"
$pf = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java"

$mi = Get-Content $pf -Raw

# 1. remove the inserted special block (from first '// TAB: partsTab (P3.2 special' to last line before next SCRAPS/other)
$startMarker = "    // TAB: partsTab (P3.2 special"
$endMarker = "    public static final DeferredItem<Item> SCRAPS = ITEMS.register(`"scraps`","
$s = $mi.IndexOf($startMarker)
$e = $mi.IndexOf($endMarker)
if ($s -lt 0) { throw "special block start not found" }
if ($e -lt 0 -or $e -lt $s) { throw "end marker not found" }
# remove from start of block to just before SCRAPS line (keep the blank line)
$before = $mi.Substring(0, $s)
$after = $mi.Substring($e)
# trim trailing blank lines of before
$before = $before.TrimEnd("`r", "`n") + "`r`n`r`n"
$mi = $before + $after
[System.IO.File]::WriteAllText($pf, $mi, [System.Text.UTF8Encoding]::new($false))

# 2. now check which of the 29 regs are still missing
$specials = @("bedrock_ore_fragment","bolt","heavy_component","ingot_raw","part_barrel_heavy","part_barrel_light","part_grip","part_mechanism","part_receiver_heavy","part_receiver_light","part_stock","pipe","plate_cast","plate_welded","shell","wire_dense","wire_fine","briquette","coke","chunk_ore","oil_tar","glyphid_meat","glyphid_meat_grilled","ingot_semtex","nugget","hand_drill","hand_drill_desh","screwdriver","screwdriver_desh")
$mi = Get-Content $pf -Raw
$missing = @()
foreach ($r in $specials) {
    $cnt = ([regex]::Matches($mi, 'ITEMS\.register\(\s*"' + [regex]::Escape($r) + '"')).Count
    if ($cnt -eq 0) { $missing += $r }
}
Write-Output ("after rollback, still missing: {0}" -f $missing.Count)
$missing | ForEach-Object { Write-Output $_ }
