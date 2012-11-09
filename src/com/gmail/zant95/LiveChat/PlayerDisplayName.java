package com.gmail.zant95.LiveChat;

import org.bukkit.entity.Player;

public class PlayerDisplayName {
	public static void main(Player player) {
		String playerGroup = LiveChat.chat.getPrimaryGroup(player);
		String playerPrefix = LiveChat.chat.getPlayerPrefix(player);
		String groupPrefix = LiveChat.chat.getGroupPrefix(player.getWorld(), playerGroup);
		String playerSuffix = LiveChat.chat.getPlayerSuffix(player);
		String groupSuffix = LiveChat.chat.getGroupSuffix(player.getWorld(), playerGroup);

		String opPrefix = FormatTool.all(MemStorage.plugin.getConfig().getString("op.prefix"));
		String opSuffix = FormatTool.all(MemStorage.plugin.getConfig().getString("op.suffix"));

		String finalPrefix;
		String finalSuffix;
		String finalName;
		String finalNameTab;
		int remainChars = 0;

		if (playerPrefix == groupPrefix) {
			if (player.isOp() && opPrefix.length() != 0) {
				finalPrefix = opPrefix;
			} else {
				finalPrefix = FormatTool.all(playerPrefix);
			}
		} else {
			finalPrefix = FormatTool.all(groupPrefix);
		}

		if (playerSuffix == groupSuffix) {
			if (player.isOp() && opSuffix.length() != 0) {
				finalSuffix = opSuffix;
			} else {
				finalSuffix = FormatTool.all(playerSuffix);
			}
		} else {
			finalSuffix = FormatTool.all(groupSuffix);
		}

		finalName = finalPrefix + player.getName() + finalSuffix;
		player.setDisplayName(finalName);

		if (!MemStorage.plugin.getConfig().getBoolean("userlist.display-prefix")) {
			finalPrefix = "";
		}

		if (!MemStorage.plugin.getConfig().getBoolean("userlist.display-suffix")) {
			finalSuffix = "";
		}

		finalName = finalPrefix + player.getName() + finalSuffix;

		if (finalName.length() > 16) {
			remainChars = finalName.length() - 16;
		}

		if (remainChars < player.getName().length()) {
			finalNameTab = finalPrefix + player.getName().substring(0, player.getName().length() - remainChars) + finalSuffix;
		} else {
			finalNameTab = finalName.substring(0, finalName.charAt(15) == '\u00a7' ? 15 : 16);
		}

		player.setPlayerListName(finalNameTab);
	}
}
