const fs = require("fs");
const {
  Document, Packer, Paragraph, TextRun, Table, TableRow, TableCell,
  Header, Footer, AlignmentType, LevelFormat, HeadingLevel,
  BorderStyle, WidthType, ShadingType, PageNumber, PageBreak,
  VerticalAlign
} = require("docx");

const border = { style: BorderStyle.SINGLE, size: 1, color: "CCCCCC" };
const borders = { top: border, bottom: border, left: border, right: border };
const headerShading = { fill: "D5E8F0", type: ShadingType.CLEAR };
const greenShading = { fill: "D5F5E3", type: ShadingType.CLEAR };
const yellowShading = { fill: "FCF3CF", type: ShadingType.CLEAR };

function heading(text, level) {
  return new Paragraph({ heading: level, children: [new TextRun(text)] });
}
function bullet(text) {
  return new Paragraph({ numbering: { reference: "bullets", level: 0 }, children: [new TextRun(text)] });
}
function makeCell(text, width, isHeader, shading) {
  return new TableCell({
    borders, width: { size: width, type: WidthType.DXA },
    shading: shading || (isHeader ? headerShading : undefined),
    margins: { top: 80, bottom: 80, left: 120, right: 120 },
    verticalAlign: VerticalAlign.CENTER,
    children: [new Paragraph({ children: [new TextRun({ text, bold: isHeader })] })]
  });
}
function makeRow(cells, widths, isHeader, shadings) {
  return new TableRow({ cantSplit: true, children: cells.map((c, i) => makeCell(c, widths[i], isHeader, shadings ? shadings[i] : undefined)) });
}

