package com.fuzzycraft.fuzzy.utilities;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class ColorRegex {

	// Converts & to \u00A7 for proper Minecraft colors.
	public static String colors(String string) {
		return string.replaceAll("&(?<!&&)(?=[0-9a-fA-F])", "\u00A7").replace("&&","&");
	}
}
