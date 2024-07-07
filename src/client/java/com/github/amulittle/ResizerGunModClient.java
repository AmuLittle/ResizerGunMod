package com.github.amulittle;

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