package io.zackmyers.hephaestusexpansion.tools;

import io.zackmyers.hephaestusexpansion.registry.HephExItemRegistry;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;

public final class ToolDefinitions {
    public static final ToolDefinition KATANA = ToolDefinition.builder(HephExItemRegistry.katana).meleeHarvest().build();
    public static final ToolDefinition KATAVISCERATOR = ToolDefinition.builder(HephExItemRegistry.kataviscerator).meleeHarvest().build();
    public static final ToolDefinition SPEAR = ToolDefinition.builder(HephExItemRegistry.spear).meleeHarvest().build();
}
