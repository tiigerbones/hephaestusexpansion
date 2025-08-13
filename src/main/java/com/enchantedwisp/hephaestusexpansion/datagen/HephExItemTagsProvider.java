package com.enchantedwisp.hephaestusexpansion.datagen;

import com.enchantedwisp.hephaestusexpansion.registry.HephExItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.mantle.registration.object.ItemObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static slimeknights.tconstruct.common.TinkerTags.Items.*;

@SuppressWarnings("unchecked")
public class HephExItemTagsProvider extends FabricTagProvider.ItemTagProvider {
    public HephExItemTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    // Common tags for all modifiable sword-like weapons
    private static final TagKey<Item>[] DEFAULT_WEAPON_TAGS = new TagKey[]{
            MULTIPART_TOOL, DURABILITY, MELEE, HELD, SWORD
    };

    // Configuration for weapons with additional tags
    private static final Map<ItemObject<?>, List<TagKey<Item>>> WEAPON_TAG_OVERRIDES = Map.of(
            HephExItemRegistry.katana, List.of(INTERACTABLE_RIGHT),
            HephExItemRegistry.claymore, List.of(INTERACTABLE_RIGHT),
            HephExItemRegistry.glaive, List.of(INTERACTABLE_RIGHT),
            HephExItemRegistry.halberd, List.of(INTERACTABLE_RIGHT),
            HephExItemRegistry.spear, List.of(INTERACTABLE_RIGHT)
//          HephExItemRegistry.rapier, List.of(PARRY)

    );

    // List of all weapon casts
    private static final CastItemObject[] WEAPON_CASTS = new CastItemObject[]{
            HephExItemRegistry.katanaBladeCast,
            HephExItemRegistry.spearHeadCast,
            HephExItemRegistry.halberdHeadCast,
            HephExItemRegistry.glaiveHeadCast,
            HephExItemRegistry.claymoreBladeCast
    };

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        registerWeaponTags();
        registerCastTags();
    }

    /** Tags all weapons with their respective tags */
    private void registerWeaponTags() {
        WEAPON_TAG_OVERRIDES.forEach((weapon, extraTags) -> {
            // Combine default tags with any extra tags for this weapon
            TagKey<Item>[] allTags = combineTags(extraTags);
            addToolTags(weapon, allTags);
        });
    }

    /** Tags all weapon casts */
    private void registerCastTags() {
        var goldCasts = this.tag(TinkerTags.Items.GOLD_CASTS);
        var sandCasts = this.tag(TinkerTags.Items.SAND_CASTS);
        var redSandCasts = this.tag(TinkerTags.Items.RED_SAND_CASTS);
        var singleUseCasts = this.tag(TinkerTags.Items.SINGLE_USE_CASTS);
        var multiUseCasts = this.tag(TinkerTags.Items.MULTI_USE_CASTS);

        Consumer<CastItemObject> addCast = cast -> {
            // Tag based on material
            goldCasts.addOptional(BuiltInRegistries.ITEM.getKey(cast.get()));
            sandCasts.addOptional(BuiltInRegistries.ITEM.getKey(cast.getSand()));
            redSandCasts.addOptional(BuiltInRegistries.ITEM.getKey(cast.getRedSand()));

            // Tag based on usage
            singleUseCasts.addOptionalTag(cast.getSingleUseTag().location());
            this.tag(cast.getSingleUseTag())
                    .addOptional(BuiltInRegistries.ITEM.getKey(cast.getSand()))
                    .addOptional(BuiltInRegistries.ITEM.getKey(cast.getRedSand()));
            multiUseCasts.addOptionalTag(cast.getMultiUseTag().location());
            this.tag(cast.getMultiUseTag())
                    .addOptional(BuiltInRegistries.ITEM.getKey(cast.get()));
        };

        for (CastItemObject cast : WEAPON_CASTS) {
            addCast.accept(cast);
        }
    }

    /** Combines default tags with extra tags for a weapon */
    private TagKey<Item>[] combineTags(List<TagKey<Item>> extraTags) {
        TagKey<Item>[] combined = Arrays.copyOf(HephExItemTagsProvider.DEFAULT_WEAPON_TAGS, HephExItemTagsProvider.DEFAULT_WEAPON_TAGS.length + extraTags.size());
        for (int i = 0; i < extraTags.size(); i++) {
            combined[HephExItemTagsProvider.DEFAULT_WEAPON_TAGS.length + i] = extraTags.get(i);
        }
        return combined;
    }

    /** Adds the given tool to all provided tags */
    @SafeVarargs
    private void addToolTags(ItemLike tool, TagKey<Item>... tags) {
        var itemKey = BuiltInRegistries.ITEM.getKey(tool.asItem());
        for (TagKey<Item> tag : tags) {
            this.tag(tag).addOptional(itemKey);
        }
    }

    @Override
    public String getName() {
        return "HephaestusExpansion Item Tags";
    }
}