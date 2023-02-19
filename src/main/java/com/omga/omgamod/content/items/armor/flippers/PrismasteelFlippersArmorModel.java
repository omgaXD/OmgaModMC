package com.omga.omgamod.content.items.armor.flippers;

import com.omga.omgamod.OmgaMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PrismasteelFlippersArmorModel extends AnimatedGeoModel<PrismasteelFlippersArmorItem> {
    @Override
    public ResourceLocation getModelLocation(PrismasteelFlippersArmorItem object) {
        return new ResourceLocation(OmgaMod.MODID, "geo/prismasteel_flippers.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PrismasteelFlippersArmorItem object) {
        return new ResourceLocation(OmgaMod.MODID, "textures/models/armor/prismasteel_flippers.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PrismasteelFlippersArmorItem animatable) {
        return new ResourceLocation(OmgaMod.MODID, "animations/armor_animation.json");
    }
}
