package net.astrospud.ccastroadds.specials;

import net.minecraft.block.CommandBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DoniItem extends Item{
    public DoniItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.ccastroadds.doniitem").formatted(Formatting.GOLD));
        tooltip.add(Text.translatable("tooltip.ccastroadds.right").formatted(Formatting.GRAY));
        //tooltip.add(Text.empty());
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient){
            user.sendMessage(Text.translatable("msg.ccastroadds.click").setStyle(Style.EMPTY.withUnderline(true).withFormatting(Formatting.GOLD).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/c/DoniBobesMC"))));
        }
        //new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/c/DoniBobesMC");
        //ClickEvent.Action.OPEN_URL
        return super.use(world, user, hand);
    }
}