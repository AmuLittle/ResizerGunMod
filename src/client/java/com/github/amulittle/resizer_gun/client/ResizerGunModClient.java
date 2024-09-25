package com.github.amulittle.resizer_gun.client;

import com.github.amulittle.resizer_gun.ResizerGunMod;
import com.github.amulittle.resizer_gun.client.entity.resize_field_insulator_marker.ResizeFieldInsulatorMarkerModel;
import com.github.amulittle.resizer_gun.client.entity.resize_field_insulator_marker.ResizeFieldInsulatorMarkerRenderer;
import com.github.amulittle.resizer_gun.client.entity.resizer_projectile.ResizerProjectileRenderer;
import com.github.amulittle.resizer_gun.register.EntityRegister;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ResizerGunModClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_RESIZE_FIELD_INSULATOR_MARKER_LAYER = new EntityModelLayer(Identifier.of(ResizerGunMod.MOD_ID, "resize_field_insulator_marker"), "bb_main");

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(EntityRegister.RESIZER_PROJECTILE, (context) -> {
			return new ResizerProjectileRenderer(context);
		});
		EntityRendererRegistry.register(EntityRegister.RESIZE_FIELD_INSULATOR_MARKER, (context) -> {
			return new ResizeFieldInsulatorMarkerRenderer(context);
		});

		EntityModelLayerRegistry.registerModelLayer(MODEL_RESIZE_FIELD_INSULATOR_MARKER_LAYER, ResizeFieldInsulatorMarkerModel::getTexturedModelData);
	}
}