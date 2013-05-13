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

	public void reset() {
		Player bukkit = getBukkit();
		bukkit.closeInventory();
		bukkit.getInventory().clear();
		bukkit.getInventory().setArmorContents(null);
		bukkit.setExhaustion(0.0F);
		bukkit.setFallDistance(0.0F);
		bukkit.setFireTicks(0);
		bukkit.setFoodLevel(20);
		bukkit.setHealth(bukkit.getMaxHealth());
		bukkit.setLevel(0);
		bukkit.setExp(0.0F);
		bukkit.setSaturation(5.0F);
		bukkit.setSneaking(false);
		bukkit.setSprinting(false);
		bukkit.getVehicle().eject();
		for (PotionEffect effect : bukkit.getActivePotionEffects()) {
			if (effect.getType() != null) {
				bukkit.removePotionEffect(effect.getType());
			}
		}
		bukkit.setBedSpawnLocation(null);
	}

	public Player getBukkit() {
		return this.player;
	}

	public void ejectFromVehicle() {
		this.player.getVehicle().eject();
	}

	public String getName() {
		return player.getName();
	}

	public EntityDamageEvent getLastDamageCause() {
		return player.getLastDamageCause();
	}

	public float getFallDistance() {
		return player.getFallDistance();
	}

}
