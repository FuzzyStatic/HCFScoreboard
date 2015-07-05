package com.fuzzycraft.fuzzy.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.fuzzycraft.fuzzy.HCFScoreboard;

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
		this.manager = Bukkit.getScoreboardManager();
		this.board = manager.getNewScoreboard();
		this.team = board.registerNewTeam(event.getPlayer().getUniqueId().toString());
    }
	
}
