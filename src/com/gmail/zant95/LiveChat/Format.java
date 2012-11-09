package com.gmail.zant95.LiveChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Format {
	public static String main(Player player, String msg, String type) {
		java.util.Date date = new java.util.Date(); 
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(date);
		String format = null;

		if (type == "public") {
			format = MemStorage.conf.getString("chat.public.format");
		} else if (type == "private") {
			format = MemStorage.conf.getString("chat.private.format");
		} else if (type == "socialspy") {
			format = MemStorage.conf.getString("chat.socialspy.format");
		} else if (type == "local") {
			format = MemStorage.conf.getString("chat.local.format");
		} else if (type == "emote") {
			format = MemStorage.conf.getString("chat.emote.format");
		} else if (type == "admin") {
			format = MemStorage.conf.getString("chat.admin.format");
		} else {
			format = "";
		}

		format = format
				.replace("%DISPLAYNAME%", player.getDisplayName())
				.replace("%NAME%", player.getName())
				.replace("%GROUPNAME%", LiveChat.chat.getPrimaryGroup(player))
				.replace("%PREFIX%", LiveChat.chat.getPlayerPrefix(player))
				.replace("%SUFFIX%", LiveChat.chat.getPlayerSuffix(player))
				.replace("%WORLDNAME%", ((LiveChat)MemStorage.plugin).getMV().getAlias(player.getWorld()))
				.replace("%GAMEMODE%", player.getGameMode().name())
				.replace("%HEALTH%", Integer.toString(player.getHealth()))
				.replace("%FOOD%", Integer.toString(player.getFoodLevel()))
				.replace("%LEVEL%", Integer.toString(player.getLevel()))
				.replace("%TOTALXP%", Integer.toString(player.getTotalExperience()))
				.replace("%TIME%", time);
		
		format = FormatTool.all(format);
		format = format.replace("%MSG%", msg);

		if (type == "public") {
			if (LiveChat.perms.has(player, "livechat.chat.color") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = FormatTool.color(format);
			}
			if (LiveChat.perms.has(player, "livechat.chat.format") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = FormatTool.format(format);
			}
			if (LiveChat.perms.has(player, "livechat.chat.magic") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = FormatTool.magic(format);
			}
		} else if (type == "private") {
			if (LiveChat.perms.has(player, "livechat.msg.color") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = FormatTool.color(format);
			}
			if (LiveChat.perms.has(player, "livechat.msg.format") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = FormatTool.format(format);
			}
			if (LiveChat.perms.has(player, "livechat.msg.magic") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = FormatTool.magic(format);
			}
		} else if (type == "local") {
			if (LiveChat.perms.has(player, "livechat.local.color") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = FormatTool.color(format);
			}
			if (LiveChat.perms.has(player, "livechat.local.format") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = FormatTool.format(format);
			}
			if (LiveChat.perms.has(player, "livechat.local.magic") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = FormatTool.magic(format);
			}
		} else if (type == "emote") {
			if (LiveChat.perms.has(player, "livechat.me.color") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = FormatTool.color(format);
			}
			if (LiveChat.perms.has(player, "livechat.me.format") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = FormatTool.format(format);
			}
			if (LiveChat.perms.has(player, "livechat.me.magic") || LiveChat.perms.has(player, "livechat.admin") || player.isOp()) {
				format = FormatTool.magic(format);
			}
		} else if (type == "admin") {
			format = FormatTool.all(format);
		} else if (type == "socialspy" && MemStorage.conf.getBoolean("chat.socialspy.color")) {
			format = FormatTool.all(format);
		}

		return format;
	}
	
	public static String withTarget(Player sender, Player target, String format, String type) {
		if (type == "private") {
			format = main(sender, format, "private");
		} else if (type == "socialspy") {
			format = main(sender, format, "socialspy");
		}

		format = format
				.replace("%TARGETDISPLAYNAME%", target.getDisplayName() + ChatColor.RESET)
				.replace("%TARGETNAME%", target.getName());
		return format;
	}

	public static String privateTarget(Player sender, Player target, String msg, String type) {
		msg = 	Format.withTarget(sender, target, msg, "private")
				.replace(target.getDisplayName(), FormatTool.all(LiveChat.chat.getPlayerPrefix(target)) + MemStorage.locale.get("YOU"))
				.replace(target.getName(), MemStorage.locale.get("YOU"));
		return msg;
	}

	public static String privateSender(Player sender, Player target, String msg, String type) {
		msg = 	Format.withTarget(sender, target, msg, "private")
				.replace(sender.getDisplayName(), FormatTool.all(LiveChat.chat.getPlayerPrefix(sender)) + MemStorage.locale.get("YOU"))
				.replace(sender.getName(), MemStorage.locale.get("YOU"));
		return msg;
	}
}
