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

  var chart1 = echarts.init(document.getElementById('chart-loot'), null, { renderer: 'svg' });
  chart1.setOption({
    animation: false,
    tooltip: { trigger: 'axis', appendToBody: true, axisPointer: { type: 'shadow' } },
    legend: { bottom: 0, textStyle: { color: muted, fontSize: 11 } },
    grid: { left: '10%', right: '5%', top: '5%', bottom: '15%' },
    xAxis: {
      type: 'category',
      data: ['核弹苦力怕', '黄金苦力怕', '挥发苦力怕', '污染蟹', '特斯拉蟹', '鸭子', '鸽子', '发光者'],
      axisLabel: { color: muted, fontSize: 10, rotate: 20 },
      axisLine: { lineStyle: { color: rule } }
    },
    yAxis: {
      type: 'value',
      name: '掉落物数量',
      axisLabel: { color: muted, fontSize: 11 },
      splitLine: { lineStyle: { color: rule } },
      axisLine: { lineStyle: { color: rule } }
    },
    series: [
      {
        name: '必掉',
        type: 'bar',
        stack: 'total',
        barWidth: '40%',
        itemStyle: { color: accent2, borderRadius: [0, 0, 0, 0] },
        data: [1, 1, 2, 1, 0, 1, 1, 2]
      },
      {
        name: '条件掉落',
        type: 'bar',
        stack: 'total',
        barWidth: '40%',
        itemStyle: { color: accent, borderRadius: [4, 4, 0, 0] },
        data: [1, 1, 1, 1, 1, 0, 0, 0]
      }
    ]
  });
  window.addEventListener('resize', function() { chart1.resize(); });
})();
