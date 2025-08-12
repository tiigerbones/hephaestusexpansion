package com.enchantedwisp.hephaestusexpansion.datagen;

import com.enchantedwisp.hephaestusexpansion.HephaestusExpansion;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import slimeknights.mantle.recipe.data.IRecipeHelper;

import java.util.function.Consumer;
public abstract class BaseRecipeProvider extends FabricRecipeProvider implements IRecipeHelper {
    public BaseRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public abstract void buildRecipes(Consumer<FinishedRecipe> consumer);

    @Override
    public abstract String getName();

    @Override
    public String getModId() {
        return HephaestusExpansion.MOD_ID;
    }
}
