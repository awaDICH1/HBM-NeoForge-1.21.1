#!/usr/bin/env python3
"""
gen_config.py — HBM CE cfg 配置类 → ModConfigSpec 代码生成器（P3-5 工具）

用法:
    python tools/gen_config.py <CE 配置类.java> [更多.java...]

功能:
    解析 1.12.2 配置类的 loadFromConfig(Configuration) 主体，生成:
      - private static ModConfigSpec.XxxValue <KEY>;  字段声明
      - build(ModConfigSpec.Builder) 方法体（CommonConfig.configXxx 调用，键名与原 .cfg 一致）
      - load() 方法体（SpecValue.get() → 静态字段，保留 setDef/setDefZero 与 if 联动逻辑）
    输出可直接粘贴到已复制字段的迁移类中；无法解析的行标注 // TODO。

支持的模式:
    X = config.get(CAT, "key", def).getBoolean(true);
    X = config.get(CAT, "key", def, "comment").getBoolean(true);
    Property p = config.get(CAT, "key", def); p.setComment("..."); X = p.getXxx();
    X = CommonConfig.createConfigBool/Int/Double/String(config, CAT, "key", "comment", def);
    X = CommonConfig.setDef(...) / setDefZero(...)   → 保留在 load()
    其他语句（if 联动、addCustomCategoryComment 等）→ 保留在 load()（addCustomCategoryComment 跳过并 TODO）
"""
import re
import sys
from pathlib import Path

# 行级模式
ASSIGN = re.compile(r'^\s*public\s+static\s+\w[\w.<>\[\]]*\s+(\w+)\s*=\s*(.+?);?\s*$')
GET_BOOL = re.compile(r'config\.get\(\s*([A-Za-z_][\w]*)\s*,\s*"([^"]+)"\s*,\s*(.+?)(?:,\s*"([^"]*)")?\s*\)\.getBoolean\(\)')
GET_INT = re.compile(r'config\.get\(\s*([A-Za-z_][\w]*)\s*,\s*"([^"]+)"\s*,\s*(.+?)(?:,\s*"([^"]*)")?\s*\)\.getInt\(\)')
GET_DOUBLE = re.compile(r'config\.get\(\s*([A-Za-z_][\w]*)\s*,\s*"([^"]+)"\s*,\s*(.+?)(?:,\s*"([^"]*)")?\s*\)\.getDouble\(\)')
GET_STRING = re.compile(r'config\.get\(\s*([A-Za-z_][\w]*)\s*,\s*"([^"]+)"\s*,\s*(.+?)(?:,\s*"([^"]*)")?\s*\)\.getString\(\)')
CREATE = re.compile(r'CommonConfig\.createConfig(\w+)\(\s*config\s*,\s*([A-Za-z_][\w]*)\s*,\s*"([^"]+)"\s*,\s*"([^"]*)"\s*,\s*(.+?)\)\s*;')
PROP_PAT = re.compile(r'Property\s+(\w+)\s*=\s*config\.get\(\s*([A-Za-z_][\w]*)\s*,\s*"([^"]+)"\s*,\s*(.+?)\s*\)\s*;')
COMMENT_PAT = re.compile(r'^\s*(\w+)\.setComment\("((?:[^"\\]|\\.)*)"\)\s*;')
GETTER_PAT = re.compile(r'^\s*(\w+)\s*=\s*\w+\.get(\w+)\(\s*(?:true|false|\d+)?\s*\)\s*;')


def to_key(field: str) -> str:
    """camelCase → UPPER_SNAKE"""
    s = re.sub(r'(?<=[a-z0-9])(?=[A-Z])', '_', field)
    return s.upper()


def camel_type(getter: str) -> str:
    return {'Boolean': 'BooleanValue', 'Int': 'IntValue', 'Double': 'DoubleValue', 'String': 'StringValue'}.get(getter, getter + 'Value')


