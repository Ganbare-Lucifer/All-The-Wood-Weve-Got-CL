package net.potionstudios.woodwevegot.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.potionstudios.woodwevegot.world.level.block.entities.WWGBlockEntities;
import org.jetbrains.annotations.NotNull;

public class WWGTrappedChestBlock extends WWGChestBlock {

    protected WWGTrappedChestBlock(String set) {
        super(() -> WWGBlockEntities.TRAPPED_CHEST.get(), set);
    }

    @Override
    protected @NotNull Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
    }

    @Override
    public boolean isSignalSource(@NotNull BlockState state) {
        return true;
    }

    @Override
    public int getSignal(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
        return Mth.clamp(ChestBlockEntity.getOpenCount(level, pos), 0, 15);
    }

    @Override
    public int getDirectSignal(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
        return direction == Direction.UP ? state.getSignal(level, pos, direction) : 0;
    }
}