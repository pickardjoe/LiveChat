package com.gmail.zant95;

import org.bukkit.entity.Player;

public class Format {
	public static String main(Player player, String msg, String type) {
		java.util.Date date = new java.util.Date(); 
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(date);
		String format = null;

		if (type == "chat") {
			format = MemStorage.plugin.getConfig().getString("chat-format");
		}

		if (type == "emote") {
			format = MemStorage.plugin.getConfig().getString("emote-format");
		}

		if (type == "local") {
			format = MemStorage.plugin.getConfig().getString("local-format");
		}

		if (type == "channelAdmin") {
			format = MemStorage.plugin.getConfig().getString("channel-admin-format");
		}

		format = format
				.replaceAll("(?i)%DISPLAYNAME%", LiveChat.chat.getPlayerPrefix(player) + player.getDisplayName() + "\u00A7r")
				.replaceAll("(?i)%NAME%", player.getName())
				.replaceAll("(?i)%GROUPNAME%", LiveChat.chat.getPrimaryGroup(player))
				.replaceAll("(?i)%PREFIX%", LiveChat.chat.getPlayerPrefix(player))
				.replaceAll("(?i)%SUFFIX%", LiveChat.chat.getPlayerSuffix(player))
				.replaceAll("(?i)%WORLDNAME%", player.getWorld().getName())
				.replaceAll("(?i)%GAMEMODE%", player.getGameMode().name())
				.replaceAll("(?i)%HEALTH%", Integer.toString(player.getHealth()))
				.replaceAll("(?i)%FOOD%", Integer.toString(player.getFoodLevel()))
				.replaceAll("(?i)%LEVEL%", Integer.toString(player.getLevel()))
				.replaceAll("(?i)%TOTALXP%", Integer.toString(player.getTotalExperience()))
				.replaceAll("(?i)%TIME%", time)
				.replaceAll("(?i)&([a-fk-or0-9])", "\u00A7$1")
				.replaceAll("(?i)%MSG%", msg);

		if (type == "chat") {
			if (LiveChat.perms.has(player, "livechat.chat.color")) {
				format = format.replaceAll("(?i)&([a-fA-F0-9])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.chat.format")) {
				format = format.replaceAll("(?i)&([l-oL-OrR])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.chat.magic")) {
				format = format.replaceAll("(?i)&([kK])", "\u00A7$1");
			}
		}

		if (type == "emote") {
			if (LiveChat.perms.has(player, "livechat.me.color")) {
				format = format.replaceAll("(?i)&([a-fA-F0-9])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.me.format")) {
				format = format.replaceAll("(?i)&([l-oL-OrR])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.me.magic")) {
				format = format.replaceAll("(?i)&([kK])", "\u00A7$1");
			}
		}

		if (type == "local") {
			if (LiveChat.perms.has(player, "livechat.local.color")) {
				format = format.replaceAll("(?i)&([a-fA-F0-9])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.local.format")) {
				format = format.replaceAll("(?i)&([l-oL-OrR])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.local.magic")) {
				format = format.replaceAll("(?i)&([kK])", "\u00A7$1");
			}
		}

		if (type == "channelAdmin") {
			format = format.replaceAll("(?i)&([a-fk-or0-9])", "\u00A7$1");
		}

		return format;
	}
}
