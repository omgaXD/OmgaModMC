package com.omga.omgamod.content.items.armor.enderpants;

import com.omga.omgamod.OmgaMod;
import com.omga.omgamod.content.items.armor.flippers.PrismasteelFlippersArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.ArrayList;

public class EnderpantsArmorModel extends AnimatedGeoModel<EnderpantsArmorItem> {
    @Override
    public ResourceLocation getModelLocation(EnderpantsArmorItem object) {
        return new ResourceLocation(OmgaMod.MODID, "geo/enderpants.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EnderpantsArmorItem object) {
        return new ResourceLocation(OmgaMod.MODID, "textures/models/armor/enderpants.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EnderpantsArmorItem animatable) {
        return new ResourceLocation(OmgaMod.MODID, "animations/armor_animation.json");
    }
}
