package com.noxtrnal.vestiges.worldgen;

import com.noxtrnal.vestiges.Vestiges;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public final class VestigesWorldgen {
	public static final RegistryKey<PlacedFeature> GRASS_VARIANTS_PATCH = RegistryKey.of(
			RegistryKeys.PLACED_FEATURE,
			Identifier.of(Vestiges.MOD_ID, "grass_variants_patch")
	);
	public static final RegistryKey<PlacedFeature> PEBBLE_PATCH = RegistryKey.of(
			RegistryKeys.PLACED_FEATURE,
			Identifier.of(Vestiges.MOD_ID, "pebble_patch")
	);

	private VestigesWorldgen() {
	}

	public static void register() {
		BiomeModifications.addFeature(
				BiomeSelectors.foundInOverworld(),
				GenerationStep.Feature.VEGETAL_DECORATION,
				GRASS_VARIANTS_PATCH
		);
		BiomeModifications.addFeature(
				BiomeSelectors.foundInOverworld(),
				GenerationStep.Feature.VEGETAL_DECORATION,
				PEBBLE_PATCH
		);
	}
}
