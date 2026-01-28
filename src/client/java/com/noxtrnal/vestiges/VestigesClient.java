package com.noxtrnal.vestiges;

import com.noxtrnal.vestiges.registry.VestigesBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.world.biome.GrassColors;

public class VestigesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.putBlocks(
				BlockRenderLayer.CUTOUT,
				VestigesBlocks.SHORT_GRASS,
				VestigesBlocks.PATCHY_GRASS,
				VestigesBlocks.MEDIUM_GRASS
		);

		ColorProviderRegistry.BLOCK.register(
				(state, world, pos, tintIndex) -> world != null && pos != null
						? BiomeColors.getGrassColor(world, pos)
						: GrassColors.getColor(0.5D, 1.0D),
				VestigesBlocks.SHORT_GRASS,
				VestigesBlocks.PATCHY_GRASS,
				VestigesBlocks.MEDIUM_GRASS
		);
	}
}
