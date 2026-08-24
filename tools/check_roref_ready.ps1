$ErrorActionPreference = "Stop"
$ceMod = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java" -Raw
$ceODM = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\inventory\OreDictManager.java" -Raw
$pf = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java" -Raw

$decl = @{}
foreach ($m in [regex]::Matches($ceMod, '(?m)^\s*public static final Item (\w+) = (.*)$')) { $decl[$m.Groups[1].Value] = $m.Groups[2].Value.Trim() }

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
foreach ($x in [regex]::Matches($body, '\b([a-z][a-z0-9_]*)\b')) { if ($decl.ContainsKey($x.Groups[1].Value)) { $used[$x.Groups[1].Value] = $true } }

$missingReg = @()
foreach ($n in $used.Keys) {
    $d = $decl[$n]
    $m = [regex]::Match($d, 'new \w+(?:<[^>]*>)?\(\s*"[a-z0-9_]+"[^"]*"([a-z0-9_]+)"')
    $reg = $null
    if ($m.Success) { $reg = $m.Groups[1].Value }  # 2nd string arg (MaterialShapes, "reg")
    else { $m2 = [regex]::Match($d, 'new \w+(?:<[^>]*>)?\(\s*"([a-z0-9_]+)"'); if ($m2.Success) { $reg = $m2.Groups[1].Value } }
    if ($reg -eq $null) { Write-Output "NO-REG: $n : $d" }
    elseif ($pf -notmatch ('ITEMS\.register\(\s*"' + [regex]::Escape($reg) + '"')) { $missingReg += "$n | $reg" }
}
Write-Output ("registerOres refs: {0}, still missing regs: {1}" -f $used.Count, $missingReg.Count)
$missingReg | ForEach-Object { Write-Output "  MISS: $_" }
