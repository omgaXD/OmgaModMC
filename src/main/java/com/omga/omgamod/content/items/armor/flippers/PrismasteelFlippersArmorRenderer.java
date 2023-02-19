package com.omga.omgamod.content.items.armor.flippers;

import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class PrismasteelFlippersArmorRenderer extends GeoArmorRenderer<PrismasteelFlippersArmorItem> {
    public PrismasteelFlippersArmorRenderer() {
        super(new PrismasteelFlippersArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorLeftArm";
        this.leftArmBone = "armorRightArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }
}
