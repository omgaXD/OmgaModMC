package com.omga.omgamod.content.items.armor.flippers;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class SkyseekersArmorRenderer extends GeoArmorRenderer<SkyseekersArmorItem> {
    public SkyseekersArmorRenderer() {
        super(new SkyseekersArmorModel());

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
