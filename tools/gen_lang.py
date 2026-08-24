import json
import re
import os

LANG_PATH = r"D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\resources\assets\hbm\lang\en_us.json"
BLOCKS_PATH = r"D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\blocks\ModBlocks.java"
ITEMS_PATH = r"D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\items\ModItems.java"

# Read existing lang
with open(LANG_PATH, "r", encoding="utf-8") as f:
    lang = json.load(f)

# Extract block names from ModBlocks.java
block_names = set()
with open(BLOCKS_PATH, "r", encoding="utf-8") as f:
    for line in f:
        m = re.search(r'BLOCKS\.register\("([^"]+)"', line)
        if m:
            block_names.add(m.group(1))

# Extract item names from ModItems.java
item_names = set()
with open(ITEMS_PATH, "r", encoding="utf-8") as f:
    for line in f:
        m = re.search(r'ITEMS\.register\("([^"]+)"', line)
        if m:
            item_names.add(m.group(1))

def to_display_name(reg_name):
    """Convert registry name to display name. e.g. 'ore_uranium' -> 'Uranium Ore'"""
    words = reg_name.replace('_', ' ').split()
    return ' '.join(w.capitalize() for w in words)

# Add missing block lang entries
added = 0
for name in sorted(block_names):
    key = f"block.hbm.{name}"
    if key not in lang:
        lang[key] = to_display_name(name)
        added += 1

# Add missing item lang entries
for name in sorted(item_names):
    key = f"item.hbm.{name}"
    if key not in lang:
        lang[key] = to_display_name(name)
        added += 1

# Sort the lang file
lang_sorted = dict(sorted(lang.items()))

# Write back
with open(LANG_PATH, "w", encoding="utf-8") as f:
    json.dump(lang_sorted, f, indent=2, ensure_ascii=False)
    f.write("\n")

print(f"Added {added} lang entries")
print(f"Total lang entries: {len(lang_sorted)}")

# Also add entity lang entries
entity_names = set()
entities_path = r"D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\entity\ModEntities.java"
with open(entities_path, "r", encoding="utf-8") as f:
    for line in f:
        m = re.search(r'ENTITY_TYPES\.register\("([^"]+)"', line)
        if m:
            entity_names.add(m.group(1))

entity_added = 0
for name in sorted(entity_names):
    key = f"entity.hbm.{name}"
    if key not in lang_sorted:
        lang_sorted[key] = to_display_name(name)
        entity_added += 1

# Rewrite with entity entries
with open(LANG_PATH, "w", encoding="utf-8") as f:
    json.dump(lang_sorted, f, indent=2, ensure_ascii=False)
    f.write("\n")

print(f"Added {entity_added} entity lang entries")
print(f"Final total: {len(lang_sorted)} lang entries")
