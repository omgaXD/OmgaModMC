package com.omga.omgamod.datagen.tcon;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Tiers;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;
import javax.annotation.Nonnull;

public class OmgamodMaterialStats extends AbstractMaterialStatsDataProvider {

    public OmgamodMaterialStats(DataGenerator gen, AbstractMaterialDataProvider materials) {
        super(gen, materials);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Omgamod Material Stats";
    }

    @Override
    protected void addMaterialStats() {
        addMaterialStats(OmgamodMaterialIds.REDSTEEL,
                new HeadMaterialStats(500, 5.5f, Tiers.DIAMOND, 1.5f),
                HandleMaterialStats.DEFAULT.withDurability(1.05f).withMiningSpeed(1.1f).withAttackDamage(0.8f).withAttackSpeed(1.1f),
                ExtraMaterialStats.DEFAULT);
        addMaterialStats(OmgamodMaterialIds.WOODSTEEL,
                new HeadMaterialStats(550, 5.75f, Tiers.DIAMOND, 1.5f),
                HandleMaterialStats.DEFAULT.withDurability(0.95f).withMiningSpeed(1.05f).withAttackDamage(1f).withAttackSpeed(1.05f),
                ExtraMaterialStats.DEFAULT);
        addMaterialStats(OmgamodMaterialIds.GOLDSTEEL,
                new HeadMaterialStats(600, 7f, Tiers.DIAMOND, 0.5f),
                HandleMaterialStats.DEFAULT.withDurability(1f).withMiningSpeed(1.1f).withAttackDamage(0.8f).withAttackSpeed(0.9f),
                ExtraMaterialStats.DEFAULT);
        addMaterialStats(OmgamodMaterialIds.CREEPERSTEEL,
                new HeadMaterialStats(650, 7f, Tiers.DIAMOND, 2.5f),
                HandleMaterialStats.DEFAULT.withDurability(1.05f).withMiningSpeed(1f).withAttackDamage(1.1f).withAttackSpeed(1f),
                ExtraMaterialStats.DEFAULT);
        addMaterialStats(OmgamodMaterialIds.PRISMASTEEL,
                new HeadMaterialStats(700, 7f, Tiers.DIAMOND, 2.5f),
                HandleMaterialStats.DEFAULT.withDurability(1.05f).withMiningSpeed(1.1f).withAttackDamage(1.05f).withAttackSpeed(1.1f),
                ExtraMaterialStats.DEFAULT);
        addMaterialStats(OmgamodMaterialIds.SKYSTEEL,
                new HeadMaterialStats(750, 7.25f, Tiers.DIAMOND, 3f),
                HandleMaterialStats.DEFAULT.withDurability(1.1f).withMiningSpeed(1.15f).withAttackDamage(1.1f).withAttackSpeed(1.15f),
                ExtraMaterialStats.DEFAULT);
        addMaterialStats(OmgamodMaterialIds.ENDERSTEEL,
                new HeadMaterialStats(800, 7.5f, Tiers.DIAMOND, 3.5f),
                HandleMaterialStats.DEFAULT.withDurability(1.15f).withMiningSpeed(1.15f).withAttackDamage(1.15f).withAttackSpeed(1.15f),
                ExtraMaterialStats.DEFAULT);

    }
}