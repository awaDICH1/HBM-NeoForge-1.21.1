import json
import os

BASE = r"D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\resources\data\hbm"
CF_DIR = os.path.join(BASE, "worldgen", "configured_feature")
PF_DIR = os.path.join(BASE, "worldgen", "placed_feature")
BM_DIR = os.path.join(BASE, "neoforge", "biome_modifier")

os.makedirs(CF_DIR, exist_ok=True)
os.makedirs(PF_DIR, exist_ok=True)
os.makedirs(BM_DIR, exist_ok=True)

# --- Ore definitions ---
# (registry_name, vein_size, count, min_y, max_y, dimension, target_tag, step)
# dimension: "overworld", "nether", "end"
# target_tag: minecraft tag for blocks to replace

OVERWORLD_ORES = [
    # (name, size, count, minY, maxY)
    ("ore_uranium", 5, 7, 5, 24),
    ("ore_thorium", 5, 7, 5, 29),
    ("ore_titanium", 6, 8, 5, 34),
    ("ore_sulfur", 8, 5, 5, 34),
    ("ore_aluminium", 6, 7, 5, 44),
    ("ore_copper", 6, 12, 5, 49),
    ("ore_fluorite", 4, 6, 5, 49),
    ("ore_niter", 6, 6, 5, 34),
    ("ore_tungsten", 8, 10, 5, 34),
    ("ore_lead", 9, 6, 5, 34),
    ("ore_beryllium", 4, 6, 5, 34),
    ("ore_rare", 5, 6, 5, 24),
    ("ore_lignite", 24, 2, 35, 59),
    ("ore_asbestos", 4, 2, 16, 31),
    ("ore_cinnabar", 4, 1, 8, 23),
    ("ore_cobalt", 4, 2, 4, 11),
    ("ore_alexandrite", 3, 1, 10, 14),  # rare, 1 in 100 chance -> count 1 with rarity
    ("ore_australium", 3, 1, 14, 31),  # rare
]

OVERWORLD_CLUSTERS = [
    ("cluster_iron", 6, 4, 15, 59),
    ("cluster_titanium", 6, 2, 15, 44),
    ("cluster_aluminium", 6, 3, 15, 49),
    ("cluster_copper", 6, 3, 15, 34),
]

NETHER_ORES = [
    ("ore_nether_uranium", 6, 8, 0, 126),
    ("ore_nether_tungsten", 10, 10, 0, 126),
    ("ore_nether_sulfur", 12, 26, 0, 126),
    ("ore_nether_fire", 6, 24, 0, 126),
    ("ore_nether_coal", 32, 24, 16, 111),
    ("ore_nether_cobalt", 6, 2, 100, 125),
    ("ore_nether_plutonium", 4, 8, 0, 126),
    ("ore_nether_smoldering", 6, 30, 11, 111),
]

END_ORES = [
    ("ore_tikite", 6, 8, 0, 126),
]

# Gneiss ores - target hbm:stone_gneiss
GNEISS_ORES = [
    ("ore_gneiss_iron", 6, 25, 30, 39),
    ("ore_gneiss_gold", 6, 10, 30, 39),
    ("ore_gneiss_uranium", 6, 21, 30, 39),
    ("ore_gneiss_copper", 6, 36, 30, 39),
    ("ore_gneiss_asbestos", 6, 6, 30, 39),
    ("ore_gneiss_lithium", 6, 6, 30, 39),
    ("ore_gneiss_rare", 6, 6, 30, 39),
    ("ore_gneiss_schrabidium", 6, 6, 30, 39),
    ("ore_gneiss_uranium_scorched", 6, 6, 30, 39),
]

# Depth deposits - near bedrock, very rare
DEPTH_ORES_OW = [
    ("cluster_depth_iron", 5, 1, 0, 2, 24),  # (name, size, count, minY, maxY, rarity)
    ("cluster_depth_titanium", 5, 1, 0, 2, 32),
    ("cluster_depth_tungsten", 5, 1, 0, 2, 32),
    ("ore_depth_cinnabar", 5, 1, 0, 2, 16),
    ("ore_depth_zirconium", 5, 1, 0, 2, 16),
    ("ore_depth_borax", 5, 1, 0, 2, 16),
]

DEPTH_ORES_NETHER = [
    ("ore_depth_nether_neodymium", 7, 1, 0, 2, 16),
    ("ore_depth_nether_nitan", 7, 1, 0, 2, 16),
]

def make_configured_feature(name, block_id, size, target_tag="minecraft:stone_ore_replaceables"):
    """Create a configured feature JSON for an ore."""
    return {
        "type": "minecraft:ore",
        "config": {
            "discard_chance_on_air_exposure": 0.0,
            "size": size,
            "targets": [
                {
                    "state": {
                        "Name": block_id
                    },
                    "target": {
                        "predicate_type": "minecraft:tag_match",
                        "tag": target_tag
                    }
                }
            ]
        }
    }

