package com.omga.omgamod.content.blocks;

import com.mojang.logging.LogUtils;
import com.omga.omgamod.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Random;

public class SkysteelBlock extends Block {

    private static final Logger LOGGER = LogUtils.getLogger();
    public static final BooleanProperty LIT = RedstoneLampBlock.LIT;

    public SkysteelBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.valueOf(false)));
    }
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_55659_) {
        return this.defaultBlockState().setValue(LIT, Boolean.valueOf(p_55659_.getLevel().hasNeighborSignal(p_55659_.getClickedPos())));
    }


    public void neighborChanged(BlockState bs, Level level, BlockPos bp, Block p_55669_, BlockPos p_55670_, boolean p_55671_) {
        boolean changed = false;
        if (!level.isClientSide) {
            boolean flag = bs.getValue(LIT);
            if (flag != level.hasNeighborSignal(bp)) {
                if (flag) {
                    level.scheduleTick(bp, this, 4);
                } else {
                    level.setBlock(bp, bs.cycle(LIT), 2);
                }
                changed = true;
            }
            if (!changed) return;
            level.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(bs.getValue(LIT), level.getServer());
            level.playSound(null, bp.getX() + 0.5d, bp.getY() + 0.5d, bp.getZ() + 0.5d, flag ? SoundInit.TIME_RESUME : SoundInit.TIME_STOP, SoundSource.BLOCKS, 1.0f, 1.0f);
            //LOGGER.debug("LET'S GOOO NOW THE DAY'S GONNA BE " + level.getGameRules().getRule(GameRules.RULE_DAYLIGHT).get());

        }


    }
    public void tick(BlockState p_55661_, ServerLevel server, BlockPos p_55663_, Random p_55664_) {
        if (p_55661_.getValue(LIT) && !server.hasNeighborSignal(p_55663_)) {
            server.setBlock(p_55663_, p_55661_.cycle(LIT), 2);
        }
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55673_) {
        p_55673_.add(LIT);
    }
}
