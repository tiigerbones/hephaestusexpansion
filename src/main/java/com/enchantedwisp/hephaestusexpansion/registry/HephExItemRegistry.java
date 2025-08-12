package com.enchantedwisp.hephaestusexpansion.registry;

import com.enchantedwisp.hephaestusexpansion.tools.ToolDefinitions;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.Item;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.TinkerTabs;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.item.ModifiableSwordItem;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import static com.enchantedwisp.hephaestusexpansion.HephaestusExpansion.LOGGER;
import static com.enchantedwisp.hephaestusexpansion.HephaestusExpansion.MOD_ID;

public class HephExItemRegistry {
    public static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(MOD_ID);
    public static final Item.Properties TOOL = new FabricItemSettings().maxCount(1);
    // Properties
    public static final Item.Properties PARTS_PROPS = new Item.Properties();
    public static final Item.Properties SMELTERY_PROPS = new Item.Properties(); // Keep as Item.Properties for TConstruct
    // Hephaestus tools
    // Katana
    public static ItemObject<ModifiableSwordItem> katana = ITEMS.register("katana", () -> new ModifiableSwordItem(TOOL, ToolDefinitions.KATANA, TinkerTabs.TAB_TOOLS));
    public static ItemObject<ToolPartItem> katanaBlade = ITEMS.register("katana_blade", () -> new ToolPartItem(PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
    public static CastItemObject katanaBladeCast = ITEMS.registerCast("katana_blade", SMELTERY_PROPS);
    // Spear
    public static ItemObject<ModifiableSwordItem> spear = ITEMS.register("spear", () -> new ModifiableSwordItem(TOOL, ToolDefinitions.SPEAR, TinkerTabs.TAB_TOOLS));
    public static ItemObject<ToolPartItem> spearHead = ITEMS.register("spear_head", () -> new ToolPartItem(PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
    public static CastItemObject spearHeadCast = ITEMS.registerCast("spear_head", SMELTERY_PROPS);
    // Halberd
    public static ItemObject<ModifiableSwordItem> halberd = ITEMS.register("halberd", () -> new ModifiableSwordItem(TOOL, ToolDefinitions.HALBERD, TinkerTabs.TAB_TOOLS));
    public static ItemObject<ToolPartItem> halberdHead = ITEMS.register("halberd_head", () -> new ToolPartItem(PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
    public static CastItemObject halberdHeadCast = ITEMS.registerCast("halberd_head", SMELTERY_PROPS);
    // Glaive
    public static ItemObject<ModifiableSwordItem> glaive = ITEMS.register("glaive", () -> new ModifiableSwordItem(TOOL, ToolDefinitions.GLAIVE, TinkerTabs.TAB_TOOLS));
    public static ItemObject<ToolPartItem> glaiveHead = ITEMS.register("glaive_head", () -> new ToolPartItem(PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
    public static CastItemObject glaiveHeadCast = ITEMS.registerCast("glaive_head", SMELTERY_PROPS);
    // Claymore
    public static ItemObject<ModifiableSwordItem> claymore = ITEMS.register("claymore", () -> new ModifiableSwordItem(TOOL, ToolDefinitions.CLAYMORE, TinkerTabs.TAB_TOOLS));
    public static ItemObject<ToolPartItem> claymoreBlade = ITEMS.register("claymore_blade", () -> new ToolPartItem(PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
    public static CastItemObject claymoreBladeCast = ITEMS.registerCast("claymore_blade", SMELTERY_PROPS);

    public static void register() {
        LOGGER.info("Registering Items");
        ITEMS.register();

        // accept casts and their sand/red sand variants to TinkerTabs.TAB_SMELTERY
        ItemGroupEvents.modifyEntriesEvent(TinkerTabs.TAB_SMELTERY).register(entries -> {
            // Katana casts
            entries.accept(katanaBladeCast.get()); // Gold cast
            entries.accept(katanaBladeCast.getSand()); // Sand cast
            entries.accept(katanaBladeCast.getRedSand()); // Red sand cast
            // Spear casts
            entries.accept(spearHeadCast.get());
            entries.accept(spearHeadCast.getSand());
            entries.accept(spearHeadCast.getRedSand());
            // Halberd casts
            entries.accept(halberdHeadCast.get());
            entries.accept(halberdHeadCast.getSand());
            entries.accept(halberdHeadCast.getRedSand());
            // Glaive casts
            entries.accept(glaiveHeadCast.get());
            entries.accept(glaiveHeadCast.getSand());
            entries.accept(glaiveHeadCast.getRedSand());
            // Claymore casts
            entries.accept(claymoreBladeCast.get());
            entries.accept(claymoreBladeCast.getSand());
            entries.accept(claymoreBladeCast.getRedSand());
        });
    }
}