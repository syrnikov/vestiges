package com.noxtrnal.vestiges.registry;

import com.noxtrnal.vestiges.Vestiges;
import com.noxtrnal.vestiges.block.PebbleBlock;
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
	public static final Block PEBBLE = registerBlock(
			"pebble",
			settings -> new PebbleBlock(
					settings
							.noCollision()
							.strength(0.5F, 0.5F)
							.offset(AbstractBlock.OffsetType.XZ)
			),
			Blocks.STONE
	);

	private VestigesBlocks() {
	}

	public static void register() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
			entries.add(SHORT_GRASS);
			entries.add(PATCHY_GRASS);
			entries.add(MEDIUM_GRASS);
			entries.add(PEBBLE);
		});
	}

	private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> blockFactory) {
		return registerBlock(name, blockFactory, Blocks.SHORT_GRASS);
	}

	private static Block registerBlock(
			String name,
			Function<AbstractBlock.Settings, Block> blockFactory,
			Block settingsSource
	) {
		Identifier id = Identifier.of(Vestiges.MOD_ID, name);
		RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, id);
		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
		AbstractBlock.Settings settings = AbstractBlock.Settings.copy(settingsSource).registryKey(blockKey);
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
