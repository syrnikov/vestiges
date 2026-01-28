package com.noxtrnal.vestiges.block;

import com.noxtrnal.vestiges.registry.VestigesBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class PebbleBlock extends Block {
	private static final VoxelShape SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 2.0, 13.0);

	public PebbleBlock(Settings settings) {
		super(settings);
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldAccess world, BlockPos pos) {
		return world.getBlockState(pos.down()).isIn(VestigesBlockTags.PEBBLE_BASE);
	}

	@Override
	public BlockState getStateForNeighborUpdate(
			BlockState state,
			Direction direction,
			BlockState neighborState,
			WorldAccess world,
			BlockPos pos,
			BlockPos neighborPos
	) {
		if (direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
			return net.minecraft.block.Blocks.AIR.getDefaultState();
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}
}
