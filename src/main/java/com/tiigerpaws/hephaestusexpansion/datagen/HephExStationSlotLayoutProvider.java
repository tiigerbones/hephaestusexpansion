package com.tiigerpaws.hephaestusexpansion.datagen;

import com.tiigerpaws.hephaestusexpansion.registry.HephExItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.world.item.crafting.Ingredient;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractStationSlotLayoutProvider;
import slimeknights.tconstruct.tools.TinkerToolParts;
public class HephExStationSlotLayoutProvider extends AbstractStationSlotLayoutProvider {
    public HephExStationSlotLayoutProvider(FabricDataOutput output) {
        super(output);
    }

    /*
    documentation: https://github.com/Alpha-s-Stuff/TinkersConstruct/blob/1.20.1/src/main/java/slimeknights/tconstruct/tools/data/StationSlotLayoutProvider.java
     */

    @Override
    protected void addLayouts() {
        Ingredient modifiable = Ingredient.of(TinkerTags.Items.MODIFIABLE);
        //  KATANA
        defineModifiable(HephExItemRegistry.katana)
                .sortIndex(SORT_WEAPON + SORT_LARGE)
                .addInputItem(HephExItemRegistry.katanaBlade, 53, 22)
                .addInputItem(TinkerToolParts.toolHandle, 15, 60)
                .addInputItem(TinkerToolParts.toolHandle, 33, 42)
                .build();

        //  SPEAR
        defineModifiable(HephExItemRegistry.spear)
                .sortIndex(SORT_WEAPON + SORT_LARGE)
                .addInputItem(HephExItemRegistry.spearHead, 45, 26)
                .addInputItem(TinkerToolParts.toolHandle, 7, 62)
                .addInputItem(TinkerToolParts.toolHandle, 25, 46)
                .build();

        //  HALBERD
        defineModifiable(HephExItemRegistry.halberd)
                .sortIndex(SORT_WEAPON + SORT_LARGE)
                .addInputItem(HephExItemRegistry.halberdHead, 35, 20)
                .addInputItem(TinkerToolParts.toughHandle, 12, 55)
                .addInputItem(TinkerToolParts.toughHandle, 30, 40)
                .build();

        //  GLAIVE
        defineModifiable(HephExItemRegistry.glaive)
                .sortIndex(SORT_WEAPON + SORT_LARGE)
                .addInputItem(HephExItemRegistry.glaiveHead, 45, 26)
                .addInputItem(TinkerToolParts.toolHandle, 7, 62)
                .addInputItem(TinkerToolParts.toolHandle, 25, 46)
                .build();

        //  CLAYMORE
        defineModifiable(HephExItemRegistry.claymore)
                .sortIndex(SORT_WEAPON + SORT_LARGE)
                .addInputItem(HephExItemRegistry.claymoreBlade, 48, 26)
                .addInputItem(TinkerToolParts.toughHandle, 12, 62)
                .addInputItem(TinkerToolParts.toughHandle, 30, 44)
                .build();
    }

    @Override
    public String getName() {
        return "HephaestusExpansion Tinker Station Slot Layouts";
    }
}
