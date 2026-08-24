$ceMod = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java" -Raw
$ceODM = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\inventory\OreDictManager.java" -Raw
$pf = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java" -Raw

# all CE field names
$ceFields = @{}
foreach ($x in [regex]::Matches($ceMod, 'public static final Item (\w+)')) { $ceFields[$x.Groups[1].Value] = $true }

# registerOres body
$start = $ceODM.IndexOf("public static void registerOres() {")
if ($start -lt 0) { throw "registerOres not found" }
$brace = $ceODM.IndexOf("{", $start)
$depth = 0; $i = $brace; $inStr = $false
while ($i -lt $ceODM.Length) {
    $c = $ceODM[$i]
    if ($inStr) { if ($c -eq '"') { $inStr = $false } }
    else { if ($c -eq '"') { $inStr = $true } elseif ($c -eq '{') { $depth++ } elseif ($c -eq '}') { $depth--; if ($depth -eq 0) { break } } }
    $i++
}
$body = $ceODM.Substring($brace, $i - $brace)

# identifiers in body that are CE item fields
$used = @{}
foreach ($x in [regex]::Matches($body, '\b([a-z][a-z0-9_]*)\b')) {
    $n = $x.Groups[1].Value
    if ($ceFields.ContainsKey($n)) { $used[$n] = $true }
}
Write-Output ("registerOres referenced item fields: {0}" -f $used.Count)

# missing in port
$missing = @()
foreach ($n in $used.Keys | Sort-Object) {
    if ($pf -notmatch ('ITEMS\.register\(\s*"' + [regex]::Escape($n) + '"')) { $missing += $n }
}
Write-Output ("missing in port: {0}" -f $missing.Count)
$missing | ForEach-Object { Write-Output $_ }