def make_configured_feature_multi(name, block_id, size, targets):
    """Create a configured feature with multiple target tags."""
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

def make_placed_feature(configured_name, count, min_y, max_y, rarity=None):
    """Create a placed feature JSON."""
    placement = []

    if rarity and rarity > 1:
        # Use rarity: count=1 with rare chance
        placement.append({
            "type": "minecraft:rarity_filter",
            "chance": rarity
        })
        placement.append({
            "type": "minecraft:in_square_world"
        })
    else:
        placement.append({
            "type": "minecraft:count",
            "count": count
        })
        placement.append({
            "type": "minecraft:in_square_world"
        })

    placement.append({
        "type": "minecraft:height_range",
        "height": {
            "type": "minecraft:uniform",
            "min_inclusive": {"absolute": min_y},
            "max_inclusive": {"absolute": max_y}
        }
    })
    placement.append({
        "type": "minecraft:biome"
    })

    return {
        "feature": f"hbm:{configured_name}",
        "placement": placement
    }

def make_biome_modifier(mod_name, biomes_tag, features, step="underground_ores"):
    """Create a NeoForge biome modifier JSON."""
    return {
        "type": "neoforge:add_features",
        "biomes": biomes_tag,
        "features": features,
        "step": step
    }

def write_json(path, data):
    with open(path, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=2)
        f.write("\n")

# ============================================================
# Generate Overworld Ores
# ============================================================
ow_features = []

for name, size, count, min_y, max_y in OVERWORLD_ORES:
    block_id = f"hbm:{name}"
    cf_name = f"cf_{name}"
    pf_name = f"pf_{name}"

    # Use both stone and deepslate replaceables for overworld ores
    cf = make_configured_feature_multi(cf_name, block_id, size,
        ["minecraft:stone_ore_replaceables", "minecraft:deepslate_ore_replaceables"])
    pf = make_placed_feature(cf_name, count, min_y, max_y)
    bm = make_biome_modifier(f"bm_{name}", "#minecraft:is_overworld", [f"hbm:{pf_name}"])

    write_json(os.path.join(CF_DIR, f"{cf_name}.json"), cf)
    write_json(os.path.join(PF_DIR, f"{pf_name}.json"), pf)
    write_json(os.path.join(BM_DIR, f"bm_{name}.json"), bm)
    ow_features.append(pf_name)

print(f"Generated {len(OVERWORLD_ORES)} overworld ores")

# ============================================================
# Generate Overworld Clusters
# ============================================================
for name, size, count, min_y, max_y in OVERWORLD_CLUSTERS:
    block_id = f"hbm:{name}"
    cf_name = f"cf_{name}"
    pf_name = f"pf_{name}"

    cf = make_configured_feature_multi(cf_name, block_id, size,
        ["minecraft:stone_ore_replaceables", "minecraft:deepslate_ore_replaceables"])
    pf = make_placed_feature(cf_name, count, min_y, max_y)
    bm = make_biome_modifier(f"bm_{name}", "#minecraft:is_overworld", [f"hbm:{pf_name}"])

    write_json(os.path.join(CF_DIR, f"{cf_name}.json"), cf)
    write_json(os.path.join(PF_DIR, f"{pf_name}.json"), pf)
    write_json(os.path.join(BM_DIR, f"bm_{name}.json"), bm)
    ow_features.append(pf_name)

print(f"Generated {len(OVERWORLD_CLUSTERS)} overworld clusters")

# ============================================================
# Generate Nether Ores
# ============================================================
for name, size, count, min_y, max_y in NETHER_ORES:
    block_id = f"hbm:{name}"
    cf_name = f"cf_{name}"
    pf_name = f"pf_{name}"

    cf = make_configured_feature(cf_name, block_id, size, "minecraft:stone_ore_replaceables")
    # For nether, target netherrack
    cf["config"]["targets"] = [{
        "state": {"Name": block_id},
        "target": {
            "predicate_type": "minecraft:tag_match",
            "tag": "minecraft:base_stone_nether"
        }
    }]
    pf = make_placed_feature(cf_name, count, min_y, max_y)
    bm = make_biome_modifier(f"bm_{name}", "#minecraft:is_nether", [f"hbm:{pf_name}"])

    write_json(os.path.join(CF_DIR, f"{cf_name}.json"), cf)
    write_json(os.path.join(PF_DIR, f"{pf_name}.json"), pf)
    write_json(os.path.join(BM_DIR, f"bm_{name}.json"), bm)

print(f"Generated {len(NETHER_ORES)} nether ores")

