package com.gmail.zant95.LiveChat;

import java.io.File;
import java.io.IOException;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


import com.gmail.zant95.LiveChat.Listeners.ChatListener;
import com.gmail.zant95.LiveChat.Listeners.JoinListener;
import com.gmail.zant95.LiveChat.Listeners.CommandListener;
import com.gmail.zant95.LiveChat.Listeners.TagListener;
import com.gmail.zant95.LiveChat.Metrics.Metrics;

public class LiveChat extends JavaPlugin {
	public final ChatListener ChatListener = new ChatListener(this);
	public final JoinListener JoinListener = new JoinListener(this);
	public final CommandListener CommandListener = new CommandListener(this);
	public final TagListener TagListener = new TagListener(this);
	public static Permission perms = null;
	public static Chat chat = null;

	@Override
	public void onEnable() {
		//Setup Vault
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			this.getLogger().info("Vault dependency not found!");
			getServer().getPluginManager().disablePlugin(this);
		}

		setupPermissions();
		setupChat();

		//Setup locale
		Locale.load();

		//Setup plugin folder
		File pluginDir = getDataFolder();
		if(!pluginDir.exists()) {
			pluginDir.mkdirs();
			this.getLogger().info("Creating plugin directory...");
		}

		//Setup log folder
		File logDir = new File(getDataFolder(), "logs");
		if(!logDir.exists()) {
			logDir.mkdirs();
			this.getLogger().info("Creating log directory...");
		}

		//Setup config
		File config = new File(getDataFolder(), "config.yml");
		if(!config.exists()) {
			config.getParentFile().mkdirs();
			Utils.copy(this.getResource("config.yml"), config);
		}

		//Implement listeners
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(ChatListener, this);
		pm.registerEvents(JoinListener, this);
		pm.registerEvents(CommandListener, this);

		//Setup TagAPI
		if (getServer().getPluginManager().getPlugin("TagAPI") == null) {
			if (!MemStorage.conf.getBoolean("color-head-tag")) {
				this.getLogger().info("TagAPI not found. Colors can't be displayed over people's heads!");
			}
		} else {
			pm.registerEvents(TagListener, this);
		}

		//Implement Plugin Metrics
		try {
			Metrics metrics = new Metrics(this); metrics.start();
		} catch (IOException e) { //Failed to submit the stats :-(
			System.out.println("Error submitting stats!");
		}

		//Implement commands
		getCommand("tell").setExecutor(new CommandHandler(this));
		getCommand("msg").setExecutor(new CommandHandler(this));
		getCommand("pm").setExecutor(new CommandHandler(this));
		getCommand("r").setExecutor(new CommandHandler(this));
		getCommand("me").setExecutor(new CommandHandler(this));
		getCommand("local").setExecutor(new CommandHandler(this));
		getCommand("global").setExecutor(new CommandHandler(this));
		getCommand("mute").setExecutor(new CommandHandler(this));
		getCommand("block").setExecutor(new CommandHandler(this));
		getCommand("ignore").setExecutor(new CommandHandler(this));
		getCommand("admin").setExecutor(new CommandHandler(this));
		getCommand("livechat").setExecutor(new CommandHandler(this));

		this.getLogger().info("Hey there! I am using LiveChat");
	}

	@Override
	public void onDisable() {
		this.getLogger().info("Goodbye LiveChat!");
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			perms = permissionProvider.getProvider();
		}
		return (perms != null);
	}

	private boolean setupChat() {
		RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
		if (chatProvider != null) {
			chat = chatProvider.getProvider();
		}
		return (chat != null);
	}
}