package com.github.amulittle.resizer_gun.register;

import org.jetbrains.annotations.Nullable;

import com.github.amulittle.resizer_gun.ResizerGunMod;
import com.github.amulittle.resizer_gun.block.resizer_block.ResizerBlock;
import com.github.amulittle.resizer_gun.util.BlockAndItem;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class BlockRegister {
    private static BlockAndItem register(Block block, String name, @Nullable Item.Settings itemSettings) {
        Identifier id = Identifier.of(ResizerGunMod.MOD_ID, name);

        Item item = null;
        if (itemSettings != null) {
            BlockItem blockItem = new BlockItem(block, itemSettings);
            item = Registry.register(Registries.ITEM, id, blockItem);
        }

        return new BlockAndItem(Registry.register(Registries.BLOCK, id, block), item);
    }

    public static final BlockAndItem RESIZER_BLOCK = register(new ResizerBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)), "resizer_block", new Item.Settings());

    public static void initialize() {

    }
}
