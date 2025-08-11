package com.tiigerpaws.hephaestusexpansion;

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import com.tiigerpaws.hephaestusexpansion.datagen.*;
import com.tiigerpaws.hephaestusexpansion.registry.HephExItemRegistry;
import com.tiigerpaws.hephaestusexpansion.spritegen.HephaestusExPartSpriteProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.data.DataProvider;
import slimeknights.tconstruct.library.client.data.TinkerSpriteSourceGenerator;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.material.MaterialRenderInfoProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

import java.lang.reflect.Constructor;

import static com.tiigerpaws.hephaestusexpansion.HephaestusExpansion.LOGGER;
import static com.tiigerpaws.hephaestusexpansion.HephaestusExpansion.MOD_ID;

public class HephExRegistry {

    public static void init() {
        HephExItemRegistry.register();
    }

    public static void gatherData(FabricDataGenerator.Pack pack, ExistingFileHelper existingFileHelper) {
        LOGGER.info("Gathering data...");

        // Always-provided tags
        FabricTagProvider.BlockTagProvider blockTags = pack.addProvider(HephExBlockTagsProvider::new);
        pack.addProvider((output, registriesFuture) -> new HephExItemTagsProvider(output, registriesFuture, blockTags));

        // Always-provided generators
        pack.addProvider(HephExToolRecipeProvider::new);
        pack.addProvider(HephExMaterialRecipeProvider::new);
        pack.addProvider(HephExStationSlotLayoutProvider::new);
        pack.addProvider(HephExSmelteryRecipeProvider::new);
        pack.addProvider(HephExToolDefinitionProvider::new);
        pack.addProvider(HephExModifierProvider::new);

        TinkerPartSpriteProvider partSprites = new TinkerPartSpriteProvider();
        TinkerMaterialSpriteProvider materialSprites = new TinkerMaterialSpriteProvider();
        HephaestusExPartSpriteProvider morePartSprites = new HephaestusExPartSpriteProvider();
        pack.addProvider((output, registriesFuture) -> new MaterialRenderInfoProvider(output, materialSprites));

        // Optional: HephaestusPlus datagen
        if (FabricLoader.getInstance().isModLoaded("hephaestusplus")) {
            LOGGER.info("HephaestusPlus detected, registering additional data providers...");
            try {
                // Instantiate the sprite provider via reflection
                Class<?> spriteProviderClass = Class.forName("konsola5.hephaestusplus.spritegen.HephaestusPlusMaterialSpriteProvider");
                Object moreToolMats = spriteProviderClass.getDeclaredConstructor().newInstance();

                // Instantiate the render info provider via reflection
                Class<?> renderInfoProviderClass = Class.forName("konsola5.hephaestusplus.spritegen.HephPlusMaterialRenderInfoProvider");
                pack.addProvider((output, registriesFuture) -> {
                    try {
                        Constructor<?> ctor = renderInfoProviderClass.getDeclaredConstructors()[0];
                        return (DataProvider) ctor.newInstance(output, moreToolMats); // <-- cast here
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to create HephPlusMaterialRenderInfoProvider", e);
                    }
                });

                // MaterialPartTextureGenerator can be created normally because TinkerMaterialSpriteProvider exists
                pack.addProvider((output, registriesFuture) ->
                        new MaterialPartTextureGenerator(output, existingFileHelper, morePartSprites, (TinkerMaterialSpriteProvider) moreToolMats)
                );

            } catch (Exception e) {
                LOGGER.error("Failed to register HephaestusPlus datagen providers", e);
            }
        } else {
            LOGGER.info("HephaestusPlus not detected, skipping additional data providers.");
        }

        // Always-provided part textures
        pack.addProvider((output, registriesFuture) -> new GeneratorPartTextureJsonGenerator(output, MOD_ID, morePartSprites));
        pack.addProvider((output, registriesFuture) -> new MaterialPartTextureGenerator(output, existingFileHelper, morePartSprites, materialSprites));
        pack.addProvider((output, registriesFuture) -> new TinkerSpriteSourceGenerator(output, existingFileHelper));
    }


}