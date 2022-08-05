package net.astrospud.ccastroadds.listeners;

import net.astrospud.ccastroadds.registration.CCAAOrganScores;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.tigereye.chestcavity.listeners.OrganFoodEffectCallback;
import net.tigereye.chestcavity.listeners.OrganFoodEffectListeners;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCTags;

import java.util.List;

public class CCAAOrganFoodEffectListeners {
    public static void register(){
        OrganFoodEffectCallback.EVENT.register(CCAAOrganFoodEffectListeners::applyIrongut);
    }

    private static List<Pair<StatusEffectInstance, Float>> applyIrongut(List<Pair<StatusEffectInstance, Float>> list, ItemStack itemStack, World world, LivingEntity entity, ChestCavityInstance cc) {
        float rotten = cc.getOrganScore(CCAAOrganScores.IRON_GUT);
        if(rotten > 0) {
            list.removeIf(pair -> pair.getFirst().getEffectType().getCategory() == (StatusEffectCategory.HARMFUL));
        }
        return list;
    }
}
