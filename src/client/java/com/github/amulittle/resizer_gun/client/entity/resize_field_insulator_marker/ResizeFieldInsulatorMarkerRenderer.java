package com.github.amulittle.resizer_gun.client.entity.resize_field_insulator_marker;

import org.jetbrains.annotations.Nullable;

import com.github.amulittle.resizer_gun.ResizerGunMod;
import com.github.amulittle.resizer_gun.client.ResizerGunModClient;
import com.github.amulittle.resizer_gun.entity.ResizeFieldInsulatorMarker;
import com.github.amulittle.resizer_gun.register.ItemRegister;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ResizeFieldInsulatorMarkerRenderer extends EntityRenderer<ResizeFieldInsulatorMarker> implements FeatureRendererContext<ResizeFieldInsulatorMarker, ResizeFieldInsulatorMarkerModel> {
    ResizeFieldInsulatorMarkerModel model;
    
    public ResizeFieldInsulatorMarkerRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new ResizeFieldInsulatorMarkerModel(context.getPart(ResizerGunModClient.MODEL_RESIZE_FIELD_INSULATOR_MARKER_LAYER));
    }

    @Override
    public void render(ResizeFieldInsulatorMarker entity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) {
            if (mc.player.getMainHandStack().itemMatches(Registries.ITEM.getEntry(ItemRegister.RESIZE_INSULATOR_DETECTOR))) {
                matrixStack.push();

                matrixStack.scale(1, 1, 1);
                matrixStack.scale(-1.0F, -1.0F, 1.0F);
                matrixStack.translate(0.0F, -1.501F, 0.0F);
      
                MinecraftClient minecraftClient = MinecraftClient.getInstance();
                boolean bl = true;
                boolean bl2 = true;
                boolean bl3 = minecraftClient.hasOutline(entity);
                RenderLayer renderLayer = this.getRenderLayer(entity, bl, bl2, bl3);
                if (renderLayer != null) {
                    VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(renderLayer);
                    int overlay = OverlayTexture.packUv(OverlayTexture.getU(tickDelta), OverlayTexture.getV(false));
                    this.model.render(matrixStack, vertexConsumer, light, overlay, bl2 ? 654311423 : -1); // WHY CAN'T I SEE YOU ??!?!?!?
                }

                matrixStack.pop();
            }
        }
    }

    protected boolean isVisible(ResizeFieldInsulatorMarker entity) {
        return !entity.isInvisible();
    }

    @Nullable
    protected RenderLayer getRenderLayer(ResizeFieldInsulatorMarker entity, boolean showBody, boolean translucent, boolean showOutline) {
        Identifier identifier = this.getTexture(entity);
        if (translucent) {
            return RenderLayer.getItemEntityTranslucentCull(identifier);
        } else if (showBody) {
            return this.model.getLayer(identifier);
        } else {
            return showOutline ? RenderLayer.getOutline(identifier) : null;
        }
    }

    @Override
    public Identifier getTexture(ResizeFieldInsulatorMarker entity) {
        return Identifier.of(ResizerGunMod.MOD_ID, "textures/entity/resize_field_insulator_marker/resize_field_insulator_marker.png");
    }

    @Override
    public ResizeFieldInsulatorMarkerModel getModel() {
        return model;
    }
}
