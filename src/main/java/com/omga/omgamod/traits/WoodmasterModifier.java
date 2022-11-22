package com.omga.omgamod.traits;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.slf4j.Logger;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class WoodmasterModifier extends Modifier
{
    public static final float COEFFICIENT_PER_LEVEL = 0.01f;
    private static final Logger LOGGER = LogUtils.getLogger();
    public WoodmasterModifier()
    {
        super();
    }


    public static final Item[] drops = new Item[]{
            Items.OAK_LOG, Items.BIRCH_LOG,
            Items.ACACIA_LOG, Items.DARK_OAK_LOG,
            Items.SPRUCE_LOG, Items.JUNGLE_LOG,
            Items.OAK_PLANKS, Items.BIRCH_PLANKS,
            Items.ACACIA_PLANKS, Items.DARK_OAK_PLANKS,
            Items.SPRUCE_PLANKS, Items.JUNGLE_PLANKS,

    };

    @Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        int res = super.afterEntityHit(tool, level, context, damageDealt);
        //LOGGER.debug("SPARE THE SYMPATHY");
        if (!(context.getAttacker() instanceof Player p)) return res;
        //LOGGER.debug("SPARE THE SYMPATHY!!!");
        if (RANDOM.nextFloat() <= COEFFICIENT_PER_LEVEL * level * damageDealt) {
            Vec3 coords = context.getTarget().position();
            ItemEntity entity = new ItemEntity(p.level, coords.x, coords.y, coords.z, new ItemStack(drops[RANDOM.nextInt(12)], RANDOM.nextInt(level) + 1));
            p.level.addFreshEntity(entity);
            //LOGGER.debug("spawned thing");
        }

        return res;
    }
}
