package com.enchantedwisp.hephaestusexpansion.datagen;

import com.enchantedwisp.hephaestusexpansion.ids.HephExModifierIds;
import com.enchantedwisp.hephaestusexpansion.registry.HephExModifierRegistry;
import io.github.fabricators_of_create.porting_lib.tool.ToolActions;
import com.enchantedwisp.hephaestusexpansion.HephaestusExpansion;
import com.enchantedwisp.hephaestusexpansion.registry.HephExItemRegistry;
import com.enchantedwisp.hephaestusexpansion.tools.ToolDefinitions;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.world.level.block.Blocks;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.tools.definition.harvest.IHarvestLogic;
import slimeknights.tconstruct.library.tools.definition.harvest.ModifiedHarvestLogic;
import slimeknights.tconstruct.library.tools.definition.weapon.SweepWeaponAttack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;

import static slimeknights.tconstruct.tools.TinkerToolParts.*;
public class HephExToolDefinitionProvider extends AbstractToolDefinitionDataProvider {
    public HephExToolDefinitionProvider(FabricDataOutput output) {
        super(output, HephaestusExpansion.MOD_ID);
    }

    /*
    documentation https://github.com/Alpha-s-Stuff/TinkersConstruct/blob/1.20.1/src/main/java/slimeknights/tconstruct/tools/data/ToolDefinitionDataProvider.java
    */

    @Override
    protected void addToolDefinitions() {
        IHarvestLogic swordLogic = ModifiedHarvestLogic
                .builder(TinkerTags.Blocks.MINABLE_WITH_SWORD)
                .blockModifier(7.5f, Blocks.COBWEB)
                .blockModifier(100f, Blocks.BAMBOO, Blocks.BAMBOO_SAPLING)
                .build();
        // KATANA
        define(ToolDefinitions.KATANA)
                // parts
                .part(HephExItemRegistry.katanaBlade)
                .part(toolHandle)
                .part(toolHandle)
                // stats
                .stat(ToolStats.ATTACK_DAMAGE, 3f)
                .stat(ToolStats.ATTACK_SPEED, 2f)
                .multiplier(ToolStats.MINING_SPEED, 0.4f)
                .multiplier(ToolStats.DURABILITY, 1.3f)
                .smallToolStartingSlots()
                // traits
                .trait(TinkerModifiers.severing, 1)
                .trait(HephExModifierIds.true_edge, 2)
                // behavior
                .action(ToolActions.SWORD_DIG)
                .harvestLogic(swordLogic)
                .attack(new SweepWeaponAttack(1));

        // SPEAR
        define(ToolDefinitions.SPEAR)
                // parts
                .part(HephExItemRegistry.spearHead)
                .part(toolHandle)
                .part(toolHandle)
                // stats
                .stat(ToolStats.ATTACK_DAMAGE, 3f)
                .stat(ToolStats.ATTACK_SPEED, 1.3f)
                .multiplier(ToolStats.MINING_SPEED, 0.25f)
                .multiplier(ToolStats.DURABILITY, 1.3f)
                .largeToolStartingSlots()
                // traits
                .trait(HephExModifierIds.piercing_thrust, 1)
                // behavior
                .action(ToolActions.SWORD_DIG)
                .harvestLogic(swordLogic);

        // HALBERD
        define(ToolDefinitions.HALBERD)
                // parts
                .part(HephExItemRegistry.halberdHead)
                .part(toughHandle)
                .part(toughHandle)
                // stats
                .stat(ToolStats.ATTACK_DAMAGE, 6f)
                .stat(ToolStats.ATTACK_SPEED, 1.2f)
                .multiplier(ToolStats.MINING_SPEED, 0.32f)
                .multiplier(ToolStats.DURABILITY, 2f)
                .largeToolStartingSlots()
                // traits
                .trait(TinkerModifiers.withered, 1)
                // behavior
                .action(ToolActions.SWORD_DIG)
                .harvestLogic(swordLogic)
                .attack(new SweepWeaponAttack(2));

        // GLAIVE
        define(ToolDefinitions.GLAIVE)
                // parts
                .part(HephExItemRegistry.glaiveHead)
                .part(toolHandle)
                .part(toolHandle)
                // stats
                .stat(ToolStats.ATTACK_DAMAGE, 3f)
                .stat(ToolStats.ATTACK_SPEED, 1.4f)
                .multiplier(ToolStats.MINING_SPEED, 0.25f)
                .multiplier(ToolStats.DURABILITY, 1.1f)
                .largeToolStartingSlots()
                // traits
                .trait(TinkerModifiers.sweeping, 1)
                // behavior
                .action(ToolActions.SWORD_DIG)
                .harvestLogic(swordLogic)
                .attack(new SweepWeaponAttack(3));

        // CLAYMORE
        define(ToolDefinitions.CLAYMORE)
                // parts
                .part(HephExItemRegistry.claymoreBlade)
                .part(toughHandle)
                .part(toughHandle)
                // stats
                .stat(ToolStats.ATTACK_DAMAGE, 5f)
                .stat(ToolStats.ATTACK_SPEED, 1.2f)
                .multiplier(ToolStats.MINING_SPEED, 0.2f)
                .multiplier(ToolStats.DURABILITY, 1.6f)
                .smallToolStartingSlots()
                // traits
                .trait(TinkerModifiers.knockback, 1)
                // behavior
                .action(ToolActions.SWORD_DIG)
                .harvestLogic(swordLogic)
                .attack(new SweepWeaponAttack(2));

    }

    @Override
    public String getName() {
        return "HephaestusExpansion Tool Definition Data Generator";
    }
}
