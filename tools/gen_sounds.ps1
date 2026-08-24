$ErrorActionPreference = "Stop"
$ce = Get-Content "D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\lib\HBMSoundHandler.java" -Raw
$ms = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\sound\ModSounds.java"
$hs = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\lib\HBMSoundHandler.java"

# extract field = register("name")
$pairs = @()
foreach ($m in [regex]::Matches($ce, '(\w+)\s*=\s*register\(\s*"([^"]+)"\s*\)')) {
    $pairs += @{ Field = $m.Groups[1].Value; Name = $m.Groups[2].Value }
}
Write-Output ("pairs: {0}" -f $pairs.Count)
$seen = @{}
$unique = @()
foreach ($p in $pairs) { if (-not $seen.ContainsKey($p.Name)) { $seen[$p.Name] = $true; $unique += $p } }
Write-Output ("unique names: {0}" -f $unique.Count)

function To-FieldName([string]$name) {
    $s = $name -replace '[\.\s-]', '_'
    return (($s -split '_' | ForEach-Object { $_.ToUpperInvariant() }) -join '_')
}

# 1. ModSounds: append registrations
$msContent = Get-Content $ms -Raw -Encoding UTF8
$regs = @()
foreach ($p in $unique) {
    if ($p.Name -eq "block.metalImpact") { continue } # already registered as METAL_IMPACT
    $f = To-FieldName $p.Name
    $regs += "    public static final net.neoforged.neoforge.registries.DeferredHolder<SoundEvent, SoundEvent> $f = SOUNDS.register(`"$($p.Name)`","
    $regs += "            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, `"$($p.Name)`")));"
}
$mk = "            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Tags.MODID, `"block.metalImpact`")));"
if (-not $msContent.Contains($mk)) { throw "ModSounds marker not found" }
$msContent = $msContent.Replace($mk, $mk + "`r`n`r`n" + "    // ===== P5.1b-1 声音批：CE HBMSoundHandler 379 项批量注册 =====`r`n" + ($regs -join "`r`n"))
[System.IO.File]::WriteAllText($ms, $msContent, [System.Text.UTF8Encoding]::new($false))
Write-Output ("ModSounds registrations: {0}" -f ($regs.Count / 2))

# 2. HBMSoundHandler: stub facade
$fields = @()
$fields += "package com.hbm.lib;"
$fields += ""
$fields += "import com.hbm.sound.ModSounds;"
$fields += "import net.minecraft.resources.ResourceLocation;"
$fields += "import net.minecraft.sounds.SoundEvent;"
$fields += ""
$fields += "/**"
$fields += " * 声音注册中心（P5.1b-1 门面版）。"
$fields += " *"
$fields += " * 迁移自 1.12.2 com.hbm.lib.HBMSoundHandler（789 行，384 SoundEvent + 播放方法）。"
$fields += " * 1.21.1：SoundEvent 注册移至 com.hbm.sound.ModSounds（DeferredRegister，注册名与 CE 一致）；"
$fields += " * 本类保留静态字段门面（= ModSounds.XXX.get()）供既有代码引用。"
$fields += " * 播放方法（Minecraft.getSoundManager/SoundInstance 等）与 GunConfiguration 依赖 → TODO P8 声音批。"
$fields += " */"
$fields += "public class HBMSoundHandler {"
$fields += ""
$fields += "    public static SoundEvent metalImpact = ModSounds.METAL_IMPACT.get();"
foreach ($p in $unique) {
    if ($p.Name -eq "block.metalImpact") { continue }
    $f = To-FieldName $p.Name
    $fields += "    public static SoundEvent $($p.Field) = ModSounds.$f.get();"
}
$fields += ""
$fields += "    // TODO P8: register/getOrCreate/play 方法（1.21 用 ModSounds + Minecraft.getInstance().getSoundManager()）"
$fields += "    // TODO P8: geigerSounds/voiceSounds/boilerGroanSounds 数组"
$fields += "}"
[System.IO.File]::WriteAllText($hs, ($fields -join "`r`n"), [System.Text.UTF8Encoding]::new($false))
Write-Output ("HBMSoundHandler facade written: {0} fields" -f ($unique.Count - 1))
