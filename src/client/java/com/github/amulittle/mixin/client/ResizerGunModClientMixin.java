package com.github.amulittle.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.amulittle.ItemRegister;
import com.github.amulittle.event.RequestResizeEffectChange;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

@Mixin(Mouse.class)
public class ResizerGunModClientMixin {
	@Inject(at = @At(value = "TAIL"), method = "onMouseScroll(JDD)V", cancellable = true)
	private void init(long window, double horizontal, double vertical, CallbackInfo callbackInfo) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player != null) {
			if (client.player.isInSneakingPose()) {
				client.player.getInventory().scrollInHotbar(-vertical);
				ItemStack handStack = client.player.getMainHandStack();
				if (handStack.itemMatches(Registries.ITEM.getEntry(ItemRegister.RESIZER_GUN))) {
					if (vertical > 0) {
						ClientPlayNetworking.send(new RequestResizeEffectChange(2.0f, RequestResizeEffectChange.Operation.MULTIPLY));
					}
					else if (vertical < 0) {
						ClientPlayNetworking.send(new RequestResizeEffectChange(0.5f, RequestResizeEffectChange.Operation.MULTIPLY));
					}
				}
				else {
					client.player.getInventory().scrollInHotbar(vertical);
				}
			}
		}
	}
}