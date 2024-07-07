package com.github.amulittle.event;

import com.github.amulittle.ResizerGunMod;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class RequestResizeEffectChange implements CustomPayload {
    public static final Identifier PAYLOAD_ID = Identifier.of(ResizerGunMod.MOD_ID, "request_resize_effect_change");

    private final float value;
    private final Operation operation;

    public RequestResizeEffectChange(Float value, RequestResizeEffectChange.Operation operation) {
        this.value = value;
        this.operation = operation;
    }

    public RequestResizeEffectChange(PacketByteBuf buffer) {
        this.value = buffer.readFloat();
        this.operation = Operation.fromInt(buffer.readByte());
    }

    public float getValue() {
        return value;
    }

    public Operation getOperation() {
        return operation;
    }

    public void writeToPacket(PacketByteBuf buffer) {
        buffer.writeFloat(value);
        buffer.writeByte(this.operation.toInt());
    }

    public CustomPayload.Id<RequestResizeEffectChange> getId() {
        return new CustomPayload.Id<RequestResizeEffectChange>(PAYLOAD_ID);
    }

    public enum Operation {
        ADD(0),
        MULTIPLY(1),
        SET(2);

        public final int number;

        private Operation(int number) {
            this.number = number;
        }

        public int toInt() {
            return number;
        }

        public static Operation fromInt(int number) {
            if (number == 0) {
                return ADD;
            }
            else if (number == 1) {
                return MULTIPLY;
            }
            return SET;
        }
    }
}
