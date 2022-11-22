package com.omga.omgamod.blocks;

import com.mojang.logging.LogUtils;
import com.omga.omgamod.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GoldsteelBlock extends Block {
    private static final Logger LOGGER = LogUtils.getLogger();
    public GoldsteelBlock(Properties p_49795_) {
        super(p_49795_);
    }

    /*
    // lord forgive me for what I am about to code
    @Override
    public void wasExploded(Level level, BlockPos blockPos, Explosion explosion) {
        List<BlockPos> goldsteelBlocks = new ArrayList<BlockPos>();
        LOGGER.debug("HOLY SHIT IT FIRED");
        for (int i = 0; i < explosion.getToBlow().size(); i++) {
            var b = explosion.getToBlow().get(i);
            if (level.getBlockState(b).is(BlockInit.GOLDSTEEL_BLOCK.get())) {
                goldsteelBlocks.add(b);
            }

        }
        BlockPos expPos = new BlockPos(explosion.getPosition());
        LOGGER.debug("HOLY SHIT THE AMOUNT IS IS " + goldsteelBlocks.size());
        if (goldsteelBlocks.size() != 3) return;
        level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
        goldsteelBlocks.forEach(b -> {
            level.setBlock(b, Blocks.AIR.defaultBlockState(), 3);
            //explosion.getToBlow().set(explosion.getToBlow().indexOf(b), expPos);
        });
        level.setBlock(expPos, BlockInit.CREEPERSTEEL_BLOCK.get().defaultBlockState(), 3);
    }
    */
}
