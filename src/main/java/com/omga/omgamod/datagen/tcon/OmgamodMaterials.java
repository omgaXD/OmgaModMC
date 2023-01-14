package com.omga.omgamod.datagen.tcon;

import com.omga.omgamod.OmgaMod;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

import javax.annotation.Nonnull;

public class OmgamodMaterials extends AbstractMaterialDataProvider {
    public OmgamodMaterials(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void addMaterials() {
        addMaterial(new MaterialId(OmgamodMaterialIds.REDSTEEL), 2, ORDER_SPECIAL, false);
        addMaterial(new MaterialId(OmgamodMaterialIds.WOODSTEEL), 3, ORDER_SPECIAL, false);
        addMaterial(new MaterialId(OmgamodMaterialIds.GOLDSTEEL), 3, ORDER_SPECIAL, false);
        addMaterial(new MaterialId(OmgamodMaterialIds.CREEPERSTEEL), 3, ORDER_SPECIAL, false);
        addMaterial(new MaterialId(OmgamodMaterialIds.PRISMASTEEL), 3, ORDER_SPECIAL, false);
        addMaterial(new MaterialId(OmgamodMaterialIds.SKYSTEEL), 3, ORDER_SPECIAL, false);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Omgamod Materials";
    }
}
