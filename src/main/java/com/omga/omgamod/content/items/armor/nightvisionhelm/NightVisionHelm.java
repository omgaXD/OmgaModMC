package com.omga.omgamod.content.items.armor.nightvisionhelm;

import com.omga.omgamod.init.ItemInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class NightVisionHelm extends ArmorItem {
    public NightVisionHelm(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_) {
        super(p_40386_, p_40387_, p_40388_);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!(entityIn instanceof Player player)) return;
        if (player.getItemBySlot(EquipmentSlot.HEAD).is(stack.getItem())) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0, true, false));
        }
    }

}
