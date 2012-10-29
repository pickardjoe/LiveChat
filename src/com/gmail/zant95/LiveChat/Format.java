package com.gmail.zant95.LiveChat;

import org.bukkit.entity.Player;

public class Format {
	public static String main(Player player, String msg, String type) {
		java.util.Date date = new java.util.Date(); 
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(date);
		String format = null;

		if (type == "public") {
			format = MemStorage.conf.getString("public-chat-format");
		} else if (type == "private") {
			format = MemStorage.conf.getString("private-chat-format");
		} else if (type == "socialSpy") {
			format = MemStorage.conf.getString("socialspy-format");
		} else if (type == "local") {
			format = MemStorage.conf.getString("local-format");
		} else if (type == "emote") {
			format = MemStorage.conf.getString("emote-format");
		} else if (type == "channelAdmin") {
			format = MemStorage.conf.getString("channel-admin-format");
		}

		format = format
				.replaceAll("(?i)%DISPLAYNAME%", player.getDisplayName())
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
				.replaceAll("&([a-fk-or0-9])", "\u00A7$1")
				.replaceAll("(?i)%MSG%", msg);

		if (type == "public") {
			if (LiveChat.perms.has(player, "livechat.chat.color") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = format.replaceAll("&([a-fA-F0-9])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.chat.format") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = format.replaceAll("&([l-oL-OrR])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.chat.magic") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = format.replaceAll("&([kK])", "\u00A7$1");
			}
		} else if (type == "private") {
			if (LiveChat.perms.has(player, "livechat.msg.color") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = format.replaceAll("&([a-fA-F0-9])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.msg.format") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = format.replaceAll("&([l-oL-OrR])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.msg.magic") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = format.replaceAll("&([kK])", "\u00A7$1");
			}
		} else if (type == "local") {
			if (LiveChat.perms.has(player, "livechat.local.color") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = format.replaceAll("&([a-fA-F0-9])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.local.format") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = format.replaceAll("&([l-oL-OrR])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.local.magic") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = format.replaceAll("&([kK])", "\u00A7$1");
			}
		} else if (type == "emote") {
			if (LiveChat.perms.has(player, "livechat.me.color") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = format.replaceAll("&([a-fA-F0-9])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.me.format") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = format.replaceAll("&([l-oL-OrR])", "\u00A7$1");
			}
			if (LiveChat.perms.has(player, "livechat.me.magic") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = format.replaceAll("&([kK])", "\u00A7$1");
			}
		} else if (type == "channelAdmin") {
			format = format.replaceAll("&([a-fk-or0-9])", "\u00A7$1");
		} else if (type == "socialSpy" && MemStorage.conf.getBoolean("socialspy-msg-color")) {
			format = format.replaceAll("&([a-fk-or0-9])", "\u00A7$1");
		}

		return format;
	}
	
	public static String withTarget(Player sender, Player target, String format, String type) {
		if (type == "private") {
			format = main(sender, format, "private");
		} else if (type == "socialSpy") {
			format = main(sender, format, "socialSpy");
		}

		format = format
				.replaceAll("(?i)%TARGETDISPLAYNAME%", target.getDisplayName() + "\u00A7r")
				.replaceAll("(?i)%TARGETNAME%", target.getName());
		return format;
	}

	public static String privateTarget(Player sender, Player target, String msg, String type) {
		msg = 	Format.withTarget(sender, target, msg, "private")
				.replaceAll(target.getDisplayName(), ColorTool.main(LiveChat.chat.getPlayerPrefix(target)) + MemStorage.locale.get("YOU"))
				.replaceAll(target.getName(), MemStorage.locale.get("YOU"));
		return msg;
	}

	public static String privateSender(Player sender, Player target, String msg, String type) {
		msg = 	Format.withTarget(sender, target, msg, "private")
				.replaceAll(sender.getDisplayName(), ColorTool.main(LiveChat.chat.getPlayerPrefix(sender)) + MemStorage.locale.get("YOU"))
				.replaceAll(sender.getName(), MemStorage.locale.get("YOU"));
		return msg;
	}
}
