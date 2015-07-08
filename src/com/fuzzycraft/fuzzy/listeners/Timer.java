package com.fuzzycraft.fuzzy.listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Objective;

import com.fuzzycraft.fuzzy.HCFScoreboard;

public class Timer {
	
	public HCFScoreboard plugin;
	private HashMap<Player, BukkitTask> map = new HashMap<Player, BukkitTask>();

	/**
	 * Constructs listener for PlayerTagged.
	 * @param plugin
	 */
	public Timer(HCFScoreboard plugin) {
		this.plugin = plugin;
	}

	/**
	 * Set cooldown timer.
	 * @param player
	 * @param cooldownTime
	 */
	public void cooldown(final Player player, int cooldownTime, final String score) {
		if (player.isOnline()) {
			// Get Scoreboard for player
			Objective objective = player.getScoreboard().getObjective("timers");
			
			if (cooldownTime < 0) {
				// Set time to 0
				objective.getScore(score).setScore(0);
				return;
			}
			
			// Set time.
			objective.getScore(score).setScore(cooldownTime);
		}
				
		// Decrement timer.
		final int newTime = --cooldownTime;
		
		// Create the task anonymously to decrement timer.
		map.put(player, new BukkitRunnable() {
		      
				public void run() {
					cooldown(player, newTime, score);
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
