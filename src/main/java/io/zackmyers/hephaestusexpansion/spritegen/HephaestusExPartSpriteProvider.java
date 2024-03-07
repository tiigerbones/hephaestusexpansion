package io.zackmyers.hephaestusexpansion.spritegen;

import io.zackmyers.hephaestusexpansion.HephaestusExpansion;
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
    protected void addAllSpites() { // sic
        buildTool("katana").addBreakableHead("blade").addHandle("guard").addHandle("handle");
    }
}
