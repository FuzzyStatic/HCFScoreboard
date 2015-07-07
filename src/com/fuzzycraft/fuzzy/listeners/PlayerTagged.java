package com.fuzzycraft.fuzzy.listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Objective;

import com.fuzzycraft.fuzzy.HCFScoreboard;
import com.fuzzycraft.fuzzy.constants.Defaults;

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
	 * Create board for joining player.
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
	
	public void cooldown(final Player player, int cooldownTime) {
		// Get Scoreboard for player
		Objective objective = player.getScoreboard().getObjective("timers");
		
		if (cooldownTime < 0) {
			// Set time to 0
			objective.getScore(Defaults.SPAWN_TAG).setScore(0);
			return;
		}
		
		// Set time.
		objective.getScore(Defaults.SPAWN_TAG).setScore(cooldownTime);
				
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
	
	public void cancel(BukkitTask task) {
		task.cancel();
	}
}
