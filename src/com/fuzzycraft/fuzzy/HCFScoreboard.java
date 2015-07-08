package com.fuzzycraft.fuzzy;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerPearled(this), this);
		pm.registerEvents(new PlayerTagged(this), this);
	}
	
	public void configDefaults() {
		getDataFolder().mkdir();
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
