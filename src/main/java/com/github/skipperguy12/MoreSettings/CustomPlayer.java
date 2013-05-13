package com.github.skipperguy12.MoreSettings;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;

public class CustomPlayer {
	SettingsMain plugin;
	private Player player;

	public CustomPlayer(SettingsMain plugin, Player player) {
		this.plugin = plugin;
		this.player = player;
	}

	public Player getBukkit() {
		return this.player;
	}

	public String getName() {
		return player.getName();
	}

	public float getFallDistance() {
		return player.getFallDistance();
	}

	public EntityDamageEvent getLastDamageCause() {
		return player.getLastDamageCause();
	}

}
