package io.zackmyers.hephaestusexpansion.datagen;

import io.zackmyers.hephaestusexpansion.registry.HephExItemRegistry;
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

        //  KATAVISCERATOR
        defineModifiable(HephExItemRegistry.kataviscerator)
                .sortIndex(SORT_WEAPON + SORT_LARGE)
                .addInputItem(HephExItemRegistry.katavisceratorBlade, 53, 22)
                .addInputItem(TinkerToolParts.toughHandle, 15, 60)
                .addInputItem(TinkerToolParts.toughHandle, 33, 42)
                .build();

        //  KATAVISCERATOR
        defineModifiable(HephExItemRegistry.spear)
                .sortIndex(SORT_WEAPON + SORT_LARGE)
                .addInputItem(HephExItemRegistry.spearHead, 45, 26)
                .addInputItem(TinkerToolParts.toolHandle, 7, 62)
                .addInputItem(TinkerToolParts.toolHandle, 25, 46)
                .addInputItem(TinkerToolParts.toolBinding, 45, 46)
                .build();
    }

    @Override
    public String getName() {
        return "HephaestusExpansion Tinker Station Slot Layouts";
    }
}
