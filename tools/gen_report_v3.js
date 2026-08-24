const fs = require("fs");
const {
  Document, Packer, Paragraph, TextRun, Table, TableRow, TableCell,
  Header, Footer, AlignmentType, LevelFormat, HeadingLevel,
  BorderStyle, WidthType, ShadingType, PageNumber,
  VerticalAlign
} = require("docx");

const border = { style: BorderStyle.SINGLE, size: 1, color: "CCCCCC" };
const borders = { top: border, bottom: border, left: border, right: border };
const headerShading = { fill: "D5E8F0", type: ShadingType.CLEAR };
const greenShading = { fill: "D5F5E3", type: ShadingType.CLEAR };
const blueShading = { fill: "D6EAF8", type: ShadingType.CLEAR };

function heading(text, level) { return new Paragraph({ heading: level, children: [new TextRun(text)] }); }
function bullet(text) { return new Paragraph({ numbering: { reference: "bullets", level: 0 }, children: [new TextRun(text)] }); }
function makeCell(text, width, isHeader, shading) {
  return new TableCell({ borders, width: { size: width, type: WidthType.DXA }, shading: shading || (isHeader ? headerShading : undefined),
    margins: { top: 80, bottom: 80, left: 120, right: 120 }, verticalAlign: VerticalAlign.CENTER,
    children: [new Paragraph({ children: [new TextRun({ text, bold: isHeader })] })] });
}
function makeRow(cells, widths, isHeader, shadings) {
  return new TableRow({ cantSplit: true, children: cells.map((c, i) => makeCell(c, widths[i], isHeader, shadings ? shadings[i] : undefined)) });
}

