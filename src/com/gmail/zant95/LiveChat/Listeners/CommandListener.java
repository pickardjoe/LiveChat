package com.gmail.zant95.LiveChat.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import com.gmail.zant95.LiveChat.LiveChat;
import com.gmail.zant95.LiveChat.Log;
import com.gmail.zant95.LiveChat.MemStorage;
import com.gmail.zant95.LiveChat.PlayerDisplayName;

public class CommandListener implements Listener{
	LiveChat plugin;

	public CommandListener(LiveChat instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		if (event.isCancelled()) return;

		Player sender = event.getPlayer();
		String[] args = event.getMessage().split(" ");

		if (args.length > 0 && args[0].compareToIgnoreCase("/block") == 0 || args[0].compareToIgnoreCase("/lcblock") == 0) {
			return;
		} else if (MemStorage.block.containsKey(sender.getName())) {
			sender.sendMessage("\u00A7c"+MemStorage.locale.get("YOU_ARE_BLOCKED")+".");
			event.setCancelled(true);
			return;
		}

		PlayerDisplayName.main(sender);
		Log.command("[" + sender.getName() + "] " + event.getMessage());
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onServerCommand(ServerCommandEvent event) {
		Log.command("[CONSOLE] " + event.getCommand());
	}
}
