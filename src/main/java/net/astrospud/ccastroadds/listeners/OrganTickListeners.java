package net.astrospud.ccastroadds.listeners;

import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.listeners.OrganTickCallback;

import java.util.List;
import java.util.UUID;

public class OrganTickListeners {

    private static final UUID PANIC_SPEED_ID = UUID.fromString("b8f37012-8795-4ac8-94ef-1a88bd132494");
    public static void register(){
        OrganTickCallback.EVENT.register(OrganTickListeners::TickNeutralWaterBuoyant);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickPanic);
    }

    public static void TickNeutralWaterBuoyant(LivingEntity entity, ChestCavityInstance chestCavity){
        if(entity.isOnGround() || entity.hasNoGravity()){
            return;
        }
        float buoyancy = chestCavity.getOrganScore(CCAAOrganScores.NEUTRAL_WATER_BUOYANT) - chestCavity.getChestCavityType().getDefaultOrganScore(CCAAOrganScores.NEUTRAL_WATER_BUOYANT);

        if(buoyancy > 0 && (entity.isInLava()))
        {
            entity.addVelocity(0.0D, 0.02D, 0.0D);
        }
        else if(buoyancy > 0 && (entity.isTouchingWater()))
        {
            entity.addVelocity(0.0D, 0.005D, 0.0D);
        }
    }

    public static void TickPanic(LivingEntity entity, ChestCavityInstance cc){
        float panic = cc.getOrganScore(CCAAOrganScores.PANIC) - cc.getChestCavityType().getDefaultOrganScore(CCAAOrganScores.PANIC);

        if (panic != 0) {
            EntityAttributeInstance att = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);

            if(att != null) {
                EntityAttributeModifier mod = new EntityAttributeModifier(PANIC_SPEED_ID, "CCAstroAddsPanicSpeed",
                        0.16D * (Math.log10(panic-0.7D) + 1.153D), EntityAttributeModifier.Operation.ADDITION);

                if (entity.getRecentDamageSource() != null) {
                    ReplaceAttributeModifier(att, mod);
                }
                else {
                    att.removeModifier(mod);
                }
            }
        }
    }

    private static void ReplaceAttributeModifier(EntityAttributeInstance att, EntityAttributeModifier mod)
    {
        //removes any existing mod and replaces it with the updated one.
        att.removeModifier(mod);
        att.addPersistentModifier(mod);
    }
}
