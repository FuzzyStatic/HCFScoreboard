package com.fuzzycraft.fuzzy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.fuzzycraft.fuzzy.constants.Defaults;

public class PlayerBoard {

	private Player player;
	private ScoreboardManager manager;
	private Scoreboard board;
	private Objective objective;
	private Team team;
	private Score scoreST;
	private Score scorePC;
	
	/**
	 * Set player for board
	 * @param player
	 */
	public PlayerBoard(Player player) {
		this.player = player;
	}
	
	/**
	 * Create Scoreboard for player
	 */
	public void createPlayerBoard() {
		this.manager = Bukkit.getScoreboardManager();
		this.board = manager.getNewScoreboard();
		this.team = board.registerNewTeam(player.getName());
		this.team.addPlayer(player);
		this.team.setDisplayName("display name");
		this.objective = this.board.registerNewObjective("Player", "Timers");
		this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		this.objective.setDisplayName(Defaults.TITLE);
		this.player.setScoreboard(this.board);
		this.scoreST = objective.getScore(Defaults.SPAWN_TAG);
		this.scoreST.setScore(60);
		this.scorePC = objective.getScore(Defaults.PEARL_COOLDOWN);
		this.scorePC.setScore(12);
	}
}
