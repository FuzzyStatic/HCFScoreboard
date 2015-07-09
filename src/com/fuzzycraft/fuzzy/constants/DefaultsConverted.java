package com.fuzzycraft.fuzzy.constants;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class DefaultsConverted {

	// Converts & to \u00A7 for proper Minecraft colors.
	public static String colors(String string) {
		return string.replaceAll("&(?<!&&)(?=[0-9a-fA-F])", "\u00A7").replace("&&","&");
	}
	
	public static final String getTitle() {
		return colors(Defaults.TITLE);
	}
	
	public static final String getSpawnTag() {
		return colors(Defaults.SPAWN_TAG);
	}
	
	public static final String getPearlCooldown() {
		return colors(Defaults.PEARL_COOLDOWN);
	}
}
