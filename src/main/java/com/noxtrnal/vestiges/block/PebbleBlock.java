package com.noxtrnal.vestiges.block;

import com.noxtrnal.vestiges.registry.VestigesBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

public class PebbleBlock extends Block {
    private static final VoxelShape SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 2.0, 13.0);
    public static final EnumProperty<PebbleVariant> VARIANT = EnumProperty.of("variant", PebbleVariant.class);

    public PebbleBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(VARIANT, PebbleVariant.SMALL));
    }

    // UPDATED: world is now WorldView in 1.21.x
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isIn(VestigesBlockTags.PEBBLE_BASE);
    }

    // UPDATED: Now requires 8 arguments including WorldView, ScheduledTickView, and Random
    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state,
            WorldView world,
            ScheduledTickView tickView,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            Random random
    ) {
        if (direction == Direction.DOWN && !this.canPlaceAt(state, world, pos)) {
            return net.minecraft.block.Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(VARIANT);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState().with(VARIANT, PebbleVariant.fromRandom(context.getWorld().getRandom()));
    }

    public enum PebbleVariant implements StringIdentifiable {
        SMALL("small"),
        FLAT("flat"),
        LARGE("large");

        private final String name;

        PebbleVariant(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return name;
        }

        public static PebbleVariant fromRandom(Random random) {
            PebbleVariant[] values = values();
            return values[random.nextInt(values.length)];
        }
    }
}
