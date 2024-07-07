package com.github.amulittle;

import com.github.amulittle.event.RequestResizeEffectChange;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

public class Packets {
    public static void initialize() {
        PayloadTypeRegistry<RegistryByteBuf> playC2S = PayloadTypeRegistry.playC2S();
        playC2S.register(new CustomPayload.Id<RequestResizeEffectChange>(RequestResizeEffectChange.PAYLOAD_ID), PacketCodec.of((request, buffer) -> {
            request.writeToPacket(buffer);
        }, 
        (buffer) -> {
            return new RequestResizeEffectChange(buffer);
        }));
        ServerPlayNetworking.registerGlobalReceiver(new CustomPayload.Id<RequestResizeEffectChange>(RequestResizeEffectChange.PAYLOAD_ID), (request, context) -> {
            if (context.player() != null) {
                ItemStack handStack = context.player().getStackInHand(Hand.MAIN_HAND);
                if (handStack.itemMatches(Registries.ITEM.getEntry(ItemRegister.RESIZER_GUN))) {
                    NbtCompound nbt = handStack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT).copyNbt();
                    if (!nbt.contains(ResizerGun.RESIZE_EFFECT_KEY)) {
                        nbt.putFloat(ResizerGun.RESIZE_EFFECT_KEY, 1.0f);
                    }
                    float effect = nbt.getFloat(ResizerGun.RESIZE_EFFECT_KEY);
                    float value = request.getValue();
                    Float product = 1f;
                    switch (request.getOperation()) {
                        case ADD:
                            product = effect + value;
                            break;
                        case MULTIPLY:
                            product = effect * value;
                            break;
                        case SET:
                            product = value;
                            break;
                    }
                    if (product < 0.0625f) {
                        ResizerGun item = (ResizerGun)handStack.getItem();
                        item.setMessage(1);
                        return;
                    }
                    if (product > 16f) {
                        ResizerGun item = (ResizerGun)handStack.getItem();
                        item.setMessage(2);
                        return;
                    }
                    nbt.putFloat(ResizerGun.RESIZE_EFFECT_KEY, product);
                    handStack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
                    if (!context.player().isInSneakingPose()) {
                        context.player().sendMessage(Text.of("Set effect to " + product.toString()), true);
                    }
                }
            }
        });
    }
}
