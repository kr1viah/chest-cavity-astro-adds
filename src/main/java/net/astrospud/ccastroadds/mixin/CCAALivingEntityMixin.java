package net.astrospud.ccastroadds.mixin;

import net.astrospud.ccastroadds.listeners.CCAAOrganOnHitListeners;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.listeners.OrganFoodEffectCallback;
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
}