def process(path: str):
    text = Path(path).read_text(encoding='utf-8')
    lines = text.splitlines()

    # 提取 loadFromConfig 方法体（括号平衡）
    start = next(i for i, l in enumerate(lines) if 'loadFromConfig' in l)
    depth = 0
    body = []
    started = False
    for l in lines[start:]:
        if not started:
            if '{' in l:
                started = True
                depth += l.count('{') - l.count('}')
            continue
        if '{' in l or '}' in l:
            depth += l.count('{') - l.count('}')
        if depth <= 0:
            break
        body.append(l)

    fields = []
    build_lines = []
    load_lines = []
    pending_comment = None  # 处理 Property + setComment 两行模式

    for raw in body:
        line = raw.strip()
        if not line:
            continue

        m = CREATE.match(line)
        if m:
            kind, cat, key, comment, default = m.groups()
            spec_field = to_key(line.split('=')[0].split()[-1])
            fields.append(f"    private static ModConfigSpec.{camel_type(kind)} {spec_field};")
            build_lines.append(
                f"        {spec_field} = CommonConfig.config{kind}(builder, CommonConfig.{cat}, \"{key}\", \"{comment}\", {default});")
            load_lines.append(f"        {line.split('=')[0].strip()} = {spec_field}.get();")
            continue

        m = GET_BOOL.search(line) or GET_INT.search(line) or GET_DOUBLE.search(line) or GET_STRING.search(line)
        if m:
            cat, key, default, comment = m.groups()
            getter = 'Boolean' if GET_BOOL.search(line) else ('Int' if GET_INT.search(line) else ('Double' if GET_DOUBLE.search(line) else 'String'))
            var = line.split('=')[0].strip()
            spec_field = to_key(var)
            fields.append(f"    private static ModConfigSpec.{camel_type(getter)} {spec_field};")
            cmt = comment or ''
            build_lines.append(
                f"        {spec_field} = CommonConfig.config{getter}(builder, CommonConfig.{cat}, \"{key}\", \"{cmt}\", {default});")
            load_lines.append(f"        {var} = {spec_field}.get();")
            continue

        m = PROP_PAT.match(line)
        if m:
            pvar, cat, key, default = m.groups()
            # 下一行通常是 setComment；预取
            idx = body.index(raw)
            comment = ''
            if idx + 1 < len(body):
                cm = COMMENT_PAT.match(body[idx + 1].strip())
                if cm and cm.group(1) == pvar:
                    comment = cm.group(2)
            # 之后通常是 X = pvar.getXxx();
            getter = 'Int'
            for j in range(idx + 1, min(idx + 4, len(body))):
                g = GETTER_PAT.match(body[j].strip())
                if g and g.group(1) == pvar.split('=')[0].strip() or (g and g.group(1)):
                    # 形如 'rain = radRain.getInt();' —— var 在赋值行
                    pass
            # 简化：属性行之后寻找 'X = pvar.getXxx();'
            var, getter = None, 'Int'
            for j in range(idx + 1, min(idx + 4, len(body))):
                gm = re.match(r'^\s*(\w+)\s*=\s*' + re.escape(pvar) + r'\.get(\w+)\(\s*(\d+)?\s*\)\s*;', body[j].strip())
                if gm:
                    var, getter, _ = gm.groups()
                    break
            if var is None:
                build_lines.append(f"        // TODO: 未解析的 Property 块: {line}")
                continue
            spec_field = to_key(var)
            fields.append(f"    private static ModConfigSpec.{camel_type(getter)} {spec_field};")
            build_lines.append(
                f"        {spec_field} = CommonConfig.config{getter}(builder, CommonConfig.{cat}, \"{key}\", \"{comment}\", {default});")
            load_lines.append(f"        {var} = {spec_field}.get();")
            continue

        if 'addCustomCategoryComment' in line:
            load_lines.append("        // TODO: 原 addCustomCategoryComment（类别注释）——ModConfigSpec 不支持，可忽略或并入类别首个键的注释")
            continue
        if 'config.' in line:
            load_lines.append(f"        // TODO: 未解析的 config 调用: {line}")
            continue

        load_lines.append(f"        {line}")

    return fields, build_lines, load_lines


def main():
    if len(sys.argv) < 2:
        print(__doc__)
        sys.exit(1)
    for path in sys.argv[1:]:
        try:
            fields, build_lines, load_lines = process(path)
        except Exception as e:
            print(f"## {path} — 解析失败: {e}", file=sys.stderr)
            continue
        name = Path(path).stem
        print(f"// ===== {name}.java 生成代码（粘贴到已复制字段的类中） =====")
        print("// --- SpecValue 字段声明 ---")
        print("\n".join(fields))
        print("\n// --- build(ModConfigSpec.Builder builder) ---")
        print("    public static void build(ModConfigSpec.Builder builder) {")
        print("\n".join(build_lines))
        print("    }")
        print("\n// --- load() ---")
        print("    public static void load() {")
        print("\n".join(load_lines))
        print("    }")
        print()


if __name__ == '__main__':
    main()
