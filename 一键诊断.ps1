# ============================================================
# HBM-NeoForge 一键诊断工具（修复版）
# ============================================================

trap { Write-Host "错误: $_" -ForegroundColor Red; Read-Host "按回车退出"; exit }

$ErrorActionPreference = "Stop"
Set-Location $PSScriptRoot

Write-Host "=== HBM-NeoForge 一键诊断工具 ===" -ForegroundColor Cyan

# 检查 gradlew
if (-not (Test-Path ".\gradlew")) {
    Write-Host "❌ 找不到 gradlew，请确认当前目录是 HBM-NeoForge 根目录" -ForegroundColor Red
    Read-Host "按回车退出"
    exit
}

# 编译
$logFile = "compile_output.txt"
if (-not (Test-Path $logFile)) {
    Write-Host "正在编译（1-3 分钟）..." -ForegroundColor Yellow
    & .\gradlew compileJava 2>&1 | Out-File -FilePath $logFile -Encoding UTF8
}

$log = Get-Content $logFile -Raw -Encoding UTF8

if ($log -match "BUILD SUCCESSFUL") {
    Write-Host "✅ 编译成功！" -ForegroundColor Green
    Read-Host "按回车退出"
    exit
}

# 提取错误位置
$matches = [regex]::Matches($log, "([A-Za-z]:[^:]+):(\d+):")
if ($matches.Count -eq 0) {
    Write-Host "⚠️ 未找到明确错误，请检查 $logFile" -ForegroundColor Yellow
    Read-Host "按回车退出"
    exit
}

Write-Host "找到 $($matches.Count) 个错误位置..." -ForegroundColor Yellow

# 去重
$errorSet = @{}
foreach ($m in $matches) {
    $file = $m.Groups[1].Value
    $line = [int]$m.Groups[2].Value
    $key = "$file|$line"
    $errorSet[$key] = @{ File = $file; Line = $line }
}

# 生成报告
$report = @()
$report += "=== HBM-NeoForge 编译诊断报告 ==="
$report += "错误总数: $($errorSet.Count)"
$report += ""

foreach ($key in $errorSet.Keys) {
    $info = $errorSet[$key]
    $file = $info.File
    $line = $info.Line

    $report += "--- 文件: $file : 行号: $line ---"

    if (-not (Test-Path $file)) {
        $report += "⚠️ 文件不存在（路径可能不完整）"
        $report += ""
        continue
    }

    $lines = Get-Content $file -Encoding UTF8
    $total = $lines.Count
    if ($line -lt 1 -or $line -gt $total) {
        $report += "⚠️ 行号超出范围"
        $report += ""
        continue
    }

    $start = [Math]::Max(0, $line - 15)
    $end = [Math]::Min($total - 1, $line + 15)
    for ($i = $start; $i -le $end; $i++) {
        $num = $i + 1
        $marker = if ($num -eq $line) { ">>>" } else { "   " }
        # ===== 修复点：用 ${num} 避免 $num: 被解析为变量引用 =====
        $report += "${num}: ${marker} $($lines[$i])"
    }
    $report += ""
}

# 输出到屏幕
$report -join "`r`n" | Out-Host

# 保存到文件
$report -join "`r`n" | Out-File -FilePath "diagnostic_report.txt" -Encoding UTF8
Write-Host ""
Write-Host "✅ 诊断报告已保存到 diagnostic_report.txt" -ForegroundColor Green
Write-Host "请将以上屏幕输出内容复制粘贴给 AI。" -ForegroundColor Cyan
Read-Host "按回车退出"