package com.github.amulittle.resizer_gun.register;

import com.github.amulittle.resizer_gun.ResizerGunMod;
import com.github.amulittle.resizer_gun.entity.ResizerProjectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityRegister {
    public static final EntityType<ResizerProjectile> RESIZER_PROJECTILE = Registry.register(
        Registries.ENTITY_TYPE,
        Identifier.of(ResizerGunMod.MOD_ID, "resizer_projectile"),
        EntityType.Builder.create(ResizerProjectile::new, SpawnGroup.MISC).dimensions(0.25f, 0.25f).build()
    );

    public static void initialize() {

    }
}
