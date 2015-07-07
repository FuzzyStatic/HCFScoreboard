package com.fuzzycraft.fuzzy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.fuzzycraft.fuzzy.constants.Defaults;

public class PlayerBoard {

	private Player player;
	
	/**
	 * Set player for board.
	 * @param player
	 */
	public PlayerBoard(Player player) {
		this.player = player;
	}
	
	/**
	 * Create Scoreboard for player.
	 */
	public void createPlayerBoard() {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = board.registerNewObjective("timers", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(Defaults.TITLE);
		objective.getScore(Defaults.SPAWN_TAG).setScore(0);
		objective.getScore(Defaults.PEARL_COOLDOWN).setScore(0);
		this.player.setScoreboard(board);
	}
}
