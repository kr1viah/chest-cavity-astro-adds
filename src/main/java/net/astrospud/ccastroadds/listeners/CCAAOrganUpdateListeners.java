package net.astrospud.ccastroadds.listeners;

import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.listeners.OrganUpdateCallback;
import net.tigereye.chestcavity.listeners.OrganUpdateListeners;
import net.tigereye.chestcavity.registration.CCOrganScores;

public class CCAAOrganUpdateListeners extends OrganUpdateListeners {

    public static void register(){
        //OrganUpdateCallback.EVENT.register(CCAAOrganUpdateListeners::UpdateFlight);
    }

    /*public static void UpdateFlight(LivingEntity entity, ChestCavityInstance cc) {
        if (!(entity instanceof PlayerEntity player)) {
            return;
        }
        float fflight = cc.getOrganScore(CCAAOrganScores.FLIGHT);
        float flight = fflight - cc.getChestCavityType().getDefaultOrganScore(CCAAOrganScores.FLIGHT);
        if(cc.getOldOrganScore(CCAAOrganScores.FLIGHT) != fflight){
            if (flight >= 1) {
                player.getAbilities().allowFlying = true;
            }
            else {
                player.getAbilities().allowFlying = false;
            }
        }
    }*/
}
