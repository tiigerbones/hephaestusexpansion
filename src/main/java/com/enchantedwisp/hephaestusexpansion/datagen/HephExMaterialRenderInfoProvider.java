package com.enchantedwisp.hephaestusexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

public class HephExMaterialRenderInfoProvider extends AbstractMaterialRenderInfoProvider {

    public HephExMaterialRenderInfoProvider(FabricDataOutput output, @Nullable AbstractMaterialSpriteProvider materialSprites) {
        super(output, materialSprites);
    }
    @Override
    protected void addMaterialRenderInfo() {

        /*
        nothing yet
         */
    }

    @Override
    public String getName() {
        return "HephaestusExpansion Material Render Info Provider";
    }
}