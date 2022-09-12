package net.astrospud.ccastroadds.listeners;

import net.astrospud.ccastroadds.CCAstroAdds;
import net.astrospud.ccastroadds.registration.CCAAItems;
import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
        //int cooldown = CCAstroAdds.config.REGROWTH_COOLDOWN;
        //int cooldown = 20;
        if (growth <= 0 || !(entity.world.getTime() % CCAstroAdds.config.REGROWTH_COOLDOWN == 0)) {
            return;
        }
        ChestCavityInventory def = cc.getChestCavityType().getDefaultChestCavity();

        for (int g = 0; g < growth; g++) {
            for (int i = 0; i < def.size(); i++) {
                ItemStack organHas = cc.inventory.getStack(i);
                ItemStack organDefault = def.getStack(i);
                organDefault.setCount(organDefault.getMaxCount());
                if (organHas.isEmpty()) {
                    if (organDefault.isEmpty() || entity.getRandom().nextFloat() <= 0.25) {
                        organDefault = CCAAItems.BENIGN_TUMOR.getDefaultStack();
                    }
                    if (!organDefault.isEmpty() && !(entity instanceof PlayerEntity)) {
                        NbtCompound tag = new NbtCompound();
                        tag.putUuid("owner", cc.compatibility_id);
                        tag.putString("name", cc.owner.getDisplayName().getString());
                        organDefault.setSubNbt(ChestCavity.COMPATIBILITY_TAG.toString(), tag);
                    }
                    cc.inventory.setStack(i, organDefault);
                    break;
                }
            }
        }
    }

    /*public static void TickPlexis(LivingEntity entity, ChestCavityInstance chestCavity) {
        World world = entity.getWorld();
        boolean hasDayCycle = !world.getDimension().hasFixedTime();
        boolean isDay = world.isDay();
        boolean isNight = world.isNight();

        if (hasDayCycle) {
            if (isDay) {
                //solar boost
            }
            else if (isNight) {
                //lunar boost
            }
        }
        //eclipse boost

        long time = world.getTimeOfDay();
    }

    public static void organUpdate(ChestCavityInstance cc){
        Map<Identifier,Float> organScores = cc.getOrganScores();
        if(!cc.oldOrganScores.equals(organScores))
        {
            if(ChestCavity.DEBUG_MODE && cc.owner instanceof PlayerEntity) {
                ChestCavityUtil.outputOrganScoresString(System.out::println,cc);
            }
            OrganUpdateCallback.EVENT.invoker().onOrganUpdate(cc.owner, cc);
            cc.oldOrganScores.clear();
            cc.oldOrganScores.putAll(organScores);
            NetworkUtil.SendS2CChestCavityUpdatePacket(cc);
        }
    }*/

    /*public static void TickNerveFallDown(LivingEntity entity, ChestCavityInstance chestCavity) {
        float nerves = chestCavity.getOrganScore(CCOrganScores.NERVES);
        float defnerves = chestCavity.getChestCavityType().getDefaultOrganScore(CCOrganScores.NERVES);
        if (nerves <= 0 && defnerves > 0 && entity instanceof PlayerEntity player) {
            entity.setPose(EntityPose.SWIMMING);
            player.setBoundingBox(entity.getBoundingBox(EntityPose.SWIMMING));
        }
        else {
            entity.shouldLeaveSwimmingPose();
        }
    }*/
}
