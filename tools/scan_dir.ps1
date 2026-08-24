# usage: $Path = "dir" ; Invoke-Expression (Get-Content scan_dir.ps1 -Raw -Encoding UTF8)
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
    'net\.minecraft\.(potion|util\.DamageSource|util\.StringUtils|inventory\.EntityEquipmentSlot|util\.math\.|init\.|item\.Item|nbt\.|world\.|client\.Minecraft|client\.resources)',
    'PotionEffect|addPotionEffect|getPotion\(|getEffectName|getIsAmbient|ticksToElapsedTime|EntityEquipmentSlot',
    '\.damageType\b|new DamageSource\(|I18n\.format\(',
    'org\.lwjgl|GL11|ActiveRenderInfo|BufferBuilder|Tessellator|GlStateManager|DefaultVertexFormats|ByteBuf|io\.netty',
    'net\.minecraftforge\.fluids|FluidRegistry|FluidTankInfo|IFluidHandler|IFluidTank|IItemHandler|FluidTankProperties'
)
$results = @()
Get-ChildItem $Path -Filter *.java -Recurse | ForEach-Object {
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
$results | Sort-Object -Unique
