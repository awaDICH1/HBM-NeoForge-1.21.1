$ErrorActionPreference = "Stop"
$p = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java"
$allFiles = Get-ChildItem $p -Recurse -Filter *.java
$dangling = @()
foreach ($f in $allFiles) {
    $content = Get-Content $f.FullName -Raw -Encoding UTF8
    foreach ($m in [regex]::Matches($content, 'import com\.hbm\.([\w\.]+);')) {
        $cls = $m.Groups[1].Value
        # skip static imports (handled separately) and nested classes (only check outer)
        $outer = ($cls -split '\.')[0]
        if ($m.Value -match '^import static') { continue }
        # outer class file must exist; nested: check outer exists
        $pathParts = $cls -split '\.'
        $filePath = Join-Path $p ("com\hbm\" + ($pathParts[0..([Math]::Min($pathParts.Count-1, 1))] -join '\') + ".java")
        # rebuild: path = com\hbm\<pkg dirs>\<outer>.java
        $pkg = $pathParts[0..($pathParts.Count-2)] -join '\'
        $outerName = $pathParts[$pathParts.Count-1]
        $check = Join-Path $p ("com\hbm\" + $pkg + "\" + $outerName + ".java")
        if (-not (Test-Path $check)) {
            # maybe it's a package import like com.hbm.items.machine.*
            if ($cls -notmatch '\*$') {
                $dangling += ("{0}: import com.hbm.{1}" -f $f.Name, $cls)
            }
        }
    }
}
# check imports with wildcard are risky but valid; list dangling
$dangling | Sort-Object -Unique | Select-Object -First 40
Write-Output ("dangling com.hbm imports total: {0}" -f ($dangling | Sort-Object -Unique).Count)
