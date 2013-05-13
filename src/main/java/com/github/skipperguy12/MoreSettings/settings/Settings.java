package com.github.skipperguy12.MoreSettings.settings;

import me.anxuiz.settings.Setting;
import me.anxuiz.settings.SettingBuilder;
import me.anxuiz.settings.SettingCallbackManager;
import me.anxuiz.settings.SettingRegistry;
import me.anxuiz.settings.bukkit.PlayerSettings;
import me.anxuiz.settings.types.BooleanType;
import me.anxuiz.settings.types.EnumType;

public class Settings {
	public static final Setting DEATH_MESSAGES = new SettingBuilder().name("DeathMessages").alias("dms").alias("dm").summary("Death messages displayed to you").description("Available options:\nALL: show all death messages\nNONE: show no death messages").type(new EnumType("Death Message Options", DeathMessageOptions.class)).defaultValue(DeathMessageOptions.ALL).get();
	public static final Setting BLOOD = new SettingBuilder().name("Blood").alias("b").summary("Blood particles when you get hit").description("Available options:\nON: Show blood\nOFF: No blood").type(new EnumType("Blood Options", BloodOptions.class)).defaultValue(BloodOptions.OFF).get();

	public static void register() {
		SettingRegistry registry = PlayerSettings.getRegistry();
		SettingCallbackManager callbacks = PlayerSettings.getCallbackManager();
		registry.register(DEATH_MESSAGES);
		registry.register(BLOOD);
	}
}
