#!/usr/bin/env python3
"""
gen_registries.py — HBM CE ModBlocks/ModItems → NeoForge DeferredRegister 代码生成器（P3 批量脚手架）

用法:
    python tools/gen_registries.py <CE ModBlocks.java 路径> [--items <CE ModItems.java 路径>]

输出:
    tools/generated/modblocks.txt   可复制进 HBM-NeoForge ModBlocks.java 的 DeferredBlock 代码块
    tools/generated/moditems.txt    可复制进 HBM-NeoForge ModItems.java 的 DeferredItem 代码块
    tools/generated/tabs.txt        按创造栏分组的字段清单（供 ModCreativeTabs.displayItems 使用）

规则:
    - 注册名 = 构造参数中最后一个字符串字面量（CE 约定，保证与原版完全一致）
    - setHardness/setResistance/setSoundType/setLightLevel/setLightOpacity/setCreativeTab 被解析
    - Material→Properties 的默认硬度、特殊构造参数（speed/BlockRenderLayer/fluid 引用等）无法自动
      转换，输出中标注 TODO，需按方块类逐个人工核对
"""
import re
import sys
import collections
from pathlib import Path

STRING_RE = re.compile(r'"((?:[^"\\]|\\.)*)"')
FLOAT_RE = re.compile(r'([0-9]+(?:\.[0-9]+)?)F?')


def find_matching_paren(s: str, start: int) -> int:
    """从 s[start]（必须是 '('）找配对 ')' 的下标"""
    depth = 0
    for i in range(start, len(s)):
        c = s[i]
        if c == '(':
            depth += 1
        elif c == ')':
            depth -= 1
            if depth == 0:
                return i
    raise ValueError("unbalanced parentheses")


def extract_ctor(text: str):
    """从 'new ClassName(...)' 提取 (类名, 参数内部文本)"""
    m = re.match(r'new\s+([A-Za-z0-9_$.]+)\s*\(', text)
    if not m:
        return None, None
    cls = m.group(1)
    end = find_matching_paren(text, m.end() - 1)
    return cls, text[m.end():end]


def parse_decl(line: str, kind: str):
    """
    解析 'public static final Block|Item NAME = new ...;' 声明。
    kind: 'Block' 或 'Item'
    """
    m = re.match(rf'public static final {kind}\s+(\w+)\s*=\s*(.+)$', line)
    if not m:
        return None
    field = m.group(1)
    rest = m.group(2).rstrip(';').strip()
    # 分离构造器与 setter 链（'new X(...)' 可能含嵌套括号）
    ctor_start = rest.index('(')
    ctor_end = find_matching_paren(rest, ctor_start)
    cls, args = extract_ctor(rest[:ctor_end + 1])
    if cls is None:
        return None
    chain = rest[ctor_end + 1:]

    strings = [s for s in STRING_RE.findall(args)]
    reg_name = strings[-1] if strings else None

    mat = None
    mm = re.match(r'\s*Material\.(\w+)', args)
    if mm:
        mat = mm.group(1)

    setters = {}
    for sm in re.finditer(r'\.set(\w+)\((.*?)\)', chain):
        setters[sm.group(1)] = sm.group(2).strip()

    tab = None
    if 'CreativeTab' in setters:
        tm = re.match(r'MainRegistry\.(\w+)', setters['CreativeTab'])
        tab = tm.group(1) if tm else (setters['CreativeTab'] or 'null')

    return {'field': field, 'cls': cls, 'reg': reg_name, 'material': mat,
            'setters': setters, 'tab': tab, 'orig': line.strip()}


