package com.enchantedwisp.hephaestusexpansion.tools;

import com.enchantedwisp.hephaestusexpansion.registry.HephExItemRegistry;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;

public final class ToolDefinitions {
    public static final ToolDefinition KATANA = ToolDefinition.builder(HephExItemRegistry.katana).meleeHarvest().build();
    public static final ToolDefinition SPEAR = ToolDefinition.builder(HephExItemRegistry.spear).meleeHarvest().build();
    public static final ToolDefinition HALBERD = ToolDefinition.builder(HephExItemRegistry.halberd).meleeHarvest().build();
    public static final ToolDefinition GLAIVE = ToolDefinition.builder(HephExItemRegistry.glaive).meleeHarvest().build();
    public static final ToolDefinition CLAYMORE = ToolDefinition.builder(HephExItemRegistry.claymore).meleeHarvest().build();
}
