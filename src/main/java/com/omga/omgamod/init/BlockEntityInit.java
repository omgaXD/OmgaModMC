package com.omga.omgamod.init;


import com.omga.omgamod.OmgaMod;
import com.omga.omgamod.content.blocks.MagmatiteChargerEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, OmgaMod.MODID);

    public static final RegistryObject<BlockEntityType<MagmatiteChargerEntity>> MAGMATITE_CHARGER = BLOCK_ENTITIES.register("magmatite_charger", () ->
            BlockEntityType.Builder.of(MagmatiteChargerEntity::new, BlockInit.MAGMATITE_CHARGER.get()).build(null));
}
