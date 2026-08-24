$ErrorActionPreference = "Stop"
$pf = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java"
$mi = Get-Content $pf -Raw

# special block starts at first '// TAB: partsTab (P3.2 special'
$s = $mi.IndexOf("    // TAB: partsTab (P3.2 special")
if ($s -lt 0) { throw "special block not found" }

# block contains 29 fields x 3 lines = 87 lines; find end by locating the 87th newline after s
$pos = $s
for ($k = 0; $k -lt 87; $k++) {
    $nl = $mi.IndexOf("`n", $pos)
    if ($nl -lt 0) { throw "block end not found at line $k" }
    $pos = $nl + 1
}
$block = $mi.Substring($s, $pos - $s)
$rest = $mi.Substring($pos)

# parse each field decl in block: fieldName + regName
$fields = @()
foreach ($m in [regex]::Matches($block, 'public static final DeferredItem<Item> (\w+) = ITEMS\.register\(\s*"([a-z0-9_]+)"')) {
    $fields += @{ Field = $m.Groups[1].Value; Reg = $m.Groups[2].Value }
}
Write-Output ("block fields parsed: {0}" -f $fields.Count)

# keep only decls whose regName does NOT already exist elsewhere in the file (before block)
$keep = @()
$removed = @()
foreach ($f in $fields) {
    $outside = ($mi.Substring(0, $s) + $rest)
    if ($outside -match ('ITEMS\.register\(\s*"' + [regex]::Escape($f.Reg) + '"')) {
        $removed += $f.Reg
    } else {
        $keep += $f
    }
}
Write-Output ("duplicates removed: {0}" -f $removed.Count)
$removed | ForEach-Object { Write-Output "  dup: $_" }

# rebuild block from original line-level decls: easiest - re-extract each kept field's 3-line decl
$lines = $block -split "`r`n"
$keptLines = @()
$i = 0
while ($i -lt $lines.Count) {
    if ($i + 2 -lt $lines.Count -and $lines[$i] -match '^    // TAB:') {
        # decl header: match next line field name
        $declLine = $lines[$i + 1]
        $fname = if ($declLine -match 'public static final DeferredItem<Item> (\w+)') { $matches[1] } else { "" }
        $isKept = ($keep | Where-Object { $_.Field -eq $fname }) -ne $null
        if ($isKept) { $keptLines += $lines[$i]; $keptLines += $lines[$i + 1]; $keptLines += $lines[$i + 2] }
        $i += 3
    } else { $i++ }
}
$newBlock = ($keptLines -join "`r`n")
if ($newBlock.Length -gt 0) { $newBlock += "`r`n" }

$mi = $mi.Substring(0, $s) + $newBlock + $rest
[System.IO.File]::WriteAllText($pf, $mi, [System.Text.UTF8Encoding]::new($false))
Write-Output ("kept: {0}, rewritten" -f $keep.Count)
