package com.github.amulittle.resizer_gun.item;

import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

import java.util.List;

import com.github.amulittle.resizer_gun.entity.ResizerProjectile;
import com.github.amulittle.resizer_gun.register.EntityRegister;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ResizerGun extends Item {
    public static final String RESIZE_EFFECT_KEY = "ResizeEffect";
    private int message = 0;
    private int ticksUntilMessageReset = -1;

    public ResizerGun(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (world.isClient) {
            return TypedActionResult.pass(itemStack);
        }

        NbtComponent nbtComp = itemStack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT);
        NbtCompound nbt = nbtComp.copyNbt();
        if (!nbt.contains(RESIZE_EFFECT_KEY)) {
            nbt.putFloat(RESIZE_EFFECT_KEY, 1);
        }
        float state = nbt.getFloat(RESIZE_EFFECT_KEY);

        if (user.isInSneakingPose()) {
            ScaleData scale = ScaleTypes.BASE.getScaleData(user);
            scale.setTargetScale(scale.getScale() * state);
        }
        else {
            ResizerProjectile proj = new ResizerProjectile(EntityRegister.RESIZER_PROJECTILE, world);
            proj.setResizeEffect(state);
            proj.setOwner(user);
            proj.setPos(user.getX(), user.getEyeY(), user.getZ());
            proj.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.0F, 0.0F);
            world.spawnEntity(proj);
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity.isPlayer() && selected && entity.isInSneakingPose()) {
            if (message == 0) {
                NbtCompound nbt = stack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT).copyNbt();
                if (!nbt.contains(RESIZE_EFFECT_KEY)) {
                    nbt.putFloat(RESIZE_EFFECT_KEY, 1);
                }
                Float state = nbt.getFloat(RESIZE_EFFECT_KEY);
    
                ((PlayerEntity)entity).sendMessage(Text.of("Resize Effect is set to " + state.toString()), true);
            }
            else if (message == 1) {
                ((PlayerEntity)entity).sendMessage(Text.of("Cannot set resize effect smaller than 0.0625"), true);
            }
            else if (message == 2) {
                ((PlayerEntity)entity).sendMessage(Text.of("Cannot set resize effect bigger than 16"), true);
            }
            if (!(ticksUntilMessageReset < 0)) {
                ticksUntilMessageReset--;
            }
            if (ticksUntilMessageReset == 0) {
                message = 0;
            }
        }
    }

    public void setMessage(int message) {
        this.message = message;
        this.ticksUntilMessageReset = 100;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.resizer_gun.resizer_gun.tooltip0").formatted(Formatting.LIGHT_PURPLE));
        tooltip.add(Text.translatable("item.resizer_gun.resizer_gun.tooltip1").formatted(Formatting.LIGHT_PURPLE));
        tooltip.add(Text.translatable("item.resizer_gun.resizer_gun.tooltip2").formatted(Formatting.LIGHT_PURPLE));
        tooltip.add(Text.translatable("item.resizer_gun.resizer_gun.tooltip3").formatted(Formatting.LIGHT_PURPLE));
        tooltip.add(Text.translatable("item.resizer_gun.resizer_gun.tooltip4").formatted(Formatting.LIGHT_PURPLE));
    }
}
