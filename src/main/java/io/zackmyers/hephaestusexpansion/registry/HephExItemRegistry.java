package io.zackmyers.hephaestusexpansion.registry;

import io.zackmyers.hephaestusexpansion.tools.ToolDefinitions;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.item.Item;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.TinkerTabs;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.item.ModifiableSwordItem;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import static io.zackmyers.hephaestusexpansion.HephaestusExpansion.LOGGER;
import static io.zackmyers.hephaestusexpansion.HephaestusExpansion.MOD_ID;
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
    // Kataviscerator
    public static ItemObject<ModifiableSwordItem> kataviscerator = HephExItemRegistry.ITEMS.register("kataviscerator", () ->  new ModifiableSwordItem(HephExItemRegistry.TOOL, ToolDefinitions.KATAVISCERATOR, TinkerTabs.TAB_TOOLS));
    public static ItemObject<ToolPartItem> katavisceratorBlade = HephExItemRegistry.ITEMS.register("kataviscerator_blade", () -> new ToolPartItem(HephExItemRegistry.PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
    public static CastItemObject katavisceratorBladeCast = HephExItemRegistry.ITEMS.registerCast("kataviscerator_blade", HephExItemRegistry.SMELTERY_PROPS);

    public static void register() {
        LOGGER.info("Registering Items");
        ITEMS.register();
    }
}
