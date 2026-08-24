import json
import os
import glob

BS_DIR = r"D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\resources\assets\hbm\blockstates"
MODEL_DIR = r"D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\resources\assets\hbm\models\block"

wall_files = glob.glob(os.path.join(BS_DIR, "*_wall.json"))

for wf in wall_files:
    basename = os.path.basename(wf).replace("_wall.json", "")

    # 1.21.1 wall blockstate format
    new_data = {
        "multipart": [
            {"when": {"up": "true"}, "apply": {"model": f"hbm:block/{basename}_wall_post"}},
            {"when": {"north": "low"}, "apply": {"model": f"hbm:block/{basename}_wall_side"}},
            {"when": {"east": "low"}, "apply": {"model": f"hbm:block/{basename}_wall_side", "y": 90}},
            {"when": {"south": "low"}, "apply": {"model": f"hbm:block/{basename}_wall_side", "y": 180}},
            {"when": {"west": "low"}, "apply": {"model": f"hbm:block/{basename}_wall_side", "y": 270}},
            {"when": {"north": "tall"}, "apply": {"model": f"hbm:block/{basename}_wall_side_tall"}},
            {"when": {"east": "tall"}, "apply": {"model": f"hbm:block/{basename}_wall_side_tall", "y": 90}},
            {"when": {"south": "tall"}, "apply": {"model": f"hbm:block/{basename}_wall_side_tall", "y": 180}},
            {"when": {"west": "tall"}, "apply": {"model": f"hbm:block/{basename}_wall_side_tall", "y": 270}},
        ]
    }

    with open(wf, "w", encoding="utf-8") as f:
        json.dump(new_data, f, indent=2)
        f.write("\n")
    print(f"Fixed: {os.path.basename(wf)}")

    # Check if side_tall model exists, create if missing
    tall_model = os.path.join(MODEL_DIR, f"{basename}_wall_side_tall.json")
    if not os.path.exists(tall_model):
        tall_data = {
            "parent": "minecraft:block/template_wall_side_tall",
            "textures": {
                "wall": f"hbm:block/{basename}_wall"
            }
        }
        with open(tall_model, "w", encoding="utf-8") as f:
            json.dump(tall_data, f, indent=2)
            f.write("\n")
        print(f"  Created: {basename}_wall_side_tall.json")

print(f"\nFixed {len(wall_files)} wall blockstate files")
