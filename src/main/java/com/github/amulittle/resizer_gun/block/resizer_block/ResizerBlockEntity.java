package com.github.amulittle.resizer_gun.block.resizer_block;

import org.jetbrains.annotations.Nullable;

import com.github.amulittle.resizer_gun.register.BlockEntityRegister;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ResizerBlockEntity extends BlockEntity {
    public ResizerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegister.RESIZER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapper) {
 
        super.writeNbt(nbt, wrapper);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapper) {
        super.readNbt(nbt, wrapper);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup regLookup) {
        return createNbt(regLookup);
    }

    // check for redstone signal, set size effect to signal strength. if there is no redstone signal, do nothing
    // Redstone Signal Strength:             1,   2,    3,    4,    5,     6,     7,   8,   9,    10,    11,   12,   13,   14,   15
    // Resize Rate (size effect / tick):   0.95, 0.96, 0.97, 0.98, 0.99, 0.995, 0.999, 1, 1.001, 1.005, 1.01, 1.02, 1.03, 1.04, 1.05
    public static void tick(World world, BlockPos pos, BlockState state, ResizerBlockEntity be) {
        int power = world.getReceivedRedstonePower(pos);
        if (power > 0) {
            // resize logic
        }
    }
}
