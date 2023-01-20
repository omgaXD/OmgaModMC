package com.omga.omgamod.init;

import com.omga.omgamod.OmgaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class SoundInit {
    public static final SoundEvent time_resume = new SoundEvent(new ResourceLocation(OmgaMod.MODID, "time_resume"));
    public static final SoundEvent time_stop = new SoundEvent(new ResourceLocation(OmgaMod.MODID, "time_stop"));
}
