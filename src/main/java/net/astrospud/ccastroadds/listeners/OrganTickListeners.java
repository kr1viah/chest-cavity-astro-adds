package net.astrospud.ccastroadds.listeners;

import net.astrospud.ccastroadds.CCAstroAdds;
import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.astrospud.ccastroadds.registration.CCAAStatusEffects;
import net.astrospud.ccastroadds.specials.AmethystExplosion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.listeners.OrganTickCallback;

import java.util.UUID;

public class OrganTickListeners {

    private static final UUID PANIC_SPEED_ID = UUID.fromString("b8f37012-8795-4ac8-94ef-1a88bd132494");
    public static void register(){
        OrganTickCallback.EVENT.register(OrganTickListeners::TickNeutralWaterBuoyant);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickPanic);
        OrganTickCallback.EVENT.register(OrganTickListeners::TickResonance);
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

    public static void TickResonance(LivingEntity entity, ChestCavityInstance cc){
        float resonance = cc.getOrganScore(CCAAOrganScores.RESONANCE) - cc.getChestCavityType().getDefaultOrganScore(CCAAOrganScores.RESONANCE);
        DamageSource entattacker = entity.getRecentDamageSource();
        if (resonance < 1 || entattacker == null) {
            return;
        }
        Entity asd = entattacker.getAttacker();
        if (asd instanceof LivingEntity && !entity.hasStatusEffect(CCAAStatusEffects.RESONANCE_COOLDOWN)) {
            entity.addStatusEffect(new StatusEffectInstance(CCAAStatusEffects.RESONANCE_COOLDOWN, 100,0, false, false, true));

            Position entityPos = entity.getPos();
            double x = entityPos.getX();
            double y = entityPos.getY();
            double z = entityPos.getZ();
            float power = 5;
            World entityWorld = entity.getWorld();

            AmethystExplosion explosion = new AmethystExplosion(entityWorld, entity, x, y, z, power);
            explosion.collectBlocksAndDamageEntities();
            entityWorld.playSound((PlayerEntity)null, entity.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 17.0F, 0.5F + entityWorld.random.nextFloat() * 1.2F);
        }
    }

    private static void ReplaceAttributeModifier(EntityAttributeInstance att, EntityAttributeModifier mod)
    {
        //removes any existing mod and replaces it with the updated one.
        att.removeModifier(mod);
        att.addPersistentModifier(mod);
    }
}
