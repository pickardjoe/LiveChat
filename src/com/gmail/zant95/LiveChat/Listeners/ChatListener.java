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

		if (MemStorage.speaker.containsKey(senderName)) {
			Player target = Bukkit.getServer().getPlayer(MemStorage.speaker.get(senderName));
			Sender.main(sender, msg, "private:" + target.getName());
		} else if (MemStorage.admin.containsKey(senderName)) {
			Sender.main(sender, msg, "admin");
		} else if (MemStorage.local.containsKey(senderName)) {
			Sender.main(sender, msg, "local");
		} else if (MemStorage.map.containsKey(senderName)) {
			Sender.main(sender, msg, "map");
		} else if (LiveChat.perms.has(sender, "livechat.chat")) {
			if (MemStorage.conf.getString("chat.public.format").equalsIgnoreCase("DISABLED") || MemStorage.conf.getString("chat.public.format").isEmpty()) {
				event.setCancelled(false);
			} else {
				Sender.main(sender, msg, "public");
			}
		} else {
			sender.sendMessage("\u00A7c" + MemStorage.locale.get("SPEAK_PERMISSION") + ".");
		}
	}
}