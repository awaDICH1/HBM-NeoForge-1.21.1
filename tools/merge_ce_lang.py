import json
import re

CE_LANG_PATH = r"D:\用户文件\文档\HBM1.21.1 Pro\libs\Hbm-s-Nuclear-Tech-CE-master\src\main\resources\assets\hbm\lang\en_us.lang"
LANG_PATH = r"D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\resources\assets\hbm\lang\en_us.json"

# Read CE lang file (1.12.2 format: key=value)
ce_lang = {}
with open(CE_LANG_PATH, "r", encoding="utf-8") as f:
    for line in f:
        line = line.strip()
        if not line or line.startswith("#"):
            continue
        if "=" in line:
            parts = line.split("=", 1)
            key = parts[0].strip()
            value = parts[1].strip()
            if value:
                ce_lang[key] = value

print(f"CE lang entries: {len(ce_lang)}")

# Read our current JSON lang
with open(LANG_PATH, "r", encoding="utf-8") as f:
    our_lang = json.load(f)

print(f"Our lang entries before merge: {len(our_lang)}")

# Merge: update display names from CE where available
updated = 0
for key in our_lang:
    if key in ce_lang:
        old_val = our_lang[key]
        new_val = ce_lang[key]
        # Only update if our value is auto-generated (Title Case with spaces)
        if old_val != new_val:
            our_lang[key] = new_val
            updated += 1

print(f"Updated {updated} entries from CE lang")

# Also add any CE entries that we're missing
added = 0
for key, value in ce_lang.items():
    if key not in our_lang and value:
        our_lang[key] = value
        added += 1

print(f"Added {added} new entries from CE lang")

# Sort and write
our_lang_sorted = dict(sorted(our_lang.items()))
with open(LANG_PATH, "w", encoding="utf-8") as f:
    json.dump(our_lang_sorted, f, indent=2, ensure_ascii=False)
    f.write("\n")

print(f"Final total: {len(our_lang_sorted)} lang entries")
