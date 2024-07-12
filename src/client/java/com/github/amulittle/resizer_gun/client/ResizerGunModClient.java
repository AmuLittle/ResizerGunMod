package com.github.amulittle.resizer_gun.client;

import com.github.amulittle.resizer_gun.client.entity.resizer_projectile.ResizerProjectileRenderer;
import com.github.amulittle.resizer_gun.register.EntityRegister;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ResizerGunModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(EntityRegister.RESIZER_PROJECTILE, (context) -> {
			return new ResizerProjectileRenderer(context);
		});
	}
}