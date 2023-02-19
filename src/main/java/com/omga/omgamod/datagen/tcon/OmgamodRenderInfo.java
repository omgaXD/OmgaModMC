package com.omga.omgamod.datagen.tcon;

import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

import javax.annotation.Nonnull;

public class OmgamodRenderInfo extends AbstractMaterialRenderInfoProvider {

    public OmgamodRenderInfo(DataGenerator gen, AbstractMaterialSpriteProvider spriteProvider) {
        super(gen, spriteProvider);
    }

    @Override
    protected void addMaterialRenderInfo() {
        buildRenderInfo(OmgamodMaterialIds.REDSTEEL);
        buildRenderInfo(OmgamodMaterialIds.WOODSTEEL);
        buildRenderInfo(OmgamodMaterialIds.GOLDSTEEL);
        buildRenderInfo(OmgamodMaterialIds.CREEPERSTEEL);
        buildRenderInfo(OmgamodMaterialIds.PRISMASTEEL);
        buildRenderInfo(OmgamodMaterialIds.SKYSTEEL);
        buildRenderInfo(OmgamodMaterialIds.ENDERSTEEL);

    }


    @Nonnull
    @Override
    public String getName() {
        return "Omgamod Render info provider!";
    }
}
