package com.omga.omgamod.content.items;

import com.mojang.logging.LogUtils;
import com.omga.omgamod.init.FluidInit;
import com.omga.omgamod.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import org.slf4j.Logger;
import slimeknights.tconstruct.fluids.TinkerFluids;

public class GoldsteelBlend extends Item {

    final short maxTimer = 20 * 5;
    short timerSinceHoney = -1;
    boolean blazingBlood = false;

    private static final Logger LOGGER = LogUtils.getLogger();

    public GoldsteelBlend(Properties p_41383_) {
        super(p_41383_);
    }

    public static final TagKey<Fluid> honey = FluidTags.create(new ResourceLocation("forge", "honey"));

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {

        BlockPos bp = entity.blockPosition();
        BlockState bs = entity.level.getBlockState(bp);
        if (bs.getFluidState().is(honey)) {
            timerSinceHoney = maxTimer;
            //LOGGER.debug("HONEY time");
        }

        if (timerSinceHoney == 0) {
            blazingBlood = false;
            return false;
        }

        if (bs.getFluidState().is(TinkerFluids.blazingBlood.get())) {
            blazingBlood = true;
            //LOGGER.debug("BLAZIN BLOD time");
        }
        if (blazingBlood && bs.getFluidState().is(TinkerFluids.moltenGold.get()) && bs.getFluidState().isSource()) {
            entity.level.setBlock(bp, Blocks.AIR.defaultBlockState(), 3);
            ItemStack newStack = new ItemStack(ItemInit.GOLDSTEEL_INGOT.get(), stack.getCount());
            entity.setItem(newStack);
            //LOGGER.debug("CONVERSION time");
            return false;
        }

        if (timerSinceHoney > 0) timerSinceHoney--;
        return false;
    }
}
