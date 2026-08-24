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

function heading(text, level) {
  return new Paragraph({ heading: level, children: [new TextRun(text)] });
}
function bullet(text) {
  return new Paragraph({ numbering: { reference: "bullets", level: 0 }, children: [new TextRun(text)] });
}
function makeCell(text, width, isHeader) {
  return new TableCell({
    borders, width: { size: width, type: WidthType.DXA },
    shading: isHeader ? headerShading : undefined,
    margins: { top: 80, bottom: 80, left: 120, right: 120 },
    verticalAlign: VerticalAlign.CENTER,
    children: [new Paragraph({ children: [new TextRun({ text, bold: isHeader })] })]
  });
}
function makeRow(cells, widths, isHeader) {
  return new TableRow({ cantSplit: true, children: cells.map((c, i) => makeCell(c, widths[i], isHeader)) });
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
    ]
  },
  numbering: {
    config: [
      { reference: "bullets", levels: [{ level: 0, format: LevelFormat.BULLET, text: "\u2022", alignment: AlignmentType.LEFT,
        style: { paragraph: { indent: { left: 720, hanging: 360 } } } }] },
    ]
  },
  sections: [{
    properties: {
      page: { size: { width: 12240, height: 15840 }, margin: { top: 1440, right: 1440, bottom: 1440, left: 1440 } }
    },
    headers: {
      default: new Header({ children: [new Paragraph({ alignment: AlignmentType.RIGHT,
        children: [new TextRun({ text: "HBM Mod Migration Report (Regular)", size: 18, color: "999999" })] })] })
    },
    footers: {
      default: new Footer({ children: [new Paragraph({ alignment: AlignmentType.CENTER,
        children: [new TextRun({ text: "Page ", size: 18 }), new TextRun({ children: [PageNumber.CURRENT], size: 18 }),
          new TextRun({ text: " / ", size: 18 }), new TextRun({ children: [PageNumber.TOTAL_PAGES], size: 18 })] })] })
    },
    children: [
      new Paragraph({
        alignment: AlignmentType.CENTER, spacing: { before: 1200, after: 400 },
        children: [new TextRun({ text: "HBM\u6A21\u7EC4\u79FB\u690D\u9879\u76EE\u62A5\u544A", size: 52, bold: true, font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } })]
      }),
      new Paragraph({
        alignment: AlignmentType.CENTER, spacing: { after: 600 },
        children: [new TextRun({ text: "HBM's Nuclear Tech Mod Migration Report", size: 28, color: "666666" })]
      }),

      heading("\u4E00\u3001\u9879\u76EE\u6982\u8FF0", HeadingLevel.HEADING_1),
      new Paragraph({ children: [new TextRun("\u5C06HBM's Nuclear Tech\u6A21\u7EC4\u4ECE1.12.2 CE\u7248\u672C\u79FB\u690D\u52301.21.1 NeoForge\u7248\u672C\u3002")] }),

      heading("\u4E8C\u3001\u5DF2\u5B8C\u6210\u6A21\u5757", HeadingLevel.HEADING_1),

      heading("1. \u7269\u54C1\u4E0E\u65B9\u5757\u6CE8\u518C", HeadingLevel.HEADING_2),
      new Table({
        width: { size: 100, type: WidthType.PERCENTAGE }, columnWidths: [4680, 4680],
        rows: [
          makeRow(["\u9879\u76EE", "\u6570\u91CF"], [4680, 4680], true),
          makeRow(["\u7269\u54C1\u6CE8\u518C", "1983\u4E2A"], [4680, 4680], false),
          makeRow(["\u65B9\u5757\u6CE8\u518C", "1125\u4E2A"], [4680, 4680], false),
          makeRow(["Blockstate\u6587\u4EF6", "1125\u4E2A (0\u7F3A\u5931)"], [4680, 4680], false),
          makeRow(["\u65B9\u5757\u6A21\u578B", "1172\u4E2A"], [4680, 4680], false),
          makeRow(["\u7269\u54C1\u6A21\u578B", "3104\u4E2A (0\u7F3A\u5931)"], [4680, 4680], false)
        ]
      }),

      heading("2. \u5DE5\u5177\u4E0E\u914D\u65B9\u7CFB\u7EDF", HeadingLevel.HEADING_2),
      bullet("\u5DE5\u5177\u7C7B\u7269\u54C1: 20\u4E2A"),
      bullet("\u914D\u65B9(DataGen): 611\u4E2AJSON\u6587\u4EF6"),
      bullet("\u81EA\u5B9A\u4E49\u914D\u65B9: Anvil(54\u4E2A) + Assembly(101\u4E2A) + ArcFurnace(52\u4E2A)"),

      heading("3. \u673A\u5668\u7CFB\u7EDF", HeadingLevel.HEADING_2),
      bullet("13\u53F0\u673A\u5668\u5B8C\u6574\u4E09\u4EF6\u5957(TE+Container+Block+Menu+GUI)"),
      bullet("\u5305\u62EC: FluidTank, RBMKConsole, Compressor, ChemicalReactor, ArcFurnace, Centrifuge, Crusher, FluidReactor, Assembler, RBMKReactor, HeatExchanger, ParticleAccelerator, Laser"),

      heading("4. \u8D44\u6E90\u6587\u4EF6", HeadingLevel.HEADING_2),
      bullet("\u7EB9\u7406\u6587\u4EF6: 6755\u4E2A"),
      bullet("\u58F0\u97F3\u6587\u4EF6: 548\u4E2A"),
      bullet("\u8BED\u8A00\u6761\u76EE: 11713\u6761"),

      heading("5. \u5B9E\u4F53\u7CFB\u7EDF", HeadingLevel.HEADING_2),
      bullet("\u5B9E\u4F53\u6CE8\u518C: 128\u4E2A\u5B9E\u4F53\u7C7B"),
      bullet("31\u4E2AMob\u5B9E\u4F53\u6CE8\u518C\u4E86\u5C5E\u6027"),

      heading("6. \u4E16\u754C\u751F\u6210\u7CFB\u7EDF", HeadingLevel.HEADING_2),
      bullet("171\u4E2AJSON\u914D\u7F6E\u6587\u4EF6"),
      bullet("57\u7C7B\u77FF\u77F3\u751F\u6210\u914D\u7F6E"),
      bullet("\u5305\u62EC\u4E3B\u4E16\u754C\u3001\u4E0B\u754C\u3001\u672B\u7AEF\u3001\u6DF1\u5C42\u77FF\u77F3\u7B49")
    ]
  }]
});

Packer.toBuffer(doc).then(buffer => {
  fs.writeFileSync("D:\\用户文件\\文档\\HBM1.21.1 Pro\\HBM-NeoForge\\tools\\HBM模组移植项目报告_普通版.docx", buffer);
  console.log("Regular report created!");
});
