package net.astrospud.ccastroadds.item;

import net.astrospud.ccastroadds.CCAstroAdds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item ABYSSAL_HEART = registerItem("abyssal_heart",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(CCAstroAdds.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CCAstroAdds.LOGGER.info("Registering Mod Items for " + CCAstroAdds.MOD_ID);
    }
}
