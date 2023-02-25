package com.omga.omgamod.content.items.armor.flippers;

import com.mojang.logging.LogUtils;
import com.omga.omgamod.init.ItemInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import software.bernie.geckolib3.core.AnimationState;
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

import java.util.*;

@Mod.EventBusSubscriber
public class SkyseekersArmorItem extends GeoArmorItem implements IAnimatable {

    private static final Logger LOGGER = LogUtils.getLogger();

    /*
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        if (!(entity instanceof Player player)) {
            return;
        }
        if (!player.getPersistentData().contains("flight_time")) {
            player.getPersistentData().putInt("flight_time", 0);

        }
        if ((stack.getEquipmentSlot() != EquipmentSlot.CHEST)) {
            return;
        }
        if (player.isOnGround()) {
            if (player.getPersistentData().getInt("flight_time") != 80) {
                final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel)level);
                final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player);
                GeckoLibNetwork.syncAnimation(target, this, id, ANIM_FLY);
            }
            player.getPersistentData().putInt("flight_time", 80);
        } else {
            if (player.getPersistentData().getInt("flight_time") == 80) {
                final int id = GeckoLibUtil.guaranteeIDForStack(stack, (ServerLevel)level);
                final PacketDistributor.PacketTarget target = PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player);
                GeckoLibNetwork.syncAnimation(target, this, id, ANIM_FLY);
            }
            player.getPersistentData().putInt("flight_time", Math.max(player.getPersistentData().getInt("flight_time") - 1, 0));
        }
        player.getAbilities().mayfly |=  player.getPersistentData().getInt("flight_time") > 0;
        player.resetFallDistance();
    }*/
    static Set<Player> playersThatEquippedIt = new HashSet<>();

    @SubscribeEvent
    public static void playerTick(final TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player.getItemBySlot(EquipmentSlot.CHEST).is(ItemInit.SKYSEEKERS.get())) {
            // add it to set to track the wings removal
            if (event.side.isServer() && playersThatEquippedIt.add(player)) {

            }
            //*
            Vec3 d = player.getDeltaMovement();

            if (event.side.isServer()) {
                player.getAbilities().mayfly = true;
                player.onUpdateAbilities();
            }
            // if player uses wings and not in creative, exhaust, slightly shrink fov and slow him down
            if (player.getAbilities().flying && !player.getAbilities().instabuild) {
                player.getFoodData().addExhaustion(0.04f);
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2, 0, false, false));

                player.setDeltaMovement(d.multiply(0.75f, 0.9f, 0.75f));
            }
            // if player doesn't hold shift, slow his flight
            if (!player.isOnGround() && !player.isShiftKeyDown() && !player.getAbilities().flying) {
                if (d.y < 0d) {
                    player.setDeltaMovement(d.multiply(1f, 0.788f, 1f));
                }
            }
            // no fall damage
            player.resetFallDistance();
            //*/
        } else {
            // if player doesn't wear the skyseekers anymore, reset his abilities appropriately
            if (event.side.isServer() && playersThatEquippedIt.remove(player)) {

                //if (event.side.isServer()) {
                    player.getAbilities().mayfly = player.getAbilities().instabuild;
                    player.getAbilities().flying = player.getAbilities().instabuild;

                    player.onUpdateAbilities();

                    player.hurtMarked = true;
                //}

            }
        }
    }


    private final AnimationFactory animationFactory = GeckoLibUtil.createFactory(this);


    public SkyseekersArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<SkyseekersArmorItem>(this, "controller", 4, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return animationFactory;
    }
    final Random r = new Random();
    final AnimationBuilder fly = new AnimationBuilder().addAnimation("fly", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    final AnimationBuilder idle = new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    final AnimationBuilder rare_idle = new AnimationBuilder().addAnimation("rare_idle", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
    final AnimationBuilder descend = new AnimationBuilder().addAnimation("descend", ILoopType.EDefaultLoopTypes.PLAY_ONCE);

    private <P extends IAnimatable>PlayState predicate(AnimationEvent<P> event) {
        /*
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if (event.getController().tickOffset == 0d) {
            if (new Random().nextFloat() < 0.1f) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("rare_idle", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
        }
        //List<EquipmentSlot> slotData = event.getExtraDataOfType(EquipmentSlot.class);
        List<ItemStack> stackData = event.getExtraDataOfType(ItemStack.class);
        if (event.getController().getAnimationState() == AnimationState.Stopped) {
            rare_animation_happening = false;
        }

        LivingEntity livingEntity = event.getExtraDataOfType(LivingEntity.class).get(0);
        if (!rare_animation_happening) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        }
        if (event.getAnimationTick() == 0d) {
            if (r.nextFloat() < 0.2f) {
                event.getController().markNeedsReload();
                event.getController().setAnimation(new AnimationBuilder().addAnimation("rare_idle", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                rare_animation_happening = true;
            }
        }

        //*
        if (!livingEntity.isOnGround()) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", ILoopType.EDefaultLoopTypes.LOOP));
            rare_animation_happening = false;
        }
        //*/
        LivingEntity livingEntity = event.getExtraDataOfType(LivingEntity.class).get(0);
        if (!(livingEntity instanceof Player player)) return PlayState.STOP;
        var con = event.getController();
        if (con.getCurrentAnimation() == null) {
            con.setAnimation(idle);
            LOGGER.debug("real 1");
            return PlayState.CONTINUE;
        }
        if (player.getAbilities().flying) {
            if (con.getAnimationState() != AnimationState.Stopped && con.getCurrentAnimation().animationName.equals("fly")) {
                return PlayState.CONTINUE;
            }
            con.markNeedsReload();
            con.setAnimation(fly);
            LOGGER.debug("its flyin time");
            return PlayState.CONTINUE;
        } else {
            if (!player.isOnGround() && !player.isShiftKeyDown()) {
                if (con.getAnimationState() != AnimationState.Stopped && con.getCurrentAnimation().animationName.equals("descend")) {
                    return PlayState.CONTINUE;
                }
                con.markNeedsReload();
                con.setAnimation(descend);
                return PlayState.CONTINUE;
            }
            if (con.getAnimationState() != AnimationState.Stopped && con.getCurrentAnimation().animationName.contains("idle")) {
                return PlayState.CONTINUE;
            }
            con.markNeedsReload();
            con.setAnimation(idle);
            LOGGER.debug("its idle time");
            if (r.nextFloat() < 0.2f) {
                con.setAnimation(rare_idle);
                LOGGER.debug("its aktually rare idle time");
            }
        }
        return PlayState.CONTINUE;
        //*/
    }



}
