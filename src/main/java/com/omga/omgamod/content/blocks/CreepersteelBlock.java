package com.omga.omgamod.content.blocks;

import com.mojang.logging.LogUtils;
import com.omga.omgamod.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.*;

@Mod.EventBusSubscriber
public class CreepersteelBlock extends Block {

    private static final Logger LOGGER = LogUtils.getLogger(); // remember son: if you feel like you're gonna have problems, this line gotta be first one
    public CreepersteelBlock(Properties p_49795_) {
        super(p_49795_);
    }

    public void onPlace(BlockState blockstate1, Level level, BlockPos bp, BlockState block2, boolean flag) {
        if (!block2.is(blockstate1.getBlock())) {
            int power = level.getBestNeighborSignal(bp);
            if (power > 0) {
                scheduleTrigger(level, blockstate1, bp, power);
            }

        }
    }

    public void neighborChanged(BlockState blockstate1, Level level, BlockPos bp, Block p_57460_, BlockPos p_57461_, boolean p_57462_) {
        int power = level.getBestNeighborSignal(bp);
        if (power > 0) {
            scheduleTrigger(level, blockstate1, bp, power);
        }
    }



    static Stack<ScheduledExplosion> explosions = new Stack<>();
    static Boolean waitForIt = true;
    public void scheduleTrigger(Level level, BlockState bs, BlockPos bp, int power) {
        waitForIt = true;
        var e = new ScheduledExplosion(level, bs, bp, power);
        explosions.add(e);
        //LOGGER.debug("Did the thing!!!");
    }
    @SubscribeEvent
    public static void tick(TickEvent.WorldTickEvent event) {
        if (event.side.isClient()) return;
        if (waitForIt) {
            waitForIt = false; return;
        }
        if (explosions.empty()) return;

        landmineTrigger(explosions.pop());

        if (explosions.size() > 32) explosions.clear(); // little optimization. might cause bugs but at least won't crash game :)
    }
    public static void landmineTrigger(ScheduledExplosion s) {
        //LOGGER.debug("trynna do the thing");
        if (s.bs.is(BlockInit.CREEPERSTEEL_BLOCK.get())) {
            float explosiveEffect = 3.75F + s.power / 4F;
            s.level.removeBlock(s.bp, false);
            Entity entity = null;
            if (s.power == 15) {
                entity = new Creeper(EntityType.CREEPER, s.level){
                    @Override
                    public boolean canDropMobsSkull() {
                        return true;
                    }
                };
            }
            s.level.explode(entity, s.bp.getX() + 0.5D, s.bp.getY() + 0.5D, s.bp.getZ() + 0.5D, explosiveEffect, Explosion.BlockInteraction.DESTROY);
            s.level.setBlock(s.bp, BlockInit.CREEPERSTEEL_SLAB.get().defaultBlockState(), 3);
        }

    }
    public class ScheduledExplosion {
        Level level;
        BlockState bs;
        BlockPos bp;
        int power;
        public ScheduledExplosion(Level level, BlockState bs, BlockPos bp, int power) {
            this.level = level;
            this.bs = bs;
            this.bp = bp;
            this.power = power;
        }
    }

}
