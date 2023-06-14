package com.omga.omgamod.init;

import com.omga.omgamod.OmgaMod;
import com.omga.omgamod.content.menus.MagmatiteChargerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuInit {
    public static final DeferredRegister<MenuType<?>> INVS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, OmgaMod.MODID);

    public static final RegistryObject<MenuType<MagmatiteChargerMenu>> MAGMATITE_C_INVENTORY = INVS.register("magmatite_charger", () ->
            new MenuType<MagmatiteChargerMenu>(MagmatiteChargerMenu::new));

}
