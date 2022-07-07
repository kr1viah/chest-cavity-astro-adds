package net.astrospud.ccastroadds.specials;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.util.ChestCavityUtil;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static net.tigereye.chestcavity.util.ChestCavityUtil.openChestCavity;
import static net.tigereye.chestcavity.util.ChestCavityUtil.organUpdate;


public class TumorItem extends Item {
    public TumorItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("tooltip.ccastroadds.tumor"));
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        super.onCraft(stack, world, player);

        //player.jump();
        //player.addVelocity(0,0.5,0);

        //ChestCavityInstance cc = ((ChestCavityEntity)player).getChestCavityInstance();
        //Map<Identifier,Float> organScores = cc.getOrganScores();

        //openChestCavity(cc);//

        //ChestCavityUtil.addOrganScore(CCOrganScores.BUOYANT, 1, organScores);
        //ChestCavityUtil.organUpdate(cc);
    }
}
