$ErrorActionPreference = "Stop"
$ceMod = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java" -Raw
$ceODM = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\inventory\OreDictManager.java" -Raw
$pf = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java" -Raw

# CE field -> declaration line (whole decl via simple marker)
$declStart = @{}
foreach ($m in [regex]::Matches($ceMod, '(?m)^\s*public static final Item (\w+) = .*$')) {
    $declStart[$m.Groups[1].Value] = $m.Value.Trim()
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

$used = @{}
foreach ($x in [regex]::Matches($body, '\b([a-z][a-z0-9_]*)\b')) { if ($declStart.ContainsKey($x.Groups[1].Value)) { $used[$x.Groups[1].Value] = $true } }

# standard reg name extraction: new Xxx("name"...) or new Xxx(\n"name"...)
$special = @()
foreach ($n in $used.Keys | Sort-Object) {
    $decl = $declStart[$n]
    # try standard single-line or any "name" string within the declaration line
    $m = [regex]::Match($decl, 'new \w+\(\s*"([a-z0-9_]+)"')
    if ($m.Success) {
        $reg = $m.Groups[1].Value
        if ($pf -notmatch ('ITEMS\.register\(\s*"' + [regex]::Escape($reg) + '"')) { $special += "$n | reg=$reg | $decl" }
    } else {
        $special += "$n | NO-REG | $decl"
    }
}
Write-Output ("special/missing-after-gen: {0}" -f $special.Count)
$special | ForEach-Object { Write-Output $_ }
