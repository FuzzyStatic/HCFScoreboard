package com.fuzzycraft.fuzzy.utilities;

/**
 * 
 * @author FuzzyStatic (fuzzy@fuzzycraft.com)
 *
 */

public class TextReplace {

	public static String colors(String string) {
		return string.replaceAll("&(?<!&&)(?=[0-9a-fA-F])", "\u00A7").replace("&&","&");
	}
}
