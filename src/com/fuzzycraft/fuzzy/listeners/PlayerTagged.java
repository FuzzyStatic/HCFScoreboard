package com.fuzzycraft.fuzzy.listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Objective;

import com.fuzzycraft.fuzzy.HCFScoreboard;
import com.fuzzycraft.fuzzy.constants.Defaults;
import com.fuzzycraft.fuzzy.constants.DefaultsConverted;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class PlayerTagged implements Listener {
		
	public HCFScoreboard plugin;
	private HashMap<Player, BukkitTask> map = new HashMap<Player, BukkitTask>();

	/**
	 * Constructs listener for PlayerTagged.
	 * @param plugin
	 */
	public PlayerTagged(HCFScoreboard plugin) {
		this.plugin = plugin;
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
        		cancel(this.map.get(player));
        	}
        	
        	if(this.map.containsKey(damager) && this.map.get(damager) != null) {
        		cancel(this.map.get(damager));
        	}
        	
        	cooldown(player, Defaults.SPAWN_TAG_TIMER);
        	cooldown(damager, Defaults.SPAWN_TAG_TIMER);
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
		
    	cooldown(player, 0);
	}
	
	/**
	 * Set cooldown timer.
	 * @param player
	 * @param cooldownTime
	 */
	public void cooldown(final Player player, int cooldownTime) {
		if (player.isOnline()) {
			// Get Scoreboard for player
			Objective objective = player.getScoreboard().getObjective("timers");
			
			if (cooldownTime < 0) {
				// Set time to 0
				objective.getScore(DefaultsConverted.getSpawnTag()).setScore(0);
				return;
			}
			
			// Set time.
			objective.getScore(DefaultsConverted.getSpawnTag()).setScore(cooldownTime);
		}
				
		// Decrement timer.
		final int newTime = --cooldownTime;
		
		// Create the task anonymously to decrement timer.
		map.put(player, new BukkitRunnable() {
		      
				public void run() {
					cooldown(player, newTime);
				}
				
			}.runTaskLater(this.plugin, 20)
		);
	}
	
	/** Cancel given task. Used to make sure there are not multiple timers running for one person.
	 * @param task
	 */
	public void cancel(BukkitTask task) {
		task.cancel();
	}
}
