package com.omga.omgamod.items;

import com.omga.omgamod.init.ItemInit;
import com.simibubi.create.content.curiosities.armor.BackTankUtil;
import com.simibubi.create.content.curiosities.weapons.PotatoCannonItem;
import com.simibubi.create.content.curiosities.weapons.PotatoProjectileTypeManager;
import com.simibubi.create.foundation.config.AllConfigs;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.Optional;
import java.util.function.Predicate;

public class TntCannon extends ProjectileWeaponItem {
    public static final int DEFAULT_RANGE = 15;

    public TntCannon(Properties p_43009_) {
        super(p_43009_.defaultDurability(128).durability(128));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> {
            return stack.is(Items.TNT) || stack.is(Items.TNT_MINECART);
        };
    }

    @Override
    public int getDefaultProjectileRange() {
        return DEFAULT_RANGE;
    }

    public InteractionResult useOn(UseOnContext context) {
        return this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult();
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        ItemStack proj = player.getProjectile(stack);
        boolean cr = (player.getAbilities().instabuild);

        if (!proj.isEmpty() || cr) {
            if (proj.isEmpty()) {
                proj = new ItemStack(Items.TNT);
            }
            if (!world.isClientSide) {
                ArrowItem arrowitem = (ArrowItem)(Items.ARROW);
                AbstractArrow abstractarrow = arrowitem.createArrow(world, new ItemStack(Items.ARROW), player);
                abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
                if (proj.is(Items.TNT)) {
                    PrimedTnt tnt = new PrimedTnt(EntityType.TNT, world);
                    tnt.setPos(abstractarrow.position());
                    tnt.setDeltaMovement(abstractarrow.getDeltaMovement().scale(0.7f));
                    tnt.setFuse(40);
                    world.addFreshEntity(tnt);
                } else if (proj.is(Items.TNT_MINECART)) {
                    MinecartTNT tnt = new MinecartTNT(EntityType.TNT_MINECART, world);
                    tnt.setDeltaMovement(abstractarrow.getDeltaMovement());
                    tnt.setPos(abstractarrow.position().add(tnt.getDeltaMovement().scale(3f)));
                    world.addFreshEntity(tnt);
                }
                proj.shrink(1);
                stack.hurtAndBreak(1, player, (thing) -> {
                    thing.broadcastBreakEvent(hand);
                });
                abstractarrow.kill();
            }
            return InteractionResultHolder.success(stack);
        } else {
            return InteractionResultHolder.pass(stack);
        }

        //BowItem


    }
    private Optional<ItemStack> findAmmoInInventory(Level world, Player player, ItemStack held) {
        ItemStack findAmmo = player.getProjectile(held);
        return PotatoProjectileTypeManager.getTypeForStack(findAmmo).map(($) -> {
            return findAmmo;
        });
    }

}
