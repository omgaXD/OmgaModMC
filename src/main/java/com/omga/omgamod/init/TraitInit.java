package com.omga.omgamod.init;

import com.omga.omgamod.OmgaMod;
import com.omga.omgamod.traits.MidasModifier;
import com.omga.omgamod.traits.WoodmasterModifier;
import net.minecraftforge.eventbus.api.Event;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.dynamic.StatBoostModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class TraitInit {
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(OmgaMod.MODID);

    public static final StaticModifier<Modifier> HASTE_TRAIT = MODIFIERS.register("haste", () -> StatBoostModifier.builder().multiplyBase(ToolStats.ATTACK_SPEED, 0.04f)
            .multiplyBase(ToolStats.MINING_SPEED, 0.12f)
            .build());
    public static final StaticModifier<Modifier> WOODMASTER_TRAIT = MODIFIERS.register("woodmaster", WoodmasterModifier::new);
    public static final StaticModifier<Modifier> MIDAS_TRAIT = MODIFIERS.register("midas", MidasModifier::new);

}
