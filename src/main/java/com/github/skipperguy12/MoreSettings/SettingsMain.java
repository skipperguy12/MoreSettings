package com.github.skipperguy12.MoreSettings;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.skipperguy12.MoreSettings.listeners.BloodListener;
import com.github.skipperguy12.MoreSettings.listeners.DeathListener;
import com.github.skipperguy12.MoreSettings.settings.Settings;

public class SettingsMain extends JavaPlugin {

	public void onEnable() {
		Settings.register();
		registerEvents();
	}

	public void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new DeathListener(this), this);
		Bukkit.getPluginManager().registerEvents(new BloodListener(this), this);
		Settings.register();
	}

	public void onDisable() {

	}

}
