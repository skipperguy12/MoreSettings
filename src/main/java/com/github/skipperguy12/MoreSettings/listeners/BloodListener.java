package com.github.skipperguy12.MoreSettings.listeners;

import me.anxuiz.settings.bukkit.PlayerSettings;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.github.skipperguy12.MoreSettings.CustomPlayer;
import com.github.skipperguy12.MoreSettings.SettingsMain;
import com.github.skipperguy12.MoreSettings.events.CustomPlayerDeathEvent;
import com.github.skipperguy12.MoreSettings.events.CustomPlayerEntityDamageByEntityEvent;
import com.github.skipperguy12.MoreSettings.settings.BloodOptions;
import com.github.skipperguy12.MoreSettings.settings.DeathMessageOptions;
import com.github.skipperguy12.MoreSettings.settings.Settings;

public class BloodListener implements Listener {
	SettingsMain plugin;

	public BloodListener(SettingsMain instance) {
		this.plugin = instance;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void handleBlood(CustomPlayerEntityDamageByEntityEvent event) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			boolean playerIsVictim = event.getVictim().getName().equals(player.getName());
			boolean playerIsKiller = (event.getKiller() != null) && (event.getKiller().getName().equals(player.getName()));
			boolean showBlood = PlayerSettings.getManager(player).getValue(Settings.BLOOD, BloodOptions.class) == BloodOptions.ON;
			if ((showBlood)) {
				event.playBloodEffect(player, event.getVictim().getBukkit().getLocation());
			}
		}

	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (!(e instanceof EntityDamageByEntityEvent)) {
			for (Player player : Bukkit.getOnlinePlayers()) {

				boolean showBlood = PlayerSettings.getManager(player).getValue(Settings.BLOOD, BloodOptions.class) == BloodOptions.ON;
				if ((showBlood)) {
					player.playEffect(e.getEntity().getLocation(), Effect.STEP_SOUND, 10);
					
				}
			}
		}
	}

	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		if (((EntityDamageByEntityEvent) event).getDamager() instanceof Player && event.getEntity() instanceof Player) {
			CustomPlayerEntityDamageByEntityEvent deathEvent = new CustomPlayerEntityDamageByEntityEvent(event, new CustomPlayer(plugin, (Player) event.getEntity()), new CustomPlayer(plugin, (Player) ((EntityDamageByEntityEvent) event).getDamager()));
			Bukkit.getPluginManager().callEvent(deathEvent);
		}
	}
}
