$ErrorActionPreference = "Stop"
$ceMod = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java" -Raw
$ceODM = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\inventory\OreDictManager.java" -Raw

# CE item fields (fieldName -> regName)
$decl = @{}
foreach ($m in [regex]::Matches($ceMod, '(?m)^\s*public static final Item (\w+) = new \w+(?:<[^>]*>)?\(\s*(?:[^,()]+,\s*)*"([a-z0-9_]+)"')) {
    $decl[$m.Groups[1].Value] = $m.Groups[2].Value
}
# also fields whose reg is 1st arg
foreach ($m in [regex]::Matches($ceMod, '(?m)^\s*public static final Item (\w+) = new \w+(?:<[^>]*>)?\(\s*"([a-z0-9_]+)"')) {
    $decl[$m.Groups[1].Value] = $m.Groups[2].Value
}

# registerOres body
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
[System.IO.File]::WriteAllText("D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\tools\generated\rores_body.txt", $body, [System.Text.UTF8Encoding]::new($false))
Write-Output ("body chars: {0}" -f $body.Length)

# 1. ModItems field refs in body
$refs = @{}
foreach ($x in [regex]::Matches($body, '\b([a-z][a-z0-9_]*)\b')) {
    $n = $x.Groups[1].Value
    if ($decl.ContainsKey($n)) { $refs[$n] = $decl[$n] }
}
Write-Output ("ModItems field refs in body: {0}" -f $refs.Count)

# 2. 1.12 API / unmigrated calls
$patterns = @('OreDictionary\.registerOre', 'OreDictionary\.getOres', 'CraftingManager\.', 'new ItemStack\(', 'Item\.getItemFromBlock', 'getMetadata\(\)', '\.setCreativeTab\(', 'EnumBasaltOreType', 'EnumStoneType', 'EnumTarType', 'EnumAshType', 'EnumChunkType', 'Mats\.', 'ModItems\.')
foreach ($p in $patterns) {
    $cnt = ([regex]::Matches($body, [regex]::Escape($p))).Count
    Write-Output ("{0}: {1}" -f $p, $cnt)
}
# save refs list
$refs.GetEnumerator() | Sort-Object Name | ForEach-Object { Write-Output ("  REF {0} -> {1}" -f $_.Name, $_.Value) }
