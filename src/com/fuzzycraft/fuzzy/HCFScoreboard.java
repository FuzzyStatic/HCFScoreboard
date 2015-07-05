package com.fuzzycraft.fuzzy;

import org.bukkit.plugin.java.JavaPlugin;

public class HCFScoreboard extends JavaPlugin {
	public void onEnable() {		
		configDefaults();		
		registerListeners();	
	}
	
	public void registerListeners() {

	}
	
	public void configDefaults() {
		getDataFolder().mkdir();
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
