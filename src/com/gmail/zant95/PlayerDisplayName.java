package com.gmail.zant95;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerDisplayName {
	public static void main(Player player) {
		String playerGroup = LiveChat.chat.getPrimaryGroup(player);
		String playerPrefix = LiveChat.chat.getPlayerPrefix(player);
		String groupPrefix = LiveChat.chat.getGroupPrefix(player.getWorld(), playerGroup);
		String normalName = ColorTool.main(groupPrefix) + player.getName();
		String customName = ColorTool.main(playerPrefix) + player.getName();
		ChatColor opPrefix = ColorTool.main(MemStorage.plugin.getConfig().getString("op-prefix"));
		String opName = opPrefix + player.getName();
		
		if (playerPrefix == groupPrefix) {
			if (player.isOp() && opPrefix != null) {
				if (opName.length() > 16) {
					String opNameTab = opName.substring(0, opName.charAt(15) == '\u00a7' ? 15 : 16);
					player.setPlayerListName(opNameTab);
					player.setDisplayName(opName + ChatColor.RESET);
					return;
				} else {
					player.setPlayerListName(opName);
					player.setDisplayName(opName + ChatColor.RESET);
					return;
				}
			} else if (customName.length() > 16) {
				String customNameTab = customName.substring(0, customName.charAt(15) == '\u00a7' ? 15 : 16);
				player.setPlayerListName(customNameTab);
				player.setDisplayName(customName + ChatColor.RESET);
				return;
			} else {
				player.setPlayerListName(customName);
				player.setDisplayName(customName + ChatColor.RESET);
			}
		} else if (normalName.length() > 16) {
			String normalNameTab = normalName.substring(0, normalName.charAt(15) == '\u00a7' ? 15 : 16);
			player.setPlayerListName(normalNameTab);
			player.setDisplayName(normalName + ChatColor.RESET);
			return;
		} else {
			player.setPlayerListName(normalName);
			player.setDisplayName(normalName + ChatColor.RESET);
		}
	}
}
