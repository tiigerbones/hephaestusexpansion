package com.tiigerpaws.hephaestusexpansion;

import com.tiigerpaws.hephaestusexpansion.registry.HephExItemRegistry;
import net.fabricmc.api.ClientModInitializer;
import slimeknights.tconstruct.library.client.model.TinkerItemProperties;

public class HephaestusExpansionClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		TinkerItemProperties.registerToolProperties(HephExItemRegistry.claymore.asItem());
		TinkerItemProperties.registerToolProperties(HephExItemRegistry.glaive.asItem());
		TinkerItemProperties.registerToolProperties(HephExItemRegistry.spear.asItem());
		TinkerItemProperties.registerToolProperties(HephExItemRegistry.halberd.asItem());
		TinkerItemProperties.registerToolProperties(HephExItemRegistry.katana.asItem());

	}
}