def props_code(blk: dict) -> list:
    """生成 BlockBehaviour.Properties 链片段（带 TODO）"""
    s = blk['setters']
    parts = []
    if 'BlockUnbreakable' in s:
        parts.append('.strength(-1.0F, 6000000.0F) // TODO: 原 setBlockUnbreakable')
    elif 'Hardness' in s or 'Resistance' in s:
        h, r = s.get('Hardness'), s.get('Resistance')
        if h and r:
            parts.append(f".strength({h}, {r})")
        elif h:
            parts.append(f".strength({h})")
        else:
            parts.append(f".strength(1.5F, {r}) // TODO: 仅设抗爆，硬度按 1.12 Material 默认近似")
    else:
        parts.append(f".strength(1.0F) // TODO: 原 Material.{blk['material'] or '?'} 默认硬度需核对")
    if 'SoundType' in s:
        sm = re.search(r'SoundType\.(\w+)', s['SoundType'])
        if sm:
            parts.append(f".sound(SoundType.{sm.group(1)})")
    if 'LightLevel' in s:
        lm = FLOAT_RE.search(s['LightLevel'])
        if lm:
            parts.append(f".lightLevel(s -> {round(float(lm.group(1)) * 15)}) // TODO: 原 setLightLevel({s['LightLevel']})")
    if 'LightOpacity' in s and '0' in s['LightOpacity']:
        parts.append('.noOcclusion() // TODO: 原 setLightOpacity(0)')
    return parts


def emit_block(blk: dict) -> str:
    field = blk['field'].upper()
    reg = blk['reg'] or f'<{blk["field"]}?>'
    p = props_code(blk)
    lines = [
        f"// 原: {blk['orig']}",
        f"// TAB: {blk['tab'] or 'null'}",
        f"public static final DeferredBlock<Block> {field} = BLOCKS.register(\"{reg}\",",
        f"        () -> new {blk['cls']}(BlockBehaviour.Properties.of()",
    ]
    lines += [f"                {x}" for x in p]
    lines += [
        "        ));",
        f"public static final DeferredItem<BlockItem> {field}_ITEM = ITEMS.registerSimpleBlockItem({field});",
        "",
    ]
    return "\n".join(lines)


def emit_item(it: dict) -> str:
    field = it['field'].upper()
    reg = it['reg'] or f'<{it["field"]}?>'
    lines = [
        f"// 原: {it['orig']}",
        f"// TAB: {it['tab'] or 'null'}",
        f"public static final DeferredItem<Item> {field} = ITEMS.register(\"{reg}\",",
        f"        () -> new {it['cls']}(new Item.Properties())); // TODO: 自定义 Properties/能力包装需人工核对",
        "",
    ]
    return "\n".join(lines)


def process_file(path: str, kind: str, out: list, tabs: dict, header: str):
    text = Path(path).read_text(encoding='utf-8')
    out.append(header)
    out.append("// ===== 由 tools/gen_registries.py 生成；TODO 标注需人工核对 =====")
    count = 0
    for line in text.splitlines():
        line = line.strip()
        if not line.startswith(f'public static final {kind} '):
            continue
        try:
            d = parse_decl(line, kind)
        except Exception:
            d = None
        if d is None:
            continue
        count += 1
        out.append(emit_block(d) if kind == 'Block' else emit_item(d))
        tabs.setdefault(d['tab'] or 'null', []).append(d['field'].upper())
    out.append(f"// 共 {count} 条")
    out.append("")


def main():
    if len(sys.argv) < 2:
        print(__doc__)
        sys.exit(1)
    modblocks = sys.argv[1]
    items = None
    if '--items' in sys.argv:
        items = sys.argv[sys.argv.index('--items') + 1]

    gen = Path(__file__).resolve().parent / 'generated'
    gen.mkdir(exist_ok=True)

    out = []
    tabs = collections.defaultdict(list)
    process_file(modblocks, 'Block', out, tabs, "// ===== ModBlocks.java 生成代码 =====")
    (gen / 'modblocks.txt').write_text("\n".join(out), encoding='utf-8')

    out = []
    if items:
        process_file(items, 'Item', out, tabs, "// ===== ModItems.java 生成代码 =====")
        (gen / 'moditems.txt').write_text("\n".join(out), encoding='utf-8')

    tab_lines = ["// 创造栏分组（供 ModCreativeTabs.displayItems 使用）", ""]
    for tab in sorted(tabs):
        tab_lines.append(f"--- {tab} ---")
        tab_lines.append(", ".join(tabs[tab]))
        tab_lines.append("")
    (gen / 'tabs.txt').write_text("\n".join(tab_lines), encoding='utf-8')

    print(f"已生成 tools/generated/：modblocks.txt ({'Block'}: {sum(1 for _ in open(gen/'modblocks.txt'))} 行), "
          f"tabs.txt（{len(tabs)} 个创造栏分组）")
    if items:
        print(f"  moditems.txt 已生成")


if __name__ == '__main__':
    main()
