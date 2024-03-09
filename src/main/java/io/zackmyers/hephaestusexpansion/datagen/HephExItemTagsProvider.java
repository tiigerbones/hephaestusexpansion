package io.zackmyers.hephaestusexpansion.datagen;

import io.zackmyers.hephaestusexpansion.registry.HephExItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.common.registration.CastItemObject;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static slimeknights.tconstruct.common.TinkerTags.Items.*;

public class HephExItemTagsProvider extends FabricTagProvider.ItemTagProvider {
    public HephExItemTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        // Katana
        addToolTags(HephExItemRegistry.katana, MULTIPART_TOOL, DURABILITY, MELEE, HELD, SWORD);
        // KATAVISCERATOR
        addToolTags(HephExItemRegistry.kataviscerator, MULTIPART_TOOL, DURABILITY, MELEE, HELD, SWORD);
        Consumer<CastItemObject> addCast = cast -> {
            this.tag(GOLD_CASTS).addOptional(BuiltInRegistries.ITEM.getKey(cast.get()));
            this.tag(SAND_CASTS).addOptional(BuiltInRegistries.ITEM.getKey(cast.getSand()));
            this.tag(RED_SAND_CASTS).addOptional(BuiltInRegistries.ITEM.getKey(cast.getRedSand()));
        };
        // Katana
        addCast.accept(HephExItemRegistry.katanaBladeCast);
        // Kataviscerator
        addCast.accept(HephExItemRegistry.katavisceratorBladeCast);
    }

    @SafeVarargs
    private void addToolTags(ItemLike tool, TagKey<Item>... tags) {
        Item item = tool.asItem();
        for (TagKey<Item> tag : tags) {
            this.tag(tag).addOptional(BuiltInRegistries.ITEM.getKey(item));
        }
    }

    @Override
    public String getName() {
        return "HephaestusPlus Item Tags";
    }

}
