package com.github.amulittle;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.util.Identifier;

public class ResizerProjectileRenderer extends ProjectileEntityRenderer<ResizerProjectile> {
        public ResizerProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
    }
 
    @Override
    public Identifier getTexture(ResizerProjectile entity) {
        DataTracker dataTracker = entity.getDataTracker();
        float effect = dataTracker.get(ResizerProjectile.TRACKED_RESIZE_EFFECT);
        if (effect < 1) {
            return Identifier.of(ResizerGunMod.MOD_ID, "textures/entity/resizer_projectile/resizer_projectile_shrink.png");
        }
        else if (effect > 1) {
            return Identifier.of(ResizerGunMod.MOD_ID, "textures/entity/resizer_projectile/resizer_projectile_grow.png");
        }
        else {
            return Identifier.of(ResizerGunMod.MOD_ID, "textures/entity/resizer_projectile/resizer_projectile_neutral.png");
        }
    }
}
