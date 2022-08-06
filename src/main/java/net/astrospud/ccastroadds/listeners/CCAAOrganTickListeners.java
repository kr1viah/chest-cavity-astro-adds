package net.astrospud.ccastroadds.listeners;

import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.astrospud.ccastroadds.registration.CCAAStatusEffects;
import net.astrospud.ccastroadds.specials.AmethystExplosion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.listeners.OrganTickCallback;
import net.tigereye.chestcavity.registration.CCOrganScores;

public class CCAAOrganTickListeners {

    public static void register(){
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickNeutralWaterBuoyant);
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickFlight);
        //OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickNerveFallDown);
    }

    public static void TickNeutralWaterBuoyant(LivingEntity entity, ChestCavityInstance chestCavity){
        float buoyancy = chestCavity.getOrganScore(CCAAOrganScores.NEUTRAL_WATER_BUOYANT) - chestCavity.getChestCavityType().getDefaultOrganScore(CCAAOrganScores.NEUTRAL_WATER_BUOYANT);
        if((entity instanceof PlayerEntity ent && ent.isCreative() && ent.getAbilities().flying) || entity.isOnGround() || entity.hasNoGravity() || buoyancy <= 0)
        {
            return;
        }

        if(entity.isInLava())
        {
            entity.addVelocity(0.0D, 0.02D, 0.0D);
        }
        else if(entity.isTouchingWater())
        {
            entity.addVelocity(0.0D, 0.005D, 0.0D);
        }
    }

    public static void TickFlight(LivingEntity entity, ChestCavityInstance chestCavity){
        float flight = chestCavity.getOrganScore(CCAAOrganScores.FLIGHT) - chestCavity.getChestCavityType().getDefaultOrganScore(CCAAOrganScores.FLIGHT);
        if (flight < 1) {
            if(entity instanceof PlayerEntity player && !player.isCreative() && !player.isSpectator()) {
                player.getAbilities().allowFlying = false;
                player.getAbilities().flying = false;
            }
            return;
        }
        else if(entity instanceof PlayerEntity player) {
            player.getAbilities().allowFlying = true;
        }

        if((entity instanceof PlayerEntity player && !player.isCreative() && player.getAbilities().flying))
        {
            if (player.getHungerManager().getFoodLevel() > 0) {
                player.getHungerManager().addExhaustion(1);
            }
            else {
                player.damage(DamageSource.MAGIC, 1);
            }
        }
    }

    /*public static void TickNerveFallDown(LivingEntity entity, ChestCavityInstance chestCavity) {
        float nerves = chestCavity.getOrganScore(CCOrganScores.NERVES);
        float defnerves = chestCavity.getChestCavityType().getDefaultOrganScore(CCOrganScores.NERVES);
        if (nerves <= 0 && defnerves > 0 && entity instanceof PlayerEntity player) {
            entity.setPose(EntityPose.SWIMMING);
            player.setBoundingBox(entity.getBoundingBox(EntityPose.SWIMMING));
        }
        else {
            entity.shouldLeaveSwimmingPose();
        }
    }*/
}
