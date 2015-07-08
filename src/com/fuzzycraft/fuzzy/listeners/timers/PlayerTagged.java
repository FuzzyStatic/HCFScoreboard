package com.fuzzycraft.fuzzy.listeners.timers;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import com.fuzzycraft.fuzzy.HCFScoreboard;
import com.fuzzycraft.fuzzy.constants.Defaults;
import com.fuzzycraft.fuzzy.constants.DefaultsConverted;
import com.fuzzycraft.fuzzy.listeners.Timer;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class PlayerTagged extends Timer implements Listener {
			
	/**
	 * Constructor
	 * @param plugin
	 */
	public PlayerTagged(HCFScoreboard plugin) {
		super(plugin);
	}
	
	/**
	 * Check for player damage.
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player) || event.isCancelled()) {
            return;
        }
        
        Player player = (Player) event.getEntity();
        Player damager = null;
        
        if (event.getDamager() instanceof Player) {
            damager = (Player) event.getDamager();
        } else if (event.getDamager() instanceof Projectile) {
            Projectile projectile = (Projectile) event.getDamager();
            
            if (projectile.getShooter() instanceof Player) {
                damager = (Player) projectile.getShooter();
            }
        }
        
        if (damager != null && damager != event.getEntity()) {
        	if(this.map.containsKey(player) && this.map.get(player) != null) {
        		cancel(super.map.get(player));
        	}
        	
        	if(this.map.containsKey(damager) && this.map.get(damager) != null) {
        		cancel(super.map.get(damager));
        	}
        	
        	super.cooldown(player, Defaults.SPAWN_TAG_TIMER, DefaultsConverted.getSpawnTag());
        	super.cooldown(damager, Defaults.SPAWN_TAG_TIMER, DefaultsConverted.getSpawnTag());
        }
    }
	
	/**
	 * Check for player death.
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onEntityDeath(EntityDeathEvent event) {
		if (!(event.getEntity() instanceof Player)) {
            return;
        }
		
		Player player = (Player) event.getEntity();
		
		if(this.map.containsKey(player) && this.map.get(player) != null) {
    		cancel(this.map.get(player));
    	}
		
    	super.cooldown(player, 0, DefaultsConverted.getSpawnTag());
	}
}
