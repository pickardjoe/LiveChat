package com.gmail.zant95.LiveChat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Sender {
	public static void publicchat(Player sender, String msg) {
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		for (Player target:players) {
			if (!Utils.isIgnored(sender, target) || LiveChat.perms.has(sender, "livechat.ignore.bypass")) {
				target.sendMessage(Format.main(sender, msg, "public"));
			}
		}
		Log.main("[" + sender.getName() + "] " + msg, "public");
	}

	public static void privatechat(Player sender, Player target, String msg) {
		if (MemStorage.mute.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c" + MemStorage.locale.get("YOU_ARE_MUTED") + ".");
			return;
		} else if (!(!Utils.isIgnored(sender, target) || LiveChat.perms.has(sender, "livechat.ignore.bypass"))) {
			sender.sendMessage("\u00A7c" + MemStorage.locale.get("YOU_ARE_IGNORED") + ".");
			return;
		}
		target.sendMessage(Format.privateTarget(sender, target, msg, "private"));
		sender.sendMessage(Format.privateSender(sender, target, msg, "private"));
		MemStorage.reply.put(target.getName(), sender.getName());
		socialSpy(sender, target, msg);
		Log.main("[" + sender.getName() + "->" + target.getName() + "] " + msg, "private");
	}

	public static void localchat(Player sender, String msg) {
		if (MemStorage.mute.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c" + MemStorage.locale.get("YOU_ARE_MUTED") + ".");
			return;
		}
		Boolean heard = false;
		Player[] players = sender.getWorld().getPlayers().toArray(new Player[0]);
		for (int i = 0; i < players.length; i++) {
			if (!players[i].equals(sender) && players[i].getLocation().distance(sender.getLocation()) < MemStorage.plugin.getConfig().getInt("chat.local.radius") && (!Utils.isIgnored(sender, players[i]) || LiveChat.perms.has(sender, "livechat.ignore.bypass"))) {
				players[i].sendMessage(Format.main(sender, msg, "local"));
				heard = true;
			}
		}
		if (heard) {
			sender.sendMessage(Format.main(sender, msg, "local"));
			Log.main("[" + sender.getName() + "] " + msg, "local");
		} else {
			sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOBODY_HEAR") + ".");
		}
	}

	public static void adminchat(Player sender, String msg, String[] channelAdminList) {
		if (MemStorage.mute.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c" + MemStorage.locale.get("YOU_ARE_MUTED") + ".");
			return;
		}
		for (int i = 0; i < channelAdminList.length; i++ ) {
			Player channelAdminUser = null;
			channelAdminUser = sender.getServer().getPlayer(channelAdminList[i]);
			if (channelAdminUser != null) {
				channelAdminUser.sendMessage(Format.main(sender, msg, "admin"));
			}
		}
		Log.main("[" + sender.getName() + "] " + msg, "admin");
	}

	public static void emotechat(Player sender, String msg) {
		if (MemStorage.mute.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c" + MemStorage.locale.get("YOU_ARE_MUTED") + ".");
			return;
		}
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		for (Player target:players) {
			if (!Utils.isIgnored(sender, target) || LiveChat.perms.has(sender, "livechat.ignore.bypass")) {
				target.sendMessage(Format.main(sender, msg, "emote"));
			}
		}
		Log.main("[" + sender.getName() + "] " + msg, "emote");
	}

	public static void socialSpy(Player sender, Player target, String msg) {
		String[] socialSpyList = MemStorage.plugin.getConfig().getString("chat.socialspy.players").replaceAll(" ", "").split(",");
		for (int i = 0; i < socialSpyList.length; i++) {
			Player socialSpyUser = null;
			socialSpyUser = Bukkit.getServer().getPlayer(socialSpyList[i]);
			if (socialSpyUser != sender && socialSpyUser != target && socialSpyUser != null) {
				socialSpyUser.sendMessage(Format.withTarget(sender, target, msg, "socialspy"));
			}
		}
	}
}