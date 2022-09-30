package net.astrospud.ccastroadds.listeners;

import net.astrospud.ccastroadds.CCAstroAdds;
import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.astrospud.ccastroadds.util.AstralCavityUtil;
import net.astrospud.ccastroadds.util.KeyBindingToggleUtil;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.listeners.OrganTickCallback;

public class CCAAOrganTickListeners {
    public static void register(){
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickNeutralWaterBuoyant);
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickFlight);
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickRegrowth);
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickTumorAutophagy);
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickTumorHunt);
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickSculkInfection);
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickAutophagy);
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
        if (entity instanceof PlayerEntity player && player instanceof ChestCavityEntity e && e.getChestCavityInstance().getOrganScore(CCAAOrganScores.FLIGHT) > 0) {
            KeyBindingToggleUtil.tick();

            if (player.getAbilities().flying) {
                KeyBindingToggleUtil.isFlying = false;
            }
            Input input = new Input();
            if (player instanceof ClientPlayerEntity client) {
                input = client.input;
            }
            if (KeyBindingToggleUtil.isFlying) {
                Vec2f vec2f = input.getMovementInput();
                float f = player.getMovementSpeed();
                float h = f * vec2f.x;
                float i = f * vec2f.y;
                float j = MathHelper.sin(player.getYaw() * 0.017453292F);
                float k = MathHelper.cos(player.getYaw() * 0.017453292F);
                Vec2f vec2d = new Vec2f((h * k - i * j), (i * k + h * j));
                double motionX = vec2d.x * 5;
                double motionZ = vec2d.y * 5;
                double motionY = input.jumping ? 0.4 : input.sneaking ? -0.4 : 0;
                player.setVelocity(motionX, motionY, motionZ);
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

    public static void TickTumorAutophagy(LivingEntity entity, ChestCavityInstance cc){
        float autophagy = MathHelper.ceil(cc.getOrganScore(CCAAOrganScores.TUMOR_AUTOPHAGY));

        if (autophagy <= 0 || entity.age % CCAstroAdds.config.AUTOPHAGY_COOLDOWN != 0) {
            return;
        }

        AstralCavityUtil.eatOrgans(entity, cc, autophagy, true);
    }

    public static void TickTumorHunt(LivingEntity entity, ChestCavityInstance cc){
        float hunting = MathHelper.ceil(cc.getOrganScore(CCAAOrganScores.TUMOR_HUNTING));

        if (hunting <= 0 || entity.age % CCAstroAdds.config.TUMOR_HUNTING_COOLDOWN != 0) {
            return;
        }

        AstralCavityUtil.huntTumors(entity, cc, hunting);
    }

    public static void TickSculkInfection(LivingEntity entity, ChestCavityInstance cc){
        float sculk_infection = MathHelper.ceil(cc.getOrganScore(CCAAOrganScores.SCULK_INFECTION));

        if (sculk_infection <= 0 || entity.age % CCAstroAdds.config.SCULK_INFECTION_COOLDOWN != 0) {
            return;
        }

        AstralCavityUtil.infectOrgans(entity, cc, sculk_infection);
    }

    public static void TickAutophagy(LivingEntity entity, ChestCavityInstance cc){
        float autophagy = MathHelper.ceil(cc.getOrganScore(CCAAOrganScores.AUTOPHAGY));

        if (autophagy <= 0 || entity.age % CCAstroAdds.config.AUTOPHAGY_COOLDOWN != 0) {
            return;
        }

        AstralCavityUtil.eatOrgans(entity, cc, autophagy, false);
        AstralCavityUtil.drinkMilk(entity, cc);
    }
}
