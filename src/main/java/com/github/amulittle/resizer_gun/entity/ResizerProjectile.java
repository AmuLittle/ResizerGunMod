package com.github.amulittle.resizer_gun.entity;

import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

import com.github.amulittle.resizer_gun.register.ItemRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ResizerProjectile extends PersistentProjectileEntity {
    public static final TrackedData<Float> TRACKED_RESIZE_EFFECT = DataTracker.registerData(ResizerProjectile.class, TrackedDataHandlerRegistry.FLOAT);
    public static final String RESIZE_EFFECT_KEY = "ResizeEffect";

    public ResizerProjectile(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }
    
    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ItemRegister.RESIZER_PROJECTILE_ITEM);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(TRACKED_RESIZE_EFFECT, 1f);
        super.initDataTracker(builder);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putFloat(RESIZE_EFFECT_KEY, this.getResizeEffect());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains(RESIZE_EFFECT_KEY)) {
            setResizeEffect(nbt.getFloat(RESIZE_EFFECT_KEY));
        }
    }
    
    @Override
    protected void onBlockHit(BlockHitResult hitResult) {
        this.kill();
    }

    @Override
    protected void onEntityHit(EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        ScaleData scale = ScaleTypes.BASE.getScaleData(entity);
        scale.setTargetScale(scale.getScale() * getResizeEffect());
        this.kill();
    }

    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return false; // you cannot pick it up lol
    }

    public float getResizeEffect() {
        return this.dataTracker.get(TRACKED_RESIZE_EFFECT);
    }

    public void setResizeEffect(Float f) {
        this.dataTracker.set(TRACKED_RESIZE_EFFECT, f);
    }
}
