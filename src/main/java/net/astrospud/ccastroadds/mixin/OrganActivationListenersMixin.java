package net.astrospud.ccastroadds.mixin;

import net.astrospud.ccastroadds.listeners.CCAAOrganOnHitListeners;
import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.listeners.OrganActivationListeners;
import net.tigereye.chestcavity.registration.CCStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OrganActivationListeners.class)
public abstract class OrganActivationListenersMixin{
    @Inject(at = @At("HEAD"), method = "ActivateGrazing")
    private static void ccaaActivateGrazingMixin(LivingEntity entity, ChestCavityInstance cc, CallbackInfo info) {
        float grazing = cc.getOrganScore(CCAAOrganScores.EAT_THE_RICH);
        if(grazing <= 0){
            return;
        }
        BlockPos blockPos = entity.getBlockPos().down();
        boolean ateGrass = false;
        if (entity.world.getBlockState(blockPos).getBlock().getBlastResistance() <= 6
                && !entity.world.getBlockState(blockPos).hasBlockEntity()
                && !(entity.world.getBlockState(blockPos).getBlock().canMobSpawnInside())
                && !(entity.world.getBlockState(blockPos).getBlock() instanceof FluidBlock)){
            //entity.world.syncWorldEvent(2001, blockPos, Block.getRawIdFromState(Blocks.GRASS_BLOCK.getDefaultState()));
            entity.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
            ateGrass = true;
        }
        if(ateGrass){
            int duration;
            if(entity.hasStatusEffect(CCStatusEffects.RUMINATING)){
                StatusEffectInstance ruminating = entity.getStatusEffect(CCStatusEffects.RUMINATING);
                duration = (int)Math.min(ChestCavity.config.RUMINATION_TIME*ChestCavity.config.RUMINATION_GRASS_PER_SQUARE*ChestCavity.config.RUMINATION_SQUARES_PER_STOMACH*grazing,
                        ruminating.getDuration()+(ChestCavity.config.RUMINATION_TIME*ChestCavity.config.RUMINATION_GRASS_PER_SQUARE));
            }
            else{
                duration = ChestCavity.config.RUMINATION_TIME*ChestCavity.config.RUMINATION_GRASS_PER_SQUARE;
            }
            duration += 20 * entity.world.getBlockState(blockPos).getBlock().getBlastResistance();
            entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.RUMINATING, duration, 0, false, false, true));
        }
    }
}