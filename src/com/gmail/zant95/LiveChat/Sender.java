package com.gmail.zant95.LiveChat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Sender {
	public static void main(Player sender, Player target, String msg, String type) {
		if (MemStorage.mute.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c" + MemStorage.locale.get("YOU_ARE_MUTED"));
		} else if (type.equalsIgnoreCase("global") && LiveChat.perms.has(sender, "livechat.global.send")) {
			globalchat(sender, msg);
		} else if (type.equalsIgnoreCase("private") && LiveChat.perms.has(sender, "livechat.private.send")) {
			privatechat(sender, target, msg);
		} else if (type.equalsIgnoreCase("map") && LiveChat.perms.has(sender, "livechat.map.send")) {
			mapchat(sender, msg);
		} else if (type.equalsIgnoreCase("local") && LiveChat.perms.has(sender, "livechat.local.send")) {
			localchat(sender, msg);
		} else if (type.equalsIgnoreCase("emote") && LiveChat.perms.has(sender, "livechat.emote.send")) {
			emotechat(sender, msg);
		} else if (type.equalsIgnoreCase("admin") && LiveChat.perms.has(sender, "livechat.admin.send")) {
			adminchat(sender, msg);
		} else {
			sender.sendMessage("\u00A7c" + MemStorage.locale.get("SPEAK_PERMISSION"));
		}
	}
	
	private static void globalchat(Player sender, String msg) {
		Boolean heard = false;
		String channel = "global";
		String fmsg = Format.main(sender, msg, channel);
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		for (Player target:players) {
			if (MemStorage.globalignore.containsKey(target.getName())) continue;
			if (LiveChat.perms.has(target, "livechat.global.receive")) continue;
			if (Utils.isIgnored(sender, target) && !LiveChat.perms.has(sender, "livechat.ignore.bypass")) continue;
			if (target.equals(sender)) continue;
			target.sendMessage(fmsg);
			heard = true;
		}
		confirmAndLog(heard, sender, fmsg, channel);
	}

	private static void privatechat(Player sender, Player target, String msg) {
		String channel = "private";
		if (target == null) {
			sender.sendMessage("\u00A7c" + MemStorage.locale.get("DISCONECTED_USER"));
		} else if (MemStorage.privateignore.containsKey(target.getName()) || (Utils.isIgnored(sender, target) && !LiveChat.perms.has(sender, "livechat.ignore.bypass") && LiveChat.perms.has(target, "livechat.private.receive"))) {
			sender.sendMessage("\u00A7c" + MemStorage.locale.get("YOU_ARE_IGNORED"));
		} else {
			String fmsgSender = Format.privateSender(sender, target, msg, "private");
			String fmsgTarget = Format.privateTarget(sender, target, msg, "private");
			MemStorage.reply.put(target.getName(), sender.getName());
			target.sendMessage(fmsgTarget);
			socialSpy(sender, target, msg);
			confirmAndLog(true, sender, fmsgSender, channel);
		}
	}

	private static void mapchat(Player sender, String msg) {
		Boolean heard = false;
		String channel = "map";
		String fmsg = Format.main(sender, msg, channel);
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		for (Player target:players) {
			if (MemStorage.mapignore.containsKey(target.getName())) continue;
			if (LiveChat.perms.has(target, "livechat.map.receive")) continue;
			if (Utils.isIgnored(sender, target) && !LiveChat.perms.has(sender, "livechat.ignore.bypass")) continue;
			if (!target.getWorld().equals(sender.getWorld())) continue;
			if (target.equals(sender)) continue;
			target.sendMessage(fmsg);
			heard = true;
		}
		confirmAndLog(heard, sender, fmsg, channel);
	}

	private static void localchat(Player sender, String msg) {
		Boolean heard = false;
		String channel = "local";
		String fmsg = Format.main(sender, msg, channel);
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		for (Player target:players) {
			if (MemStorage.localignore.containsKey(target.getName())) continue;
			if (LiveChat.perms.has(target, "livechat.local.receive")) continue;
			if (Utils.isIgnored(sender, target) && !LiveChat.perms.has(sender, "livechat.ignore.bypass")) continue;
			if (!target.getWorld().equals(sender.getWorld())) continue;
			if (target.equals(sender)) continue;
			if (target.getLocation().distance(sender.getLocation()) > MemStorage.plugin.getConfig().getInt("chat.local.radius")) continue;
			target.sendMessage(fmsg);
			heard = true;
		}
		confirmAndLog(heard, sender, fmsg, channel);
	}

	private static void adminchat(Player sender, String msg) {
		Boolean heard = false;
		String channel = "admin";
		String fmsg = Format.main(sender, msg, channel);
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		for (Player target:players) {
			if (!LiveChat.perms.has(target, "livechat.admin")) continue;
			if (target.equals(sender)) continue;
			target.sendMessage(fmsg);
			heard = true;
		}
		confirmAndLog(heard, sender, fmsg, channel);
	}

	private static void emotechat(Player sender, String msg) {
		Boolean heard = false;
		String channel = "emote";
		String fmsg = Format.main(sender, msg, channel);
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		for (Player target:players) {
			if (MemStorage.emoteignore.containsKey(target.getName())) continue;
			if (LiveChat.perms.has(target, "livechat.emote.receive")) continue;
			if (Utils.isIgnored(sender, target) && !LiveChat.perms.has(sender, "livechat.ignore.bypass")) continue;
			if (target.equals(sender)) continue;
			target.sendMessage(fmsg);
			heard = true;
		}
		confirmAndLog(heard, sender, fmsg, channel);
	}
	
	private static void socialSpy(Player sender, Player target, String msg) {
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		for (Player playerlist:players) {
			if (!LiveChat.perms.has(playerlist, "livechat.socialspy")) continue;
			if (MemStorage.socialspyoff.containsKey(playerlist)) continue;
			if (playerlist.equals(sender) || playerlist.equals(target)) continue;
			playerlist.sendMessage(Format.withTarget(sender, target, msg, "socialspy"));
		}
	}

	private static void confirmAndLog(Boolean heard, Player sender, String fmsg, String channel) {
		if (heard || LiveChat.perms.has(sender, "livechat.hear.bypass")) {
			sender.sendMessage(fmsg);
			if (channel.equalsIgnoreCase("private")) {
				Log.main(fmsg.replaceFirst(MemStorage.locale.get("YOU"), sender.getName()), channel);
			} else {
				Log.main(fmsg, channel);
			}
		} else {
			sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOBODY_HEAR"));
		}
	}
}