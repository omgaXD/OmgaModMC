package com.omga.omgamod.datagen.tcon;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.omga.omgamod.datagen.tcon.OmgamodMaterialIds;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.tools.modifiers.upgrades.general.MagneticModifier;

import javax.annotation.Nonnull;

import static com.omga.omgamod.init.TraitInit.*;

public class OmgamodMaterialTraits extends AbstractMaterialTraitDataProvider {

    public OmgamodMaterialTraits(DataGenerator gen, AbstractMaterialDataProvider materials) {
        super(gen, materials);
    }

    @Override
    protected void addMaterialTraits() {
        addDefaultTraits(OmgamodMaterialIds.REDSTEEL, HASTE_TRAIT);
        addDefaultTraits(OmgamodMaterialIds.WOODSTEEL, WOODMASTER_TRAIT);
        addDefaultTraits(OmgamodMaterialIds.GOLDSTEEL, MIDAS_TRAIT);
        addDefaultTraits(OmgamodMaterialIds.CREEPERSTEEL, MIDAS_TRAIT);
        addDefaultTraits(OmgamodMaterialIds.PRISMASTEEL, MIDAS_TRAIT);
        addDefaultTraits(OmgamodMaterialIds.SKYSTEEL, MIDAS_TRAIT);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Omgamod Material Traits";
    }
}
