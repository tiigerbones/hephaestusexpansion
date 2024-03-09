package io.zackmyers.hephaestusexpansion.datagen;

import io.zackmyers.hephaestusexpansion.HephaestusExpansion;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.recipe.v1.ingredient.DefaultCustomIngredients;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.recipe.modifiers.adding.SwappableModifierRecipeBuilder;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.function.Consumer;
public class HephExModifierRecipeProvider extends BaseRecipeProvider {
    public HephExModifierRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        addModifierRecipes(consumer);
    }

    @Override
    public String getName() {
        return "HephaestusExpansion Modifier Recipes";
    }

    private void addModifierRecipes(Consumer<FinishedRecipe> consumer) {
        String modifiersFolder = "tools/modifiers/";
        String upgradeFolder = "tools/modifiers/upgrade/";
        String abilityFolder = "tools/modifiers/ability/";
        String slotlessFolder = "tools/modifiers/slotless/";
        String upgradeSalvage = "tools/modifiers/salvage/upgrade/";
        String abilitySalvage = "tools/modifiers/salvage/ability/";
        String defenseFolder = "tools/modifiers/defense/";
        String defenseSalvage = "tools/modifiers/salvage/defense/";
        String compatFolder = "tools/modifiers/compat/";
        String compatSalvage = "tools/modifiers/salvage/compat/";
        String worktableFolder = "tools/modifiers/worktable/";

    }

    @SafeVarargs
    private static Ingredient ingredientFromTags(TagKey<Item>... tags) {
        Ingredient[] tagIngredients = new Ingredient[tags.length];
        for (int i = 0; i < tags.length; i++) {
            tagIngredients[i] = Ingredient.of(tags[i]);
        }
        return DefaultCustomIngredients.any(tagIngredients);
    }

    private static String makeRequirementsError(String recipe) {
        return HephaestusExpansion.makeTranslationKey("recipe", "modifier." + recipe);
    }

/*
    private void buildEmbellishment(MaterialId material, ItemLike ingot, String suffix, Consumer<FinishedRecipe> consumer) {
        SwappableModifierRecipeBuilder.modifier(TinkerModifiers.embellishment, material.toString())
                .setTools(TinkerTags.Items.EMBELLISHMENT_METAL)
                .addInput(ingot).addInput(ingot).addInput(ingot)
                .save(consumer, wrap(TinkerModifiers.embellishment.getId(), "tools/modifiers/slotless/", suffix));
    }
*/

    private void buildEmbellishment(MaterialVariantId material, String tag, Consumer<FinishedRecipe> consumer) {
        Ingredient ingot = Ingredient.of(getItemTag("c", tag));
        consumer = withCondition(consumer, tagCondition(tag));
        SwappableModifierRecipeBuilder.modifier(TinkerModifiers.embellishment, material.toString())
                .setTools(TinkerTags.Items.EMBELLISHMENT_METAL)
                .addInput(ingot).addInput(ingot).addInput(ingot)
                .save(consumer, wrap(TinkerModifiers.embellishment.getId(), "tools/modifiers/slotless/", "_" + material.getLocation('_').getPath()));
    }
}
