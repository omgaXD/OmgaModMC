package com.omga.omgamod.content.items.armor.flippers;

import com.omga.omgamod.OmgaMod;
import com.omga.omgamod.init.ItemInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.UUID;

@Mod.EventBusSubscriber
public class PrismasteelFlippersArmorItem extends GeoArmorItem implements IAnimatable {

    public static final AttributeModifier PRISMASTEEL_FLIPPERS = new AttributeModifier(UUID.fromString("abcdef94-dcd7-11ec-9d64-0242ac120002"), "Flippers", 2.5, AttributeModifier.Operation.MULTIPLY_BASE);


    @SubscribeEvent
    public static void playerTick(final TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        player.getAttribute(ForgeMod.SWIM_SPEED.get()).removeModifier(PRISMASTEEL_FLIPPERS);
        if (player.getItemBySlot(EquipmentSlot.FEET).is(ItemInit.PRISMASTEEL_FLIPPERS.get())) {
            if (!player.isSwimming()) return;
            if (!player.isInWater()) return;
            //player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 20, 0, true, false));
            player.getAttribute(ForgeMod.SWIM_SPEED.get()).addTransientModifier(PRISMASTEEL_FLIPPERS);
        }
        //EndPortalBlock
    }









    private final AnimationFactory animationFactory = GeckoLibUtil.createFactory(this);

    public PrismasteelFlippersArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<PrismasteelFlippersArmorItem>(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return animationFactory;
    }

    private <P extends IAnimatable>PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }
}
