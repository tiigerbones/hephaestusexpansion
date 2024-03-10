package io.zackmyers.hephaestusexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;

import java.util.function.Consumer;
public class HephExMaterialRecipeProvider extends BaseRecipeProvider implements IMaterialRecipeHelper, ISmelteryRecipeHelper {

    public HephExMaterialRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/materials/";
        String metalFolder = folder + "metal/";
        String alloyFolder = "smeltery/alloys/";

        Consumer<FinishedRecipe> wrapped;

    }

    @Override
    public String getName() {
        return "HephaestusExpansion Material Recipes";
    }
}
