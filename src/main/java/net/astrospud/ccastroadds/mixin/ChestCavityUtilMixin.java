package net.astrospud.ccastroadds.mixin;

import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.tag.TagKey;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.tigereye.chestcavity.chestcavities.ChestCavityType;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.chestcavities.organs.OrganData;
import net.tigereye.chestcavity.chestcavities.organs.OrganManager;
import net.tigereye.chestcavity.listeners.OrganOnHitContext;
import net.tigereye.chestcavity.listeners.OrganOnHitListener;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCTagOrgans;
import net.tigereye.chestcavity.util.ChestCavityUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.Map;

import static net.tigereye.chestcavity.util.ChestCavityUtil.*;

@Mixin(ChestCavityUtil.class)
public abstract class ChestCavityUtilMixin {
    @Inject(at = @At("HEAD"), method = "evaluateChestCavity")
    public static void ccaaevaluateChestCavity(ChestCavityInstance cc, CallbackInfo cir) {
        Map<Identifier,Float> organScores = cc.getOrganScores();
        if(cc.opened){
            cc.onHitListeners.clear();
            cc.getChestCavityType().loadBaseOrganScores(organScores);

            for (int i = 0; i < cc.inventory.size(); i++) {
                ItemStack itemStack = cc.inventory.getStack(i);
                if (!itemStack.isEmpty() && itemStack.getItem() == Items.SHULKER_BOX) {
                    Item slotitem = itemStack.getItem();
                    NbtCompound nbt = itemStack.getOrCreateNbt();
                    if (nbt.contains("Items", NbtElement.LIST_TYPE)) {
                        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(27, ItemStack.EMPTY);
                        Inventories.readNbt(nbt, defaultedList);
                        for (ItemStack itemStack1 : defaultedList) {
                            if (itemStack1.isEmpty()) continue;
                            OrganData data = lookupOrgan(itemStack,cc.getChestCavityType());
                            if (data != null) {
                                data.organScores.forEach((key, value) ->
                                        addOrganScore(key, 0.037037037f * value * Math.min(((float)itemStack1.getCount()) / itemStack1.getMaxCount(),1),organScores)
                                );
                                if(slotitem instanceof OrganOnHitListener){
                                    cc.onHitListeners.add(new OrganOnHitContext(itemStack1,(OrganOnHitListener)slotitem));
                                }
                                if (!data.pseudoOrgan) {
                                    int compatibility = getCompatibilityLevel(cc,itemStack1);
                                    if(compatibility < 1){
                                        addOrganScore(CCOrganScores.INCOMPATIBILITY, 1, organScores);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        organUpdate(cc);
    }

    protected static OrganData lookupOrgan(ItemStack itemStack, ChestCavityType cct) {
        OrganData organData = cct.catchExceptionalOrgan(itemStack);
        if (organData != null) {
            return organData;
        } else if (OrganManager.hasEntry(itemStack.getItem())) {
            return OrganManager.getEntry(itemStack.getItem());
        } else {
            Iterator var3 = CCTagOrgans.tagMap.keySet().iterator();

            TagKey itemTag;
            do {
                if (!var3.hasNext()) {
                    return null;
                }

                itemTag = (TagKey)var3.next();
            } while(!itemStack.isIn(itemTag));

            organData = new OrganData();
            organData.pseudoOrgan = true;
            organData.organScores = (Map)CCTagOrgans.tagMap.get(itemTag);
            return organData;
        }
    }
}
