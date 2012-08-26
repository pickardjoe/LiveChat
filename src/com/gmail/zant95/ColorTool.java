package com.gmail.zant95;

import org.bukkit.ChatColor;

public class ColorTool {
	public static ChatColor main(String color) {
		if (color.equalsIgnoreCase("&0") || color.equalsIgnoreCase("\u00A70")) {
			return ChatColor.BLACK;
		}

		if (color.equalsIgnoreCase("&1") || color.equalsIgnoreCase("\u00A71")) {
			return ChatColor.DARK_BLUE;
		}

		if (color.equalsIgnoreCase("&2") || color.equalsIgnoreCase("\u00A72")) {
			return ChatColor.DARK_GREEN;
		}

		if (color.equalsIgnoreCase("&3") || color.equalsIgnoreCase("\u00A73")) {
			return ChatColor.DARK_AQUA;
		}

		if (color.equalsIgnoreCase("&4") || color.equalsIgnoreCase("\u00A74")) {
			return ChatColor.DARK_RED;
		}

		if (color.equalsIgnoreCase("&5") || color.equalsIgnoreCase("\u00A75")) {
			return ChatColor.DARK_PURPLE;
		}

		if (color.equalsIgnoreCase("&6") || color.equalsIgnoreCase("\u00A76")) {
			return ChatColor.GOLD;
		}

		if (color.equalsIgnoreCase("&7") || color.equalsIgnoreCase("\u00A77")) {
			return ChatColor.GRAY;
		}

		if (color.equalsIgnoreCase("&8") || color.equalsIgnoreCase("\u00A78")) {
			return ChatColor.DARK_GRAY;
		}

		if (color.equalsIgnoreCase("&9") || color.equalsIgnoreCase("\u00A79")) {
			return ChatColor.BLUE;
		}

		if (color.equalsIgnoreCase("&a") || color.equalsIgnoreCase("\u00A7a")) {
			return ChatColor.GREEN;
		}

		if (color.equalsIgnoreCase("&b") || color.equalsIgnoreCase("\u00A7b")) {
			return ChatColor.AQUA;
		}

		if (color.equalsIgnoreCase("&c") || color.equalsIgnoreCase("\u00A7c")) {
			return ChatColor.RED;
		}

		if (color.equalsIgnoreCase("&d") || color.equalsIgnoreCase("\u00A7d")) {
			return ChatColor.LIGHT_PURPLE;
		}

		if (color.equalsIgnoreCase("&e") || color.equalsIgnoreCase("\u00A7e")) {
			return ChatColor.YELLOW;
		}

		if (color.equalsIgnoreCase("&f") || color.equalsIgnoreCase("\u00A7f")) {
			return ChatColor.WHITE;
		}

		if (color.equalsIgnoreCase("&k") || color.equalsIgnoreCase("\u00A7k")) {
			return ChatColor.MAGIC;
		}

		if (color.equalsIgnoreCase("&l") || color.equalsIgnoreCase("\u00A7l")) {
			return ChatColor.BOLD;
		}

		if (color.equalsIgnoreCase("&m") || color.equalsIgnoreCase("\u00A7m")) {
			return ChatColor.STRIKETHROUGH;
		}

		if (color.equalsIgnoreCase("&n") || color.equalsIgnoreCase("\u00A7n")) {
			return ChatColor.UNDERLINE;
		}

		if (color.equalsIgnoreCase("&o") || color.equalsIgnoreCase("\u00A7o")) {
			return ChatColor.ITALIC;
		}

		if (color.equalsIgnoreCase("&r") || color.equalsIgnoreCase("\u00A7r")) {
			return ChatColor.RESET;
		}

		return null;
	}
}
