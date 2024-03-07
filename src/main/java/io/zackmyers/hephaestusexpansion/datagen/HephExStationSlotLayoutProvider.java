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
    }

    @Override
    public String getName() {
        return "HephaestusExpansion Tinker Station Slot Layouts";
    }
}
