# usage: $Path = "..." ; Invoke-Expression (Get-Content scan_file.ps1 -Raw -Encoding UTF8)
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
    '\.getPos\(\)',
    '\.getWorld\(\)',
    'Minecraft\.getMinecraft',
    '\.setDead\(\)|\.isDead\b',
    '\.getItemDamage\(\)|\.setItemDamage\(',
    '\.hasTagCompound\(\)|\.getTagCompound\(\)|\.setTagCompound\(',
    'EnumHand|EnumActionResult',
    'ForgeRegistries\.',
    'OreDictionary|OreRegisterEvent|MainRegistry|CraftingManager|ItemEnums|BlockEnums|\.init\.(Blocks|Items)|@SubscribeEvent',
    'new ItemStack\([^)]*,[^)]*,[^)]*\)'
)
$results = @()
$lines = Get-Content $Path
for ($i = 0; $i -lt $lines.Count; $i++) {
    $ln = $lines[$i]
    foreach ($p in $patterns) {
        if ($ln -cmatch $p) {
            $results += ("{0}:{1}: [{2}] {3}" -f ($Path -replace '.*\\com\\hbm\\', 'com\hbm\'), ($i + 1), $p, $ln.Trim())
        }
    }
}
$results | Sort-Object -Unique
