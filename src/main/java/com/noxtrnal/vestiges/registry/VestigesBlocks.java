package com.noxtrnal.vestiges.registry;

import com.noxtrnal.vestiges.Vestiges;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import java.util.function.Function;

public final class VestigesBlocks {
	public static final Block SHORT_GRASS = registerBlock(
			"short_grass",
			ShortPlantBlock::new
	);
	public static final Block PATCHY_GRASS = registerBlock(
			"patchy_grass",
			ShortPlantBlock::new
	);
	public static final Block MEDIUM_GRASS = registerBlock(
			"medium_grass",
			ShortPlantBlock::new
	);

	private VestigesBlocks() {
	}

	public static void register() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
			entries.add(SHORT_GRASS);
			entries.add(PATCHY_GRASS);
			entries.add(MEDIUM_GRASS);
		});
	}

	private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> blockFactory) {
		Identifier id = Identifier.of(Vestiges.MOD_ID, name);
		RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, id);
		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
		AbstractBlock.Settings settings = AbstractBlock.Settings.copy(Blocks.SHORT_GRASS).registryKey(blockKey);
		Block block = blockFactory.apply(settings);
		Registry.register(Registries.BLOCK, id, block);
		Registry.register(
				Registries.ITEM,
				id,
				new BlockItem(block, new Item.Settings().registryKey(itemKey))
		);
		return block;
	}
}
