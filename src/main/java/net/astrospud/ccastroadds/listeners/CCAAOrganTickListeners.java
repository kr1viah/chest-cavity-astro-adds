package net.astrospud.ccastroadds.listeners;

import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.astrospud.ccastroadds.registration.CCAAStatusEffects;
import net.astrospud.ccastroadds.specials.AmethystExplosion;
import net.minecraft.entity.Entity;
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

public class CCAAOrganTickListeners {

    public static void register(){
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickNeutralWaterBuoyant);
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickPanic);
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickResonance);
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

    public static void TickPanic(LivingEntity entity, ChestCavityInstance cc){
        float panic = cc.getOrganScore(CCAAOrganScores.PANIC) - cc.getChestCavityType().getDefaultOrganScore(CCAAOrganScores.PANIC);
        if (panic <= 0) { return; }
        DamageSource dmg = entity.getRecentDamageSource();
        if (dmg == null || entity.hasStatusEffect(CCAAStatusEffects.PANIC)) { return; }
        int power = (int) Math.floor(2 * Math.log10((2 * panic) + 1));
        entity.addStatusEffect(new StatusEffectInstance(CCAAStatusEffects.PANIC, 100, power, false, true, true));
    }

    public static void TickResonance(LivingEntity entity, ChestCavityInstance cc){
        float resonance = cc.getOrganScore(CCAAOrganScores.RESONANCE) - cc.getChestCavityType().getDefaultOrganScore(CCAAOrganScores.RESONANCE);
        DamageSource dmg = entity.getRecentDamageSource();
        if (resonance < 1 || dmg == null) {
            return;
        }
        Entity ent = dmg.getAttacker();
        if (ent instanceof LivingEntity && !entity.hasStatusEffect(CCAAStatusEffects.RESONANCE_COOLDOWN)) {
            entity.addStatusEffect(new StatusEffectInstance(CCAAStatusEffects.RESONANCE_COOLDOWN, 100,0, false, false, true));

            Position entityPos = entity.getPos();
            double x = entityPos.getX();
            double y = entityPos.getY();
            double z = entityPos.getZ();
            float power = 5;
            World entityWorld = entity.getWorld();

            AmethystExplosion explosion = new AmethystExplosion(entityWorld, entity, x, y, z, power);
            explosion.collectBlocksAndDamageEntities();
            if (!entityWorld.isClient) {
                float rand = entityWorld.random.nextFloat() * 1.2F;
                entityWorld.playSound((PlayerEntity)null, entity.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_HIT, SoundCategory.BLOCKS, 7.0F, 0.5F + rand);
                entityWorld.playSound((PlayerEntity)null, entity.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.BLOCKS, 7.0F, 0.5F + rand);
                entityWorld.playSound((PlayerEntity)null, entity.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.BLOCKS, 2.0F, 2.5F + rand);
                //entityWorld.playSound((PlayerEntity) null, entity.getBlockPos(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 0.75F, 3F + entityWorld.random.nextFloat() * 0.4F);
            }
        }
    }
}
