#!/usr/bin/env python3
"""
Parse CE (1.12.2) and NeoForge ModItems.java files,
find missing items, and generate NeoForge DeferredRegister code.
"""

import re
import os

CE_FILE = r"D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\java\com\hbm\items\ModItems.java"
NEOFORGE_FILE = r"D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java"
OUTPUT_FILE = r"D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\tools\generated\missing_items.txt"

def parse_ce_items(filepath):
    """
    Extract (variable_name, registry_name) pairs from the CE ModItems.java.
    Pattern: [annotations] public static [final] <Type> <var_name> = new <ClassName>(... "registry_name" ...)
    The registry name is the first quoted string matching ^[a-z0-9_]+$ on the SAME line.
    """
    items = []  # list of (var_name, registry_name)
    seen_registries = set()

    with open(filepath, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    # Pattern: optional @Annotations, then public static [final] Type var_name = new ClassName
    # Handles: @Deprecated public static final Item xxx = new SomeClass(...)
    # Also handles: public static final Item xxx = new SomeClass(...)
    # Also handles: public static ToolMaterial xxx = EnumHelper... (won't match - no 'new')
    psf_pattern = re.compile(
        r'^\s*(?:@\w+\s+)*public\s+static\s+(?:final\s+)?\S+\s+(\w+)\s*=\s*new\s+\w+'
    )

    for i, line in enumerate(lines):
        m = psf_pattern.match(line)
        if not m:
            continue

        var_name = m.group(1)

        # Skip non-item collections and fields
        if var_name in ('ALL_ITEMS', 'excludeNEI', 'enumToolMaterialElecTerra'):
            continue

        # Find all quoted strings ONLY on this same line
        strings = re.findall(r'"([^"]*)"', line)

        if not strings:
            # No string on this line - skip (could be a non-item field)
            continue

        # The registry name is the first string that looks like a registry name
        # (lowercase letters, digits, underscores only - no colons, dots, slashes)
        registry_name = None
        for s in strings:
            if re.match(r'^[a-z0-9_]+$', s):
                registry_name = s
                break

        if registry_name is None:
            # Fallback: use the first string
            registry_name = strings[0]

        # Skip if we've already seen this registry name (dedup)
        if registry_name in seen_registries:
            continue

        seen_registries.add(registry_name)
        items.append((var_name, registry_name))

    return items


def parse_neoforge_items(filepath):
    """Extract registry names from the NeoForge ModItems.java."""
    registries = set()

    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    pattern = re.compile(r'ITEMS\.register\("([^"]+)"')
    matches = pattern.findall(content)

    for m in matches:
        registries.add(m)

    return registries


def find_missing_items(ce_items, neoforge_registries):
    """Find CE items whose registry name is not in the NeoForge file."""
    missing = []
    for var_name, registry_name in ce_items:
        if registry_name not in neoforge_registries:
            missing.append((var_name, registry_name))
    return missing


def generate_code(missing_items):
    """Generate NeoForge DeferredRegister code for missing items."""
    lines = []
    lines.append("// =====================================================")
    lines.append("// Missing Items: CE (1.12.2) items not yet registered")
    lines.append("// in the NeoForge version of ModItems.java")
    lines.append("// Total missing items: {}".format(len(missing_items)))
    lines.append("// Generated automatically - register as simple Item type")
    lines.append("// =====================================================")
    lines.append("")

    current_section = None

    for var_name, registry_name in missing_items:
        # Convert var_name to UPPER_CASE for the constant name
        constant_name = var_name.upper()

        lines.append("public static final DeferredItem<Item> {} = ITEMS.register(\"{}\",".format(constant_name, registry_name))
        lines.append("        () -> new Item(new Item.Properties()));")
        lines.append("")

    lines.append("// =====================================================")
    lines.append("// End of missing items")
    lines.append("// Total: {} items".format(len(missing_items)))
    lines.append("// =====================================================")

    return "\n".join(lines)


def main():
    print("Parsing CE ModItems.java...")
    ce_items = parse_ce_items(CE_FILE)
    print("  Found {} unique items in CE source".format(len(ce_items)))

    print("Parsing NeoForge ModItems.java...")
    neoforge_registries = parse_neoforge_items(NEOFORGE_FILE)
    print("  Found {} registered items in NeoForge source".format(len(neoforge_registries)))

    print("Finding missing items...")
    missing = find_missing_items(ce_items, neoforge_registries)
    print("  Found {} missing items".format(len(missing)))

    # Print first 20 and last 5 for verification
    print("\nFirst 20 missing items:")
    for var_name, registry_name in missing[:20]:
        print("    {} -> {}".format(var_name, registry_name))

    print("\nLast 5 missing items:")
    for var_name, registry_name in missing[-5:]:
        print("    {} -> {}".format(var_name, registry_name))

    # Check for specific @Deprecated items
    deprecated_names = ['energy_core', 'alloy_helmet', 'alloy_plate', 'alloy_legs', 'alloy_boots',
                        'alloy_sword', 'alloy_pickaxe', 'alloy_axe', 'alloy_shovel', 'alloy_hoe',
                        'defuser_desh']
    print("\n@Deprecated items check:")
    for name in deprecated_names:
        found = [m for m in missing if m[0] == name]
        if found:
            print("  {} -> {} (FOUND in missing)".format(name, found[0][1]))
        else:
            # Check if it's in CE items at all
            ce_found = [c for c in ce_items if c[0] == name]
            if ce_found:
                reg = ce_found[0][1]
                if reg in neoforge_registries:
                    print("  {} -> {} (already registered in NeoForge)".format(name, reg))
                else:
                    print("  {} -> {} (IN CE BUT NOT IN MISSING - BUG!)".format(name, reg))
            else:
                print("  {} (NOT FOUND in CE items - parsing issue)".format(name))

    # Generate output code
    print("\nGenerating output code...")
    code = generate_code(missing)

    # Ensure output directory exists
    output_dir = os.path.dirname(OUTPUT_FILE)
    if output_dir and not os.path.exists(output_dir):
        os.makedirs(output_dir)

    with open(OUTPUT_FILE, 'w', encoding='utf-8') as f:
        f.write(code)

    print("  Written to: {}".format(OUTPUT_FILE))
    print("  Total lines: {}".format(len(code.split('\n'))))
    print("\nDone!")


if __name__ == '__main__':
    main()
