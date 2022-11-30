package com.omga.omgamod.datagen.tcon;

import com.omga.omgamod.OmgaMod;
import com.omga.omgamod.init.BlockInit;
import com.omga.omgamod.init.FluidInit;
import com.omga.omgamod.init.ItemInit;
import dev.latvian.mods.kubejs.KubeJS;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;


import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class OmgamodRecipes extends RecipeProvider implements IMaterialRecipeHelper, ISmelteryRecipeHelper {
    public OmgamodRecipes(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        String folder = "tools/materials/";
        String smelteryFolder = "smeltery/casting/";
        String metalFolder = smelteryFolder + "metal/";

        metalMaterialRecipe(consumer, OmgamodMaterialIds.REDSTEEL, folder, "redsteel", false);
        materialMeltingCasting(consumer, OmgamodMaterialIds.REDSTEEL, FluidInit.MOLTEN_REDSTEEL, true, folder );
        metalTagCasting(consumer, FluidInit.MOLTEN_REDSTEEL, "redsteel", metalFolder, true);
        metalMelting(consumer, FluidInit.MOLTEN_REDSTEEL.get(), "redsteel", false, metalFolder, false);

        metalMaterialRecipe(consumer, OmgamodMaterialIds.WOODSTEEL, folder, "woodsteel", false);
        materialMeltingCasting(consumer, OmgamodMaterialIds.WOODSTEEL, FluidInit.MOLTEN_WOODSTEEL, true, folder );
        metalTagCasting(consumer, FluidInit.MOLTEN_WOODSTEEL, "woodsteel", metalFolder, true);
        metalMelting(consumer, FluidInit.MOLTEN_WOODSTEEL.get(), "woodsteel", false, metalFolder, false);

        metalMaterialRecipe(consumer, OmgamodMaterialIds.GOLDSTEEL, folder, "goldsteel", false);
        materialMeltingCasting(consumer, OmgamodMaterialIds.GOLDSTEEL, FluidInit.MOLTEN_GOLDSTEEL, true, folder );
        metalTagCasting(consumer, FluidInit.MOLTEN_GOLDSTEEL, "goldsteel", metalFolder, true);
        metalMelting(consumer, FluidInit.MOLTEN_GOLDSTEEL.get(), "goldsteel", false, metalFolder, false);

        metalMaterialRecipe(consumer, OmgamodMaterialIds.CREEPERSTEEL, folder, "creepersteel", false);
        materialMeltingCasting(consumer, OmgamodMaterialIds.CREEPERSTEEL, FluidInit.MOLTEN_CREEPERSTEEL, true, folder );
        metalTagCasting(consumer, FluidInit.MOLTEN_CREEPERSTEEL, "creepersteel", metalFolder, true);
        metalMelting(consumer, FluidInit.MOLTEN_CREEPERSTEEL.get(), "creepersteel", false, metalFolder, false);

        metalMaterialRecipe(consumer, OmgamodMaterialIds.PRISMASTEEL, folder, "prismasteel", false);
        materialMeltingCasting(consumer, OmgamodMaterialIds.PRISMASTEEL, FluidInit.MOLTEN_PRISMASTEEL, true, folder );
        metalTagCasting(consumer, FluidInit.MOLTEN_PRISMASTEEL, "prismasteel", metalFolder, true);
        metalMelting(consumer, FluidInit.MOLTEN_PRISMASTEEL.get(), "prismasteel", false, metalFolder, false);

    }




    @Override
    public @NotNull String getModId() {
        return OmgaMod.MODID;
    }
}