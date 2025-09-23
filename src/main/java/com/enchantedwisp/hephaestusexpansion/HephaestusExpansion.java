package com.enchantedwisp.hephaestusexpansion;

import blue.endless.jankson.annotation.Nullable;
import com.enchantedwisp.hephaestusexpansion.registry.HephExItemRegistry;
import com.enchantedwisp.hephaestusexpansion.registry.HephExModifierRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HephaestusExpansion implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("hephaestusexpansion");
	public static final String MOD_ID = "hephaestusexpansion";

	@Override
	public void onInitialize() {
        HephExModifierRegistry.register();
        HephExItemRegistry.register();
		LOGGER.info("Loaded Registry");
	}

	public static ResourceLocation getResource(String name) {
		return new ResourceLocation(MOD_ID, name);
	}

	public static String makeTranslationKey(String base, @Nullable ResourceLocation name) {
		return net.minecraft.Util.makeDescriptionId(base, name);
	}

	public static String makeTranslationKey(String base, String name) {
		return makeTranslationKey(base, getResource(name));
	}

	public static MutableComponent makeTranslation(String base, String name) {
		return Component.translatable(makeTranslationKey(base, name));
	}

	public static MutableComponent makeTranslation(String base, String name, Object... arguments) {
		return Component.translatable(makeTranslationKey(base, name), arguments);
	}
}