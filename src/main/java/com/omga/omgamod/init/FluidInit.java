package com.omga.omgamod.init;

import com.omga.omgamod.OmgaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.ModelFluidAttributes;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;

public class FluidInit {
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(OmgaMod.MODID);
    public static final FluidObject<ForgeFlowingFluid> MOLTEN_REDSTEEL = register("molten_redsteel", 950);
    public static final FluidObject<ForgeFlowingFluid> MOLTEN_WOODSTEEL = register("molten_woodsteel", 1050);
    public static final FluidObject<ForgeFlowingFluid> MOLTEN_GOLDSTEEL = register("molten_goldsteel", 1150);
    public static final FluidObject<ForgeFlowingFluid> MOLTEN_CREEPERSTEEL = register("molten_creepersteel", 1250);
    public static final FluidObject<ForgeFlowingFluid> MOLTEN_PRISMASTEEL = register("molten_prismasteel", 1350);

    private static FluidObject<ForgeFlowingFluid> register(String name, int temp) {

        String still = String.format("%s:block/fluid/%s/still", OmgaMod.MODID, name);
        String flow = String.format("%s:block/fluid/%s/flowing", OmgaMod.MODID, name);
        return FLUIDS.register(name, hotBuilder().temperature(temp), Material.LAVA, 15);
    }


    private static FluidAttributes.Builder hotBuilder() {
        return ModelFluidAttributes.builder().density(2000).viscosity(10000).temperature(1000).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA);
    }
}