# ============================================================
# Generate End Ores
# ============================================================
for name, size, count, min_y, max_y in END_ORES:
    block_id = f"hbm:{name}"
    cf_name = f"cf_{name}"
    pf_name = f"pf_{name}"

    cf = make_configured_feature(cf_name, block_id, size)
    # For end, target end_stone
    cf["config"]["targets"] = [{
        "state": {"Name": block_id},
        "target": {
            "predicate_type": "minecraft:block_match",
            "block": "minecraft:end_stone"
        }
    }]
    pf = make_placed_feature(cf_name, count, min_y, max_y)
    bm = make_biome_modifier(f"bm_{name}", "#minecraft:is_end", [f"hbm:{pf_name}"])

    write_json(os.path.join(CF_DIR, f"{cf_name}.json"), cf)
    write_json(os.path.join(PF_DIR, f"{pf_name}.json"), pf)
    write_json(os.path.join(BM_DIR, f"bm_{name}.json"), bm)

print(f"Generated {len(END_ORES)} end ores")

# ============================================================
# Generate Gneiss Ores
# ============================================================
for name, size, count, min_y, max_y in GNEISS_ORES:
    block_id = f"hbm:{name}"
    cf_name = f"cf_{name}"
    pf_name = f"pf_{name}"

    cf = make_configured_feature(cf_name, block_id, size)
    # Target hbm:stone_gneiss
    cf["config"]["targets"] = [{
        "state": {"Name": block_id},
        "target": {
            "predicate_type": "minecraft:block_match",
            "block": "hbm:stone_gneiss"
        }
    }]
    pf = make_placed_feature(cf_name, count, min_y, max_y)
    bm = make_biome_modifier(f"bm_{name}", "#minecraft:is_overworld", [f"hbm:{pf_name}"])

    write_json(os.path.join(CF_DIR, f"{cf_name}.json"), cf)
    write_json(os.path.join(PF_DIR, f"{pf_name}.json"), pf)
    write_json(os.path.join(BM_DIR, f"bm_{name}.json"), bm)

print(f"Generated {len(GNEISS_ORES)} gneiss ores")

# ============================================================
# Generate Depth Deposits (Overworld)
# ============================================================
for name, size, count, min_y, max_y, rarity in DEPTH_ORES_OW:
    block_id = f"hbm:{name}"
    cf_name = f"cf_{name}"
    pf_name = f"pf_{name}"

    cf = make_configured_feature_multi(cf_name, block_id, size,
        ["minecraft:stone_ore_replaceables", "minecraft:deepslate_ore_replaceables"])
    pf = make_placed_feature(cf_name, count, min_y, max_y, rarity=rarity)
    bm = make_biome_modifier(f"bm_{name}", "#minecraft:is_overworld", [f"hbm:{pf_name}"])

    write_json(os.path.join(CF_DIR, f"{cf_name}.json"), cf)
    write_json(os.path.join(PF_DIR, f"{pf_name}.json"), pf)
    write_json(os.path.join(BM_DIR, f"bm_{name}.json"), bm)

print(f"Generated {len(DEPTH_ORES_OW)} depth deposits (overworld)")

# ============================================================
# Generate Depth Deposits (Nether)
# ============================================================
for name, size, count, min_y, max_y, rarity in DEPTH_ORES_NETHER:
    block_id = f"hbm:{name}"
    cf_name = f"cf_{name}"
    pf_name = f"pf_{name}"

    cf = make_configured_feature(cf_name, block_id, size)
    cf["config"]["targets"] = [{
        "state": {"Name": block_id},
        "target": {
            "predicate_type": "minecraft:tag_match",
            "tag": "minecraft:base_stone_nether"
        }
    }]
    pf = make_placed_feature(cf_name, count, min_y, max_y, rarity=rarity)
    bm = make_biome_modifier(f"bm_{name}", "#minecraft:is_nether", [f"hbm:{pf_name}"])

    write_json(os.path.join(CF_DIR, f"{cf_name}.json"), cf)
    write_json(os.path.join(PF_DIR, f"{pf_name}.json"), pf)
    write_json(os.path.join(BM_DIR, f"bm_{name}.json"), bm)

print(f"Generated {len(DEPTH_ORES_NETHER)} depth deposits (nether)")

# Summary
total_cf = len(OVERWORLD_ORES) + len(OVERWORLD_CLUSTERS) + len(NETHER_ORES) + len(END_ORES) + len(GNEISS_ORES) + len(DEPTH_ORES_OW) + len(DEPTH_ORES_NETHER)
print(f"\n=== Total: {total_cf} ore features generated ===")
print(f"Configured features: {CF_DIR}")
print(f"Placed features: {PF_DIR}")
print(f"Biome modifiers: {BM_DIR}")
