package net.astrospud.ccastroadds.listeners;

import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.astrospud.ccastroadds.registration.CCAAStatusEffects;
import net.astrospud.ccastroadds.specials.AmethystExplosion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.listeners.OrganOnHitCallback;
import net.tigereye.chestcavity.listeners.OrganOnHitListeners;
import net.tigereye.chestcavity.listeners.OrganTickCallback;
import net.tigereye.chestcavity.registration.CCOrganScores;

public class CCAAOrganOnHitListeners {
    public static void register(){
        OrganOnHitCallback.EVENT.register(CCAAOrganOnHitListeners::TickPanic);
        OrganOnHitCallback.EVENT.register(CCAAOrganOnHitListeners::TickResonance);
    }

    public static void TickPanic(LivingEntity attacker, LivingEntity entity, ChestCavityInstance notused){
        if (!(entity instanceof ChestCavityEntity)) { return; }
        ChestCavityInstance cc = ((ChestCavityEntity)entity).getChestCavityInstance();
        float panic = cc.getOrganScore(CCAAOrganScores.PANIC) - cc.getChestCavityType().getDefaultOrganScore(CCAAOrganScores.PANIC);
        if (panic <= 0 || entity.hasStatusEffect(CCAAStatusEffects.PANIC)) { return; }
        int power = (int) Math.floor(2 * Math.log10((panic) + 1));
        int duration = (int)(100 * Math.log10(2*panic -1))+100;
        entity.addStatusEffect(new StatusEffectInstance(CCAAStatusEffects.PANIC, duration, power, false, true, true));
    }

    public static void TickResonance(LivingEntity attacker, LivingEntity entity, ChestCavityInstance notused){
        if (!(entity instanceof ChestCavityEntity)) { return; }
        ChestCavityInstance cc = ((ChestCavityEntity)entity).getChestCavityInstance();
        float resonance = cc.getOrganScore(CCAAOrganScores.RESONANCE) - cc.getChestCavityType().getDefaultOrganScore(CCAAOrganScores.RESONANCE);
        if (resonance < 1) {
            return;
        }
        if (!entity.hasStatusEffect(CCAAStatusEffects.RESONANCE_COOLDOWN)) {
            entity.addStatusEffect(new StatusEffectInstance(CCAAStatusEffects.RESONANCE_COOLDOWN, (int)(75/(resonance/2)),0, false, false, true));

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
