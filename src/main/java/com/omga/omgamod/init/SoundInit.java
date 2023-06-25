package com.omga.omgamod.init;

import com.omga.omgamod.OmgaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class SoundInit {
    public static final SoundEvent TIME_RESUME = new SoundEvent(new ResourceLocation(OmgaMod.MODID, "time_resume"));
    public static final SoundEvent TIME_STOP = new SoundEvent(new ResourceLocation(OmgaMod.MODID, "time_stop"));

    public static final SoundEvent MUSIC_DISC_TREE = new SoundEvent(new ResourceLocation(OmgaMod.MODID, "music_disc.tree"));
    public static final SoundEvent MUSIC_DISC_RAGE = new SoundEvent(new ResourceLocation(OmgaMod.MODID, "music_disc.rage"));
    public static final SoundEvent MUSIC_DISC_GEARS = new SoundEvent(new ResourceLocation(OmgaMod.MODID, "music_disc.gears"));

}
