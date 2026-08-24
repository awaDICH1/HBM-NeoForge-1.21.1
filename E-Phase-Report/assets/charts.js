(function() {
  var style = getComputedStyle(document.documentElement);
  var accent = style.getPropertyValue('--accent').trim();
  var accent2 = style.getPropertyValue('--accent2').trim();
  var accent3 = style.getPropertyValue('--accent3').trim();
  var ink = style.getPropertyValue('--ink').trim();
  var muted = style.getPropertyValue('--muted').trim();
  var rule = style.getPropertyValue('--rule').trim();
  var bg2 = style.getPropertyValue('--bg2').trim();
  var warn = style.getPropertyValue('--warn').trim();

  // 图1: 粒子纹理复用分布
  var chart1 = echarts.init(document.getElementById('chart-particles'), null, { renderer: 'svg' });
  chart1.setOption({
    animation: false,
    tooltip: { trigger: 'item', appendToBody: true },
    legend: { bottom: 0, textStyle: { color: muted, fontSize: 11 } },
    series: [{
      type: 'pie',
      radius: ['35%', '65%'],
      center: ['50%', '45%'],
      label: { color: ink, fontSize: 11, formatter: '{b}: {c}' },
      data: [
        { value: 8, name: 'RBMK火焰', itemStyle: { color: accent } },
        { value: 5, name: '通用粒子', itemStyle: { color: accent2 } },
        { value: 4, name: '雾', itemStyle: { color: accent3 } },
        { value: 2, name: '烟雾', itemStyle: { color: warn } },
        { value: 2, name: '深色烟雾', itemStyle: { color: '#a78bfa' } },
        { value: 2, name: '冲击波', itemStyle: { color: '#06b6d4' } },
        { value: 2, name: 'RBMK蒸汽', itemStyle: { color: '#ec4899' } },
        { value: 1, name: '骷髅血', itemStyle: { color: '#f87171' } },
        { value: 1, name: '金属', itemStyle: { color: '#94a3b8' } },
        { value: 1, name: '爆炸', itemStyle: { color: '#fb923c' } },
        { value: 1, name: '血肉', itemStyle: { color: '#e879f9' } },
        { value: 1, name: '强子', itemStyle: { color: '#818cf8' } },
        { value: 1, name: '薄雾', itemStyle: { color: '#34d399' } },
        { value: 1, name: '凝结尾迹', itemStyle: { color: '#fbbf24' } },
        { value: 1, name: '贝勒火爆炸', itemStyle: { color: '#f97316' } },
        { value: 1, name: '闪光', itemStyle: { color: '#ef4444' } },
        { value: 1, name: 'RBMK蘑菇云', itemStyle: { color: '#a3a3a3' } },
        { value: 1, name: '弹壳', itemStyle: { color: '#d4d4d8' } }
      ]
    }]
  });
  window.addEventListener('resize', function() { chart1.resize(); });

  // 图2: 各模块完成度
  var chart2 = echarts.init(document.getElementById('chart-progress'), null, { renderer: 'svg' });
  chart2.setOption({
    animation: false,
    tooltip: { trigger: 'axis', appendToBody: true, axisPointer: { type: 'shadow' } },
    grid: { left: '25%', right: '8%', top: '3%', bottom: '5%' },
    xAxis: {
      type: 'value', max: 100,
      axisLabel: { color: muted, fontSize: 11, formatter: '{value}%' },
      splitLine: { lineStyle: { color: rule } },
      axisLine: { lineStyle: { color: rule } }
    },
    yAxis: {
      type: 'category',
      data: ['粒子系统', '世界生成', '语言文件', '实体掉落表', '实体AI', '实体属性', '配方系统', '机器方块', '音效系统', '纹理资源', '实体注册', '方块注册', '物品注册'],
      axisLabel: { color: muted, fontSize: 11 },
      axisLine: { lineStyle: { color: rule } },
      inverse: true
    },
    series: [{
      type: 'bar',
      barWidth: '55%',
      itemStyle: { color: accent2, borderRadius: [0, 4, 4, 0] },
      label: { show: true, position: 'right', color: ink, fontSize: 11, formatter: '{c}%' },
      data: [100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100]
    }]
  });
  window.addEventListener('resize', function() { chart2.resize(); });
})();
