$ErrorActionPreference = "Stop"
$ceMod = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java" -Raw
$body = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\tools\generated\rores_body.txt" -Raw

# CE field names (all public static final Item fields)
$fields = @{}
foreach ($m in [regex]::Matches($ceMod, '(?m)^\s*public static final Item (\w+)')) { $fields[$m.Groups[1].Value] = $true }
$fieldList = $fields.Keys | Sort-Object { $_.Length } -Descending

# 1. field refs -> UPPERCASE (skip method calls: word followed by '(')
foreach ($f in $fieldList) {
    $up = ($f -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_'
    $body = [regex]::Replace($body, '\b' + [regex]::Escape($f) + '\b(?!\s*\()', $up)
}

# 2. ModItems.xxx -> ModItems.XXX.get()
foreach ($m in [regex]::Matches($body, 'ModItems\.(\w+)')) {
    $up = ($m.Groups[1].Value -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_'
    $body = $body.Replace('ModItems.' + $m.Groups[1].Value, 'ModItems.' + $up + '.get()')
}

# 3. Mats.*.make(field) -> .get()
$body = [regex]::Replace($body, '\.make\((\w+)\)', '.make($1.get())')

# 4. missing ModBlocks args -> null with TODO
$body = [regex]::Replace($body, 'DictFrame\.fromOne\((basalt_ore|stone_resource), ([^)]*)\)', 'null /* TODO P3: DictFrame.fromOne($1, $2) */')
$body = [regex]::Replace($body, 'fromOne\((basalt_ore|stone_resource), ([^)]*)\)', 'null /* TODO P3: fromOne($1, $2) */')
$body = [regex]::Replace($body, 'fromOne\((block_coke), ([^)]*)\)', 'null /* TODO P3: fromOne($1, $2) */')

# 5. line-comment stubs (keep original text as comment)
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
    elseif ($t -match 'new ItemStack\(ModBlocks\.concrete_colored') { $stub = $true }
    if ($stub) {
        $out += "            // TODO P4.2 tag/P3/P4: " + $ln.TrimStart()
    } else {
        $out += $ln
    }
}
$result = ($out -join "`r`n")

# strip leading '{' line
$result = $result -replace '(?s)^\s*\{\s*', ''

[System.IO.File]::WriteAllText("D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\tools\generated\rores_ported.txt", $result, [System.Text.UTF8Encoding]::new($false))
Write-Output ("ported body lines: {0}" -f (($result -split "`r?`n").Count))
# sanity: no lowercase field refs remain in non-comment lines
$lower = 0
foreach ($ln in ($result -split "`r?`n")) {
    $t = $ln.Trim()
    if ($t.StartsWith("//")) { continue }
    foreach ($f in $fieldList) {
        if ($t -match '\b' + [regex]::Escape($f) + '\b') { $lower++; break }
    }
}
Write-Output ("lowercase field refs in code lines: {0}" -f $lower)
