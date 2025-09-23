package com.enchantedwisp.hephaestusexpansion;

import com.enchantedwisp.hephaestusexpansion.datagen.*;
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import com.enchantedwisp.hephaestusexpansion.datagen.*;
import com.enchantedwisp.hephaestusexpansion.spritegen.HephaestusExPartSpriteProvider;
import konsola5.hephaestusplus.spritegen.HephPlusMaterialRenderInfoProvider;
import konsola5.hephaestusplus.spritegen.HephaestusPlusMaterialSpriteProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import org.slf4j.Logger;
import slimeknights.tconstruct.library.client.data.TinkerSpriteSourceGenerator;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.material.MaterialRenderInfoProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

import static com.enchantedwisp.hephaestusexpansion.HephaestusExpansion.MOD_ID;

public class HephExRegistry {
    private static final Logger LOGGER = HephaestusExpansion.LOGGER;

    public static void gatherData(FabricDataGenerator.Pack pack, ExistingFileHelper existingFileHelper) {
        LOGGER.info("gathering data....");

        FabricTagProvider.BlockTagProvider blockTags = pack.addProvider(HephExBlockTagsProvider::new); // Required, despite not adding any blocks yet.
        pack.addProvider((output, registriesFuture) -> new HephExItemTagsProvider(output, registriesFuture, blockTags));

        pack.addProvider(HephExToolRecipeProvider::new);
        pack.addProvider(HephExMaterialRecipeProvider::new);
        pack.addProvider(HephExStationSlotLayoutProvider::new);
        pack.addProvider(HephExSmelteryRecipeProvider::new);
        pack.addProvider(HephExToolDefinitionProvider::new);
        pack.addProvider(HephExModifierProvider::new);
        pack.addProvider(HephExModifierRecipeProvider::new);


        TinkerMaterialSpriteProvider materialSprites = new TinkerMaterialSpriteProvider();
        HephaestusPlusMaterialSpriteProvider moreToolMats = new HephaestusPlusMaterialSpriteProvider();


        TinkerPartSpriteProvider partSprites = new TinkerPartSpriteProvider();
        HephaestusExPartSpriteProvider morePartSprites = new HephaestusExPartSpriteProvider();

        pack.addProvider((output, registriesFuture) -> new MaterialRenderInfoProvider(output, materialSprites));
        pack.addProvider((output, registriesFuture) -> new HephPlusMaterialRenderInfoProvider(output, moreToolMats));
        pack.addProvider((output, registriesFuture) -> new GeneratorPartTextureJsonGenerator(output, MOD_ID, morePartSprites));


        // Tinkers' materials for HephaestusExpansion parts
        pack.addProvider((output, registriesFuture) -> new MaterialPartTextureGenerator(output, existingFileHelper, morePartSprites, materialSprites));
        // HephPlus materials for HephaestusExpansion parts
        pack.addProvider((output, registriesFuture) -> new MaterialPartTextureGenerator(output, existingFileHelper, morePartSprites, moreToolMats));


        pack.addProvider((output, registriesFuture) -> new TinkerSpriteSourceGenerator(output, existingFileHelper));

    }
}


