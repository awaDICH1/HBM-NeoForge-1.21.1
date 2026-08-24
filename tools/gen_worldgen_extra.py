import json
import os

BASE = r"D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\resources\data\hbm"
CF_DIR = os.path.join(BASE, "worldgen", "configured_feature")
PF_DIR = os.path.join(BASE, "worldgen", "placed_feature")
BM_DIR = os.path.join(BASE, "neoforge", "biome_modifier")

def write_json(path, data):
    with open(path, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=2)
        f.write("\n")

def make_cf(name, block_id, size, target_tag):
    return {
        "type": "minecraft:ore",
        "config": {
            "discard_chance_on_air_exposure": 0.0,
            "size": size,
            "targets": [{
                "state": {"Name": block_id},
                "target": {
                    "predicate_type": "minecraft:tag_match",
                    "tag": target_tag
                }
            }]
        }
    }

def make_cf_block(name, block_id, size, target_block):
    return {
        "type": "minecraft:ore",
        "config": {
            "discard_chance_on_air_exposure": 0.0,
            "size": size,
            "targets": [{
                "state": {"Name": block_id},
                "target": {
                    "predicate_type": "minecraft:block_match",
                    "block": target_block
                }
            }]
        }
    }

def make_cf_multi(name, block_id, size, targets):
    target_list = []
    for tag in targets:
        target_list.append({
            "state": {"Name": block_id},
            "target": {
                "predicate_type": "minecraft:tag_match",
                "tag": tag
            }
        })
    return {
        "type": "minecraft:ore",
        "config": {
            "discard_chance_on_air_exposure": 0.0,
            "size": size,
            "targets": target_list
        }
    }

def make_pf(cf_name, count, min_y, max_y, rarity=None):
    placement = []
    if rarity and rarity > 1:
        placement.append({"type": "minecraft:rarity_filter", "chance": rarity})
        placement.append({"type": "minecraft:in_square_world"})
    else:
        placement.append({"type": "minecraft:count", "count": count})
        placement.append({"type": "minecraft:in_square_world"})
    placement.append({
        "type": "minecraft:height_range",
        "height": {
            "type": "minecraft:uniform",
            "min_inclusive": {"absolute": min_y},
            "max_inclusive": {"absolute": max_y}
        }
    })
    placement.append({"type": "minecraft:biome"})
    return {"feature": f"hbm:{cf_name}", "placement": placement}

def make_bm(name, biomes, features, step="underground_ores"):
    return {
        "type": "neoforge:add_features",
        "biomes": biomes,
        "features": features,
        "step": step
    }

# --- Oil deposits (overworld) ---
# ore_oil: size 24, count 1 (rare, 1/200), Y 5-25
oil_ores = [
    ("ore_oil", 24, 1, 5, 25, 200),
    ("ore_oil_empty", 24, 1, 5, 25, 200),
    ("ore_oil_sand", 20, 1, 50, 70, 100),  # near surface in deserts
]

for name, size, count, min_y, max_y, rarity in oil_ores:
    block_id = f"hbm:{name}"
    cf_name = f"cf_{name}"
    pf_name = f"pf_{name}"

    if "sand" in name:
        cf = make_cf_block(cf_name, block_id, size, "minecraft:sandstone")
    else:
        cf = make_cf_multi(cf_name, block_id, size,
            ["minecraft:stone_ore_replaceables", "minecraft:deepslate_ore_replaceables"])
    pf = make_pf(cf_name, count, min_y, max_y, rarity=rarity)
    bm = make_bm(f"bm_{name}", "#minecraft:is_overworld", [f"hbm:{pf_name}"])

    write_json(os.path.join(CF_DIR, f"{cf_name}.json"), cf)
    write_json(os.path.join(PF_DIR, f"{pf_name}.json"), pf)
    write_json(os.path.join(BM_DIR, f"bm_{name}.json"), bm)

