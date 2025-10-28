package net.astrospud.ccastroadds.registration;

import net.astrospud.ccastroadds.CCAstroAdds;
import net.astrospud.ccastroadds.specials.DoniItem;
import net.astrospud.ccastroadds.specials.GlintItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CCAAItems {

    // new Item(new Item.Settings().maxCount(1).group(CCAstroAdds.ORGAN_ITEM_GROUP));

    //echo set
    public static final Item ABYSSAL_HEART = registerItem("abyssal_heart",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item ABYSSAL_LUNG = registerItem("abyssal_lung",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item ABYSSAL_MUSCLE = registerItem("abyssal_muscle",
            new Item(new FabricItemSettings().maxCount(16)));

    //copper set
    public static final Item ACTUATOR = registerItem("actuator",
            new Item(new FabricItemSettings().maxCount(16)));
    public static final Item COPPER_WIRING = registerItem("copper_wiring",
            new Item(new FabricItemSettings().maxCount(4)));
    public static final Item COPPER_FRAME = registerItem("copper_frame",
            new Item(new FabricItemSettings().maxCount(4)));
    public static final Item PHANTOM_PUMP = registerItem("phantom_pump",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item FILTER_PUMP = registerItem("filter_pump",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item BLOOD_PUMP = registerItem("blood_pump",
            new Item(new FabricItemSettings().maxCount(1)));

    //gold set
    public static final Item JUMP_SHAFT = registerItem("jump_shaft",
            new Item(new FabricItemSettings().maxCount(16)));
    public static final Item GOLDEN_WIRING = registerItem("golden_wiring",
            new Item(new FabricItemSettings().maxCount(4)));
    public static final Item CLOCKWORK_CORE = registerItem("clockwork_core",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item GEM_HOLSTER = registerItem("gem_holster",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item ACID_MIXER = registerItem("acid_mixer",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item ELECTROLYZER = registerItem("electrolyzer",
            new Item(new FabricItemSettings().maxCount(1)));

    //iron set
    public static final Item STEEL_ACTUATOR = registerItem("steel_actuator",
            new Item(new FabricItemSettings().maxCount(16)));
    public static final Item STEEL_CABLE = registerItem("steel_cable",
            new Item(new FabricItemSettings().maxCount(4)));
    public static final Item STEEL_FRAME = registerItem("steel_frame",
            new Item(new FabricItemSettings().maxCount(4)));
    public static final Item STEEL_DETOXIFIER = registerItem("steel_detoxifier",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item STEEL_DISTRIBUTOR = registerItem("steel_distributor",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item BOILER = registerItem("boiler",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item NUTRIENT_MIXER = registerItem("nutrient_mixer",
            new Item(new FabricItemSettings().maxCount(1)));

    //crystal set
    public static final Item CRYSTAL_ROD = registerItem("crystal_rod",
            new Item(new FabricItemSettings().maxCount(16)));
    public static final Item CRYSTAL_CORE = registerItem("crystal_core",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SHARDED_RIB = registerItem("sharded_rib",
            new Item(new FabricItemSettings().maxCount(4)));

    //computer parts
    public static final Item CIRCUITRY = registerItem("circuitry",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SUPER_CAPACITOR = registerItem("super_capacitor",
            new Item(new FabricItemSettings().maxCount(1)));

    //rejuvenated parts
    public static final Item REJUVENATED_RIB = registerItem("rejuvenated_rib",
            new GlintItem(new FabricItemSettings().maxCount(4)));
    public static final Item REJUVENATED_SPINE = registerItem("rejuvenated_spine",
            new GlintItem(new FabricItemSettings().maxCount(1)));
    public static final Item REANIMATED_HEART = registerItem("reanimated_heart",
            new GlintItem(new FabricItemSettings().maxCount(1)));
    public static final Item REANIMATED_KIDNEY = registerItem("reanimated_kidney",
            new GlintItem(new FabricItemSettings().maxCount(1)));

    //utility
    public static final Item CAPTURED_SOUL = registerItem("captured_soul",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CREATIVE_SOUL = registerItem("creative_soul",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item IRON_LUNG = registerItem("iron_lung",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item HEART_OF_GOLD = registerItem("heart_of_gold",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item HASTY_MUSCLE = registerItem("hasty_muscle",
            new Item(new FabricItemSettings().maxCount(16)));
    public static final Item SWIM_BLADDER = registerItem("swim_bladder",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item RESONANT_CORE = registerItem("resonant_core",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item ADRENAL_CATALYST = registerItem("adrenal_catalyst",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item ADRENAL_SHRIEKER = registerItem("adrenal_shrieker",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item STEEL_STOMACH = registerItem("steel_stomach",
            new DoniItem(new FabricItemSettings().maxCount(1)));
    public static final Item STEEL_RUMEN = registerItem("steel_rumen",
            new DoniItem(new FabricItemSettings().maxCount(1)));

    public static final Item BENIGN_TUMOR = registerItem("benign_tumor",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item AUTOPHAGY_TUMOR = registerItem("autophagy_tumor",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SCULK_TUMOR = registerItem("sculk_tumor",
            new Item(new FabricItemSettings().maxCount(1)));

    public static final Item AUTOPHAGY_STOMACH = registerItem("autophagy_stomach",
            new Item(new FabricItemSettings().maxCount(1)));

    public static final Item EMPTY_CANISTER = registerItem("empty_canister",
            new Item(new FabricItemSettings().maxCount(16)));
    public static final Item STEM_CELL_CANISTER = registerItem("stem_cell_canister",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item NANOBOT_CANISTER = registerItem("nanobot_canister",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SCULK_CANISTER = registerItem("sculk_canister",
            new Item(new FabricItemSettings().maxCount(1)));

    public static final Item CLUSTER_BOMB = registerItem("cluster_bomb",
            new Item(new FabricItemSettings().maxCount(1)));
    //I have zero idea how to make tumor work, so I've removed its recipe and all child recipes
    public static final Item DRACONIS_FUNDAMENTUM = registerItem("draconis_fundamentum",
            new GlintItem(new FabricItemSettings().maxCount(1)));

    public static final ItemGroup ORGAN_ITEM_GROUP = FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.ccastroadds.organs"))
            .icon(CCAAItems.STEEL_DISTRIBUTOR::getDefaultStack)
            .entries((context, entries) -> {
                entries.add(ABYSSAL_HEART);
                entries.add(ABYSSAL_LUNG);
                entries.add(ABYSSAL_MUSCLE);

                entries.add(ACTUATOR);
                entries.add(COPPER_WIRING);
                entries.add(COPPER_FRAME);
                entries.add(PHANTOM_PUMP);
                entries.add(FILTER_PUMP);
                entries.add(BLOOD_PUMP);

                entries.add(JUMP_SHAFT);
                entries.add(GOLDEN_WIRING);
                entries.add(CLOCKWORK_CORE);
                entries.add(GEM_HOLSTER);
                entries.add(ACID_MIXER);
                entries.add(ELECTROLYZER);

                entries.add(STEEL_ACTUATOR);
                entries.add(STEEL_CABLE);
                entries.add(STEEL_FRAME);
                entries.add(STEEL_DETOXIFIER);
                entries.add(STEEL_DISTRIBUTOR);
                entries.add(BOILER);
                entries.add(NUTRIENT_MIXER);

                entries.add(CRYSTAL_ROD);
                entries.add(CRYSTAL_CORE);
                entries.add(SHARDED_RIB);

                entries.add(CIRCUITRY);
                entries.add(SUPER_CAPACITOR);

                entries.add(REJUVENATED_RIB);
                entries.add(REJUVENATED_SPINE);
                entries.add(REANIMATED_HEART);
                entries.add(REANIMATED_KIDNEY);

                entries.add(CAPTURED_SOUL);
                entries.add(CREATIVE_SOUL);
                entries.add(IRON_LUNG);
                entries.add(HEART_OF_GOLD);
                entries.add(HASTY_MUSCLE);
                entries.add(SWIM_BLADDER);
                entries.add(RESONANT_CORE);
                entries.add(ADRENAL_CATALYST);
                entries.add(ADRENAL_SHRIEKER);
                entries.add(STEEL_STOMACH);
                entries.add(STEEL_RUMEN);

                entries.add(BENIGN_TUMOR);
                entries.add(AUTOPHAGY_TUMOR);
                entries.add(SCULK_TUMOR);

                entries.add(AUTOPHAGY_STOMACH);

                entries.add(EMPTY_CANISTER);
                entries.add(STEM_CELL_CANISTER);
                entries.add(NANOBOT_CANISTER);
                entries.add(SCULK_CANISTER);

                entries.add(CLUSTER_BOMB);
                entries.add(DRACONIS_FUNDAMENTUM);

            })
            .build();

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(CCAstroAdds.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CCAstroAdds.LOGGER.info("Registering Mod Items for " + CCAstroAdds.MOD_ID);
    }
}