const doc = new Document({
  styles: {
    default: {
      document: {
        run: { font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" }, size: 24 }
      }
    },
    paragraphStyles: [
      { id: "Heading1", name: "Heading 1", basedOn: "Normal", next: "Normal", quickFormat: true,
        run: { size: 36, bold: true, font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } },
        paragraph: { spacing: { before: 360, after: 200 }, outlineLevel: 0, keepNext: false, keepLines: false } },
      { id: "Heading2", name: "Heading 2", basedOn: "Normal", next: "Normal", quickFormat: true,
        run: { size: 30, bold: true, font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } },
        paragraph: { spacing: { before: 280, after: 160 }, outlineLevel: 1, keepNext: false, keepLines: false } },
      { id: "Heading3", name: "Heading 3", basedOn: "Normal", next: "Normal", quickFormat: true,
        run: { size: 26, bold: true, font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } },
        paragraph: { spacing: { before: 200, after: 120 }, outlineLevel: 2, keepNext: false, keepLines: false } },
    ]
  },
  numbering: {
    config: [
      { reference: "bullets", levels: [{ level: 0, format: LevelFormat.BULLET, text: "\u2022", alignment: AlignmentType.LEFT,
        style: { paragraph: { indent: { left: 720, hanging: 360 } } } }] },
      { reference: "numbers", levels: [{ level: 0, format: LevelFormat.DECIMAL, text: "%1.", alignment: AlignmentType.LEFT,
        style: { paragraph: { indent: { left: 720, hanging: 360 } } } }] },
    ]
  },
  sections: [{
    properties: {
      page: { size: { width: 12240, height: 15840 }, margin: { top: 1440, right: 1440, bottom: 1440, left: 1440 } }
    },
    headers: {
      default: new Header({ children: [new Paragraph({ alignment: AlignmentType.RIGHT,
        children: [new TextRun({ text: "HBM Mod Migration Report v2", size: 18, color: "999999" })] })] })
    },
    footers: {
      default: new Footer({ children: [new Paragraph({ alignment: AlignmentType.CENTER,
        children: [new TextRun({ text: "Page ", size: 18 }), new TextRun({ children: [PageNumber.CURRENT], size: 18 }),
          new TextRun({ text: " / ", size: 18 }), new TextRun({ children: [PageNumber.TOTAL_PAGES], size: 18 })] })] })
    },
    children: [
      // Title
      new Paragraph({
        alignment: AlignmentType.CENTER, spacing: { before: 1200, after: 400 },
        children: [new TextRun({ text: "HBM\u6A21\u7EC4\u79FB\u690D\u9879\u76EE\u62A5\u544A", size: 52, bold: true, font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } })]
      }),
      new Paragraph({
        alignment: AlignmentType.CENTER, spacing: { after: 200 },
        children: [new TextRun({ text: "TileEntity\u903B\u8F91\u8865\u5168\u7248", size: 32, color: "2980B9", font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } })]
      }),
      new Paragraph({
        alignment: AlignmentType.CENTER, spacing: { after: 600 },
        children: [new TextRun({ text: "2026-08-21 | DeepSeek \u6307\u6325\u4E0B\u7B2C\u4E8C\u8F6E\u5F00\u53D1", size: 24, color: "666666" })]
      }),

      // Section 1: DeepSeek directive
      heading("\u4E00\u3001DeepSeek \u5F00\u53D1\u6307\u4EE4", HeadingLevel.HEADING_1),
      new Paragraph({ children: [new TextRun("DeepSeek \u7ED9\u51FA5\u4E2A\u4F18\u5148\u7EA7\u5EFA\u8BAE\uFF0C\u672C\u8F6E\u6267\u884C\u4F18\u5148\u7EA7 2\uFF1ATileEntity \u5B8C\u6574\u903B\u8F91\u8865\u5168\u3002")] }),
      new Table({
        width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [600, 3000, 5760],
        rows: [
          makeRow(["\u4F18\u5148\u7EA7", "\u4EFB\u52A1", "\u72B6\u6001"], [600, 3000, 5760], true),
          makeRow(["P1", "\u6838\u5FC3\u73A9\u6CD5\u5FAA\u73AF\u6D4B\u8BD5", "\u5F85\u6267\u884C"], [600, 3000, 5760], false, [undefined, undefined, yellowShading]),
          makeRow(["P2", "TileEntity \u5B8C\u6574\u903B\u8F91\u8865\u5168", "\u5DF2\u5B8C\u6210"], [600, 3000, 5760], false, [undefined, undefined, greenShading]),
          makeRow(["P3", "\u6E32\u67D3/\u7C92\u5B50\u7CFB\u7EDF\u8FC1\u79FB", "\u5F85\u6267\u884C"], [600, 3000, 5760], false, [undefined, undefined, yellowShading]),
          makeRow(["P4", "\u5B9E\u4F53\u884C\u4E3A\u5B8C\u5584", "\u5F85\u6267\u884C"], [600, 3000, 5760], false, [undefined, undefined, yellowShading]),
          makeRow(["P5", "\u9AD8\u7EA7\u914D\u65B9\u8865\u5168", "\u5F85\u6267\u884C"], [600, 3000, 5760], false, [undefined, undefined, yellowShading]),
        ]
      }),

      // Section 2: TE Logic Implementation
      heading("\u4E8C\u3001TileEntity \u903B\u8F91\u8865\u5168\u8BE6\u60C5", HeadingLevel.HEADING_1),

      heading("1. \u538B\u7F29\u673A (Compressor)", HeadingLevel.HEADING_2),
      new Table({
        width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [3120, 6240],
        rows: [
          makeRow(["\u9879\u76EE", "\u8BE6\u60C5"], [3120, 6240], true),
          makeRow(["\u69FD\u4F4D\u5E03\u5C40", "3\u69FD (0=\u8F93\u5165, 1=\u7535\u6C60, 2=\u8F93\u51FA)"], [3120, 6240], false),
          makeRow(["\u80FD\u91CF\u7CFB\u7EDF", "maxPower=5000, \u6D88\u8017=5/tick"], [3120, 6240], false),
          makeRow(["\u52A0\u5DE5\u8FDB\u5EA6", "maxProgress=100 ticks"], [3120, 6240], false),
          makeRow(["\u914D\u65B9\u7CFB\u7EDF", "CompressorRecipes (\u65B0\u5EFA) - 16\u4E2A\u914D\u65B9"], [3120, 6240], false),
          makeRow(["\u914D\u65B9\u793A\u4F8B", "\u94C1\u952D \u2192 \u94C1\u677F, \u94A8\u952D \u2192 \u94A8\u677F, \u94C5\u952D \u2192 \u94C5\u677F"], [3120, 6240], false),
          makeRow(["\u7535\u6C60\u5145\u80FD", "\u7EA2\u77F3\u5145\u80FD (+50/tick)"], [3120, 6240], false),
        ]
      }),

      heading("2. \u7535\u5F27\u7194\u7089 (ArcFurnace)", HeadingLevel.HEADING_2),
      new Table({
        width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [3120, 6240],
        rows: [
          makeRow(["\u9879\u76EE", "\u8BE6\u60C5"], [3120, 6240], true),
          makeRow(["\u69FD\u4F4D\u5E03\u5C40", "5\u69FD (0-2=\u8F93\u5165, 3=\u7535\u6C60, 4=\u8F93\u51FA)"], [3120, 6240], false),
          makeRow(["\u80FD\u91CF\u7CFB\u7EDF", "maxPower=20000, \u6D88\u8017=15/tick (\u9AD8\u80FD\u8017)"], [3120, 6240], false),
          makeRow(["\u52A0\u5DE5\u8FDB\u5EA6", "maxProgress=300 ticks"], [3120, 6240], false),
          makeRow(["\u914D\u65B9\u7CFB\u7EDF", "ArcFurnaceRecipes (\u5DF2\u6709) - 52\u4E2A\u914D\u65B9"], [3120, 6240], false),
          makeRow(["\u914D\u65B9\u793A\u4F8B", "\u94C1\u77FF\u77F3 \u2192 2x\u94C1\u952D, \u6C99\u5B50 \u2192 2x\u73BB\u7483"], [3120, 6240], false),
          makeRow(["\u7535\u6C60\u5145\u80FD", "\u7EA2\u77F3\u5145\u80FD (+100/tick)"], [3120, 6240], false),
        ]
      }),

      heading("3. \u5316\u5B66\u53CD\u5E94\u5668 (ChemicalReactor)", HeadingLevel.HEADING_2),
      new Table({
        width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [3120, 6240],
        rows: [
          makeRow(["\u9879\u76EE", "\u8BE6\u60C5"], [3120, 6240], true),
          makeRow(["\u69FD\u4F4D\u5E03\u5C40", "6\u69FD (0-3=\u8F93\u5165, 4=\u7535\u6C60, 5=\u8F93\u51FA)"], [3120, 6240], false),
          makeRow(["\u80FD\u91CF\u7CFB\u7EDF", "maxPower=10000, \u6D88\u8017=8/tick"], [3120, 6240], false),
          makeRow(["\u52A0\u5DE5\u8FDB\u5EA6", "maxProgress=200 ticks"], [3120, 6240], false),
          makeRow(["\u914D\u65B9\u7CFB\u7EDF", "AssemblyMachineRecipes (\u5DF2\u6709) - 101\u4E2A\u914D\u65B9"], [3120, 6240], false),
          makeRow(["\u914D\u65B9\u793A\u4F8B", "\u94A8\u952D+\u94A8\u952D \u2192 3x\u94A8\u5408\u91D1, \u7164+\u7164 \u2192 \u77F3\u58A8"], [3120, 6240], false),
          makeRow(["\u591A\u8F93\u5165\u5339\u914D", "\u652F\u63014\u4E2A\u8F93\u5165\u69FD\u7EC4\u5408\u5339\u914D"], [3120, 6240], false),
        ]
      }),

      heading("4. \u79BB\u5FC3\u673A (Centrifuge)", HeadingLevel.HEADING_2),
      new Table({
        width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [3120, 6240],
        rows: [
          makeRow(["\u9879\u76EE", "\u8BE6\u60C5"], [3120, 6240], true),
          makeRow(["\u69FD\u4F4D\u5E03\u5C40", "4\u69FD (0=\u8F93\u5165, 1=\u8F93\u51FA1, 2=\u8F93\u51FA2, 3=\u7535\u6C60)"], [3120, 6240], false),
          makeRow(["\u80FD\u91CF\u7CFB\u7EDF", "maxPower=8000, \u6D88\u8017=10/tick"], [3120, 6240], false),
          makeRow(["\u52A0\u5DE5\u8FDB\u5EA6", "maxProgress=500 ticks (\u5206\u79BB\u8017\u65F6\u957F)"], [3120, 6240], false),
          makeRow(["\u914D\u65B9\u7CFB\u7EDF", "\u5185\u7F6E\u540C\u4F4D\u7D20\u5206\u79BB\u914D\u65B9 - 3\u7C7B"], [3120, 6240], false),
          makeRow(["\u914D\u65B9\u793A\u4F8B", "\u94C2\u952D \u2192 U235 + U238, \u94B5\u952D \u2192 Pu239 + Pu240"], [3120, 6240], false),
          makeRow(["\u53CC\u8F93\u51FA", "\u8F7B/\u91CD\u4EA7\u7269\u5206\u522B\u8F93\u51FA\u5230\u4E24\u4E2A\u69FD"], [3120, 6240], false),
        ]
      }),

      heading("5. \u7C89\u788E\u673A (Crusher)", HeadingLevel.HEADING_2),
      new Table({
        width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [3120, 6240],
        rows: [
          makeRow(["\u9879\u76EE", "\u8BE6\u60C5"], [3120, 6240], true),
          makeRow(["\u69FD\u4F4D\u5E03\u5C40", "3\u69FD (0=\u8F93\u5165, 1=\u8F93\u51FA, 2=\u7535\u6C60)"], [3120, 6240], false),
          makeRow(["\u80FD\u91CF\u7CFB\u7EDF", "maxPower=3000, \u6D88\u8017=3/tick (\u4F4E\u80FD\u8017)"], [3120, 6240], false),
          makeRow(["\u52A0\u5DE5\u8FDB\u5EA6", "maxProgress=80 ticks (\u7834\u788E\u8F83\u5FEB)"], [3120, 6240], false),
          makeRow(["\u914D\u65B9\u7CFB\u7EDF", "CrusherRecipes (\u65B0\u5EFA) - 19\u4E2A\u914D\u65B9"], [3120, 6240], false),
          makeRow(["\u914D\u65B9\u793A\u4F8B", "\u94C1\u77FF\u77F3 \u2192 2x\u539F\u94C1, \u5706\u77F3 \u2192 \u6C99\u783E"], [3120, 6240], false),
        ]
      }),

      // Section 3: New Recipe Systems
      heading("\u4E09\u3001\u65B0\u5EFA\u914D\u65B9\u7CFB\u7EDF", HeadingLevel.HEADING_1),
      new Table({
        width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [3120, 3120, 3120],
        rows: [
          makeRow(["\u914D\u65B9\u7CFB\u7EDF", "\u914D\u65B9\u6570\u91CF", "\u673A\u5668"], [3120, 3120, 3120], true),
          makeRow(["CompressorRecipes", "16\u4E2A", "\u538B\u7F29\u673A"], [3120, 3120, 3120], false),
          makeRow(["CrusherRecipes", "19\u4E2A", "\u7C89\u788E\u673A"], [3120, 3120, 3120], false),
          makeRow(["ArcFurnaceRecipes", "52\u4E2A", "\u7535\u5F27\u7194\u7089"], [3120, 3120, 3120], false),
          makeRow(["AssemblyMachineRecipes", "101\u4E2A", "\u5316\u5B66\u53CD\u5E94\u5668"], [3120, 3120, 3120], false),
          makeRow(["\u5408\u8BA1", "188\u4E2A\u81EA\u5B9A\u4E49\u914D\u65B9", ""], [3120, 3120, 3120], false),
        ]
      }),

      // Section 4: Verification
      heading("\u56DB\u3001\u9A8C\u8BC1\u7ED3\u679C", HeadingLevel.HEADING_1),
      new Table({
        width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [3120, 3120, 3120],
        rows: [
          makeRow(["\u9A8C\u8BC1\u9879\u76EE", "\u7ED3\u679C", "\u72B6\u6001"], [3120, 3120, 3120], true),
          makeRow(["\u7F16\u8BD1", "BUILD SUCCESSFUL", "\u901A\u8FC7"], [3120, 3120, 3120], false, [undefined, undefined, greenShading]),
          makeRow(["DataGen", "611\u4E2A\u6587\u4EF6\u751F\u6210", "\u901A\u8FC7"], [3120, 3120, 3120], false, [undefined, undefined, greenShading]),
          makeRow(["runClient", "\u6A21\u7EC4\u6210\u529F\u52A0\u8F7D", "\u901A\u8FC7"], [3120, 3120, 3120], false, [undefined, undefined, greenShading]),
          makeRow(["\u914D\u65B9\u6CE8\u518C", "\u5168\u914D\u65B9\u7CFB\u7EDF\u6CE8\u518C\u5B8C\u6210", "\u901A\u8FC7"], [3120, 3120, 3120], false, [undefined, undefined, greenShading]),
          makeRow(["\u5D29\u6E83\u62A5\u544A", "0\u4E2A\u65B0\u5D29\u6E83", "\u901A\u8FC7"], [3120, 3120, 3120], false, [undefined, undefined, greenShading]),
        ]
      }),

      // Section 5: Next Steps
      heading("\u4E94\u3001\u4E0B\u4E00\u6B65\u8BA1\u5212", HeadingLevel.HEADING_1),
      bullet("P3: \u6E32\u67D3/\u7C92\u5B50\u7CFB\u7EDF\u8FC1\u79FB (~580\u4E2A\u6587\u4EF6)"),
      bullet("P4: \u5B9E\u4F53\u884C\u4E3A\u5B8C\u5584 (128\u4E2A\u5B9E\u4F53\u7684AI/\u653B\u51FB/\u6389\u843D\u903B\u8F91)"),
      bullet("P5: \u9AD8\u7EA7\u914D\u65B9\u8865\u5168 (\u6838\u71C3\u6599\u5FAA\u73AF/\u9AD8\u7EA7\u5408\u91D1)"),
      bullet("P1: \u6838\u5FC3\u73A9\u6CD5\u5FAA\u73AF\u6D4B\u8BD5 (\u751F\u5B58\u6A21\u5F0F\u5B9E\u6D4B)"),
    ]
  }]
});

Packer.toBuffer(doc).then(buffer => {
  fs.writeFileSync("D:\\用户文件\\文档\\HBM1.21.1 Pro\\HBM-NeoForge\\tools\\HBM模组移植项目报告_TE逻辑补全版.docx", buffer);
  console.log("Report v2 created!");
});
