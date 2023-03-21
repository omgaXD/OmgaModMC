package com.omga.omgamod;

import com.omga.omgamod.content.items.armor.enderpants.EnderpantsArmorItem;
import com.omga.omgamod.content.items.armor.enderpants.EnderpantsArmorRenderer;
import com.omga.omgamod.content.items.armor.flippers.PrismasteelFlippersArmorItem;
import com.omga.omgamod.content.items.armor.flippers.PrismasteelFlippersArmorRenderer;
import com.omga.omgamod.content.items.armor.skyseekers.SkyseekersArmorItem;
import com.omga.omgamod.content.items.armor.skyseekers.SkyseekersArmorRenderer;
import com.omga.omgamod.datagen.tcon.*;
import com.omga.omgamod.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.slf4j.Logger;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

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
        modEventBus.addListener(this::setup);
        //modEventBus.addListener(this::enqueueIMC);
        //modEventBus.addListener(this::processIMC);
        BlockInit.BLOCKS.register(modEventBus);

        ItemInit.ITEMS.register(modEventBus);

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
        //Vindicator

    }


    private void setup(final FMLClientSetupEvent event)
    {
        ItemBlockRenderTypes.setRenderLayer(BlockInit.MAGMATITE_CHARGER.get(), RenderType.cutout());
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
    public static void registerArmorRenderer(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(PrismasteelFlippersArmorItem.class, new PrismasteelFlippersArmorRenderer());
        GeoArmorRenderer.registerArmorRenderer(SkyseekersArmorItem.class, new SkyseekersArmorRenderer());
        GeoArmorRenderer.registerArmorRenderer(EnderpantsArmorItem.class, new EnderpantsArmorRenderer());

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




}
