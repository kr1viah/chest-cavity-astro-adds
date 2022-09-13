package net.astrospud.ccastroadds.util;

import net.astrospud.ccastroadds.registration.CCAAItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;

import java.util.List;

public class AstralCavityUtil {
    static Item[] growable_tumors = {CCAAItems.BENIGN_TUMOR, CCAAItems.AUTOPHAGY_TUMOR};
    static List<Item> infectable_tumors = List.of(new Item[]{CCAAItems.BENIGN_TUMOR, CCAAItems.AUTOPHAGY_TUMOR});
    static List<Item> harmful_tumors = List.of(new Item[]{CCAAItems.AUTOPHAGY_TUMOR, CCAAItems.SCULK_TUMOR});

    public static void growBackOrgans(LivingEntity entity, ChestCavityInstance cc, float count) {
        count = MathHelper.ceil(count);
        ChestCavityInventory def = cc.getChestCavityType().getDefaultChestCavity();
        for (int i = 0; i < def.size() && count > 0; i++) {
            ItemStack organHas = cc.inventory.getStack(i);
            ItemStack organDefault = def.getStack(i);
            organDefault.setCount(organDefault.getMaxCount());
            if (organHas.isEmpty()) {
                if (organDefault.isEmpty() || entity.getRandom().nextFloat() <= 0.25 || organDefault.getItem() == Items.DIRT) {
                    organDefault = getTumor(entity);
                    organDefault.setCount(organDefault.getMaxCount());
                }
                if (!organDefault.isEmpty() && !(entity instanceof PlayerEntity)) {
                    NbtCompound tag = new NbtCompound();
                    tag.putUuid("owner", cc.compatibility_id);
                    tag.putString("name", cc.owner.getDisplayName().getString());
                    organDefault.setSubNbt(ChestCavity.COMPATIBILITY_TAG.toString(), tag);
                    organDefault.setCount(organDefault.getMaxCount());
                }
                organDefault.setCount(organDefault.getMaxCount());
                cc.inventory.setStack(i, organDefault);
                if (entity instanceof PlayerEntity player) {
                    if (organDefault.isFood() && organDefault.getItem().getFoodComponent() != null) {
                        player.addExhaustion(organDefault.getItem().getFoodComponent().getHunger()+organDefault.getItem().getFoodComponent().getSaturationModifier());
                    } else {
                        player.addExhaustion(0.25f);
                    }
                }
                count--;
            }
        }
    }

    public static void eatOrgans(LivingEntity entity, ChestCavityInstance cc, float count, boolean tumorous) {
        count = MathHelper.ceil(count);
        ChestCavityInventory def = cc.getChestCavityType().getDefaultChestCavity();
        for (int i = 0; i < def.size() && count > 0; i++) {
            ItemStack organHas = cc.inventory.getStack(i);
            if (organHas.isFood()) {
                //FoodComponent food = organHas.getItem().getFoodComponent();
                for (int g = 0; g < organHas.getCount() && count > 0; g++) {
                    if (entity instanceof PlayerEntity player && player.getHungerManager().isNotFull()) {
                       player.eatFood(player.getWorld(), organHas);
                        //organHas.decrement(1);
                        if (tumorous && organHas.getCount() <= 0 || organHas.isEmpty() && entity.getRandom().nextFloat() <= 0.5) {
                            organHas = getTumor(entity);
                        }
                        cc.inventory.setStack(i, organHas);
                    } else if (entity.getHealth() < entity.getMaxHealth()){
                        entity.heal(1);
                        organHas.decrement(1);
                        if (tumorous && organHas.getCount() <= 0 || organHas.isEmpty() && entity.getRandom().nextFloat() <= 0.5) {
                            organHas = getTumor(entity);
                        }
                        cc.inventory.setStack(i, organHas);
                    }
                    count--;
                }
            }
        }
    }

    public static void huntTumors(LivingEntity entity, ChestCavityInstance cc, float count) {
        count = MathHelper.ceil(count);
        ChestCavityInventory def = cc.getChestCavityType().getDefaultChestCavity();
        for (int i = 0; i < def.size() && count > 0; i++) {
            ItemStack organHas = cc.inventory.getStack(i);
            if (harmful_tumors.contains(organHas.getItem())) {
                organHas = ItemStack.EMPTY;
                cc.inventory.setStack(i, organHas);
                count--;
            }
        }
    }

    public static void infectOrgans(LivingEntity entity, ChestCavityInstance cc, float count) {
        count = MathHelper.ceil(count);
        ChestCavityInventory def = cc.getChestCavityType().getDefaultChestCavity();
        for (int i = 0; i < def.size() && count > 0; i++) {
            ItemStack organHas = cc.inventory.getStack(i);
            if (organHas.isFood() || infectable_tumors.contains(organHas.getItem())) {
                //FoodComponent food = organHas.getItem().getFoodComponent();
                for (int g = 0; g < organHas.getCount() && count > 0; g++) {
                    organHas = CCAAItems.SCULK_TUMOR.getDefaultStack();
                    cc.inventory.setStack(i, organHas);
                    count--;
                }
            }
        }
    }

    public static ItemStack getTumor(LivingEntity entity) {
        return growable_tumors[entity.getRandom().nextInt(growable_tumors.length)].getDefaultStack();
    }
}
