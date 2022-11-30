package com.omga.omgamod.blocks;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.block.ProperWaterloggedBlock;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.ArrayVoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

@MethodsReturnNonnullByDefault
public class PrismasteelBlock extends Block implements SimpleWaterloggedBlock {
    private static final Logger LOGGER = LogUtils.getLogger();
    protected static final VoxelShape INNER = Block.box(3.0D, 3.0D, 3.0D, 13.0D, 13.0D, 13.0D);

    protected static final VoxelShape OUTER = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 2.0D),
                                                        Block.box(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 16.0D),
                                                        Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 2.0D),
                                                        Block.box(2.0D ,14.0D, 14.0D, 16.0D, 16.0D, 16.0D),
                                                        Block.box(14.0D, 14.0D, 2.0D, 16.0D, 16.0D, 16.0D),
                                                        Block.box(14.0D, 2.0D, 14.0D, 16.0D, 16.0D, 16.0D),

                                                        Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 2.0D),
                                                        Block.box(0.0D, 14.0D, 0.0D, 2.0D, 16.0D, 16.0D),

                                                        Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D),
                                                        Block.box(14.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),

                                                        Block.box(0.0D, 0.0D, 14.0D, 16.0D, 2.0D, 16.0D),
                                                        Block.box(0.0D, 0.0D, 14.0D, 2.0D, 16.0D, 16.0D)

    );

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public PrismasteelBlock(Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, Boolean.FALSE));
    }
    @Override
    public VoxelShape getShape(BlockState p_52122_, BlockGetter p_52123_, BlockPos p_52124_, CollisionContext p_52125_) {
        return Shapes.or(INNER, OUTER);
    }
    public boolean useShapeForLightOcclusion(BlockState p_56395_) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(WATERLOGGED));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49820_) {
        return super.getStateForPlacement(p_49820_).setValue(WATERLOGGED, Boolean.TRUE);
    }
    @Override
    public FluidState getFluidState(BlockState bs) {
        //bs.setValue(WATERLOGGED, true);
        return bs.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(true) : super.getFluidState(bs);
    }
    @Override
    public boolean placeLiquid(LevelAccessor la, BlockPos bp, BlockState bs, FluidState fs) {
        LOGGER.debug("HOLY CRAP LETS GO LIQUID PLCAED (" + fs.getType().getRegistryName() + " btw)");
        if (fs.getType() == Fluids.WATER) {
            if (!la.isClientSide()) {
                la.setBlock(bp, bs.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)), 3);
                la.scheduleTick(bp, fs.getType(), fs.getType().getTickDelay(la));
            }

            LOGGER.debug("H,,mmmm dam no cap thers " + bs.getFluidState().getType().getRegistryName() + " liquid!!");
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean canPlaceLiquid(BlockGetter p_56363_, BlockPos p_56364_, BlockState p_56365_, Fluid p_56366_) {
        return p_56366_.isSame(Fluids.WATER);
    }

    @Override
    public void onPlace(BlockState bs, Level l, BlockPos bp, BlockState bs2, boolean b) {
        placeLiquid(l, bp, bs, Fluids.WATER.getSource(true));
    }

    @Override
    public BlockState updateShape(BlockState p_56381_, Direction p_56382_, BlockState p_56383_, LevelAccessor p_56384_, BlockPos p_56385_, BlockPos p_56386_) {
        p_56384_.scheduleTick(p_56385_, Fluids.WATER, Fluids.WATER.getTickDelay(p_56384_));
        return super.updateShape(p_56381_, p_56382_, p_56383_, p_56384_, p_56385_, p_56386_);
    }

    @Override
    public ItemStack pickupBlock(LevelAccessor p_154560_, BlockPos p_154561_, BlockState p_154562_) {
        if (p_154562_.getValue(BlockStateProperties.WATERLOGGED)) {
            return new ItemStack(Items.WATER_BUCKET);
        } else {
            return ItemStack.EMPTY;
        }
    }

}
