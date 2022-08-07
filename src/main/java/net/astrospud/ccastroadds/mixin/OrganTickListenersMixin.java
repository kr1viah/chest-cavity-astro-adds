package net.astrospud.ccastroadds.mixin;

import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.listeners.OrganActivationListeners;
import net.tigereye.chestcavity.listeners.OrganTickListeners;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OrganTickListeners.class)
public abstract class OrganTickListenersMixin {
    @Inject(at = @At("HEAD"), method = "TickBuoyant", cancellable = true)
    private static void CCAATickBuoyantMixin(LivingEntity entity, ChestCavityInstance chestCavity, CallbackInfo cir){
        float buoyancy = chestCavity.getOrganScore(CCAAOrganScores.NEUTRAL_WATER_BUOYANT) - chestCavity.getChestCavityType().getDefaultOrganScore(CCAAOrganScores.NEUTRAL_WATER_BUOYANT);
        if (buoyancy > 0 && (entity.isTouchingWater() || entity.isInLava())) {
            cir.cancel();
        }
    }
}