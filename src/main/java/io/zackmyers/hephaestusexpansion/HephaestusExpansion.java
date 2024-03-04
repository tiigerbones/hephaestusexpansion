package io.zackmyers.hephaestusexpansion;

import com.llamalad7.mixinextras.MixinExtrasBootstrap;
import io.github.fabricators_of_create.porting_lib.util.TierSortingRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.util.List;

public class HephaestusExpansion implements ModInitializer {

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("hephaestusexpansion");
	public static final String MOD_ID = "hephaestusexpansion";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		MixinExtrasBootstrap.init();

		boolean isSSLoaded = FabricLoader.getInstance().isModLoaded("simplyswords");
		boolean isMythicMetalsLoaded = FabricLoader.getInstance().isModLoaded("mythicmetals");
		if (!(isSSLoaded || isMythicMetalsLoaded)) {
			LOGGER.warn("Neither SimplySwords nor Mythic Metals were detected! Currently HephaestusExpansion does not add any additions to the base Hephaestus.");
		}
	}
}