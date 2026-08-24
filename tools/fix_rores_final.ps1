$ErrorActionPreference = "Stop"
$f = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\inventory\OreDictManager.java"
$c = Get-Content $f -Raw -Encoding UTF8

# 1. collapse .get().get() chains -> .get()
$c = [regex]::Replace($c, '(\.get\(\)){2,}', '.get()')

# 2. comment out the CraftingManager for-loop block (347-357 in original body: 'for(NTMMaterial mat : Mats.orderedList) {' ... closing '}')
# find it: the last for-loop over Mats.orderedList inside registerOres that contains CraftingManager
$s = $c.IndexOf("public static void registerOres() {")
$startIdx = $c.IndexOf("for(NTMMaterial mat : Mats.orderedList) {", $s)
if ($startIdx -lt 0) { $startIdx = $c.IndexOf("for(NTMMaterial mat : Mats.orderedList) {", $s) }
if ($startIdx -lt 0) { throw "crafting for-loop not found" }
$depth = 0; $i = $startIdx; $inStr = $false
while ($i -lt $c.Length) {
    $ch = $c[$i]
    if ($inStr) { if ($ch -eq '"') { $inStr = $false } }
    else { if ($ch -eq '"') { $inStr = $true } elseif ($ch -eq '{') { $depth++ } elseif ($ch -eq '}') { $depth--; if ($depth -eq 0) { break } } }
    $i++
}
$block = $c.Substring($startIdx, $i - $startIdx)
$commented = ($block -split "`r?`n" | ForEach-Object {
    if ($_.Trim().Length -eq 0) { $_ } else { "            // TODO P4 (CraftingManager): " + $_.TrimStart() }
}) -join "`r`n"
$c = $c.Substring(0, $startIdx) + $commented + $c.Substring($i)
[System.IO.File]::WriteAllText($f, $c, [System.Text.UTF8Encoding]::new($false))
Write-Output "get() chains collapsed, CraftingManager block commented"
