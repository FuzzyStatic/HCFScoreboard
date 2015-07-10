package com.fuzzycraft.fuzzy.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.fuzzycraft.fuzzy.HCFScoreboard;
import com.fuzzycraft.fuzzy.PlayerBoard;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class PlayerJoin implements Listener {
		
	public HCFScoreboard plugin;
	
	/**
	 * Constructor
	 * @param plugin
	 */
	public PlayerJoin(HCFScoreboard plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * Create board for joining player.
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
		PlayerBoard pb = new PlayerBoard(this.plugin, event.getPlayer());
		pb.createPlayerBoard();
    }
}
