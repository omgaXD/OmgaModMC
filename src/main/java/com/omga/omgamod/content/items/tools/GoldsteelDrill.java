package com.omga.omgamod.content.items.tools;

import com.omga.omgamod.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

public class GoldsteelDrill extends PickaxeItem {

    public GoldsteelDrill(int p_42962_, float p_42963_, Properties p_42964_) {
        super(new Tier() {
            @Override
            public int getUses() {
                return 1000;
            }

            @Override
            public float getSpeed() {
                return 18f;
            }

            @Override
            public float getAttackDamageBonus() {
                return 0;
            }

            @Override
            public int getLevel() {
                return 0;
            }

            @Override
            public int getEnchantmentValue() {
                return 0;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of(ItemInit.GOLDSTEEL_INGOT.get());
            }
        }, p_42962_, p_42963_, p_42964_);
        //Items.GOLDEN_PICKAXE
    }

    @Override
    public float getDestroySpeed(ItemStack p_41004_, BlockState block) {
        float mod = (block.is(Tags.Blocks.STONE) || block.is(Tags.Blocks.COBBLESTONE)) ? 16 : 1;
        return super.getDestroySpeed(p_41004_, block) * mod;
    }
}
