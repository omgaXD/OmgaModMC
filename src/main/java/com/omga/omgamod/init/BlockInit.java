package com.omga.omgamod.init;

import com.omga.omgamod.OmgaMod;
import com.omga.omgamod.content.blocks.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.function.ToIntFunction;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, OmgaMod.MODID);

    public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block", () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6f, 6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> REDSTEEL_BLOCK = BLOCKS.register("redsteel_block", () -> new RedstoneLampBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).lightLevel(litBlockEmission(15)).strength(6f, 6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WOODSTEEL_BLOCK = BLOCKS.register("woodsteel_block", () -> new WoodsteelBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6f, 6f).requiresCorrectToolForDrops().randomTicks()));
    public static final RegistryObject<Block> GOLDSTEEL_BLOCK = BLOCKS.register("goldsteel_block", () -> new GoldsteelBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6f, 6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CREEPERSTEEL_BLOCK = BLOCKS.register("creepersteel_block", () -> new CreepersteelBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6f, 1200f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PRISMASTEEL_BLOCK = BLOCKS.register("prismasteel_block", () -> new PrismasteelBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6f, 1200f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SKYSTEEL_BLOCK = BLOCKS.register("skysteel_block", () -> new SkysteelBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6f, 1200f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENDERSTEEL_BLOCK = BLOCKS.register("endersteel_block", () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6f, 1200f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MAGMATITE_CHARGER = BLOCKS.register("magmatite_charger", () -> new MagmatiteChargerBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).noOcclusion().isViewBlocking((_1, _2, _3) -> false).lightLevel(litBlockEmission(15)).strength(6f, 6f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CREEPERSTEEL_SLAB = BLOCKS.register("creepersteel_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6f, 1200f).requiresCorrectToolForDrops()));

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach( (block) -> {
            boolean flag = shouldSkip(block);
            if (flag) {
                return;
            }
            final Item.Properties properties = new Item.Properties().tab(ItemInit.OmgaModCreativeTab.instance);
            BlockItem blockItem = new BlockItem(block, properties);

            blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
            registry.register(blockItem);
        });
        //Blocks.OBSIDIAN
    }
    //*
    private static boolean shouldSkip(Block block) {
        if (block instanceof GoldsteelBlock) {
            return true;
        }
        return false;
    }//*/

    private static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? p_50760_ : 0;
        };
    }
}
