package com.omga.omgamod.content.items.armor.skyseekers;

import com.omga.omgamod.OmgaMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SkyseekersArmorModel extends AnimatedGeoModel<SkyseekersArmorItem> {
    @Override
    public ResourceLocation getModelLocation(SkyseekersArmorItem object) {
        return new ResourceLocation(OmgaMod.MODID, "geo/sky_seekers.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SkyseekersArmorItem object) {
        return new ResourceLocation(OmgaMod.MODID, "textures/models/armor/skyseekers.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SkyseekersArmorItem animatable) {
        return new ResourceLocation(OmgaMod.MODID, "animations/sky_seekers.animation.json");
    }
}
