package io.zackmyers.hephaestusexpansion;

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import io.zackmyers.hephaestusexpansion.datagen.*;
import io.zackmyers.hephaestusexpansion.registry.HephExItemRegistry;
import io.zackmyers.hephaestusexpansion.spritegen.HephaestusExPartSpriteProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import slimeknights.tconstruct.library.client.data.TinkerSpriteSourceGenerator;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.material.MaterialRenderInfoProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

import static io.zackmyers.hephaestusexpansion.HephaestusExpansion.LOGGER;
import static io.zackmyers.hephaestusexpansion.HephaestusExpansion.MOD_ID;

public class HephExRegistry {

    public static void init() {
        HephExItemRegistry.register();
    }

    public static void gatherData(FabricDataGenerator.Pack pack, ExistingFileHelper existingFileHelper) {
        LOGGER.info("gathering data....");

        FabricTagProvider.BlockTagProvider blockTags = pack.addProvider(HephExBlockTagsProvider::new); // Required, despite not adding any blocks yet.
        pack.addProvider((output, registriesFuture) -> new HephExItemTagsProvider(output, registriesFuture, blockTags));

        pack.addProvider(HephExToolRecipeProvider::new);
        pack.addProvider(HephExMaterialRecipeProvider::new);
        pack.addProvider(HephExStationSlotLayoutProvider::new);
        pack.addProvider(HephExModifierRecipeProvider::new);
        pack.addProvider(HephExSmelteryRecipeProvider::new);
        pack.addProvider(HephExToolDefinitionProvider::new);
        pack.addProvider(HephExModifierProvider::new);

        /*
        we don't add materials or traits yet
        HephExMaterialDataProvider materials = pack.addProvider(HephExMaterialDataProvider::new);
        pack.addProvider((output, registriesFuture) -> new HephExMaterialStatsProvider(output, materials));
        pack.addProvider((output, registriesFuture) -> new HephExMaterialTraitsProvider(output, materials));
        */

        TinkerMaterialSpriteProvider materialSprites = new TinkerMaterialSpriteProvider();

        /*
        we don't add materials yet
        HephaestusExMaterialSpriteProvider moreToolMats = new HephaestusExMaterialSpriteProvider();
        */

        TinkerPartSpriteProvider partSprites = new TinkerPartSpriteProvider();

        HephaestusExPartSpriteProvider morePartSprites = new HephaestusExPartSpriteProvider();

        pack.addProvider((output, registriesFuture) -> new MaterialRenderInfoProvider(output, materialSprites));
        //pack.addProvider((output, registriesFuture) -> new GeneratorPartTextureJsonGenerator(output, HephaestusExpansion.MOD_ID, partSprites));

        /*
        we don't add materials yet
        pack.addProvider((output, registriesFuture) -> new HephExMaterialRenderInfoProvider(output, moreToolMats));
        */


        pack.addProvider((output, registriesFuture) -> new GeneratorPartTextureJsonGenerator(output, MOD_ID, morePartSprites));


        //Tinkers' materials for HephaestusExpansion parts
        pack.addProvider((output, registriesFuture) -> new MaterialPartTextureGenerator(output, existingFileHelper, morePartSprites, materialSprites));



        /*
        we don't add materials yet

        HephaestusExpansion materials for Tinkers' parts
        pack.addProvider((output, registriesFuture) -> new MaterialPartTextureGenerator(output, existingFileHelper, partSprites));

        HephaestusExpansion materials for HephaestusExpansion parts
        pack.addProvider((output, registriesFuture) -> new MaterialPartTextureGenerator(output, existingFileHelper));
         */

        pack.addProvider((output, registriesFuture) -> new TinkerSpriteSourceGenerator(output, existingFileHelper));
    }
}

