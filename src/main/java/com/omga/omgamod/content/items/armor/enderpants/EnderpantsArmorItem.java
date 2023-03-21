package com.omga.omgamod.content.items.armor.enderpants;

import com.jozufozu.flywheel.event.ForgeEvents;
import com.mojang.logging.LogUtils;
import com.omga.omgamod.OmgaMod;
import com.omga.omgamod.content.items.armor.flippers.PrismasteelFlippersArmorItem;
import com.omga.omgamod.init.ItemInit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
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

import java.util.Random;
import java.util.random.RandomGenerator;

@Mod.EventBusSubscriber(modid = OmgaMod.MODID)
public class EnderpantsArmorItem extends GeoArmorItem implements IAnimatable {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Random RAND = new Random();
    final static int CHANCE_NOT_TO_CONSUME_ENDERPEAL = 66/*%*/;
    @SubscribeEvent
    public static void enderpearlThrowSave(PlayerInteractEvent.RightClickItem event) {
        if (event.getEntity() instanceof LivingEntity entity) {
            if (entity.getItemBySlot(EquipmentSlot.LEGS).is(ItemInit.ENDERPANTS.get())) {
                if (!event.getItemStack().is(Tags.Items.ENDER_PEARLS)) {
                    return;
                }
                if (RAND.nextInt(100) < CHANCE_NOT_TO_CONSUME_ENDERPEAL) {
                    if (event.getEntity() instanceof Player player && player.getAbilities().instabuild) {
                        return;
                    }
                    event.getItemStack().grow(1);
                }
            }
        }
    }
    @SubscribeEvent
    public static void enderpearlDamageNeg(EntityTeleportEvent.EnderPearl event) {
        if (event.getEntity() instanceof LivingEntity entity) {
            if (entity.getItemBySlot(EquipmentSlot.LEGS).is(ItemInit.ENDERPANTS.get())) {
                event.setAttackDamage(0);
            }
        }
    }



    public EnderpantsArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }
    private final AnimationFactory animationFactory = GeckoLibUtil.createFactory(this);

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<EnderpantsArmorItem>(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return animationFactory;
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }
}
