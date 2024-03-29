package com.omga.omgamod.init;

import com.omga.omgamod.OmgaMod;
import com.omga.omgamod.content.items.*;
import com.omga.omgamod.content.items.armor.enderpants.EnderpantsArmorItem;
import com.omga.omgamod.content.items.armor.flippers.PrismasteelFlippersArmorItem;
import com.omga.omgamod.content.items.armor.skyseekers.SkyseekersArmorItem;
import com.omga.omgamod.content.items.armor.nightvisionhelm.NightVisionHelm;
import com.omga.omgamod.content.items.tools.FertilizerSpray;
import com.omga.omgamod.content.items.tools.GoldsteelDrill;
import com.omga.omgamod.content.items.tools.TntCannon;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.fluids.TinkerFluids;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OmgaMod.MODID);

    public static final RegistryObject<Item> MUSIC_DISC_TREE = ITEMS.register("music_disc_tree", () -> new RecordItem(5, () -> SoundInit.MUSIC_DISC_TREE, LTTab().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_RAGE = ITEMS.register("music_disc_rage", () -> new RecordItem(6, () -> SoundInit.MUSIC_DISC_RAGE, LTTab().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> MUSIC_DISC_GEARS= ITEMS.register("music_disc_gears", () -> new RecordItem(7, () -> SoundInit.MUSIC_DISC_GEARS, LTTab().stacksTo(1).rarity(Rarity.RARE)));

    // # # # # #

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", ItemInit::OMTabItem);
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", ItemInit::OMTabItem);
    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate", ItemInit::OMTabItem);
    public static final RegistryObject<Item> REDSTEEL_INGOT = ITEMS.register("redsteel_ingot", ItemInit::OMTabItem);
    public static final RegistryObject<Item> REDSTEEL_NUGGET = ITEMS.register("redsteel_nugget", ItemInit::OMTabItem);
    public static final RegistryObject<Item> REDSTEEL_PLATE = ITEMS.register("redsteel_plate", ItemInit::OMTabItem);

    public static final RegistryObject<Item> WOODSTEEL_INGOT = ITEMS.register("woodsteel_ingot", ItemInit::OMTabItem);
    public static final RegistryObject<Item> WOODSTEEL_NUGGET = ITEMS.register("woodsteel_nugget", ItemInit::OMTabItem);
    public static final RegistryObject<Item> WOODSTEEL_PLATE = ITEMS.register("woodsteel_plate", ItemInit::OMTabItem);
    public static final RegistryObject<Item> GOLDSTEEL_BLAND = ITEMS.register("goldsteel_blend", () -> new GoldsteelBlend(OMTab()));
    public static final RegistryObject<Item> GOLDSTEEL_INGOT = ITEMS.register("goldsteel_ingot", ItemInit::OMTabItem);
    public static final RegistryObject<Item> GOLDSTEEL_NUGGET = ITEMS.register("goldsteel_nugget", ItemInit::OMTabItem);
    public static final RegistryObject<Item> GOLDSTEEL_PLATE = ITEMS.register("goldsteel_plate", ItemInit::OMTabItem);

    public static final RegistryObject<Item> CREEPERSTEEL_INGOT = ITEMS.register("creepersteel_ingot", ItemInit::OMTabItem);
    public static final RegistryObject<Item> CREEPERSTEEL_NUGGET = ITEMS.register("creepersteel_nugget", ItemInit::OMTabItem);
    public static final RegistryObject<Item> CREEPERSTEEL_PLATE = ITEMS.register("creepersteel_plate", ItemInit::OMTabItem);

    public static final RegistryObject<Item> PRISMASTEEL_INGOT = ITEMS.register("prismasteel_ingot", ItemInit::OMTabItem);
    public static final RegistryObject<Item> PRISMASTEEL_NUGGET = ITEMS.register("prismasteel_nugget", ItemInit::OMTabItem);
    public static final RegistryObject<Item> PRISMASTEEL_PLATE = ITEMS.register("prismasteel_plate", ItemInit::OMTabItem);

    public static final RegistryObject<Item> SKYSTEEL_INGOT = ITEMS.register("skysteel_ingot", ItemInit::OMTabItem);
    public static final RegistryObject<Item> SKYSTEEL_NUGGET = ITEMS.register("skysteel_nugget", ItemInit::OMTabItem);
    public static final RegistryObject<Item> SKYSTEEL_PLATE = ITEMS.register("skysteel_plate", ItemInit::OMTabItem);

    public static final RegistryObject<Item> ENDERSTEEL_INGOT = ITEMS.register("endersteel_ingot", ItemInit::OMTabItem);
    public static final RegistryObject<Item> ENDERSTEEL_NUGGET = ITEMS.register("endersteel_nugget", ItemInit::OMTabItem);
    public static final RegistryObject<Item> ENDERSTEEL_PLATE = ITEMS.register("endersteel_plate", ItemInit::OMTabItem);

    public static final RegistryObject<Item> FREED_SOUL = ITEMS.register("freed_soul", () -> new Item(OMTab().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> SPECTRAL_CATALYST = ITEMS.register("spectral_catalyst", () -> new SimpleFoiledItem(OMTab().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> NETHERITE_PLATE = ITEMS.register("netherite_plate", ItemInit::OMTabItem);
    // # # # # #

    public static final RegistryObject<Item> GSHROOM = ITEMS.register("gshroom", () -> new Item(new Item.Properties().tab(OmgaModCreativeTab.instance).food(new FoodProperties.Builder().nutrition(3).saturationMod(1f).build())));
    public static final RegistryObject<Item> SATURATION_FRUIT = ITEMS.register("saturation_fruit", () -> new EnchantedGoldenAppleItem(new Item.Properties().tab(OmgaModCreativeTab.instance).food(new FoodProperties.Builder().nutrition(3).saturationMod(1.2f).effect(() -> new MobEffectInstance(MobEffects.SATURATION, 100, 0), 1f).build())));

    // # # # # #

    public static final RegistryObject<Item> NIGHT_VISION_HELM = ITEMS.register("night_vision_helm", () -> new NightVisionHelm(ModArmorMaterials.NIGHT_VISION_HELM, EquipmentSlot.HEAD, OMTab()));
    public static final RegistryObject<Item> FERTILIZER_SPRAY = ITEMS.register("fertilizer_spray", () -> new FertilizerSpray(OMTab()));
    public static final RegistryObject<Item> FERTILIZER_SPRAY_EMPTY = ITEMS.register("fertilizer_spray_empty", () -> new FertilizerSpray.FertilizerSprayEmpty(OMTab()));
    public static final RegistryObject<Item> GOLDSTEEL_DRILL = ITEMS.register("goldsteel_drill", () -> new GoldsteelDrill(1, 0f, OMTab()));
    public static final RegistryObject<Item> TNT_CANNON = ITEMS.register("tnt_cannon", () -> new TntCannon(OMTab()));

    public static final RegistryObject<Item> PRISMASTEEL_FLIPPERS = ITEMS.register("prismasteel_flippers", () -> new PrismasteelFlippersArmorItem(ArmorMaterials.DIAMOND, EquipmentSlot.FEET, OMTab()));
    public static final RegistryObject<Item> SKYSEEKERS = ITEMS.register("skyseekers", () -> new SkyseekersArmorItem(ArmorMaterials.DIAMOND, EquipmentSlot.CHEST, OMTab()));
    public static final RegistryObject<Item> ENDERPANTS = ITEMS.register("enderpants", () -> new EnderpantsArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.LEGS, OMTab()));

    // # # # # #
    public static final RegistryObject<Item> ARSENICUM = ITEMS.register("arsenicum", () -> new Item(OMTab().food(new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.1f)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100, 3), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, 100, 3), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100, 0), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 3), 1F)
            .build())));
    public static final RegistryObject<Item> OMGIUM = ITEMS.register("omgium", ItemInit::OMTabItem);
    public static final RegistryObject<Item> RAW_OMGIUM = ITEMS.register("raw_omgium", ItemInit::OMTabItem);

    //*
    public static final RegistryObject<Item> GOLDSTEEL_BLOCK_ITEM = ITEMS.register(BlockInit.GOLDSTEEL_BLOCK.getId().getPath(), () -> new BlockItem(BlockInit.GOLDSTEEL_BLOCK.get(), OMTab()){

        @Override
        public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
            var f = entity.level.getBlockState(entity.blockPosition()).getFluidState();
            if (f.isSource() && !f.is(TinkerFluids.moltenGold.get()) && f.is(TinkerTags.Fluids.METAL_TOOLTIPS)) {
                entity.level.setBlock(entity.blockPosition(), TinkerFluids.moltenGold.getBlock().defaultBlockState(), 3);
                //stack.shrink(1);
            }
            return super.onEntityItemUpdate(stack, entity);
        }
    });//*/
    public static class OmgaModCreativeTab extends CreativeModeTab {
        public static final OmgaModCreativeTab instance = new OmgaModCreativeTab(CreativeModeTab.TABS.length, "omgamodtab");
        private OmgaModCreativeTab(int index, String label) {
            super(index, label);
        }

        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(OMGIUM.get());
        }


        @Override
        public void fillItemList(NonNullList<ItemStack> list) {
            for(Item item : Registry.ITEM) {
                if (item.equals(GOLDSTEEL_BLOCK_ITEM.get())) continue;

                if (list.size() > 0 && list.get(list.size()-1).is(BlockInit.WOODSTEEL_BLOCK.get().asItem())) {
                    GOLDSTEEL_BLOCK_ITEM.get().fillItemCategory(this, list);
                }
                item.fillItemCategory(this, list);
            }
        }
    }

    public static Item OMTabItem() {
        return new Item(OMTab());
    }
    public static Item.Properties OMTab() {
        return new Item.Properties().tab(OmgaModCreativeTab.instance);
    }
    public static Item.Properties LTTab() {
        return new Item.Properties().tab(LoottableChestsTab.instance);
    }

}