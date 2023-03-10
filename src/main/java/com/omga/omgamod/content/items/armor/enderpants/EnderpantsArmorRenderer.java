package com.omga.omgamod.content.items.armor.enderpants;

import com.omga.omgamod.content.items.armor.flippers.PrismasteelFlippersArmorItem;
import com.omga.omgamod.content.items.armor.flippers.PrismasteelFlippersArmorModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class EnderpantsArmorRenderer extends GeoArmorRenderer<EnderpantsArmorItem> {
    public EnderpantsArmorRenderer() {
        super(new EnderpantsArmorModel());

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
