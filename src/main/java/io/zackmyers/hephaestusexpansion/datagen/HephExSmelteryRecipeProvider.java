package io.zackmyers.hephaestusexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;

import java.util.function.Consumer;
public class HephExSmelteryRecipeProvider extends BaseRecipeProvider implements ISmelteryRecipeHelper {

    public HephExSmelteryRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.addCastingRecipes(consumer);
    }

    @Override
    public String getName() {
        return "HephaestusExpansion Smeltery Recipes";
    }

    private void addCastingRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "smeltery/casting/";

    }
}