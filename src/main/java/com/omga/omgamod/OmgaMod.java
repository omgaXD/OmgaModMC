package com.omga.omgamod;

import com.omga.omgamod.datagen.tcon.*;
import com.omga.omgamod.init.BlockInit;
import com.omga.omgamod.init.FluidInit;
import com.omga.omgamod.init.ItemInit;
import com.mojang.logging.LogUtils;
import com.omga.omgamod.init.TraitInit;
import dev.architectury.event.EventResult;
import net.minecraft.core.BlockPos;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.slf4j.Logger;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.events.TinkerToolEvent;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

import java.util.ArrayList;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("omgamod")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class OmgaMod
{
    public static final String MODID = "omgamod";
    public static ResourceLocation getLocation(String name) {
        return new ResourceLocation(MODID, name);
    }
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public OmgaMod()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //modEventBus.addListener(this::setup);
        //modEventBus.addListener(this::enqueueIMC);
        //modEventBus.addListener(this::processIMC);

        ItemInit.ITEMS.register(modEventBus);

        BlockInit.BLOCKS.register(modEventBus);

        FluidInit.FLUIDS.register(modEventBus);

        TraitInit.MODIFIERS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

    }

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        if (event.includeServer()) {
            AbstractMaterialDataProvider materials = new OmgamodMaterials(gen);
            gen.addProvider(materials);
            gen.addProvider(new OmgamodMaterialStats(gen, materials));
            gen.addProvider(new OmgamodMaterialTraits(gen, materials));
            gen.addProvider(new OmgamodRecipes(gen));
        }
        if(event.includeClient()) {
            AbstractMaterialSpriteProvider provider = new OmgamodMaterialSpriteProvider();
            AbstractMaterialSpriteProvider tinkersProvider = new TinkerMaterialSpriteProvider();
            gen.addProvider(new MaterialPartTextureGenerator(gen, fileHelper, new TinkerPartSpriteProvider(), provider));
            gen.addProvider(new OmgamodRenderInfo(gen, provider));
        }
    }


    private void setup(final FMLCommonSetupEvent event)
    {

    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
    }




    @SubscribeEvent
    public void onCropGrowPre(BlockEvent.CropGrowEvent.Pre event) {
        if (!event.getState().is(BlockTags.CROPS)) {event.setResult(Event.Result.DEFAULT); return;}
        if (event.getWorld().isClientSide()) return;
        BlockPos cropPos = event.getPos();
        BlockPos dirtPos = cropPos.below();

        LOGGER.debug("LOOKING FOR IMPOSTOR");
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                if (event.getWorld().getFluidState(dirtPos.offset(x, 0, z)).getType().getRegistryName().equals(new ResourceLocation("kubejs:fertilizer"))) {
                    LOGGER.debug("FOUND THE IMPOSTOR");
                    event.setResult(Event.Result.DENY);
                    event.getWorld().setBlock(dirtPos, Blocks.DIRT.defaultBlockState(), 3);
                    //Blocks.GRASS_BLOCK
                    event.getWorld().setBlock(cropPos, Blocks.DEAD_BUSH.defaultBlockState(), 3);
                }
            }
        }
    }

    @SubscribeEvent
    public void onExplosion(ExplosionEvent.Start event) {
        LOGGER.debug("HOLY SHIT AAAAAAA");
        BlockPos expPos = new BlockPos(event.getExplosion().getPosition());
        ArrayList<BlockPos> bp = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    var temp = expPos.offset(x, y, z);
                    if (event.getWorld().getBlockState(temp).is(BlockInit.GOLDSTEEL_BLOCK.get())) {
                        bp.add(temp);
                    }
                }
            }
        }
        LOGGER.debug("YOU HAVE BEEN EXPLODEN EXACTLY " + bp.size() + " FUCKIN TIMES!!");
        if (bp.size() != 4) return;

        for (BlockPos b : bp) {
            event.getWorld().setBlock(b, Blocks.AIR.defaultBlockState(), 3);
        }
        event.getWorld().setBlock(expPos, BlockInit.CREEPERSTEEL_BLOCK.get().defaultBlockState(), 3);
    }

}
