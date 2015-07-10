package com.fuzzycraft.fuzzy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.fuzzycraft.fuzzy.constants.Paths;
import com.fuzzycraft.fuzzy.utilities.ColorRegex;

public class PlayerBoard {

	public HCFScoreboard plugin;
	private Player player;
	
	/**
	 * Constructor
	 * @param plugin
	 * @param player
	 */
	public PlayerBoard(HCFScoreboard plugin, Player player) {
		this.plugin = plugin;
		this.player = player;
	}
	
	/**
	 * Create Scoreboard for player.
	 */
	public void createPlayerBoard() {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = board.registerNewObjective("timers", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ColorRegex.colors(this.plugin.getConfig().getString(Paths.TITLE)));
		objective.getScore(ColorRegex.colors(this.plugin.getConfig().getString(Paths.SPAWN_TAG_TITLE))).setScore(0);
		objective.getScore(ColorRegex.colors(this.plugin.getConfig().getString(Paths.PEARL_COOLDOWN_TITLE))).setScore(0);
		this.player.setScoreboard(board);
	}
}
