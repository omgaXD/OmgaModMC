package com.omga.omgamod.content.blocks;

import com.omga.omgamod.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class MagmatiteChargerBlock extends BaseEntityBlock {
    public MagmatiteChargerBlock(BlockBehaviour.Properties p_55657_) {
        super(p_55657_);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.FALSE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> definition) {
        definition.add(LIT);
    }

    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public BlockState getStateForPlacement(BlockPlaceContext p_48689_) {
        return this.defaultBlockState();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new MagmatiteChargerEntity(p_153215_, p_153216_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState bs, BlockEntityType<T> blockEntityType) {
        return createMagmatiteTicker(level, blockEntityType, BlockEntityInit.MAGMATITE_CHARGER.get());
    }
    @javax.annotation.Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createMagmatiteTicker(Level level, BlockEntityType<T> type, BlockEntityType<MagmatiteChargerEntity> mgmt) {
        return level.isClientSide ? null : createTickerHelper(type, mgmt, MagmatiteChargerEntity::serverTick);
    }
    @Override
    public @NotNull RenderShape getRenderShape(BlockState p_48727_) {
        return RenderShape.MODEL;
    }
    @Override
    public InteractionResult use(BlockState p_48706_, Level p_48707_, BlockPos p_48708_, Player p_48709_, InteractionHand p_48710_, BlockHitResult p_48711_) {
        if (p_48707_.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            this.openContainer(p_48707_, p_48708_, p_48709_);
            return InteractionResult.CONSUME;
        }
    }
    protected void openContainer(Level p_53631_, BlockPos p_53632_, Player p_53633_) {
        BlockEntity blockentity = p_53631_.getBlockEntity(p_53632_);
        if (blockentity instanceof MagmatiteChargerEntity) {
            p_53633_.openMenu((MenuProvider)blockentity);
            p_53633_.awardStat(Stats.INTERACT_WITH_FURNACE);
        }

    }
    @Override
    public void animateTick(BlockState p_53635_, Level p_53636_, BlockPos p_53637_, Random p_53638_) {
        if (p_53635_.getValue(LIT)) {
            double d0 = (double)p_53637_.getX() + 0.5D;
            double d1 = (double)p_53637_.getY();
            double d2 = (double)p_53637_.getZ() + 0.5D;
            if (p_53638_.nextDouble() < 0.1D) {
                p_53636_.playLocalSound(d0, d1, d2, SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }
            if (p_53638_.nextDouble() < 0.05D) {
                p_53636_.playLocalSound(d0, d1, d2, SoundEvents.LAVA_AMBIENT, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }
            double d5 = p_53638_.nextDouble() * 0.6D - 0.3D;
            double d6 = p_53638_.nextDouble() * 6.0D / 16.0D;
            double d7 = p_53638_.nextDouble() * 0.6D - 0.3D;
            p_53636_.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            p_53636_.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }
}

