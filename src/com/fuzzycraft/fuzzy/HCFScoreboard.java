package com.fuzzycraft.fuzzy;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.fuzzycraft.fuzzy.constants.Defaults;
import com.fuzzycraft.fuzzy.constants.Paths;
import com.fuzzycraft.fuzzy.listeners.PlayerJoin;
import com.fuzzycraft.fuzzy.listeners.timers.PlayerPearled;
import com.fuzzycraft.fuzzy.listeners.timers.PlayerTagged;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class HCFScoreboard extends JavaPlugin {
	public void onEnable() {		
		configDefaults();		
		registerListeners();	
	}
	
	public void registerListeners() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(this), this);
		pm.registerEvents(new PlayerPearled(this), this);
		pm.registerEvents(new PlayerTagged(this), this);
	}
	
	public void configDefaults() {
		getDataFolder().mkdir();
		getConfig().addDefault(Paths.TITLE, Defaults.TITLE);
		getConfig().addDefault(Paths.SPAWN_TAG_TITLE, Defaults.SPAWN_TAG_TITLE);
		getConfig().addDefault(Paths.SPAWN_TAG_MAX_TIME, Defaults.SPAWN_TAG_MAX_TIME);
		getConfig().addDefault(Paths.PEARL_COOLDOWN_TITLE, Defaults.PEARL_COOLDOWN_TITLE);
		getConfig().addDefault(Paths.PEARL_COOLDOWN_MAX_TIME, Defaults.PEARL_COOLDOWN_MAX_TIME);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
