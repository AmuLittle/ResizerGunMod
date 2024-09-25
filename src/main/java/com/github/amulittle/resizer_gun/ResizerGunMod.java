package com.github.amulittle.resizer_gun;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.amulittle.resizer_gun.packet.Packets;
import com.github.amulittle.resizer_gun.register.BlockEntityRegister;
import com.github.amulittle.resizer_gun.register.BlockRegister;
import com.github.amulittle.resizer_gun.register.EntityRegister;
import com.github.amulittle.resizer_gun.register.ItemRegister;

public class ResizerGunMod implements ModInitializer {
	public static final String MOD_ID = "resizer_gun";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Packets.initialize();
		ItemRegister.initialize();
		BlockRegister.initialize();
		BlockEntityRegister.initialize();
		EntityRegister.initialize();
	}
}