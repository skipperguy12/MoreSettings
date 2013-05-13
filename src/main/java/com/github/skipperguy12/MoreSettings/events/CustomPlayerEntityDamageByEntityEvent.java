package com.github.skipperguy12.MoreSettings.events;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.github.skipperguy12.MoreSettings.CustomPlayer;

public class CustomPlayerEntityDamageByEntityEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	protected final EntityDamageByEntityEvent parent;
	protected final CustomPlayer victim;
	protected final CustomPlayer killer;

	public CustomPlayerEntityDamageByEntityEvent(EntityDamageByEntityEvent parent, CustomPlayer victim, CustomPlayer killer) {

		this.parent = parent;
		this.victim = victim;
		this.killer = killer;
	}

	public EntityDamageEvent getParent() {
		return this.parent;
	}

	public EntityDamageEvent.DamageCause getCause() {
		return this.victim.getBukkit().getLastDamageCause().getCause();
	}

	public void playBloodEffect(Player player, Location location) {

		if ((((EntityDamageByEntityEvent) getParent()).getDamager().getType() == EntityType.ARROW)) {
			player.playEffect(getParent().getEntity().getLocation(), Effect.STEP_SOUND, 10);
	
		}

		if ((((EntityDamageByEntityEvent) getParent()).getDamager() instanceof Player)) {
			Player p = (Player) ((EntityDamageByEntityEvent) getParent()).getDamager();
			Entity victim = getParent().getEntity();

			p.playSound(p.getLocation(), Sound.HURT_FLESH, 200.0F, 200.0F);
			player.playEffect(getParent().getEntity().getLocation(), Effect.STEP_SOUND, 10);
			

		}
	}

	private int getBloodType(EntityType type) {
		switch (type) {
		case FIREBALL:
		case GHAST:
		case PIG_ZOMBIE:
		case PLAYER:
		case PRIMED_TNT:
		case SHEEP:
		case SILVERFISH:
		case SKELETON:
		case SQUID:
		case WITHER_SKULL:
			return 10;
		case LIGHTNING:
			return 49;
		case FALLING_BLOCK:
			return 133;
		case MUSHROOM_COW:
			return 41;
		case IRON_GOLEM:
			return 42;
		case SNOWBALL:
			return 99;
		case FIREWORK:
		case FISHING_HOOK:
		case GIANT:
		case ITEM_FRAME:
		case MAGMA_CUBE:
		case MINECART:
		case OCELOT:
		case PAINTING:
		case PIG:
		case SLIME:
		case SMALL_FIREBALL:
		case SNOWMAN:
		case SPIDER:
		case SPLASH_POTION:
		case THROWN_EXP_BOTTLE:
		case UNKNOWN:
		case VILLAGER:
		case WEATHER:
		case WITCH:
		case WITHER:
		}
		return 55;
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
