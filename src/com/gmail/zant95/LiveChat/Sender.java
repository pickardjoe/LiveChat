package com.gmail.zant95.LiveChat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Sender {
	public static void publicchat(Player sender, String msg) {
		String string = "\u00A7r" + Format.main(sender, msg, "public");
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		for (Player target:players) {
			if (!Utils.isIgnored(sender, target)) {
				target.sendMessage(string);
			}
		}
		Log.publicchat("[" + sender.getName() + "] " + msg);
	}

	public static void privatechat(Player sender, Player target, String msg) {
		if (MemStorage.mute.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c"+MemStorage.locale.get("YOU_ARE_MUTED")+".");
			return;
		} else if (Utils.isIgnored(sender, target)) {
			sender.sendMessage("\u00A7c"+MemStorage.locale.get("YOU_ARE_IGNORED")+".");
			return;
		}
		target.sendMessage(Format.privateTarget(sender, target, msg, "private"));
		sender.sendMessage(Format.privateSender(sender, target, msg, "private"));
		MemStorage.reply.put(target.getName(), sender.getName());
		socialSpy(sender, target, msg);
		Log.privatechat("[" + sender.getName() + "->" + target.getName() + "] " + msg);
	}

	public static void me(Player sender, String msg, String log) {
		if (MemStorage.mute.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c"+MemStorage.locale.get("YOU_ARE_MUTED")+".");
			return;
		}
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		for (Player target:players) {
			if (!Utils.isIgnored(sender, target)) {
				target.sendMessage(msg);
			}
		}
		Log.publicchat("[" + sender.getName() + "][Me] " + log);
	}

	public static void local(Player sender, String msg, String log) {
		if (MemStorage.mute.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c"+MemStorage.locale.get("YOU_ARE_MUTED")+".");
			return;
		}
		Player[] players = sender.getWorld().getPlayers().toArray(new Player[0]);
		for (int i = 0; i < players.length; i++) {
			if (players[i].getLocation().distance(sender.getLocation()) < MemStorage.plugin.getConfig().getInt("local-radius") && !Utils.isIgnored(sender, players[i])) {
				players[i].sendMessage(msg);
				Log.publicchat("[" + sender.getName() + "][Local] " + log);
			}
		}
	}

	public static void socialSpy(Player sender, Player target, String msg) {
		String[] socialSpyList = MemStorage.plugin.getConfig().getString("socialspy-players").replaceAll(" ", "").split(",");
		for (int i = 0; i < socialSpyList.length; i++) {
			Player socialSpyUser = null;
			socialSpyUser = Bukkit.getServer().getPlayer(socialSpyList[i]);
			if (socialSpyUser != sender && socialSpyUser != target && socialSpyUser != null) {
				socialSpyUser.sendMessage(Format.withTarget(sender, target, msg, "socialSpy"));
			}
		}
	}
}