package com.omga.omgamod.datagen.tcon;

import com.omga.omgamod.init.FluidInit;
import net.minecraft.world.level.material.Fluid;
import org.openjdk.nashorn.internal.objects.annotations.Getter;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IByproduct;
import slimeknights.tconstruct.library.recipe.FluidValues;

import java.util.Locale;
import java.util.function.Supplier;

public enum OmgamodByproduct implements IByproduct {

    REDSTEEL(true, FluidInit.MOLTEN_REDSTEEL, FluidValues.INGOT ),
    WOODSTEEL(true, FluidInit.MOLTEN_WOODSTEEL, FluidValues.INGOT ),
    GOLDSTEEL(true, FluidInit.MOLTEN_GOLDSTEEL, FluidValues.INGOT),
    CREEPERSTEEL(true, FluidInit.MOLTEN_CREEPERSTEEL, FluidValues.INGOT),
    PRISMASTEEL(true, FluidInit.MOLTEN_PRISMASTEEL, FluidValues.INGOT),
    SKYSTEEL(true, FluidInit.MOLTEN_SKYSTEEL, FluidValues.INGOT);


    private final String name;
    private final boolean alwaysPresent;
    private final Supplier<? extends Fluid> fluidSupplier;
    private final int amount;

    OmgamodByproduct(boolean alwaysPresent, Supplier<? extends Fluid> fluidSupplier, int fluidVal) {
        this.name = name().toLowerCase(Locale.ROOT);
        this.alwaysPresent = alwaysPresent;
        this.fluidSupplier = fluidSupplier;
        this.amount = fluidVal;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isAlwaysPresent() {
        return alwaysPresent;
    }

    @Override
    public Fluid getFluid() {
        return fluidSupplier.get();
    }

    @Override
    public int getAmount() {
        return amount;
    }

}
