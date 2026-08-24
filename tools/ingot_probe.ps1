$ceMod = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java" -Raw
$ceLang = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\resources\assets\hbm\lang\en_us.lang" -Raw

# tabs per ingot field
$tabs = @{}
foreach ($x in [regex]::Matches($ceMod, 'public static final Item (\w+) = new Item\w+\(\s*"(ingot_[a-z0-9_]+)"[^;]*?\.setCreativeTab\((\w+)\)')) {
    $tabs[$x.Groups[1].Value] = $x.Groups[3].Value
}
Write-Output "=== fields WITH setCreativeTab in same decl: $($tabs.Count) ==="
$tabCounts = $tabs.Values | Group-Object | ForEach-Object { "{0}: {1}" -f $_.Name, $_.Count }
$tabCounts

# fields without tab in same line (multi-line decls)
$noTab = @()
foreach ($x in [regex]::Matches($ceMod, 'public static final Item (\w+) = new Item\w+\(\s*"(ingot_[a-z0-9_]+)"')) {
    if (-not $tabs.ContainsKey($x.Groups[1].Value)) { $noTab += $x.Groups[1].Value }
}
Write-Output "=== no tab in decl: $($noTab.Count) ==="
$noTab | Select-Object -First 20

# lang keys
$langKeys = @{}
foreach ($x in [regex]::Matches($ceLang, '(?m)^item\.(ingot_[a-z0-9_]+)\.name=(.*)$')) {
    $langKeys[$x.Groups[1].Value] = $x.Groups[2].Value
}
Write-Output "=== CE lang keys for ingot_: $($langKeys.Count) ==="
$langKeys.GetEnumerator() | Sort-Object Name | Select-Object -First 10 | ForEach-Object { "{0} = {1}" -f $_.Name, $_.Value }
