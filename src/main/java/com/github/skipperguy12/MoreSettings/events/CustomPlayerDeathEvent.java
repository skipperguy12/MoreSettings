package com.github.skipperguy12.MoreSettings.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.github.skipperguy12.MoreSettings.CustomPlayer;

public class CustomPlayerDeathEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	protected final PlayerDeathEvent parent;
	protected final CustomPlayer victim;
	protected final CustomPlayer killer;

	public CustomPlayerDeathEvent(PlayerDeathEvent parent, CustomPlayer victim, CustomPlayer killer) {

		this.parent = parent;
		this.victim = victim;
		this.killer = killer;
	}

	public PlayerDeathEvent getParent() {
		return this.parent;
	}

	public EntityDamageEvent.DamageCause getCause() {
		return this.victim.getBukkit().getLastDamageCause().getCause();
	}

	public String getDeathMessage() {
		return this.parent.getDeathMessage();
	}

	public void setDeathMessage(String message) {
		this.parent.setDeathMessage(message);
	}

	public CustomPlayer getVictim() {
		return this.victim;
	}

	public CustomPlayer getKiller() {
		return this.killer;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
