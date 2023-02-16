package com.omga.omgamod.init;

import com.omga.omgamod.OmgaMod;
import com.omga.omgamod.content.items.*;
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

    // # # # # #

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", ItemInit::OMTabItem);
    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate", ItemInit::OMTabItem);
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", ItemInit::OMTabItem);

    public static final RegistryObject<Item> REDSTEEL_INGOT = ITEMS.register("redsteel_ingot", ItemInit::OMTabItem);
    public static final RegistryObject<Item> REDSTEEL_PLATE = ITEMS.register("redsteel_plate", ItemInit::OMTabItem);
    public static final RegistryObject<Item> REDSTEEL_NUGGET = ITEMS.register("redsteel_nugget", ItemInit::OMTabItem);

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


    public static final RegistryObject<Item> FREED_SOUL = ITEMS.register("freed_soul", () -> new Item(OMTab().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> SPECTRAL_CATALYST = ITEMS.register("spectral_catalyst", () -> new SimpleFoiledItem(OMTab().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> NETHERITE_PLATE = ITEMS.register("netherite_plate", ItemInit::OMTabItem);
    // # # # # #

    public static final RegistryObject<Item> GSHROOM = ITEMS.register("gshroom", () -> new Item(new Item.Properties().tab(OmgaModCreativeTab.instance).food(new FoodProperties.Builder().nutrition(3).saturationMod(1f).build())));
    public static final RegistryObject<Item> SATURATION_FRUIT = ITEMS.register("saturation_fruit", () -> new EnchantedGoldenAppleItem(new Item.Properties().tab(OmgaModCreativeTab.instance).food(new FoodProperties.Builder().nutrition(3).saturationMod(1.2f).effect(() -> new MobEffectInstance(MobEffects.SATURATION, 100, 0), 1f).build())));
    //{Items.NETHERITE_INGOT}
    // # # # # #

    public static final RegistryObject<Item> NIGHT_VISION_HELM = ITEMS.register("night_vision_helm", () -> new NightVisionHelm(ModArmorMaterials.NIGHT_VISION_HELM, EquipmentSlot.HEAD, OMTab()));
    public static final RegistryObject<Item> FERTILIZER_SPRAY = ITEMS.register("fertilizer_spray", () -> new FertilizerSpray(OMTab()));
    public static final RegistryObject<Item> FERTILIZER_SPRAY_EMPTY = ITEMS.register("fertilizer_spray_empty", () -> new FertilizerSpray.FertilizerSprayEmpty(OMTab()));
    public static final RegistryObject<Item> GOLDSTEEL_DRILL = ITEMS.register("goldsteel_drill", () -> new GoldsteelDrill(1, 0f, OMTab()));
    public static final RegistryObject<Item> TNT_CANNON = ITEMS.register("tnt_cannon", () -> new TntCannon(OMTab()));
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
            return new ItemStack(STEEL_INGOT.get());
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
}