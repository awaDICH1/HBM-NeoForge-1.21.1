$ErrorActionPreference = "Stop"
$ceModB = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\blocks\ModBlocks.java" -Raw
$portModB = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\blocks\ModBlocks.java" -Raw
$body = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\tools\generated\rores_body.txt" -Raw

$bFields = @{}
foreach ($m in [regex]::Matches($ceModB, 'public static final Block (\w+)')) { $bFields[$m.Groups[1].Value] = $true }
$bUsed = @{}
foreach ($x in [regex]::Matches($body, '\b([a-z][a-z0-9_]*)\b')) { if ($bFields.ContainsKey($x.Groups[1].Value)) { $bUsed[$x.Groups[1].Value] = $true } }

$missing = @()
foreach ($n in $bUsed.Keys | Sort-Object) {
    if ($portModB -notmatch ('register\(\s*"' + [regex]::Escape($n) + '"')) { $missing += $n }
}
Write-Output ("missing ModBlocks: {0}" -f $missing.Count)

function To-FieldName([string]$reg) { return (($reg -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_') }

$decls = @()
$accepts = @()
foreach ($n in $missing) {
    $f = To-FieldName $n
    $decls += "    // P3.2 registerOres placeholder (CE: Block $n, real class P3 block batch)"
    $decls += "    public static final DeferredBlock<Block> $f = BLOCKS.register(`"$n`","
    $decls += "            () -> new Block(BlockBehaviour.Properties.of()));"
    $decls += "    public static final DeferredItem<BlockItem> ${f}_ITEM = ITEMS.registerSimpleBlockItem($f);"
    $accepts += "                output.accept(ModBlocks.$f.get().asItem());"
}

$mb = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\blocks\ModBlocks.java" -Raw
$mk = "    public static final DeferredItem<BlockItem> TRAPDOOR_STEEL_ITEM = ITEMS.registerSimpleBlockItem(TRAPDOOR_STEEL);"
if (-not $mb.Contains($mk)) { throw "ModBlocks marker not found" }
$mb = $mb.Replace($mk, $mk + "`r`n" + ($decls -join "`r`n"))
[System.IO.File]::WriteAllText("D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\blocks\ModBlocks.java", $mb, [System.Text.UTF8Encoding]::new($false))

# tabs: insert into BLOCK_TAB accept block - find its end
$mt = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\creativetabs\ModCreativeTabs.java" -Raw
$bt = $mt.IndexOf('BLOCK_TAB = TABS.register("blocks"')
if ($bt -lt 0) { throw "BLOCK_TAB not found" }
$btEnd = $mt.IndexOf("}));", $bt)
if ($btEnd -lt 0) { throw "BLOCK_TAB end not found" }
$insert = "`r`n" + ($accepts -join "`r`n")
$mt = $mt.Substring(0, $btEnd) + $insert + $mt.Substring($btEnd)
[System.IO.File]::WriteAllText("D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\creativetabs\ModCreativeTabs.java", $mt, [System.Text.UTF8Encoding]::new($false))
Write-Output ("inserted {0} block placeholders + {1} accepts" -f $missing.Count, $accepts.Count)
