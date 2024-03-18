package io.zackmyers.hephaestusexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.dynamic.ExtraModifier;
import slimeknights.tconstruct.library.modifiers.dynamic.StatBoostModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
public class HephExModifierProvider extends AbstractModifierProvider {
    public HephExModifierProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    protected void addModifiers() {
        /*
        nothing yet
         */

    }

    @Override
    public String getName() {
        return "HephaestusExpansion Modifiers";
    }
}
