package com.gmail.zant95.LiveChat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;

public class PlayerDisplayName {
	public static void main(Player player) {
		String playerGroup = LiveChat.chat.getPrimaryGroup(player);
		String playerPrefix = LiveChat.chat.getPlayerPrefix(player);
		String groupPrefix = LiveChat.chat.getGroupPrefix(player.getWorld(), playerGroup);
		String playerSuffix = LiveChat.chat.getPlayerSuffix(player);
		String groupSuffix = LiveChat.chat.getGroupSuffix(player.getWorld(), playerGroup);

		String playerName;
		String finalPrefix;
		String finalSuffix;
		String finalName;

		if (playerPrefix == groupPrefix) {
			finalPrefix = FormatTool.all(playerPrefix);
		} else {
			finalPrefix = FormatTool.all(groupPrefix);
		}

		if (playerSuffix == groupSuffix) {
			finalSuffix = FormatTool.all(playerSuffix);
		} else {
			finalSuffix = FormatTool.all(groupSuffix);
		}

		Essentials ess = (Essentials)Bukkit.getServer().getPluginManager().getPlugin("Essentials");
		if (ess != null) {
			User essPlayer = ess.getUser(player.getName());
			try {
				playerName = essPlayer.getNickname();
				playerName.length(); //If player doesn't have a nickname it will throw a NullPointerException.
			} catch(Exception e) {
				playerName = player.getName();
			}
		} else {
			playerName = player.getName();
		}

		finalName = finalPrefix + playerName + finalSuffix;
		player.setDisplayName(finalName);

		//Userlist format
		finalName = Format.userlist(player);
		
		try {
			if (finalName.length() > 16) {
				player.setPlayerListName(finalName.substring(0, finalName.charAt(15) == '\u00a7' ? 15 : 16));
			} else {
				player.setPlayerListName(finalName);
			}
		} catch (Exception e) {
			player.setPlayerListName(player.getName());
		}
	}
}
