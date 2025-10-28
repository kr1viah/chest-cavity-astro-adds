package net.astrospud.ccastroadds.specials;

import net.astrospud.ccastroadds.util.AstralCavityUtil;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.items.ChestOpener;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.List;
import java.util.Optional;

public class ChestOpenerDispenserBehavior extends ItemDispenserBehavior {
    @Override
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        ServerWorld world = pointer.getWorld();
        if (!world.isClient()) {
            BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
            tryMutilateEntity(world, blockPos, (ChestOpener)(stack.getItem()));
            //this.setSuccess(tryMutilateEntity(world, blockPos));
        }
        return stack;
    }

    private static void tryMutilateEntity(ServerWorld world, BlockPos pos, ChestOpener stack) {
        List<LivingEntity> list = world.getEntitiesByClass(LivingEntity.class, new Box(pos), EntityPredicates.EXCEPT_SPECTATOR);
        for (LivingEntity livingEntity : list) {
            /*if (livingEntity instanceof PlayerEntity player && CCRequiem.REQUIEM_ACTIVE) {
                livingEntity = PossessionComponent.getHost(player);
            }*///damn requiem users to heck
            if (openChestCavity(livingEntity)) {
                ChestCavityEntity ccE = (ChestCavityEntity) livingEntity;
                AstralCavityUtil.dropOrgans(livingEntity, ccE.getChestCavityInstance(), 1);
            }
        }
    }

    public static boolean openChestCavity(LivingEntity target) {
        Optional<ChestCavityEntity> optional = ChestCavityEntity.of(target);
        if (optional.isPresent()) {
            ChestCavityEntity chestCavityEntity = (ChestCavityEntity)optional.get();
            ChestCavityInstance cc = chestCavityEntity.getChestCavityInstance();
            if (!cc.getChestCavityType().isOpenable(cc)) {
                if (target.getWorld().isClient) {
                    if (!target.getEquippedStack(EquipmentSlot.CHEST).isEmpty()) {
                        //target.sendMessage(Text.literal("Target's chest is obstructed"), true);
                        target.playSound(SoundEvents.BLOCK_CHAIN_HIT, 1F, 0.75F);
                    } else {
                        //target.sendMessage(Text.literal("Target is too healthy to open"), true);
                        target.playSound(SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 1F, 0.75F);
                    }
                }

                return false;
            } else {
                if (cc.getOrganScore(CCOrganScores.EASE_OF_ACCESS) > 0.0F) {
                    if (target.getWorld().isClient) {
                        target.playSound(SoundEvents.BLOCK_CHEST_OPEN, 1F, 0.75F);
                    }
                } else {
                    target.damage(new DamageSource(target.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(DamageTypes.GENERIC)), 4.0F);
                }

                if (target.isAlive()) {
                    String name;
                    try {
                        name = target.getDisplayName().getString();
                        name = name.concat("'s ");
                    } catch (Exception var9) {
                        name = "";
                    }

                    ChestCavityInventory inv = ChestCavityUtil.openChestCavity(cc);
                    //((ChestCavityEntity)t).getChestCavityInstance().ccBeingOpened = cc;
                    /*player.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
                        return new ChestCavityScreenHandler(i, playerInventory, inv);
                    }, Text.translatable(name + "Chest Cavity")));*/
                }

                return true;
            }
        } else {
            return false;
        }
    }
}
