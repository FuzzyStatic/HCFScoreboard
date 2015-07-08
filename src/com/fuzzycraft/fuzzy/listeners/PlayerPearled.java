package com.fuzzycraft.fuzzy.listeners;

import java.util.HashMap;

import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
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

public class PlayerPearled implements Listener {

	public HCFScoreboard plugin;
	private HashMap<Player, BukkitTask> map = new HashMap<Player, BukkitTask>();

	/**
	 * Constructs listener for PlayerTagged.
	 * @param plugin
	 */
	public PlayerPearled(HCFScoreboard plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (!(event.getEntity() instanceof EnderPearl) || !(event.getEntity().getShooter() instanceof Player) || event.isCancelled()) {
            return;
        }
        
        Player player = (Player) event.getEntity().getShooter();
    	cooldown(player, Defaults.PEARL_COOLDOWN_TIMER);
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
				objective.getScore(DefaultsConverted.getPearlCooldown()).setScore(0);
				return;
			}
			
			// Set time.
			objective.getScore(DefaultsConverted.getPearlCooldown()).setScore(cooldownTime);
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
}
