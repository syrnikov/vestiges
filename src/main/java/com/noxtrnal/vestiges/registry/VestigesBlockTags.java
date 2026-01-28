package com.noxtrnal.vestiges.registry;

import com.noxtrnal.vestiges.Vestiges;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.block.Block;

public final class VestigesBlockTags {
	public static final TagKey<Block> PEBBLE_BASE = TagKey.of(
			RegistryKeys.BLOCK,
			Identifier.of(Vestiges.MOD_ID, "pebble_base")
	);

	private VestigesBlockTags() {
	}
}
