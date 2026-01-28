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
import net.minecraft.util.Identifier;

public final class VestigesBlocks {
	public static final Block SHORT_GRASS = registerBlock(
			"short_grass",
			new ShortPlantBlock(AbstractBlock.Settings.copy(Blocks.SHORT_GRASS))
	);
	public static final Block PATCHY_GRASS = registerBlock(
			"patchy_grass",
			new ShortPlantBlock(AbstractBlock.Settings.copy(Blocks.SHORT_GRASS))
	);
	public static final Block MEDIUM_GRASS = registerBlock(
			"medium_grass",
			new ShortPlantBlock(AbstractBlock.Settings.copy(Blocks.SHORT_GRASS))
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

	private static Block registerBlock(String name, Block block) {
		Identifier id = Identifier.of(Vestiges.MOD_ID, name);
		Registry.register(Registries.BLOCK, id, block);
		Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
		return block;
	}
}
