package com.tiigerpaws.hephaestusexpansion.datagen;

import com.tiigerpaws.hephaestusexpansion.registry.HephExItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;

import java.util.function.Consumer;
public class HephExToolRecipeProvider extends BaseRecipeProvider implements IMaterialRecipeHelper, IToolRecipeHelper {

    public HephExToolRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        String folder = "tools/building/";

        toolBuilding(consumer, HephExItemRegistry.katana, folder);
        toolBuilding(consumer, HephExItemRegistry.spear, folder);
        toolBuilding(consumer, HephExItemRegistry.halberd, folder);
        toolBuilding(consumer, HephExItemRegistry.glaive, folder);
        toolBuilding(consumer, HephExItemRegistry.claymore, folder);

        this.addPartRecipes(consumer);
    }

    private void addPartRecipes(Consumer<FinishedRecipe> consumer) {
        String partFolder = "tools/parts/";
        String castFolder = "smeltery/casts/";

        partRecipes(consumer, HephExItemRegistry.katanaBlade, HephExItemRegistry.katanaBladeCast, 2, partFolder, castFolder);
        partRecipes(consumer, HephExItemRegistry.spearHead, HephExItemRegistry.spearHeadCast, 2, partFolder, castFolder);
        partRecipes(consumer, HephExItemRegistry.halberdHead, HephExItemRegistry.halberdHeadCast, 2, partFolder, castFolder);
        partRecipes(consumer, HephExItemRegistry.glaiveHead, HephExItemRegistry.glaiveHeadCast, 2, partFolder, castFolder);
        partRecipes(consumer, HephExItemRegistry.claymoreBlade, HephExItemRegistry.claymoreBladeCast, 2, partFolder, castFolder);
    }

    @Override
    public String getName() {
        return "HephaestusExpansion Tool Recipes";
    }
}
