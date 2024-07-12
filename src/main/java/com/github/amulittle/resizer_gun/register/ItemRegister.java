package com.github.amulittle.resizer_gun.register;

import com.github.amulittle.resizer_gun.ResizerGunMod;
import com.github.amulittle.resizer_gun.item.ResizerGun;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemRegister {
    public static Item register(Item item, String id) {
        Identifier itemId = Identifier.of(ResizerGunMod.MOD_ID, id);

        Item registeredItem = Registry.register(Registries.ITEM, itemId, item);

        return registeredItem;
    }

    public static final Item RESIZER_PROJECTILE_ITEM = register(new Item(new Item.Settings()), "resizer_gun_projectile");

    public static final Item RESIZER_GUN = register(new ResizerGun(new Item.Settings().maxCount(1)), "resizer_gun");

    public static final ItemGroup RESIZER_GROUP = FabricItemGroup.builder()
        .icon(() -> new ItemStack(RESIZER_GUN))
        .displayName(Text.translatable("itemGroup.resizer_gun.resizer_group"))
        .entries((context, entries) -> {
            entries.add(RESIZER_GUN);
            entries.add(BlockRegister.RESIZER_BLOCK.item);
        })
        .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, Identifier.of(ResizerGunMod.MOD_ID, "resizer_group"), RESIZER_GROUP);
    }
}
