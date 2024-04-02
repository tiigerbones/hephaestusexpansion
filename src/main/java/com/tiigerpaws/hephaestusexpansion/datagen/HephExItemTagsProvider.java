package com.tiigerpaws.hephaestusexpansion.datagen;

import com.tiigerpaws.hephaestusexpansion.registry.HephExItemRegistry;
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
        // KATANA
        addToolTags(HephExItemRegistry.katana, MULTIPART_TOOL, DURABILITY, MELEE, HELD, SWORD);
        // SPEAR
        addToolTags(HephExItemRegistry.spear, MULTIPART_TOOL, DURABILITY, MELEE, HELD, SWORD);
        // HALBERD
        addToolTags(HephExItemRegistry.halberd, MULTIPART_TOOL, DURABILITY, MELEE, HELD, SWORD);
        // GLAIVE
        addToolTags(HephExItemRegistry.glaive, MULTIPART_TOOL, DURABILITY, MELEE, HELD, SWORD);
        // CLAYMORE
        addToolTags(HephExItemRegistry.claymore, MULTIPART_TOOL, DURABILITY, MELEE, HELD, SWORD);


        Consumer<CastItemObject> addCast = cast -> {
            this.tag(GOLD_CASTS).addOptional(BuiltInRegistries.ITEM.getKey(cast.get()));
            this.tag(SAND_CASTS).addOptional(BuiltInRegistries.ITEM.getKey(cast.getSand()));
            this.tag(RED_SAND_CASTS).addOptional(BuiltInRegistries.ITEM.getKey(cast.getRedSand()));
        };
        // Katana
        addCast.accept(HephExItemRegistry.katanaBladeCast);
        // Spear
        addCast.accept(HephExItemRegistry.spearHeadCast);
        // Halberd
        addCast.accept(HephExItemRegistry.halberdHeadCast);
        // Glaive
        addCast.accept(HephExItemRegistry.glaiveHeadCast);
        // Claymore
        addCast.accept(HephExItemRegistry.claymoreBladeCast);
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
        return "HephaestusExpansion Item Tags";
    }

}
