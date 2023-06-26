package com.omga.omgamod.init;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.logging.LogUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Objects;


public class LoottableChestsTab extends CreativeModeTab {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final LoottableChestsTab instance = new LoottableChestsTab(CreativeModeTab.TABS.length, "loottablechesttab");

    public LoottableChestsTab(int i, String label) {
        super(i, label);
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(Items.CHEST);
    }

    private static final String loottables_namespace = "omgamod:chests/crates";
    private static final String LANG_STRING = "item.omgamod.crates";
    private static final String CRATES_JSON_DIR = "data/omgamod/loot_tables/chests/crates/crates.json";


    @Override
    public void fillItemList(@NotNull NonNullList<ItemStack> list) {
        super.fillItemList(list);
        //LOGGER.debug("you're the judge oh no set me freeeeeeeeee");
        // iterating through all file names to substitute that name as loottable.
        try (InputStreamReader reader = new InputStreamReader(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream(CRATES_JSON_DIR)), StandardCharsets.UTF_8)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonElement.getAsJsonObject().get("crates").getAsJsonArray();
            ArrayList<String> crates = new ArrayList<>();
            for (JsonElement crateElement : jsonArray) {
                crates.add(crateElement.getAsString());
            }
            for (String crate : crates) {
                // generating the loottable name
                String loottable_name = MessageFormat.format("{0}/{1}", loottables_namespace, crate);

                // creating the chest itemstack, later appending needed NBT to it.
                ItemStack chest = new ItemStack(Items.CHEST, 1);
                CompoundTag loottable = new CompoundTag();
                loottable.putString("LootTable", loottable_name);
                CompoundTag blockentitytag = new CompoundTag();
                blockentitytag.put("BlockEntityTag", loottable);
                chest.setTag(blockentitytag);
                // translatable name
                chest.setHoverName(new TranslatableComponent(MessageFormat.format("{0}.{1}", LANG_STRING, crate)));
                list.add(chest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
