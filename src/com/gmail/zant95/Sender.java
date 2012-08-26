package com.gmail.zant95;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Sender {
	public static void player(Player sender, Player target, String msg) {
		if (MemStorage.mute.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c"+MemStorage.locale.get("YOU_ARE_MUTED")+".");
			return;
		}
		if (Utils.isIgnored(sender, target)) {
			sender.sendMessage("\u00A7c"+MemStorage.locale.get("YOU_ARE_IGNORED")+".");
			return;
		}
		String to = target.getDisplayName() + "\u00A7r";
		String from = sender.getDisplayName() + "\u00A7r";
		target.sendMessage("\u00A78[\u00A7e" + from + "\u00A77 -> \u00A76You\u00A78]\u00A7r " + msg);
		sender.sendMessage("\u00A78[\u00A76You\u00A77 -> \u00A7e" + to + "\u00A78]\u00A7r " + msg);
		MemStorage.reply.put(target.getName(), sender.getName());
		Utils.socialSpy(sender, target, msg);
		Log.privatechat("[" + sender.getName() + "->" + target.getName() + "][x:" + sender.getLocation().getBlockX() + ",y:" + sender.getLocation().getBlockY() + ",z:" + sender.getLocation().getBlockZ() + "]" + msg);
	}

	public static void me(Player sender, String string) {
		if (MemStorage.mute.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c"+MemStorage.locale.get("YOU_ARE_MUTED")+".");
			return;
		}
		Player[] players = Bukkit.getServer().getOnlinePlayers();
		for (Player target:players) {
			if (!Utils.isIgnored(sender, target)) {
				target.sendMessage(string);
			}
		}
		Log.publicchat("[" + sender.getName() + "][" + sender.getWorld().getName() + "][x:" + sender.getLocation().getBlockX() + ",y:" + sender.getLocation().getBlockY() + ",z:" + sender.getLocation().getBlockZ() + "][ME]" + string);
	}

	public static void local(Player sender, String string) {
		if (MemStorage.mute.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c"+MemStorage.locale.get("YOU_ARE_MUTED")+".");
			return;
		}
		Player[] players = sender.getWorld().getPlayers().toArray(new Player[0]);
		for (int i = 0; i < players.length; i++) {
			if (players[i].getLocation().distance(sender.getLocation()) < MemStorage.plugin.getConfig().getInt("local-radius") && !Utils.isIgnored(sender, players[i])) {
				players[i].sendMessage(string);
				Log.publicchat("[" + sender.getName() + "][" + sender.getWorld().getName() + "][x:" + sender.getLocation().getBlockX() + ",y:" + sender.getLocation().getBlockY() + ",z:" + sender.getLocation().getBlockZ() + "][LOCAL]" + string);
			}
		}
	}
}