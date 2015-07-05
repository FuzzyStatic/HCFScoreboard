package com.fuzzycraft.fuzzy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.fuzzycraft.fuzzy.HCFScoreboard;
import com.fuzzycraft.fuzzy.constants.Defaults;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class PlayerBoard {

	private HCFScoreboard hcfs;
	private ScoreboardManager manager;
	private Scoreboard board;
	private Team team;
	
	/**
	 * Get hcfs instance.
	 * @param catchee
	 */
	public PlayerBoard(HCFScoreboard hcfs) {
		this.hcfs = hcfs;
	}
	
	/**
	 * Create board for joining player.
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		this.manager.getMainScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Defaults.TITLE);
		this.manager = Bukkit.getScoreboardManager();
		this.board = manager.getNewScoreboard();
		this.team = board.registerNewTeam(player.getUniqueId().toString());
		this.team.addPlayer(player);
		
    }
	
}
