package io.zackmyers.hephaestusexpansion.registry;

import io.zackmyers.hephaestusexpansion.tools.ToolDefinitions;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.Item;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.TinkerTabs;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import static io.zackmyers.hephaestusexpansion.HephaestusExpansion.MOD_ID;
public class HephExItemRegistry {
    public static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(MOD_ID);
    public static final Item.Properties TOOL = new FabricItemSettings().maxCount(1);
    // Properties
    public static final Item.Properties PARTS_PROPS = new Item.Properties();
    public static final Item.Properties SMELTERY_PROPS = new Item.Properties();
    private static final Item.Properties ITEM_PROPS = new Item.Properties();
    public static final ItemObject<Item> carmotReinforcement = ITEMS.register("carmot_reinforcement", ITEM_PROPS);
    // Hephaestus tools
    public static ItemObject<ModifiableItem> katana;
    public static ItemObject<ToolPartItem> katanaBlade;
    public static CastItemObject katanaBladeCast;

    static {
        if (FabricLoader.getInstance().isModLoaded("simplyswords")) {
            HephExItemRegistry.katana = HephExItemRegistry.ITEMS.register("katana", () -> new ModifiableItem(HephExItemRegistry.TOOL, ToolDefinitions.KATANA, TinkerTabs.TAB_TOOLS));

            HephExItemRegistry.katanaBlade = HephExItemRegistry.ITEMS.register("katana_blade", () -> new ToolPartItem(HephExItemRegistry.PARTS_PROPS, HeadMaterialStats.ID, TinkerTabs.TAB_TOOL_PARTS));
            HephExItemRegistry.katanaBladeCast = HephExItemRegistry.ITEMS.registerCast("katana_blade", HephExItemRegistry.SMELTERY_PROPS);
        } else {
            HephExItemRegistry.katana = null;

            HephExItemRegistry.katanaBlade = null;
            HephExItemRegistry.katanaBladeCast = null;
        }
    }
    public static void register() {
        ITEMS.register();
    }
}
