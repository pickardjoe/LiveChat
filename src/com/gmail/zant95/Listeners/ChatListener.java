package com.gmail.zant95.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gmail.zant95.Format;
import com.gmail.zant95.LiveChat;
import com.gmail.zant95.Log;
import com.gmail.zant95.MemStorage;
import com.gmail.zant95.PlayerDisplayName;
import com.gmail.zant95.Sender;
import com.gmail.zant95.Utils;

public class ChatListener implements Listener {
	LiveChat plugin;

	public ChatListener(LiveChat instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);

		Player sender = event.getPlayer();
		String senderName = sender.getName();
		String senderMsg = event.getMessage();

		if (MemStorage.mute.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c"+MemStorage.locale.get("YOU_ARE_MUTED")+".");
			return;
		}

		PlayerDisplayName.main(sender);

		String log = "[" + senderName + "][" + sender.getWorld().getName() + "][x:" + sender.getLocation().getBlockX() + ",y:" + sender.getLocation().getBlockY() + ",z:" + sender.getLocation().getBlockZ() + "]" + event.getMessage();

		if (Utils.isPrivate(senderName)) {
			Player target = Bukkit.getServer().getPlayer(MemStorage.speaker.get(senderName));
			if (target != null) {
				Sender.player(sender, target, senderMsg);
				return;
			} else {
				sender.sendMessage("\u00A7c"+MemStorage.locale.get("DISCONECTED_USER")+".");
				return;
			}
		} else if (LiveChat.perms.has(sender, "livechat.chat")) {
			if (plugin.getConfig().getString("chat-format").equalsIgnoreCase("DISABLED") || plugin.getConfig().getString("chat-format").isEmpty()) {
				event.setCancelled(false);
				Log.publicchat(log);
				return;
			} else {
				String string = "\u00A7r" + Format.main(event.getPlayer(), event.getMessage(), "chat");
				Player[] players = Bukkit.getServer().getOnlinePlayers();
				for (Player target:players) {
					if (!Utils.isIgnored(sender, target)) {
						target.sendMessage(string);
					}
				}
				Log.publicchat(log);
				return;
			}
		} else {
			sender.sendMessage("\u00A7c"+MemStorage.locale.get("SPEAK_PERMISSION")+".");
			return;
		}
	}
}