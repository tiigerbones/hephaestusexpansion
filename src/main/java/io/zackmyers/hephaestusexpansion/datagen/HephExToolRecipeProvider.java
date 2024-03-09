package io.zackmyers.hephaestusexpansion.datagen;

import io.zackmyers.hephaestusexpansion.registry.HephExItemRegistry;
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
        toolBuilding(consumer, HephExItemRegistry.kataviscerator, folder);
        toolBuilding(consumer, HephExItemRegistry.spear, folder);

        this.addPartRecipes(consumer);
    }

    private void addPartRecipes(Consumer<FinishedRecipe> consumer) {
        String partFolder = "tools/parts/";
        String castFolder = "smeltery/casts/";

        partRecipes(consumer, HephExItemRegistry.katanaBlade, HephExItemRegistry.katanaBladeCast, 2, partFolder, castFolder);
        partRecipes(consumer, HephExItemRegistry.katavisceratorBlade, HephExItemRegistry.katavisceratorBladeCast, 2, partFolder, castFolder);
        partRecipes(consumer, HephExItemRegistry.spearHead, HephExItemRegistry.spearHeadCast, 2, partFolder, castFolder);
    }

    @Override
    public String getName() {
        return "HephaestusExpansion Tool Recipes";
    }
}
