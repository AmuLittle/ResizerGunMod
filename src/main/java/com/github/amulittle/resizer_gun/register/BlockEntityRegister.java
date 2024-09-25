package com.github.amulittle.resizer_gun.register;

import com.github.amulittle.resizer_gun.ResizerGunMod;
import com.github.amulittle.resizer_gun.block.resizer_block.ResizerBlockEntity;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockEntityRegister {
    public static <T extends BlockEntity> BlockEntityType<T> register(BlockEntityType.BlockEntityFactory<T> entity, String name, Block attachTo) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(ResizerGunMod.MOD_ID), BlockEntityType.Builder.create(entity, attachTo).build());
    }

    public static final BlockEntityType<ResizerBlockEntity> RESIZER_BLOCK_ENTITY = register(ResizerBlockEntity::new, "resizer_block_entity", BlockRegister.RESIZER_BLOCK.block);
    
    public static void initialize() {

    }
}
