package com.github.skipperguy12.MoreSettings.listeners;

import me.anxuiz.settings.bukkit.PlayerSettings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.github.skipperguy12.MoreSettings.CustomPlayer;
import com.github.skipperguy12.MoreSettings.SettingsMain;
import com.github.skipperguy12.MoreSettings.events.CustomPlayerDeathEvent;
import com.github.skipperguy12.MoreSettings.settings.DeathMessageOptions;
import com.github.skipperguy12.MoreSettings.settings.Settings;

public class DeathListener implements Listener {
	SettingsMain plugin;

	public DeathListener(SettingsMain instance) {
		this.plugin = instance;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void handleDeathBroadcast(CustomPlayerDeathEvent event) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			boolean playerIsVictim = event.getVictim().getName().equals(player.getName());
			boolean playerIsKiller = (event.getKiller() != null) && (event.getKiller().getName().equals(player.getName()));
			boolean showDeathMessages = PlayerSettings.getManager(player).getValue(Settings.DEATH_MESSAGES, DeathMessageOptions.class) == DeathMessageOptions.ALL;

			if ((playerIsVictim) || (playerIsKiller) || (showDeathMessages)) {
				String message = event.getVictim().getLastDamageCause().getCause() == DamageCause.FALL ? event.getDeathMessage() + " (" + Math.round(event.getVictim().getFallDistance()) + " blocks)" : event.getDeathMessage();
				player.sendMessage(message);
			}
		}
		event.setDeathMessage("");
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		CustomPlayerDeathEvent deathEvent = new CustomPlayerDeathEvent((PlayerDeathEvent) event, new CustomPlayer(plugin, (Player) event.getEntity()), new CustomPlayer(plugin, (Player) event.getEntity().getKiller()));
		Bukkit.getPluginManager().callEvent(deathEvent);
	}
}
