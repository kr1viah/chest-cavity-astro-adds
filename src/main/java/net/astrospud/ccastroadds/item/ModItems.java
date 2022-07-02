package net.astrospud.ccastroadds.item;

import net.astrospud.ccastroadds.CCAstroAdds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;

public class ModItems {

    // new Item(new Item.Settings().maxCount(1).group(ChestCavity.ORGAN_ITEM_GROUP));

    //echo set
    public static final Item ABYSSAL_HEART = registerItem("abyssal_heart",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item ABYSSAL_LUNG = registerItem("abyssal_lung",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item ABYSSAL_MUSCLE = registerItem("abyssal_muscle",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(16)));

    //copper set
    public static final Item ACTUATOR = registerItem("actuator",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(16)));
    public static final Item COPPER_WIRING = registerItem("copper_wiring",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(4)));
    public static final Item COPPER_FRAME = registerItem("copper_frame",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(4)));
    public static final Item PHANTOM_PUMP = registerItem("phantom_pump",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item FILTER_PUMP = registerItem("filter_pump",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item BLOOD_PUMP = registerItem("blood_pump",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));

    //gold set
    public static final Item JUMP_SHAFT = registerItem("jump_shaft",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(16)));
    public static final Item GOLDEN_WIRING = registerItem("golden_wiring",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(4)));
    public static final Item CLOCKWORK_CORE = registerItem("clockwork_core",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item GEM_HOLSTER = registerItem("gem_holster",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item ACID_MIXER = registerItem("acid_mixer",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));

    //iron set
    public static final Item STEEL_ACTUATOR = registerItem("steel_actuator",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(16)));
    public static final Item STEEL_CABLE = registerItem("steel_cable",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(4)));
    public static final Item STEEL_FRAME = registerItem("steel_frame",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(4)));
    public static final Item STEEL_DETOXIFIER = registerItem("steel_detoxifier",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item STEEL_CORE = registerItem("steel_core",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));

    //utility
    public static final Item CAPTURED_SOUL = registerItem("captured_soul",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item CREATIVE_SOUL = registerItem("creative_soul",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));

    public static final Item IRON_LUNG = registerItem("iron_lung",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item HEART_OF_GOLD = registerItem("heart_of_gold",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item HASTY_MUSCLE = registerItem("hasty_muscle",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(16)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(CCAstroAdds.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CCAstroAdds.LOGGER.info("Registering Mod Items for " + CCAstroAdds.MOD_ID);
    }
}
