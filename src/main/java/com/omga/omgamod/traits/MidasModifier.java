package com.omga.omgamod.traits;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class MidasModifier extends Modifier
{
    public static final float COEFFICIENT_PER_LEVEL = 0.25f;
    private static final Logger LOGGER = LogUtils.getLogger();
    public MidasModifier()
    {
        super();
    }

    @Override
    public void afterBlockBreak(IToolStackView tool, int level, ToolHarvestContext context) {
        if (context.canHarvest() && context.getTargetedState().is(Blocks.COBBLESTONE)) {
            float x = context.getTargetedPos().getX() + 0.5f;
            float y = context.getTargetedPos().getY() + 0.5f;
            float z = context.getTargetedPos().getZ() + 0.5f;

            ItemEntity e = new ItemEntity(context.getWorld(), x, y, z, new ItemStack(Items.GOLD_NUGGET, (int)(COEFFICIENT_PER_LEVEL * level * 4 * RANDOM.nextFloat() + 0.5f)));
            context.getWorld().addFreshEntity(e);

        }
    }
}
