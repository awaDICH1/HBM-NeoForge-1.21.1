$dirs = @(
    "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\api\energymk2",
    "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\api\fluidmk2",
    "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\uninos"
)
$patterns = @(
    'net\.minecraftforge\.fml(?!\.neoforge)',
    'NBTTag',
    'EntityPlayer|EntityLivingBase|EntityLiving\b|WorldServer|WorldClient',
    '(?<!Block)TileEntity',
    'MathHelper',
    'EnumFacing',
    'IBlockState',
    '\.isRemote\b',
    '\.getTileEntity\(',
    '\.getEntityWorld\(',
    '\.getTotalWorldTime\(',
    '\.world\.provider\b',
    '\.world\.rand\b',
    '\.getPos\(\)',
    '\.getWorld\(\)',
    'Minecraft\.getMinecraft',
    '\.setDead\(\)|\.isDead\b',
    '\.setBlockToAir\(',
    '\.getItemDamage\(\)|\.setItemDamage\(|\.splitStack\(',
    '\.hasTagCompound\(\)|\.getTagCompound\(\)|\.setTagCompound\(',
    '\.getEntityData\(\)|\.getDataManager\(\)',
    'EnumHand|EnumActionResult',
    'Block\.getStateFromMeta|getMetaFromState',
    'Item\.getItemFromBlock|Item\.getByNameOrId',
    'ForgeRegistries\.'
)
$results = @()
foreach ($d in $dirs) {
    Get-ChildItem $d -Filter *.java -Recurse | ForEach-Object {
        $f = $_.FullName
        $lines = Get-Content $f
        for ($i = 0; $i -lt $lines.Count; $i++) {
            $ln = $lines[$i]
            foreach ($p in $patterns) {
                if ($ln -cmatch $p) {
                    $short = $f -replace '.*\\com\\hbm\\', 'com\hbm\'
                    $results += ("{0}:{1}: [{2}] {3}" -f $short, ($i + 1), $p, $ln.Trim())
                }
            }
        }
    }
}
$results | Sort-Object -Unique
