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

function heading(text, level) {
  return new Paragraph({
    heading: level,
    children: [new TextRun(text)]
  });
}

function bullet(text) {
  return new Paragraph({
    numbering: { reference: "bullets", level: 0 },
    children: [new TextRun(text)]
  });
}

function numberItem(text) {
  return new Paragraph({
    numbering: { reference: "numbers", level: 0 },
    children: [new TextRun(text)]
  });
}

function makeCell(text, width, isHeader) {
  return new TableCell({
    borders,
    width: { size: width, type: WidthType.DXA },
    shading: isHeader ? headerShading : undefined,
    margins: { top: 80, bottom: 80, left: 120, right: 120 },
    verticalAlign: VerticalAlign.CENTER,
    children: [new Paragraph({ children: [new TextRun({ text, bold: isHeader })] })]
  });
}

function makeRow(cells, widths, isHeader) {
  return new TableRow({
    cantSplit: true,
    children: cells.map((c, i) => makeCell(c, widths[i], isHeader))
  });
}

const doc = new Document({
  styles: {
    default: {
      document: {
        run: {
          font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" },
          size: 24
        }
      }
    },
    paragraphStyles: [
      {
        id: "Heading1", name: "Heading 1", basedOn: "Normal", next: "Normal", quickFormat: true,
        run: { size: 36, bold: true, font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } },
        paragraph: { spacing: { before: 360, after: 200 }, outlineLevel: 0, keepNext: false, keepLines: false }
      },
      {
        id: "Heading2", name: "Heading 2", basedOn: "Normal", next: "Normal", quickFormat: true,
        run: { size: 30, bold: true, font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } },
        paragraph: { spacing: { before: 280, after: 160 }, outlineLevel: 1, keepNext: false, keepLines: false }
      },
      {
        id: "Heading3", name: "Heading 3", basedOn: "Normal", next: "Normal", quickFormat: true,
        run: { size: 26, bold: true, font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } },
        paragraph: { spacing: { before: 200, after: 120 }, outlineLevel: 2, keepNext: false, keepLines: false }
      }
    ]
  },
  numbering: {
    config: [
      {
        reference: "bullets",
        levels: [{
          level: 0, format: LevelFormat.BULLET, text: "\u2022",
          alignment: AlignmentType.LEFT,
          style: { paragraph: { indent: { left: 720, hanging: 360 } } }
        }]
      },
      {
        reference: "numbers",
        levels: [{
          level: 0, format: LevelFormat.DECIMAL, text: "%1.",
          alignment: AlignmentType.LEFT,
          style: { paragraph: { indent: { left: 720, hanging: 360 } } }
        }]
      }
    ]
  },
  sections: [{
    properties: {
      page: {
        size: { width: 12240, height: 15840 },
        margin: { top: 1440, right: 1440, bottom: 1440, left: 1440 }
      }
    },
    headers: {
      default: new Header({
        children: [new Paragraph({
          alignment: AlignmentType.RIGHT,
          children: [new TextRun({ text: "HBM Mod Migration Report", size: 18, color: "999999" })]
        })]
      })
    },
    footers: {
      default: new Footer({
        children: [new Paragraph({
          alignment: AlignmentType.CENTER,
          children: [
            new TextRun({ text: "Page ", size: 18 }),
            new TextRun({ children: [PageNumber.CURRENT], size: 18 }),
            new TextRun({ text: " / ", size: 18 }),
            new TextRun({ children: [PageNumber.TOTAL_PAGES], size: 18 })
          ]
        })]
      })
    },
    children: [
      new Paragraph({
        alignment: AlignmentType.CENTER,
        spacing: { before: 1200, after: 400 },
        children: [new TextRun({ text: "HBM\u6A21\u7EC4\u79FB\u690D\u9879\u76EE\u5B8C\u6210\u62A5\u544A", size: 52, bold: true, font: { ascii: "Arial", hAnsi: "Arial", eastAsia: "Microsoft YaHei" } })]
      }),
      new Paragraph({
        alignment: AlignmentType.CENTER,
        spacing: { after: 600 },
        children: [new TextRun({ text: "HBM's Nuclear Tech Mod Migration Report", size: 28, color: "666666" })]
      }),
      new Paragraph({ children: [new TextRun("")] }),

      heading("\u4E00\u3001\u9879\u76EE\u6982\u8FF0", HeadingLevel.HEADING_1),
      new Paragraph({
        children: [new TextRun("\u5C06HBM's Nuclear Tech\u6A21\u7EC4\u4ECE1.12.2 CE\u7248\u672C\u79FB\u690D\u52301.21.1 NeoForge\u7248\u672C\u3002")]
      }),

      heading("\u4E8C\u3001\u5DF2\u5B8C\u6210\u6A21\u5757", HeadingLevel.HEADING_1),

      heading("1. \u7269\u54C1\u4E0E\u65B9\u5757\u6CE8\u518C", HeadingLevel.HEADING_2),
      new Table({
        width: { size: 100, type: WidthType.PERCENTAGE },
        columnWidths: [4680, 4680],
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
      bullet("31\u4E2AMob\u5B9E\u4F53\u6CE8\u518C\u4E86\u5C5E\u6027(MAX_HEALTH/MOVEMENT_SPEED/ATTACK_DAMAGE)"),

      heading("6. \u4E16\u754C\u751F\u6210\u7CFB\u7EDF", HeadingLevel.HEADING_2),
      bullet("171\u4E2AJSON\u914D\u7F6E\u6587\u4EF6"),
      bullet("57\u7C7B\u77FF\u77F3\u751F\u6210\u914D\u7F6E"),
      bullet("\u4E3B\u4E16\u754C\u77FF\u77F3: 18\u79CD (\u94E8\u77FF\u3001\u9488\u77FF\u3001\u9492\u77FF\u7B49)"),
      bullet("\u96C6\u7FA4\u77FF\u77F3: 4\u79CD"),
      bullet("\u4E0B\u754C\u77FF\u77F3: 8\u79CD"),
      bullet("\u672B\u7AEF\u77FF\u77F3: 1\u79CD"),
      bullet("\u7247\u9EBB\u5CA9\u77FF\u77F3: 9\u79CD"),
      bullet("\u6DF1\u5C42\u77FF\u77F3: 8\u79CD"),
      bullet("\u77F3\u6CB9/\u57FA\u5CA9\u77FF\u5E8A: 9\u79CD"),

      heading("7. \u672C\u8F6E\u4FEE\u590D\u5185\u5BB9", HeadingLevel.HEADING_2),
      bullet("\u5B9E\u4F53\u5C5E\u6027\u6CE8\u518C: 31\u4E2AMob\u5B9E\u4F53\u6DFB\u52A0\u4E86Attributes"),
      bullet("\u5899\u4F53blockstate\u4FEE\u590D: 7\u4E2A\u5899\u4F53\u6587\u4EF6\u66F4\u65B0\u4E3A1.21.1\u683C\u5F0F"),
      bullet("\u7740\u8272\u5668\u8DEF\u5F84\u4FEE\u590D: 4\u4E2A\u6587\u4EF6\u91CD\u547D\u540D\u4E3A\u5168\u5C0F\u5199"),
      bullet("\u8BED\u8A00\u6587\u4EF6\u8865\u5168: \u4ECE3273\u6761\u6269\u5C55\u523011713\u6761"),
      bullet("Fluids.init() NPE\u4FEE\u590D: \u6DFB\u52A0\u76EE\u5F55\u81EA\u52A8\u521B\u5EFA\u548Ctry-catch"),

      heading("\u4E09\u3001\u9A8C\u8BC1\u7ED3\u679C", HeadingLevel.HEADING_1),
      new Table({
        width: { size: 100, type: WidthType.PERCENTAGE },
        columnWidths: [4680, 4680],
        rows: [
          makeRow(["\u9A8C\u8BC1\u9879\u76EE", "\u7ED3\u679C"], [4680, 4680], true),
          makeRow(["\u7F16\u8BD1", "BUILD SUCCESSFUL"], [4680, 4680], false),
          makeRow(["DataGen", "611\u4E2A\u6587\u4EF6\u6210\u529F\u751F\u6210"], [4680, 4680], false),
          makeRow(["runClient", "\u65E0\u5D29\u6E83\uFF0C\u65E0\"has no attributes\"\u9519\u8BEF"], [4680, 4680], false),
          makeRow(["\u5D29\u6E83\u62A5\u544A", "0\u4E2A"], [4680, 4680], false)
        ]
      }),

      heading("\u56DB\u3001\u6280\u672F\u6808", HeadingLevel.HEADING_1),
      new Table({
        width: { size: 100, type: WidthType.PERCENTAGE },
        columnWidths: [4680, 4680],
        rows: [
          makeRow(["\u9879\u76EE", "\u8BE6\u60C5"], [4680, 4680], true),
          makeRow(["\u6E90\u7248\u672C", "Minecraft 1.12.2 (HBM CE 2.5.0.6)"], [4680, 4680], false),
          makeRow(["\u76EE\u6807\u7248\u672C", "Minecraft 1.21.1 NeoForge 21.1.128"], [4680, 4680], false),
          makeRow(["\u6784\u5EFA\u5DE5\u5177", "Gradle 9.2.1"], [4680, 4680], false),
          makeRow(["JDK", "Azul Systems OpenJDK 21.0.11"], [4680, 4680], false)
        ]
      })
    ]
  }]
});

Packer.toBuffer(doc).then(buffer => {
  fs.writeFileSync("D:\\用户文件\\文档\\HBM1.21.1 Pro\\HBM-NeoForge\\tools\\HBM模组移植项目完成报告.docx", buffer);
  console.log("Word document created successfully!");
});
