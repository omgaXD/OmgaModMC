package com.omga.omgamod.content.menus;

import com.omga.omgamod.init.BlockInit;
import com.omga.omgamod.init.MenuInit;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.LevelAccessor;

public class MagmatiteChargerMenu extends RecipeBookMenu<Container> {
    public static final int INGREDIENT_SLOT = 0;
    public static final int LAVA_SLOT = 1;
    public static final int RESULT_SLOT = 2;
    public static final int SLOT_COUNT = 3;
    public static final int DATA_COUNT = 4;
    private static final int INV_SLOT_START = 3;
    private static final int INV_SLOT_END = 30;
    private static final int USE_ROW_SLOT_START = 30;
    private static final int USE_ROW_SLOT_END = 39;
    private final ContainerLevelAccess access;
    private final Container container;
    private final ContainerData containerData;

    public MagmatiteChargerMenu(int containerId, Inventory playerInv, ContainerLevelAccess access) {
        super(MenuInit.MAGMATITE_C_INVENTORY.get(), containerId);
        this.access = access;
        this.container = new SimpleContainer(3);
        this.containerData = new SimpleContainerData(4);
        checkContainerSize(container, 3);
        checkContainerDataCount(containerData, 4);
        this.addSlot(new Slot(container, 0, 56, 17));
        this.addSlot(new Slot(container, 1, 56, 53));
        this.addSlot(new FurnaceResultSlot(playerInv.player, container, 2, 116, 35));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInv, k, 8 + k * 18, 142));
        }

        this.addDataSlots(containerData);
    }

    public MagmatiteChargerMenu(int containerId, Inventory playerInv) {
        this(containerId, playerInv, ContainerLevelAccess.NULL);
    }
    @Override
    public boolean stillValid(Player player) {
        return AbstractContainerMenu.stillValid(this.access,
                player, BlockInit.MAGMATITE_CHARGER.get());
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents p_40117_) {

    }

    @Override
    public void clearCraftingContent() {

    }

    public int getBurnProgress() {
        int i = this.containerData.get(2);
        int j = this.containerData.get(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    public int getLitProgress() {
        int i = this.containerData.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.containerData.get(0) * 13 / i;
    }

    public boolean isLit() {
        return this.containerData.get(0) > 0;
    }

    @Override
    public boolean recipeMatches(Recipe<? super Container> match) {
        return false;
    }

    @Override
    public int getResultSlotIndex() {
        return 2;
    }

    @Override
    public int getGridWidth() {
        return 1;
    }

    @Override
    public int getGridHeight() {
        return 1;
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return null;
    }

    @Override
    public boolean shouldMoveToInventory(int p_150635_) {
        return false;
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return super.quickMoveStack(p_38941_, p_38942_);
    }
}
