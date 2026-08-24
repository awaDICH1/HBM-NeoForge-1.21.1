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

  // 图1: 数据生成文件分布
  var chart1 = echarts.init(document.getElementById('chart-datagen'), null, { renderer: 'svg' });
  chart1.setOption({
    animation: false,
    tooltip: { trigger: 'item', appendToBody: true },
    legend: { bottom: 0, textStyle: { color: muted, fontSize: 12 } },
    series: [{
      type: 'pie',
      radius: ['40%', '70%'],
      center: ['50%', '45%'],
      label: { color: ink, fontSize: 12, formatter: '{b}\n{c} 个' },
      data: [
        { value: 429, name: '配方JSON', itemStyle: { color: accent } },
        { value: 32, name: '掉落表JSON', itemStyle: { color: accent2 } },
        { value: 430, name: '其他数据', itemStyle: { color: accent3 } }
      ]
    }]
  });
  window.addEventListener('resize', function() { chart1.resize(); });

  // 图2: 各配方系统数量对比
  var chart2 = echarts.init(document.getElementById('chart-recipes'), null, { renderer: 'svg' });
  chart2.setOption({
    animation: false,
    tooltip: { trigger: 'axis', appendToBody: true, axisPointer: { type: 'shadow' } },
    grid: { left: '15%', right: '5%', top: '5%', bottom: '10%' },
    xAxis: {
      type: 'category',
      data: ['合成管理器', '装配机', 'SILEX分离', 'RBMK燃料', '熔炼'],
      axisLabel: { color: muted, fontSize: 11, rotate: 15 },
      axisLine: { lineStyle: { color: rule } }
    },
    yAxis: {
      type: 'value',
      axisLabel: { color: muted, fontSize: 11 },
      splitLine: { lineStyle: { color: rule } },
      axisLine: { lineStyle: { color: rule } }
    },
    series: [{
      type: 'bar',
      barWidth: '50%',
      itemStyle: {
        color: function(params) {
          var colors = [accent, accent2, accent3, warn, accent];
          return colors[params.dataIndex];
        },
        borderRadius: [4, 4, 0, 0]
      },
      label: { show: true, position: 'top', color: ink, fontSize: 12, fontWeight: 'bold' },
      data: [429, 211, 95, 320, 8]
    }]
  });
  window.addEventListener('resize', function() { chart2.resize(); });

  // 图3: 子任务完成进度
  var chart3 = echarts.init(document.getElementById('chart-progress'), null, { renderer: 'svg' });
  chart3.setOption({
    animation: false,
    tooltip: { trigger: 'axis', appendToBody: true, axisPointer: { type: 'shadow' } },
    grid: { left: '25%', right: '10%', top: '5%', bottom: '5%' },
    xAxis: {
      type: 'value', max: 100,
      axisLabel: { color: muted, fontSize: 11, formatter: '{value}%' },
      splitLine: { lineStyle: { color: rule } },
      axisLine: { lineStyle: { color: rule } }
    },
    yAxis: {
      type: 'category',
      data: ['B-1 SILEX', 'B-2 装配机', 'B-3 RBMK燃料', 'P5 高级配方', 'C-1 音效', 'C-2 粒子', 'C-3 红石'],
      axisLabel: { color: muted, fontSize: 11 },
      axisLine: { lineStyle: { color: rule } }
    },
    series: [{
      type: 'bar',
      barWidth: '60%',
      itemStyle: { color: accent2, borderRadius: [0, 4, 4, 0] },
      label: { show: true, position: 'right', color: ink, fontSize: 11, formatter: '{c}%' },
      data: [100, 100, 100, 100, 100, 100, 100]
    }]
  });
  window.addEventListener('resize', function() { chart3.resize(); });
})();