print(f"Generated {len(oil_ores)} oil deposit features")

# --- Bedrock ores (overworld, replace bedrock) ---
bedrock_ores = [
    ("ore_bedrock_oil", 6, 1, 0, 1, 200),     # bedrock oil, 1/200 chance
    ("ore_bedrock_block", 6, 1, 0, 1, 10),      # bedrock ore block, 1/10 chance
    ("ore_bedrock_coltan", 4, 1, 15, 39, 100),  # coltan, 1/100 chance (random spawn mode)
]

for name, size, count, min_y, max_y, rarity in bedrock_ores:
    block_id = f"hbm:{name}"
    cf_name = f"cf_{name}"
    pf_name = f"pf_{name}"

    cf = make_cf_block(cf_name, block_id, size, "minecraft:bedrock")
    pf = make_pf(cf_name, count, min_y, max_y, rarity=rarity)

    if "nether" in name:
        bm = make_bm(f"bm_{name}", "#minecraft:is_nether", [f"hbm:{pf_name}"])
    else:
        bm = make_bm(f"bm_{name}", "#minecraft:is_overworld", [f"hbm:{pf_name}"])

    write_json(os.path.join(CF_DIR, f"{cf_name}.json"), cf)
    write_json(os.path.join(PF_DIR, f"{pf_name}.json"), pf)
    write_json(os.path.join(BM_DIR, f"bm_{name}.json"), bm)

print(f"Generated {len(bedrock_ores)} bedrock ore features")

# --- Limestone (stone_resource, overworld) ---
# Limestone: size 6, count 1, Y 15-34
cf_name = "cf_stone_resource_limestone"
pf_name = "pf_stone_resource_limestone"
cf = make_cf(cf_name, "hbm:stone_resource", 6, "minecraft:stone_ore_replaceables")
pf = make_pf(cf_name, 1, 15, 34)
bm = make_bm("bm_stone_resource_limestone", "#minecraft:is_overworld", [f"hbm:{pf_name}"])
write_json(os.path.join(CF_DIR, f"{cf_name}.json"), cf)
write_json(os.path.join(PF_DIR, f"{pf_name}.json"), pf)
write_json(os.path.join(BM_DIR, "bm_stone_resource_limestone.json"), bm)
print("Generated limestone feature")

# --- Gneiss gas (ore_gneiss_gas, overworld, target stone_gneiss) ---
cf_name = "cf_ore_gneiss_gas"
pf_name = "pf_ore_gneiss_gas"
cf = make_cf_block(cf_name, "hbm:ore_gneiss_gas", 10, "hbm:stone_gneiss")
pf = make_pf(cf_name, 15, 30, 39)
bm = make_bm("bm_ore_gneiss_gas", "#minecraft:is_overworld", [f"hbm:{pf_name}"])
write_json(os.path.join(CF_DIR, f"{cf_name}.json"), cf)
write_json(os.path.join(PF_DIR, f"{pf_name}.json"), pf)
write_json(os.path.join(BM_DIR, "bm_ore_gneiss_gas.json"), bm)
print("Generated gneiss gas feature")

# --- Stone keyhole (overworld) ---
cf_name = "cf_stone_keyhole"
pf_name = "pf_stone_keyhole"
cf = make_cf(cf_name, "hbm:stone_keyhole", 4, "minecraft:stone_ore_replaceables")
pf = make_pf(cf_name, 1, 6, 18, rarity=4)
bm = make_bm("bm_stone_keyhole", "#minecraft:is_overworld", [f"hbm:{pf_name}"])
write_json(os.path.join(CF_DIR, f"{cf_name}.json"), cf)
write_json(os.path.join(PF_DIR, f"{pf_name}.json"), pf)
write_json(os.path.join(BM_DIR, "bm_stone_keyhole.json"), bm)
print("Generated stone keyhole feature")

# Summary
print("\n=== Extra worldgen features generated ===")
