$f = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java"
$content = Get-Content $f -Raw
$names = @("sulfur", "fluorite", "powder_molysite", "cap_nuka", "cap_quantum", "cap_rad", "cap_sparkle", "cap_korl", "cap_fritz")
foreach ($n in $names) {
    $pat = 'public static final DeferredItem<[^>]+> (\w+) = ITEMS\.register\("' + $n + '"'
    $m = [regex]::Match($content, $pat)
    if ($m.Success) { Write-Output ("{0} -> {1}" -f $n, $m.Groups[1].Value) }
    else { Write-Output ("{0} -> NOT FOUND" -f $n) }
}
