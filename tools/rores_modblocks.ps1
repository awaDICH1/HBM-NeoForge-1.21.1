$ErrorActionPreference = "Stop"
$ceModB = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\blocks\ModBlocks.java" -Raw
$portModB = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\blocks\ModBlocks.java" -Raw
$body = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\tools\generated\rores_body.txt" -Raw

# CE ModBlocks field names
$bFields = @{}
foreach ($m in [regex]::Matches($ceModB, '(?m)^\s*public static final (?:Block|ItemBlock|BlockBase|Block\w+) (\w+)')) { $bFields[$m.Groups[1].Value] = $true }
# also generic 'public static final Block xxx ='
foreach ($m in [regex]::Matches($ceModB, 'public static final Block (\w+)')) { $bFields[$m.Groups[1].Value] = $true }

# body refs that are ModBlocks fields
$bUsed = @{}
foreach ($x in [regex]::Matches($body, '\b([a-z][a-z0-9_]*)\b')) {
    if ($bFields.ContainsKey($x.Groups[1].Value)) { $bUsed[$x.Groups[1].Value] = $true }
}
Write-Output ("ModBlocks fields referenced by registerOres: {0}" -f $bUsed.Count)

$missing = @()
foreach ($n in $bUsed.Keys | Sort-Object) {
    if ($portModB -notmatch ('register\(\s*"' + [regex]::Escape($n) + '"')) { $missing += $n }
}
Write-Output ("missing in port ModBlocks: {0}" -f $missing.Count)
$missing | ForEach-Object { Write-Output "  MISS: $_" }
