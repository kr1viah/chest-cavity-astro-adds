package net.astrospud.ccastroadds.registration;

import net.astrospud.ccastroadds.CCAstroAdds;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.mob_effect.CCStatusEffect;
import net.tigereye.chestcavity.mob_effect.FurnacePower;
import net.tigereye.chestcavity.mob_effect.OrganRejection;
import net.tigereye.chestcavity.mob_effect.Ruminating;

public class CCAAStatusEffects {

    public static final StatusEffect RESONANCE_COOLDOWN = new CCStatusEffect(StatusEffectCategory.NEUTRAL,0x000000);
    public static final StatusEffect SHRIEKING_COOLDOWN = new CCStatusEffect(StatusEffectCategory.NEUTRAL,0x000000);
    public static final StatusEffect PANIC = (new CCStatusEffect(StatusEffectCategory.BENEFICIAL, 0xf25e65)).addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "b8f27012-8795-4ac8-95ef-1a88bd132494", 0.600000009, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);


    public static void register(){
        Registry.register(Registry.STATUS_EFFECT, new Identifier(CCAstroAdds.MOD_ID, "resonance_cooldown"), RESONANCE_COOLDOWN);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(CCAstroAdds.MOD_ID, "shrieking_cooldown"), SHRIEKING_COOLDOWN);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(CCAstroAdds.MOD_ID, "panic"), PANIC);
    }
}