package com.gmail.zant95.LiveChat.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

import com.gmail.zant95.LiveChat.LiveChat;
import com.gmail.zant95.LiveChat.MemStorage;

public class TagListener implements Listener {
	LiveChat plugin;

	public TagListener(LiveChat instance) {
		plugin = instance;
	}

	//Refresh player head tag
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onNameTag(PlayerReceiveNameTagEvent event) {
		if (event.getNamedPlayer() != null && MemStorage.conf.getBoolean("color-head-tag")) {
			event.setTag(event.getNamedPlayer().getPlayerListName());
		}
	}
}
