package net.astrospud.ccastroadds.registration;

import net.astrospud.ccastroadds.CCAstroAdds;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.mob_effect.CCStatusEffect;
import net.tigereye.chestcavity.mob_effect.FurnacePower;
import net.tigereye.chestcavity.mob_effect.OrganRejection;
import net.tigereye.chestcavity.mob_effect.Ruminating;

public class CCAAStatusEffects {

    public static final StatusEffect RESONANCE_COOLDOWN = new CCStatusEffect(StatusEffectCategory.NEUTRAL,0x000000);

    public static void register(){
        Registry.register(Registry.STATUS_EFFECT, new Identifier(CCAstroAdds.MOD_ID, "resonance_cooldown"), RESONANCE_COOLDOWN);
    }
}