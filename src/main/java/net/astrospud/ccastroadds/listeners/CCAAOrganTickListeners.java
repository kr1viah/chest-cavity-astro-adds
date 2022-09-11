package net.astrospud.ccastroadds.listeners;

import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.astrospud.ccastroadds.registration.CCAAStatusEffects;
import net.astrospud.ccastroadds.specials.AmethystExplosion;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.minecraft.world.gen.stateprovider.PredicatedStateProvider;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.listeners.OrganTickCallback;
import net.tigereye.chestcavity.listeners.OrganUpdateCallback;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;
import net.tigereye.chestcavity.util.NetworkUtil;
import org.spongepowered.include.com.google.common.base.Predicates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CCAAOrganTickListeners {

    public static void register(){
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickNeutralWaterBuoyant);
        OrganTickCallback.EVENT.register(CCAAOrganTickListeners::TickFlight);
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

    public static void TickThing(LivingEntity entity, ChestCavityInstance cc){
        //List<ItemStack> organs = new ArrayList<>();
        for (int i = 0; i < cc.getChestCavityType().getDefaultChestCavity().size(); i++) {
            ItemStack organ = cc.getChestCavityType().getDefaultChestCavity().getStack(i);
            //organs.add(organ);
            int count = organ.getCount();
            Item item = organ.getItem();
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
