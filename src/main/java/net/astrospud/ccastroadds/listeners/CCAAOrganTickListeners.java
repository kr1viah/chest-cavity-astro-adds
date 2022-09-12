package net.astrospud.ccastroadds.listeners;

import net.astrospud.ccastroadds.CCAstroAdds;
import net.astrospud.ccastroadds.registration.CCAAItems;
import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.astrospud.ccastroadds.util.AstralCavityUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.listeners.OrganTickCallback;

public class CCAAOrganTickListeners {
    public static void register(){
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickNeutralWaterBuoyant);
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickFlight);
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickRegrowth);
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickAutophagy);
//OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickPlexis);
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
        //TODO: Use PAL for flight instead of breaking compatibility
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

    public static void TickRegrowth(LivingEntity entity, ChestCavityInstance cc){
        float growth = cc.getOrganScore(CCAAOrganScores.REGROWTH) - cc.getChestCavityType().getDefaultOrganScore(CCAAOrganScores.REGROWTH);

        if (growth <= 0 || entity.age % CCAstroAdds.config.REGROWTH_COOLDOWN != 0) {
            return;
        }

        AstralCavityUtil.growBackOrgans(entity, cc, growth);
    }

    public static void TickAutophagy(LivingEntity entity, ChestCavityInstance cc){
        float autophagy = MathHelper.ceil(cc.getOrganScore(CCAAOrganScores.AUTOPHAGY));

        if (autophagy <= 0 || entity.age % CCAstroAdds.config.AUTOPHAGY_COOLDOWN != 0) {
            return;
        }

        AstralCavityUtil.eatOrgans(entity, cc, autophagy);
    }
}
