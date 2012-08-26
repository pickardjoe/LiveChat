package com.gmail.zant95;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Locale {
	LiveChat plugin;
	
	public Locale(LiveChat plugin) {
		this.plugin = plugin;
	}

	public Properties getLocale() throws IOException {
		Properties localeFile = new Properties();
		InputStream inputStream = plugin.getClass().getClassLoader().getResourceAsStream("locales/messages.properties");
		localeFile.load(inputStream);
		return localeFile;
	}

	public String getText(String name) {
		try {
			return getLocale().getProperty(name);
		} catch (IOException e) {}
			return "Unexpected error :-(";
	}
	
	public void load() {
		MemStorage.locale.put("NOT_AS_CONSOLE", getText("NOT_AS_CONSOLE"));
		MemStorage.locale.put("PLAYER_NOT_FOUND", getText("PLAYER_NOT_FOUND"));
		MemStorage.locale.put("SPEAK_PERMISSION", getText("SPEAK_PERMISSION"));
		MemStorage.locale.put("TALK_TO_YOURSELF", getText("TALK_TO_YOURSELF"));
		MemStorage.locale.put("CONVERSATION_WITH", getText("CONVERSATION_WITH"));
		MemStorage.locale.put("CONVERSATION_WITH_YOURSELF", getText("CONVERSATION_WITH_YOURSELF"));
		MemStorage.locale.put("END_CONVERSATION", getText("END_CONVERSATION"));
		MemStorage.locale.put("NOT_PERMISSION", getText("NOT_PERMISSION"));
		MemStorage.locale.put("NOBODY_REPLY", getText("NOBODY_REPLY"));
		MemStorage.locale.put("NOT_WITH_YOURSELF", getText("NOT_WITH_YOURSELF"));
		MemStorage.locale.put("REPLY_USAGE", getText("REPLY_USAGE"));
		MemStorage.locale.put("EMOTE_USAGE", getText("EMOTE_USAGE"));
		MemStorage.locale.put("LOCAL_USAGE", getText("LOCAL_USAGE"));
		MemStorage.locale.put("MUTE_USAGE", getText("MUTE_USAGE"));
		MemStorage.locale.put("BLOCK_USAGE", getText("BLOCK_USAGE"));
		MemStorage.locale.put("IGNORE_USAGE", getText("IGNORE_USAGE"));
		MemStorage.locale.put("CHANNEL_USAGE", getText("CHANNEL_USAGE"));
		MemStorage.locale.put("NOT_IN_CHANNEL_ADMIN", getText("NOT_IN_CHANNEL_ADMIN"));
		MemStorage.locale.put("MUTED_PLAYER", getText("MUTED_PLAYER"));
		MemStorage.locale.put("UNMUTED_PLAYER", getText("UNMUTED_PLAYER"));
		MemStorage.locale.put("YOU_ARE_MUTED", getText("YOU_ARE_MUTED"));
		MemStorage.locale.put("BLOCKED_PLAYER", getText("BLOCKED_PLAYER"));
		MemStorage.locale.put("UNBLOCKED_PLAYER", getText("UNBLOCKED_PLAYER"));
		MemStorage.locale.put("YOU_ARE_BLOCKED", getText("YOU_ARE_BLOCKED"));
		MemStorage.locale.put("IGNORED_PLAYER", getText("IGNORED_PLAYER"));
		MemStorage.locale.put("UNIGNORED_PLAYER", getText("UNIGNORED_PLAYER"));
		MemStorage.locale.put("YOU_ARE_IGNORED", getText("YOU_ARE_IGNORED"));
		MemStorage.locale.put("DISCONECTED_USER", getText("DISCONECTED_USER"));
		MemStorage.locale.put("RELOAD_CONFIG", getText("RELOAD_CONFIG"));
	}
}