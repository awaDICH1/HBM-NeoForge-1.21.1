$ErrorActionPreference = "Stop"
$f = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\inventory\OreDictManager.java"
$ceMod = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java" -Raw
$c = Get-Content $f -Raw -Encoding UTF8

$iFields = @{}
foreach ($m in [regex]::Matches($ceMod, '(?m)^\s*public static final Item (\w+)')) { $iFields[$m.Groups[1].Value] = $true }

# registerOres body region
$s = $c.IndexOf("public static void registerOres() {")
$brace = $c.IndexOf("{", $s)
$depth = 0; $i = $brace; $inStr = $false
while ($i -lt $c.Length) {
    $ch = $c[$i]
    if ($inStr) { if ($ch -eq '"') { $inStr = $false } }
    else { if ($ch -eq '"') { $inStr = $true } elseif ($ch -eq '{') { $depth++ } elseif ($ch -eq '}') { $depth--; if ($depth -eq 0) { break } } }
    $i++
}
$body = $c.Substring($brace, $i - $brace)

# fromOne/fromAll with bare ModItems field arg -> add .get()
foreach ($m in [regex]::Matches($body, '(fromOne|fromAll)\((\w+)(?=, )')) {
    $arg = $m.Groups[2].Value
    if ($iFields.ContainsKey($arg)) {
        $body = $body.Replace($m.Groups[1].Value + "(" + $arg + ", ", $m.Groups[1].Value + "(" + $arg + ".get(), ")
    }
}

$c = $c.Substring(0, $brace) + $body + $c.Substring($i)
[System.IO.File]::WriteAllText($f, $c, [System.Text.UTF8Encoding]::new($false))
Write-Output "fromOne/fromAll .get() applied"
