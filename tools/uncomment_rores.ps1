$ErrorActionPreference = "Stop"
$f = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\inventory\OreDictManager.java"
$c = Get-Content $f -Raw -Encoding UTF8

# 1. uncomment TODO P4.2 tag/P3/P4 lines
$c = $c -replace '(?m)^\s*// TODO P4\.2 tag/P3/P4: ', '            '

# 2. OreDictionary.registerOre -> registerOre
$c = $c -replace 'OreDictionary\.registerOre\(', 'registerOre('

# 3. 3-arg ItemStack with WILDCARD -> stackOf(x, 0)
$c = $c -replace 'new ItemStack\(([^,()]+), 1, OreDictionary\.WILDCARD_VALUE\)', 'stackOf($1, 0)'

# 4. 3-arg ItemStack with meta var -> stackOf(x, meta)
$c = $c -replace 'new ItemStack\(([^,()]+), 1, ([^)]+)\)', 'stackOf($1, $2)'

# 5. restore DictFrame.registerStack 2 stubs
$c = $c -replace '// TODO P4\.2: OreDictionary\.registerOre\(tag \+ mat, stack\) -> 1\.21 tag 注册', 'registerOre(tag + mat, stack);'
$c = $c -replace '// TODO P4\.2: OreDictionary\.registerOre\(mat, stack\) -> 1\.21 tag 注册', 'registerOre(mat, stack);'

[System.IO.File]::WriteAllText($f, $c, [System.Text.UTF8Encoding]::new($false))
# count remaining OreDictionary refs
$left = ([regex]::Matches($c, 'OreDictionary')).Count
$leftStub = ([regex]::Matches($c, 'TODO P4\.2 tag/P3/P4')).Count
Write-Output ("OreDictionary refs left: {0}, stub lines left: {1}" -f $left, $leftStub)
