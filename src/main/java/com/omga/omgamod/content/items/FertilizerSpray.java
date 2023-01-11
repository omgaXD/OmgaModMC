package com.omga.omgamod.content.items;

import com.omga.omgamod.init.ItemInit;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class FertilizerSpray extends BoneMealItem {
    public static final int DURABILITY = 64;
    public FertilizerSpray(Properties props) {
        super(props.defaultDurability(DURABILITY).durability(DURABILITY));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var item = context.getItemInHand();
        int count = item.getCount();
        InteractionResult result = super.useOn(context);
        if (item.getCount() < count) {
            item.setCount(count);
            item.hurtAndBreak(1, context.getPlayer(), (thing) -> {
                thing.broadcastBreakEvent(context.getHand());
                thing.setItemInHand(context.getHand(), new ItemStack(ItemInit.FERTILIZER_SPRAY_EMPTY.get()));
            });

        }
        return result;
    }

    public static class FertilizerSprayEmpty extends Item {
        public FertilizerSprayEmpty(Properties props) {
            super(props);
        }
    }
}
