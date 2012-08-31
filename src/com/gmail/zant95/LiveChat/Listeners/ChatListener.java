package com.gmail.zant95.LiveChat.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gmail.zant95.LiveChat.Format;
import com.gmail.zant95.LiveChat.LiveChat;
import com.gmail.zant95.LiveChat.MemStorage;
import com.gmail.zant95.LiveChat.PlayerDisplayName;
import com.gmail.zant95.LiveChat.Sender;
import com.gmail.zant95.LiveChat.Utils;

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

		if (MemStorage.mute.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c"+MemStorage.locale.get("YOU_ARE_MUTED")+".");
			return;
		} else if (Utils.isPrivate(senderName)) {
			Player target = Bukkit.getServer().getPlayer(MemStorage.speaker.get(senderName));
			if (target != null) {
				Sender.privatechat(sender, target, msg);
				return;
			} else {
				sender.sendMessage("\u00A7c"+MemStorage.locale.get("DISCONECTED_USER")+".");
				return;
			}
		} else if (MemStorage.local.containsKey(senderName)) {
			Sender.local(sender, Format.main(sender, msg, "local"), msg);
			return;
		} else if (LiveChat.perms.has(sender, "livechat.chat")) {
			if (MemStorage.conf.getString("public-chat-format").equalsIgnoreCase("DISABLED") || MemStorage.conf.getString("public-chat-format").isEmpty()) {
				event.setCancelled(false);
				return;
			} else {
				Sender.publicchat(sender, msg);
				return;
			}
		} else {
			sender.sendMessage("\u00A7c"+MemStorage.locale.get("SPEAK_PERMISSION")+".");
			return;
		}
	}
}