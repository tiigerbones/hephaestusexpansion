package io.zackmyers.hephaestusexpansion.datagen;

import io.github.fabricators_of_create.porting_lib.tool.ToolActions;
import io.zackmyers.hephaestusexpansion.HephaestusExpansion;
import io.zackmyers.hephaestusexpansion.registry.HephExItemRegistry;
import io.zackmyers.hephaestusexpansion.tools.ToolDefinitions;
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

    @Override
    protected void addToolDefinitions() {
        IHarvestLogic swordLogic = ModifiedHarvestLogic
                .builder(TinkerTags.Blocks.MINABLE_WITH_SWORD)
                .blockModifier(7.5f, Blocks.COBWEB)
                .blockModifier(100f, Blocks.BAMBOO, Blocks.BAMBOO_SAPLING)
                .build();
        // katana
        define(ToolDefinitions.KATANA)
                // parts
                .part(HephExItemRegistry.katanaBlade)
                .part(toolHandle)
                .part(toolHandle)
                // stats
                .stat(ToolStats.ATTACK_DAMAGE, 3f)
                .stat(ToolStats.ATTACK_SPEED, 1.6f)
                .multiplier(ToolStats.ATTACK_DAMAGE, 1.6f)
                .multiplier(ToolStats.MINING_SPEED, 0.4f)
                .multiplier(ToolStats.DURABILITY, 1.7f)
                .largeToolStartingSlots()
                // traits
                .trait(TinkerModifiers.severing, 1)
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
