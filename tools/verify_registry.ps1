# verify_registry.ps1 — 注册名重复检查（ModItems/ModBlocks/ModSounds/ModTileEntities/ModMenus）
# usage: $Path = "src\main\java\com\hbm\items\ModItems.java" ; Invoke-Expression (Get-Content verify_registry.ps1 -Raw -Encoding UTF8)

$content = Get-Content $Path -Raw -Encoding UTF8
$pattern = 'register\(\s*"([^"]+)"'
$names = [regex]::Matches($content, $pattern) | ForEach-Object { $_.Groups[1].Value }
$dups = $names | Group-Object | Where-Object { $_.Count -gt 1 }
if ($dups) {
    Write-Output ("DUP ({0}):" -f $dups.Count)
    $dups | ForEach-Object { Write-Output ("  {0} x{1}" -f $_.Name, $_.Count) }
} else {
    Write-Output ("OK: {0} unique registrations, no duplicates" -f ($names | Sort-Object -Unique).Count)
}
