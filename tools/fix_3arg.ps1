$ErrorActionPreference = "Stop"
$f = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\inventory\OreDictManager.java"
$c = Get-Content $f -Raw -Encoding UTF8

# new ItemStack(FIELD.get(), 1, meta) -> stackOf(FIELD.get(), meta)  (meta: i / mat.id)
$c = [regex]::Replace($c, 'new ItemStack\(([A-Za-z_][\w]*\.[A-Za-z_][\w]*\.get\(\)|[A-Za-z_][\w]*\.get\(\)), 1, (i|mat\.id)\)', 'stackOf($1, $2)')

[System.IO.File]::WriteAllText($f, $c, [System.Text.UTF8Encoding]::new($false))
$left = ([regex]::Matches($c, 'new ItemStack\([^;]*,[^;]*,[^;]*\)')).Count
Write-Output ("remaining 3-arg new ItemStack: {0}" -f $left)
