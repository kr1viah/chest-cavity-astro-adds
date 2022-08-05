package net.astrospud.ccastroadds.registration;

import net.astrospud.ccastroadds.CCAstroAdds;
import net.astrospud.ccastroadds.specials.DoniItem;
import net.astrospud.ccastroadds.specials.GlintItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;

public class CCAAItems {

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
    public static final Item ELECTROLYZER = registerItem("electrolyzer",
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
    public static final Item STEEL_DISTRIBUTOR = registerItem("steel_distributor",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item BOILER = registerItem("boiler",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item NUTRIENT_MIXER = registerItem("nutrient_mixer",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));

    //crystal set
    public static final Item CRYSTAL_ROD = registerItem("crystal_rod",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(16)));
    public static final Item CRYSTAL_CORE = registerItem("crystal_core",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item SHARDED_RIB = registerItem("sharded_rib",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(4)));

    //computer parts
    public static final Item CIRCUITRY = registerItem("circuitry",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item SUPER_CAPACITOR = registerItem("super_capacitor",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));

    //rejuvenated parts
    public static final Item REJUVENATED_RIB = registerItem("rejuvenated_rib",
            new GlintItem(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(4)));
    public static final Item REJUVENATED_SPINE = registerItem("rejuvenated_spine",
            new GlintItem(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item REANIMATED_HEART = registerItem("reanimated_heart",
            new GlintItem(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item REANIMATED_KIDNEY = registerItem("reanimated_kidney",
            new GlintItem(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));

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
    public static final Item SWIM_BLADDER = registerItem("swim_bladder",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item RESONANT_CORE = registerItem("resonant_core",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item ADRENAL_CATALYST = registerItem("adrenal_catalyst",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item ADRENAL_SHRIEKER = registerItem("adrenal_shrieker",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item STEEL_STOMACH = registerItem("steel_stomach",
            new DoniItem(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    public static final Item STEEL_RUMEN = registerItem("steel_rumen",
            new DoniItem(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));

    public static final Item CLUSTER_BOMB = registerItem("cluster_bomb",
            new Item(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));
    //I have zero idea how to make tumor work, so I've removed its recipe and all child recipes
    public static final Item DRACONIS_FUNDAMENTUM = registerItem("draconis_fundamentum",
            new GlintItem(new FabricItemSettings().group(ChestCavity.ORGAN_ITEM_GROUP).maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(CCAstroAdds.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CCAstroAdds.LOGGER.info("Registering Mod Items for " + CCAstroAdds.MOD_ID);
    }
}
