package com.gmail.zant95.LiveChat.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gmail.zant95.LiveChat.LiveChat;
import com.gmail.zant95.LiveChat.MemStorage;
import com.gmail.zant95.LiveChat.PlayerDisplayName;
import com.gmail.zant95.LiveChat.Sender;

public class ChatListener implements Listener {
	LiveChat plugin;

	public ChatListener(LiveChat instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);

		Player sender = event.getPlayer();
		String senderName = sender.getName();
		String msg = event.getMessage();
		PlayerDisplayName.main(sender);
		String defaultchannel = MemStorage.conf.getString("default-chat");
		
		if (MemStorage.speaker.containsKey(senderName)) {
			Player target = Bukkit.getServer().getPlayer(MemStorage.speaker.get(senderName));
			Sender.main(sender, target, msg, "private");
		} else if (MemStorage.admin.containsKey(senderName)) {
			Sender.main(sender, sender, msg, "admin");
		} else if (MemStorage.local.containsKey(senderName)) {
			Sender.main(sender, sender, msg, "local");
		} else if (MemStorage.map.containsKey(senderName)) {
			Sender.main(sender, sender, msg, "map");
		} else if (MemStorage.global.containsKey(senderName)) {
			Sender.main(sender, sender, msg, "global");
		} else if (defaultchannel.equalsIgnoreCase("global") || defaultchannel.equalsIgnoreCase("local") || defaultchannel.equalsIgnoreCase("map")) {
			Sender.main(sender, sender, msg, defaultchannel);
		} else {
			sender.sendMessage("\u00A7c" + MemStorage.locale.get("CHANNEL_NOT_FOUND") + ".");
		}
	}
}