$ce = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java" -Raw
$pf = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java" -Raw
$ceNames = @{}
foreach ($x in [regex]::Matches($ce, 'new (Item\w+)\(\s*"(ingot_[a-z0-9_]+)"')) { $ceNames[$x.Groups[2].Value] = $x.Groups[1].Value }
$portNames = @{}
foreach ($x in [regex]::Matches($pf, 'ITEMS\.register\(\s*"(ingot_[a-z0-9_]+)"')) { $portNames[$x.Groups[1].Value] = $true }
$missing = @()
foreach ($n in $ceNames.Keys | Sort-Object) { if (-not $portNames.ContainsKey($n)) { $missing += $n } }
Write-Output ("CE: {0}  port: {1}  missing: {2}" -f $ceNames.Count, $portNames.Count, $missing.Count)
$missing | ForEach-Object { Write-Output ("{0}  [{1}]" -f $_, $ceNames[$_]) }
