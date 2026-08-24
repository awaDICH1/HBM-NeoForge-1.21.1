$ErrorActionPreference = "Stop"
$ceMod = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java" -Raw
$ceModB = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\blocks\ModBlocks.java" -Raw
$body = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\tools\generated\rores_body.txt" -Raw

# ModItems fields -> UPPERCASE (skip method calls)
$iFields = @{}
foreach ($m in [regex]::Matches($ceMod, '(?m)^\s*public static final Item (\w+)')) { $iFields[$m.Groups[1].Value] = $true }
foreach ($f in ($iFields.Keys | Sort-Object { $_.Length } -Descending)) {
    $up = ($f -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_'
    $body = [regex]::Replace($body, '\b' + [regex]::Escape($f) + '\b(?!\s*\()', $up)
}

# ModBlocks fields -> UPPERCASE.get()
$bFields = @{}
foreach ($m in [regex]::Matches($ceModB, 'public static final Block (\w+)')) { $bFields[$m.Groups[1].Value] = $true }
foreach ($f in ($bFields.Keys | Sort-Object { $_.Length } -Descending)) {
    $up = ($f -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_'
    $body = [regex]::Replace($body, '\b' + [regex]::Escape($f) + '\b(?!\s*\()', $up + '.get()')
}

# ModItems.xxx -> ModItems.XXX.get()
foreach ($m in [regex]::Matches($body, 'ModItems\.(\w+)')) {
    $up = ($m.Groups[1].Value -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_'
    $body = $body.Replace('ModItems.' + $m.Groups[1].Value, 'ModItems.' + $up + '.get()')
}
# ModBlocks.xxx -> ModBlocks.XXX.get()
foreach ($m in [regex]::Matches($body, 'ModBlocks\.(\w+)')) {
    $up = ($m.Groups[1].Value -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_'
    $body = $body.Replace('ModBlocks.' + $m.Groups[1].Value, 'ModBlocks.' + $up + '.get()')
}

# Mats.*.make(field) -> .get()
$body = [regex]::Replace($body, '\.make\((\w+)\)', '.make($1.get())')

# line-comment stubs
$lines = $body -split "`r?`n"
$out = @()
foreach ($ln in $lines) {
    $t = $ln.Trim()
    $stub = $false
    if ($t -match '^OreDictionary\.registerOre') { $stub = $true }
    elseif ($t -match '^OreDictionary\.getOres') { $stub = $true }
    elseif ($t -match '^CraftingManager\.') { $stub = $true }
    elseif ($t -match '^if \(mat\.autogen\.contains') { $stub = $true }
    elseif ($t -match '^for \(String name : new String\[\]\{"fuelCoke"') { $stub = $true }
    elseif ($t -match 'new ItemStack\(ModBlocks\.CONCRETE_COLORED') { $stub = $true }
    if ($stub) {
        $out += "            // TODO P4.2 tag/P3/P4: " + $ln.TrimStart()
    } else {
        $out += $ln
    }
}
$result = ($out -join "`r`n")
$result = $result -replace '(?s)^\s*\{\s*', ''

[System.IO.File]::WriteAllText("D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\tools\generated\rores_ported.txt", $result, [System.Text.UTF8Encoding]::new($false))
Write-Output ("ported lines: {0}" -f (($result -split "`r?`n").Count))

# leftover check: lowercase refs in non-comment lines (case-sensitive via regex -cmatch)
$allFields = @{}
foreach ($f in $iFields.Keys) { $allFields[$f] = $true }
foreach ($f in $bFields.Keys) { $allFields[$f] = $true }
$left = 0
foreach ($ln in ($result -split "`r?`n")) {
    $t = $ln.Trim()
    if ($t.StartsWith("//")) { continue }
    foreach ($f in $allFields.Keys) {
        if ($t -cmatch '\b' + [regex]::Escape($f) + '\b') { $left++; break }
    }
}
Write-Output ("lowercase field refs left (code lines): {0}" -f $left)