const doc = new Document({
  styles: {
    default: { document: { run: { font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" }, size: 24 } } },
    paragraphStyles: [
      { id: "Heading1", name: "Heading 1", basedOn: "Normal", next: "Normal", quickFormat: true,
        run: { size: 36, bold: true, font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } },
        paragraph: { spacing: { before: 360, after: 200 }, outlineLevel: 0, keepNext: false, keepLines: false } },
      { id: "Heading2", name: "Heading 2", basedOn: "Normal", next: "Normal", quickFormat: true,
        run: { size: 30, bold: true, font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } },
        paragraph: { spacing: { before: 280, after: 160 }, outlineLevel: 1, keepNext: false, keepLines: false } },
    ]
  },
  numbering: { config: [
    { reference: "bullets", levels: [{ level: 0, format: LevelFormat.BULLET, text: "\u2022", alignment: AlignmentType.LEFT,
      style: { paragraph: { indent: { left: 720, hanging: 360 } } } }] },
  ] },
  sections: [{
    properties: { page: { size: { width: 12240, height: 15840 }, margin: { top: 1440, right: 1440, bottom: 1440, left: 1440 } } },
    headers: { default: new Header({ children: [new Paragraph({ alignment: AlignmentType.RIGHT,
      children: [new TextRun({ text: "HBM Mod Migration Report v3 - Render System", size: 18, color: "999999" })] })] }) },
    footers: { default: new Footer({ children: [new Paragraph({ alignment: AlignmentType.CENTER,
      children: [new TextRun({ text: "Page ", size: 18 }), new TextRun({ children: [PageNumber.CURRENT], size: 18 }),
        new TextRun({ text: " / ", size: 18 }), new TextRun({ children: [PageNumber.TOTAL_PAGES], size: 18 })] })] }) },
    children: [
      new Paragraph({ alignment: AlignmentType.CENTER, spacing: { before: 1200, after: 400 },
        children: [new TextRun({ text: "HBM\u6A21\u7EC4\u79FB\u690D\u62A5\u544A", size: 52, bold: true, font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } })] }),
      new Paragraph({ alignment: AlignmentType.CENTER, spacing: { after: 200 },
        children: [new TextRun({ text: "\u6E32\u67D3\u4E0E\u7C92\u5B50\u7CFB\u7EDF\u8FC1\u79FB\u7248", size: 32, color: "2980B9", font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } })] }),
      new Paragraph({ alignment: AlignmentType.CENTER, spacing: { after: 600 },
        children: [new TextRun({ text: "DeepSeek P3 \u6307\u4EE4 | 2026-08-21", size: 24, color: "666666" })] }),

      heading("\u4E00\u3001\u672C\u8F6E\u5B8C\u6210\u6982\u89C8", HeadingLevel.HEADING_1),
      new Table({ width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [3120, 6240],
        rows: [
          makeRow(["\u6A21\u5757", "\u8BE6\u60C5"], [3120, 6240], true),
          makeRow(["P3-1 \u5B9E\u4F53\u6E32\u67D3\u5668", "128\u4E2A\u5B9E\u4F53\u6CE8\u518C\u5360\u4F4D\u6E32\u67D3\u5668 (RenderEmpty)"], [3120, 6240], false, [blueShading, undefined]),
          makeRow(["P3-2 \u7C92\u5B50\u7CFB\u7EDF", "34\u4E2A\u81EA\u5B9A\u4E49\u7C92\u5B50\u7C7B\u578B\u6CE8\u518C (ModParticleTypes)"], [3120, 6240], false, [blueShading, undefined]),
          makeRow(["P3-3 OBJ\u6A21\u578B", "\u65E0\u9700\u8FC1\u79FB (CE\u6E90\u7801\u65E0forge:obj\u5F15\u7528)"], [3120, 6240], false, [greenShading, undefined]),
        ]
      }),

      heading("\u4E8C\u3001\u5B9E\u4F53\u6E32\u67D3\u5668\u6CE8\u518C (P3-1)", HeadingLevel.HEADING_1),
      new Table({ width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [2340, 2340, 4680],
        rows: [
          makeRow(["\u7C7B\u522B", "\u6570\u91CF", "\u8BF4\u660E"], [2340, 2340, 4680], true),
          makeRow(["\u6295\u5C04\u7269", "16\u4E2A", "\u5B50\u5F39/\u624B\u68B0\u5F39/\u706B\u7BAD\u7B49"], [2340, 2340, 4680], false),
          makeRow(["\u751F\u7269", "25\u4E2A", "\u8F89\u5C04\u751F\u7269/\u8FB1\u5C04\u8005/\u6728\u4E43\u4F0A\u7B49"], [2340, 2340, 4680], false),
          makeRow(["\u5BFC\u5F39", "15\u4E2A", "\u5404\u7EA7\u522B\u5BFC\u5F39/Soyuz/MIRV\u7B49"], [2340, 2340, 4680], false),
          makeRow(["\u7269\u54C1\u5B9E\u4F53", "8\u4E2A", "\u65E0\u4EBA\u673A/\u7A7A\u6295/\u79FB\u52A8\u7269\u54C1\u7B49"], [2340, 2340, 4680], false),
          makeRow(["\u6548\u679C", "14\u4E2A", "\u9ED1\u6D1E/\u68D5\u68E8\u4E91/\u8F90\u5C04\u5C18\u7B49"], [2340, 2340, 4680], false),
          makeRow(["\u903B\u8F91", "8\u4E2A", "\u6838\u7206/\u8F70\u70B8\u673A/\u6B66\u88C5\u98DE\u8239\u7B49"], [2340, 2340, 4680], false),
          makeRow(["\u5176\u4ED6", "42\u4E2A", "\u6FC0\u5149\u5149\u675F/\u706B\u7BAD/\u70AE\u5854\u7B49"], [2340, 2340, 4680], false),
          makeRow(["\u5408\u8BA1", "128\u4E2A", "\u5168\u90E8\u5B9E\u4F53\u5DF2\u6CE8\u518C\u6E32\u67D3\u5668"], [2340, 2340, 4680], true, [greenShading, greenShading, greenShading]),
        ]
      }),

      heading("\u4E09\u3001\u7C92\u5B50\u7CFB\u7EDF\u6CE8\u518C (P3-2)", HeadingLevel.HEADING_1),
      new Paragraph({ children: [new TextRun("\u65B0\u5EFA ModParticleTypes \u7C7B\uFF0C\u6CE8\u518C34\u4E2A\u81EA\u5B9A\u4E49\u7C92\u5B50\u7C7B\u578B\uFF1A")] }),
      new Table({ width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [4680, 4680],
        rows: [
          makeRow(["\u7C92\u5B50\u7C7B\u578B", "\u7528\u9014"], [4680, 4680], true),
          makeRow(["ashes / smoke_plume", "\u70DF\u96FE\u6548\u679C"], [4680, 4680], false),
          makeRow(["black_powder_smoke / spark", "\u70ED\u5175\u5668\u6548\u679C"], [4680, 4680], false),
          makeRow(["flame_nt / flamethrower / large_flame", "\u706B\u7130\u6548\u679C"], [4680, 4680], false),
          makeRow(["rocket_flame / jetpack_trail", "\u706B\u7BAD\u5C3E\u7130/\u55B7\u6C14\u5305\u5C3E\u8FF9"], [4680, 4680], false),
          makeRow(["rbmk_flame / rbmk_mush / rbmk_steam", "RBMK\u53CD\u5E94\u5806\u6548\u679C"], [4680, 4680], false),
          makeRow(["muke_cloud / muke_flash", "\u6838\u7206\u70B8\u4E91/\u95EA\u5149"], [4680, 4680], false),
          makeRow(["lightning / plasma_blast", "\u95EA\u7535/\u7B49\u79BB\u5B50\u6D46"], [4680, 4680], false),
          makeRow(["radiation_fog / haze / rift", "\u8F90\u5C04\u96FE/\u96FE\u970DE/\u88C2\u9699"], [4680, 4680], false),
          makeRow(["\u5176\u4ED6 17\u79CD", "\u8840\u6DB2/\u788E\u7247/\u5F39\u58F3/\u51B7\u5374\u5854\u7B49"], [4680, 4680], false),
        ]
      }),

      heading("\u56DB\u3001\u65B0\u589E\u6587\u4EF6", HeadingLevel.HEADING_1),
      new Table({ width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [6240, 3120],
        rows: [
          makeRow(["\u6587\u4EF6", "\u8BF4\u660E"], [6240, 3120], true),
          makeRow(["ModRenderers.java", "\u5B9E\u4F53\u6E32\u67D3\u5668\u6CE8\u518C\u4E2D\u5FC3 (128\u4E2A\u5B9E\u4F53)"], [6240, 3120], false),
          makeRow(["RenderEmpty.java", "\u5360\u4F4D\u6E32\u67D3\u5668 (\u9632\u6B62 Missing renderer \u9519\u8BEF)"], [6240, 3120], false),
          makeRow(["ModParticleTypes.java", "\u7C92\u5B50\u7C7B\u578B\u6CE8\u518C (34\u4E2A\u7C92\u5B50)"], [6240, 3120], false),
        ]
      }),

      heading("\u4E94\u3001\u9A8C\u8BC1\u7ED3\u679C", HeadingLevel.HEADING_1),
      new Table({ width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [3120, 3120, 3120],
        rows: [
          makeRow(["\u9879\u76EE", "\u7ED3\u679C", "\u72B6\u6001"], [3120, 3120, 3120], true),
          makeRow(["\u7F16\u8BD1", "BUILD SUCCESSFUL", "\u901A\u8FC7"], [3120, 3120, 3120], false, [undefined, undefined, greenShading]),
          makeRow(["DataGen", "611\u4E2A\u6587\u4EF6", "\u901A\u8FC7"], [3120, 3120, 3120], false, [undefined, undefined, greenShading]),
          makeRow(["runClient", "\u6A21\u7EC4\u6210\u529F\u52A0\u8F7D", "\u901A\u8FC7"], [3120, 3120, 3120], false, [undefined, undefined, greenShading]),
          makeRow(["Missing renderer", "0\u4E2A\u9519\u8BEF", "\u901A\u8FC7"], [3120, 3120, 3120], false, [undefined, undefined, greenShading]),
          makeRow(["\u5D29\u6E83", "0\u4E2A\u65B0\u5D29\u6E83", "\u901A\u8FC7"], [3120, 3120, 3120], false, [undefined, undefined, greenShading]),
        ]
      }),

      heading("\u516D\u3001\u4E0B\u4E00\u6B65", HeadingLevel.HEADING_1),
      bullet("P4: \u5B9E\u4F53\u884C\u4E3A\u5B8C\u5584 (128\u4E2A\u5B9E\u4F53\u7684AI/\u653B\u51FB/\u6389\u843D\u903B\u8F91)"),
      bullet("P5: \u9AD8\u7EA7\u914D\u65B9\u8865\u5168 (\u6838\u71C3\u6599\u5FAA\u73AF/\u9AD8\u7EA7\u5408\u91D1)"),
      bullet("P1: \u6838\u5FC3\u73A9\u6CD5\u5FAA\u73AF\u6D4B\u8BD5 (\u751F\u5B58\u6A21\u5F0F\u5B9E\u6D4B)"),
    ]
  }]
});

Packer.toBuffer(doc).then(buffer => {
  fs.writeFileSync("D:\\用户文件\\文档\\HBM1.21.1 Pro\\HBM-NeoForge\\tools\\HBM模组移植项目报告_渲染系统迁移版.docx", buffer);
  console.log("Report v3 created!");
});
