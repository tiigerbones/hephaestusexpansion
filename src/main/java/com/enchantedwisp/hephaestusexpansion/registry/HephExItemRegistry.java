package com.enchantedwisp.hephaestusexpansion.registry;

import com.enchantedwisp.hephaestusexpansion.tools.ToolDefinitions;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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
    public static final Item.Properties SMELTERY_PROPS = new Item.Properties();
    // Hephaestus tools
    // Katana
    public static ItemObject<ModifiableSwordItem> katana = HephExItemRegistry.ITEMS.register("katana", () ->  new ModifiableSwordItem(HephExItemRegistry.TOOL, ToolDefinitions.KATANA, TinkerTabs.TAB_TOOLS));
    public static ItemObject<ToolPartItem> katanaBlade = HephExItemRegistry.ITEMS.register("katana_blade", () -> new ToolPartItem(HephExItemRegistry.PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
    public static CastItemObject katanaBladeCast = HephExItemRegistry.ITEMS.registerCast("katana_blade", HephExItemRegistry.SMELTERY_PROPS);
    // Spear
    public static ItemObject<ModifiableSwordItem> spear = HephExItemRegistry.ITEMS.register("spear", () ->  new ModifiableSwordItem(HephExItemRegistry.TOOL, ToolDefinitions.SPEAR, TinkerTabs.TAB_TOOLS));
    public static ItemObject<ToolPartItem> spearHead = HephExItemRegistry.ITEMS.register("spear_head", () -> new ToolPartItem(HephExItemRegistry.PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
    public static CastItemObject spearHeadCast = HephExItemRegistry.ITEMS.registerCast("spear_head", HephExItemRegistry.SMELTERY_PROPS);
    // Halberd
    public static ItemObject<ModifiableSwordItem> halberd = HephExItemRegistry.ITEMS.register("halberd", () ->  new ModifiableSwordItem(HephExItemRegistry.TOOL, ToolDefinitions.HALBERD, TinkerTabs.TAB_TOOLS));
    public static ItemObject<ToolPartItem> halberdHead = HephExItemRegistry.ITEMS.register("halberd_head", () -> new ToolPartItem(HephExItemRegistry.PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
    public static CastItemObject halberdHeadCast = HephExItemRegistry.ITEMS.registerCast("halberd_head", HephExItemRegistry.SMELTERY_PROPS);
    // Glaive
    public static ItemObject<ModifiableSwordItem> glaive = HephExItemRegistry.ITEMS.register("glaive", () ->  new ModifiableSwordItem(HephExItemRegistry.TOOL, ToolDefinitions.GLAIVE, TinkerTabs.TAB_TOOLS));
    public static ItemObject<ToolPartItem> glaiveHead = HephExItemRegistry.ITEMS.register("glaive_head", () -> new ToolPartItem(HephExItemRegistry.PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
    public static CastItemObject glaiveHeadCast = HephExItemRegistry.ITEMS.registerCast("glaive_head", HephExItemRegistry.SMELTERY_PROPS);
    // Claymore
    public static ItemObject<ModifiableSwordItem> claymore = HephExItemRegistry.ITEMS.register("claymore", () ->  new ModifiableSwordItem(HephExItemRegistry.TOOL, ToolDefinitions.CLAYMORE, TinkerTabs.TAB_TOOLS));
    public static ItemObject<ToolPartItem> claymoreBlade = HephExItemRegistry.ITEMS.register("claymore_blade", () -> new ToolPartItem(HephExItemRegistry.PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
    public static CastItemObject claymoreBladeCast = HephExItemRegistry.ITEMS.registerCast("claymore_blade", HephExItemRegistry.SMELTERY_PROPS);

    public static void register() {
        LOGGER.info("Registering Items");
        ITEMS.register();
    }
}
