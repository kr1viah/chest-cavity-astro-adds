package net.astrospud.ccastroadds.listeners;

import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.astrospud.ccastroadds.registration.CCAAStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;

public class CCAAOrganOnDamageListeners {
    public static void register(){
        OrganOnDamageCallback.EVENT.register(CCAAOrganOnDamageListeners::TickPanic);
    }
    public static void TickPanic(LivingEntity entity, DamageSource source, float amount){
        if (!(entity instanceof ChestCavityEntity)) { return; }
        ChestCavityInstance cc = ((ChestCavityEntity)entity).getChestCavityInstance();
        float panic = cc.getOrganScore(CCAAOrganScores.PANIC) - cc.getChestCavityType().getDefaultOrganScore(CCAAOrganScores.PANIC);
        if (panic <= 0 || entity.hasStatusEffect(CCAAStatusEffects.PANIC)) { return; }
        int power = (int) Math.floor(2 * Math.log10((panic) + 1));
        int duration = (int)(100 * Math.log10(2*panic -1))+100;
        entity.addStatusEffect(new StatusEffectInstance(CCAAStatusEffects.PANIC, duration, power, false, true, true));
    }
}
