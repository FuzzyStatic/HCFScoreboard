package com.fuzzycraft.fuzzy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.fuzzycraft.fuzzy.constants.Defaults;

public class PlayerBoard {

	private Player player;
	private ScoreboardManager manager;
	private Scoreboard board;
	private Team team;
	
	/**
	 * Get hcfs instance.
	 * @param catchee
	 */
	public PlayerBoard(Player player) {
		this.player = player;
	}
	
	public void createPlayerBoard() {
		this.manager.getMainScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(Defaults.TITLE);
		this.manager = Bukkit.getScoreboardManager();
		this.board = manager.getNewScoreboard();
		this.team = board.registerNewTeam(player.getUniqueId().toString());
		this.team.addPlayer(player);
	}
}
