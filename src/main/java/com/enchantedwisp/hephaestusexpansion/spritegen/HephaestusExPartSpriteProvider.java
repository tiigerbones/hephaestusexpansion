package com.enchantedwisp.hephaestusexpansion.spritegen;

import com.enchantedwisp.hephaestusexpansion.HephaestusExpansion;
import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;

public class HephaestusExPartSpriteProvider extends AbstractPartSpriteProvider {

    public HephaestusExPartSpriteProvider() {
        super(HephaestusExpansion.MOD_ID);
    }

    @Override
    public String getName() {
        return "HephaestusExpansion Parts";
    }

    @Override
    protected void addAllSpites() {
        // KATANA
        buildTool("katana").withLarge().addBreakableHead("blade").withLarge().addHandle("guard").withLarge().addHandle("handle");
        // SPEAR
        buildTool("spear").withLarge().addBreakableHead("head").withLarge().addHandle("guard").withLarge().addHandle("handle");
        // HALBERD
        buildTool("halberd").withLarge().addBreakableHead("head").withLarge().addHandle("guard").withLarge().addHandle("handle");
        // GLAIVE
        buildTool("glaive").withLarge().addBreakableHead("head").withLarge().addHandle("guard").withLarge().addHandle("handle");
        // CLAYMORE
        buildTool("claymore").withLarge().addBreakableHead("blade").withLarge().addHandle("guard").withLarge().addHandle("handle");
    }
}
