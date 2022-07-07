package net.astrospud.ccastroadds.recipes;

import net.astrospud.ccastroadds.registration.CCAAItems;
import net.astrospud.ccastroadds.registration.CCAARecipes;
import net.astrospud.ccastroadds.specials.TumorItem;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tigereye.chestcavity.registration.CCItems;
import net.tigereye.chestcavity.registration.CCRecipes;
import net.tigereye.chestcavity.util.OrganUtil;

public class EditTumor extends SpecialCraftingRecipe {
    public EditTumor(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory craftingInventory, World world) {
        boolean foundTumor = false;
        boolean foundTumorEdits = false;
        for(int i = 0; i < craftingInventory.getWidth(); ++i) {
            for(int j = 0; j < craftingInventory.getHeight(); ++j) {
                ItemStack itemStack = craftingInventory.getStack(i + j * craftingInventory.getWidth());
                if (itemStack.getItem() == CCAAItems.TUMOR) {
                    if(foundTumor){
                        return false;
                    }
                    foundTumor = true;
                }
                else if(itemStack.getItem() == Items.ECHO_SHARD ||
                        itemStack.getItem() == Items.BONE){
                    if(foundTumorEdits){
                        return false;
                    }
                    foundTumorEdits = true;
                }
            }
        }
        return foundTumor&&foundTumorEdits;
    }

    @Override
    public ItemStack craft(CraftingInventory craftingInventory) {
        ItemStack tumor = null;
        ItemStack tumorEdit = null;
        ItemStack output = null;
        for(int i = 0; i < craftingInventory.getWidth(); ++i) {
            for(int j = 0; j < craftingInventory.getHeight(); ++j) {
                ItemStack itemStack = craftingInventory.getStack(i + j * craftingInventory.getWidth());
                if (itemStack.getItem() == CCAAItems.TUMOR) {
                    if(tumor != null){
                        return ItemStack.EMPTY;
                    }
                    tumor = itemStack;
                }
                else if(itemStack.getItem() == Items.BONE ||
                        itemStack.getItem() == Items.ECHO_SHARD){
                    if(tumorEdit != null){
                        return ItemStack.EMPTY;
                    }
                    tumorEdit = itemStack;
                }
            }
        }
        if(tumor != null && tumorEdit != null){
            output = tumor.copy();
            if (tumorEdit.getItem() == Items.BONE) {
                output.setCount(2);
            }
            if (tumorEdit.getItem() == Items.ECHO_SHARD) {
                output.setCount(4);
            }

            return output;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return (width * height >= 2);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CCAARecipes.EDIT_TUMOR;
    }
}