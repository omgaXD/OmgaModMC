package com.omga.omgamod.content.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.commands.SaveOffCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class WoodsteelBlock extends Block {
    public WoodsteelBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, Random rand) {
        var bl = level.getBlockState(blockPos.above());
        if (!bl.is(BlockTags.SAPLINGS)) return;
        if (!bl.hasProperty(SaplingBlock.STAGE)) return;
        var sap = (SaplingBlock)bl.getBlock();
        sap.performBonemeal(level, rand, blockPos.above(), bl);
        //BoneMealItem
    }
}
