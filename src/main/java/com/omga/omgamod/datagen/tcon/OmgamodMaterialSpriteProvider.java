package com.omga.omgamod.datagen.tcon;

import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;

import javax.annotation.Nonnull;

public class OmgamodMaterialSpriteProvider extends AbstractMaterialSpriteProvider {

    @Nonnull
    @Override
    public String getName() {
        return "Omgamod Material Sprite Provider";
    }

    @Override
    protected void addAllMaterials() {
        buildMaterial(OmgamodMaterialIds.REDSTEEL)
                .meleeHarvest()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,  0xFF5e0909)
                        .addARGB(102, 0xFF600b0b)
                        .addARGB(140, 0xFF7a1111)
                        .addARGB(178, 0xFFa51616)
                        .addARGB(216, 0xFFf04444)
                        .addARGB(255, 0xFFff5555)
                        .build());
        buildMaterial(OmgamodMaterialIds.WOODSTEEL)
                .meleeHarvest()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,  0xFF5C3910)
                        .addARGB(95,  0xFF5C3C1D)
                        .addARGB(127, 0xFF8A593D)
                        .addARGB(195, 0xFFBD7945)
                        .addARGB(255, 0xFFF9C6AD)
                        .build());

        buildMaterial(OmgamodMaterialIds.GOLDSTEEL)
                .meleeHarvest()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,  0xFF523909)
                        .addARGB(95,  0xFF664806)
                        .addARGB(127, 0xFFA88F4A)
                        .addARGB(195, 0xFFF5C432)
                        .addARGB(255, 0xFFEEE0C2)
                        .build());
        buildMaterial(OmgamodMaterialIds.CREEPERSTEEL)
                .meleeHarvest()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,  0xFF113909)
                        .addARGB(95,  0xFF224806)
                        .addARGB(127, 0xFF338F2A)
                        .addARGB(195, 0xFF44C432)
                        .addARGB(255, 0xFFC2E0C2)
                        .build());
        buildMaterial(OmgamodMaterialIds.PRISMASTEEL)
                .meleeHarvest()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,  0xFF113939)
                        .addARGB(95,  0xFF224856)
                        .addARGB(127, 0xFF337F7A)
                        .addARGB(195, 0xFF44B4B2)
                        .addARGB(255, 0xFFC2E0E2)
                        .build());
        buildMaterial(OmgamodMaterialIds.SKYSTEEL)
                .meleeHarvest()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,  0xFF203052)
                        .addARGB(95,  0xFF1f1a6b)
                        .addARGB(127, 0xFF5733cc)
                        .addARGB(195, 0xFF7375ff)
                        .addARGB(255, 0xFFc7d3ff)
                        .build());
        buildMaterial(OmgamodMaterialIds.ENDERSTEEL)
                .meleeHarvest()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63,  0xFFe598eb)
                        .addARGB(95,  0xFFf680ff)
                        .addARGB(127, 0xFF301130)
                        .addARGB(195, 0xFF471648)
                        .addARGB(255, 0xFF6D0073)
                        .build());



    }
}