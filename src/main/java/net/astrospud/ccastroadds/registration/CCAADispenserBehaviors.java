package net.astrospud.ccastroadds.registration;

import net.astrospud.ccastroadds.specials.ChestOpenerDispenserBehavior;
import net.minecraft.block.DispenserBlock;
import net.tigereye.chestcavity.registration.CCItems;

public class CCAADispenserBehaviors {
    public static void register() {
        DispenserBlock.registerBehavior(CCItems.CHEST_OPENER.asItem(), new ChestOpenerDispenserBehavior());
    }
}
