$result = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\tools\generated\rores_ported.txt" -Raw
$ceMod = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java" -Raw
$ceModB = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\blocks\ModBlocks.java" -Raw
$allFields = @{}
foreach ($m in [regex]::Matches($ceMod, '(?m)^\s*public static final Item (\w+)')) { $allFields[$m.Groups[1].Value] = $true }
foreach ($m in [regex]::Matches($ceModB, 'public static final Block (\w+)')) { $allFields[$m.Groups[1].Value] = $true }
$out = @()
$lineNo = 0
foreach ($ln in ($result -split "`r?`n")) {
    $lineNo++
    $t = $ln.Trim()
    if ($t.StartsWith("//")) { continue }
    foreach ($f in $allFields.Keys) {
        if ($t -cmatch ('\b' + [regex]::Escape($f) + '\b')) {
            $out += ("{0}: [{1}] {2}" -f $lineNo, $f, $t.Substring(0, [Math]::Min(90, $t.Length)))
            break
        }
    }
}
$out | Select-Object -First 40
