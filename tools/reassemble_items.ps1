# reassemble_items.ps1 — 重新组装 ModItems.java（生成器输出 → 目标文件）
$gen = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\tools\generated"
$mi = "D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java"
$hdr = Get-Content "$gen\ModItems_header.txt" -Raw -Encoding UTF8
$body = Get-Content "$gen\items_base.txt" -Raw -Encoding UTF8
$full = $hdr.TrimEnd() + "`n`n" + $body.TrimEnd() + "`n}`n"
[System.IO.File]::WriteAllText($mi, $full, [System.Text.UTF8Encoding]::new($false))
$decls = (Select-String -Path $mi -Pattern 'public static final DeferredItem<Item>').Count
$ib = (Select-String -Path $mi -Pattern 'new ItemBase\(').Count
$ei = (Select-String -Path $mi -Pattern 'new EffectItem\(').Count
$fields = Select-String -Path $mi -Pattern 'public static final DeferredItem<Item> (\w+)' | ForEach-Object { $_.Matches[0].Groups[1].Value }
$dups = $fields | Group-Object | Where-Object { $_.Count -gt 1 }
Write-Host "decls: $decls | ItemBase: $ib | EffectItem: $ei"
if ($dups) { Write-Host "duplicates: $($dups.Name -join ', ')" } else { Write-Host "duplicates: none" }
