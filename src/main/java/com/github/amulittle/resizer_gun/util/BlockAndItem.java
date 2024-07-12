package com.github.amulittle.resizer_gun.util;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BlockAndItem {
    public final Block block;
    @Nullable public final Item item;

    public BlockAndItem(Block block, @Nullable Item item) {
        this.block = block;
        this.item = item;
    }
}
