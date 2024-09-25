package com.github.amulittle.resizer_gun.block.resizer_block;

import org.jetbrains.annotations.Nullable;

import com.github.amulittle.resizer_gun.register.BlockEntityRegister;
import com.mojang.serialization.MapCodec;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ResizerBlock extends BlockWithEntity {
    
    public ResizerBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ResizerBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (type != BlockEntityRegister.RESIZER_BLOCK_ENTITY) return null;
        return (world1, pos, state1, be) -> ResizerBlockEntity.tick(world1, pos, state1, (ResizerBlockEntity)be);
    }
}
