package net.astrospud.ccastroadds.mixin;

import net.astrospud.ccastroadds.listeners.CCAAOrganOnHitListeners;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCOrganScores;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Entity.class)
public abstract class CCAAEntityMixin {
	private ChestCavityInstance chestCavityInstance;
	protected CCAAEntityMixin() {
		super();
	}

	/*@Inject(at = @At("RETURN"), method = "shouldLeaveSwimmingPose", cancellable = true)
	public void ccaaShouldLeaveSwimmingPoseMixin(CallbackInfoReturnable<Boolean> cir) {
		if ((Object)this instanceof ChestCavityEntity entity) {
			ChestCavityInstance chestCavity = entity.getChestCavityInstance();
			float nerves = chestCavity.getOrganScore(CCOrganScores.NERVES);
			float defnerves = chestCavity.getChestCavityType().getDefaultOrganScore(CCOrganScores.NERVES);
			if (nerves <= 0 && defnerves > 0) {
				if (entity instanceof PlayerEntity c && c.getAbilities().flying) {
					return;
				}
				cir.setReturnValue(false);
			}
		}
	}*/
}