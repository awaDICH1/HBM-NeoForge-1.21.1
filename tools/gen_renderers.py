import re

ENTITIES_FILE = r"D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\entity\ModEntities.java"
OUTPUT_FILE = r"D:\用户文件\文档\HBM1.21.1 Pro\HBM-NeoForge\src\main\java\com\hbm\render\ModRenderers.java"

# Extract all variable names
var_names = []
with open(ENTITIES_FILE, "r", encoding="utf-8") as f:
    for line in f:
        m = re.search(r'public static final DeferredHolder.*>\s+(\w+)\s*=', line)
        if m:
            var_names.append(m.group(1))

print(f"Found {len(var_names)} entity variables")

# Generate ModRenderers.java
code = """package com.hbm.render;

import com.hbm.Tags;
import com.hbm.entity.ModEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/**
 * Entity renderer registration.
 * Registers placeholder renderers for all 128 entities.
 */
@EventBusSubscriber(modid = Tags.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRenderers {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
"""

for var in var_names:
    code += f'        event.registerEntityRenderer(ModEntities.{var}.get(), com.hbm.render.entity.RenderEmpty::new);\n'

code += """    }
}
"""

with open(OUTPUT_FILE, "w", encoding="utf-8") as f:
    f.write(code)

print(f"Generated ModRenderers.java with {len(var_names)} entity renderer registrations")
