package com.omga.omgamod.content.items.tools;

import com.omga.omgamod.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class FertilizerSpray extends BoneMealItem {
    public static final int DURABILITY = 64;
    public FertilizerSpray(Properties props) {
        super(props.defaultDurability(DURABILITY).durability(DURABILITY));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var item = context.getItemInHand();
        int count = item.getCount();
        Level lvl = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        if (lvl.getBlockState(clickedPos).is(Blocks.DIRT)) {
            if (!lvl.getBlockState(clickedPos.above()).isAir()) return InteractionResult.FAIL;
            lvl.setBlockAndUpdate(clickedPos, Blocks.GRASS_BLOCK.defaultBlockState());
            setCountAndBreak(count, item, context);
        }

        InteractionResult result = super.useOn(context);
        if (item.getCount() < count) {
            setCountAndBreak(count, item, context);
        }
        return result;
    }

    private void setCountAndBreak(int count, ItemStack item, UseOnContext context) {
        item.setCount(count);
        item.hurtAndBreak(1, context.getPlayer(), (thing) -> {
            thing.broadcastBreakEvent(context.getHand());
            thing.setItemInHand(context.getHand(), new ItemStack(ItemInit.FERTILIZER_SPRAY_EMPTY.get()));
        });
    }

    public static class FertilizerSprayEmpty extends Item {
        public FertilizerSprayEmpty(Properties props) {
            super(props);
        }
    }
}
