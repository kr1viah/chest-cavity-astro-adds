package net.astrospud.ccastroadds.mixin;

import net.astrospud.ccastroadds.listeners.CCAAOrganOnHitListeners;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.listeners.OrganFoodEffectCallback;
import net.tigereye.chestcavity.registration.CCOrganScores;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.mojang.datafixers.util.Pair;
import net.minecraft.item.ItemStack;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Mixin(LivingEntity.class)
public abstract class CCAALivingEntityMixin extends Entity implements ChestCavityEntity {
	private ChestCavityInstance chestCavityInstance;
	protected CCAALivingEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}
	@Inject(at = @At("RETURN"), method = "damage")
	private void ccaaDamageMixin(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
		if (info.getReturnValue()) {
			CCAAOrganOnHitListeners.TickPanic((LivingEntity) (Object) this);
		}
	}

	@Inject(at = @At("RETURN"), method = "isInSwimmingPose", cancellable = true)
	public void ccaaIsInSwimmingPoseMixin(CallbackInfoReturnable<Boolean> cir) {
		if ((Object)this instanceof ChestCavityEntity entity) {
			ChestCavityInstance chestCavity = entity.getChestCavityInstance();
			float nerves = chestCavity.getOrganScore(CCOrganScores.NERVES);
			float defnerves = chestCavity.getChestCavityType().getDefaultOrganScore(CCOrganScores.NERVES);
			if (nerves <= 0 && defnerves > 0) {
				if (entity instanceof PlayerEntity c && c.getAbilities().flying) {
					return;
				}
				this.setSwimming(true);
				cir.setReturnValue(true);
				if (entity instanceof PlayerEntity c) {
					if (this.wouldPoseNotCollide(EntityPose.SWIMMING)) {
						EntityPose entityPose = EntityPose.SWIMMING;

						EntityPose entityPose2;
						if (!this.isSpectator() && !this.hasVehicle() && !this.wouldPoseNotCollide(entityPose)) {
							if (this.wouldPoseNotCollide(EntityPose.CROUCHING)) {
								entityPose2 = EntityPose.CROUCHING;
							} else {
								entityPose2 = EntityPose.SWIMMING;
							}
						} else {
							entityPose2 = entityPose;
						}

						this.setPose(entityPose2);
					}
				}
			}
		}
	}
}