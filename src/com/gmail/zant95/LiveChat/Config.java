package com.gmail.zant95.LiveChat;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {
	static Plugin plugin = MemStorage.plugin;
	static FileConfiguration config = plugin.getConfig();

	public static void load() {
		if (config.get("default-chat") == null) {
			config.set("default-chat", "global");
		}
		if (config.get("chat.global.format") == null) {
			config.set("chat.global.format", "&7[&6Global&7] %DISPLAYNAME%&7: &r%MSG%");
		}
		if (config.get("chat.private.format") == null) {
			config.set("chat.private.format", "&7[%DISPLAYNAME% &7>> %TARGETDISPLAYNAME%&7] &r%MSG%");
		}
		if (config.get("chat.map.format") == null) {
			config.set("chat.map.format", "&7[&b%WORLDNAME%&7] %DISPLAYNAME%&7: &r%MSG%");
		}
		if (config.get("chat.local.format") == null) {
			config.set("chat.local.format", "&7[&2Local&7] %DISPLAYNAME%&7: &r%MSG%");
		}
		if (config.get("chat.local.radius") == null) {
			config.set("chat.local.radius", 150);
		}
		if (config.get("chat.emote.format") == null) {
			config.set("chat.emote.format",  "* %DISPLAYNAME%&r %MSG%");
		}
		if (config.get("chat.admin.format") == null) {
			config.set("chat.admin.format", "&7[&4Admin&7] &c%NAME%&7: &r%MSG%");
		}
		if (config.get("chat.socialspy.format") == null) {
			config.set("chat.socialspy.format", "&7[&dSpy&7] &7[%DISPLAYNAME% &7>> %TARGETDISPLAYNAME%&7] %MSG%");
		}
		if (config.get("chat.socialspy.color") == null) {
			config.set("chat.socialspy.color", false);
		}
		if (config.get("op.prefix") == null) {
			config.set("op.prefix", "");
		}
		if (config.get("op.suffix") == null) {
			config.set("op.suffix", "");
		}
		if (config.get("userlist.display-prefix") == null) {
			config.set("userlist.display-prefix", true);
		}
		if (config.get("userlist.display-suffix") == null) {
			config.set("userlist.display-suffix", false);
		}
		if (config.get("head.tag") == null) {
			config.set("head.tag", true);
		}
		if (config.get("log.global") == null) {
			config.set("log.global", true);
		}
		if (config.get("log.private") == null) {
			config.set("log.private", true);
		}
		if (config.get("log.admin") == null) {
			config.set("log.admin", true);
		}
		if (config.get("log.map") == null) {
			config.set("log.map", true);
		}
		if (config.get("log.local") == null) {
			config.set("log.local", true);
		}
		if (config.get("log.emote") == null) {
			config.set("log.emote", true);
		}
		if (config.get("log.commands") == null) {
			config.set("log.commands", true);
		}
		if (config.get("console.color") == null) {
			config.set("console.color", true);
		}
		if (config.get("console.debug.global") == null) {
			config.set("console.debug.global", true);
		}
		if (config.get("console.debug.private") == null) {
			config.set("console.debug.private", true);
		}
		if (config.get("console.debug.admin") == null) {
			config.set("console.debug.admin", true);
		}
		if (config.get("console.debug.map") == null) {
			config.set("console.debug.map", true);
		}
		if (config.get("console.debug.local") == null) {
			config.set("console.debug.local", true);
		}
		if (config.get("console.debug.emote") == null) {
			config.set("console.debug.emote", true);
		}
		if (config.get("console.debug.commands") == null) {
			config.set("console.debug.commands", true);
		}
		if (config.get("locale") == null) {
			config.set("locale", "en");
		}
		noLongerRequired();
		plugin.saveConfig();
	}
	
	private static void noLongerRequired() {
		config.set("chat.socialspy.players", null);
		config.set("chat.admin.players", null);
		config.set("log.public", null);
		config.set("console.debug.public", null);
		config.set("version", null);
	}
}
