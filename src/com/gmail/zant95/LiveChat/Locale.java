package com.gmail.zant95.LiveChat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Locale {
	public static Properties getLocale() throws IOException {
		Properties localeFile = new Properties();
		InputStream inputStream = MemStorage.plugin.getClass().getClassLoader().getResourceAsStream("locales/messages_" + MemStorage.conf.getString("locale") + ".properties");
		localeFile.load(inputStream);
		return localeFile;
	}

	public static String getText(String name) {
		try {
			return getLocale().getProperty(name);
		} catch (IOException e) {}
			return "Unexpected error :-(";
	}

	public static void loadValue(String text) {
		MemStorage.locale.put(text, getText(text));
	}

	public static void load() {
		loadValue("YOU");
		loadValue("NOT_AS_CONSOLE");
		loadValue("PLAYER_NOT_FOUND");
		loadValue("SPEAK_PERMISSION");
		loadValue("TALK_TO_YOURSELF");
		loadValue("CONVERSATION_WITH");
		loadValue("CONVERSATION_WITH_YOURSELF");
		loadValue("END_CONVERSATION");
		loadValue("ANY_CONVERSATION");
		loadValue("NOT_PERMISSION");
		loadValue("NOBODY_REPLY");
		loadValue("NOT_WITH_YOURSELF");
		loadValue("EMOTE_USAGE");
		loadValue("MUTE_USAGE");
		loadValue("BLOCK_USAGE");
		loadValue("IGNORE_USAGE");
		loadValue("CHANNEL_USAGE");
		loadValue("CHANNEL_NOT_FOUND");
		loadValue("MUTED_PLAYER");
		loadValue("UNMUTED_PLAYER");
		loadValue("YOU_ARE_MUTED");
		loadValue("BLOCKED_PLAYER");
		loadValue("UNBLOCKED_PLAYER");
		loadValue("YOU_ARE_BLOCKED");
		loadValue("IGNORED_PLAYER");
		loadValue("UNIGNORED_PLAYER");
		loadValue("YOU_ARE_IGNORED");
		loadValue("STARTED_GLOBAL_CONVERSATION");
		loadValue("ENDED_GLOBAL_CONVERSATION");
		loadValue("STARTED_MAP_CONVERSATION");
		loadValue("ENDED_MAP_CONVERSATION");
		loadValue("STARTED_LOCAL_CONVERSATION");
		loadValue("ENDED_LOCAL_CONVERSATION");
		loadValue("STARTED_ADMIN_CONVERSATION");
		loadValue("ENDED_ADMIN_CONVERSATION");
		loadValue("NOBODY_HEAR");
		loadValue("IGNORED_GLOBAL_CHANNEL");
		loadValue("UNIGNORED_GLOBAL_CHANNEL");
		loadValue("IGNORED_PRIVATE_CHANNEL");
		loadValue("UNIGNORED_PRIVATE_CHANNEL");
		loadValue("IGNORED_MAP_CHANNEL");
		loadValue("UNIGNORED_MAP_CHANNEL");
		loadValue("IGNORED_LOCAL_CHANNEL");
		loadValue("UNIGNORED_LOCAL_CHANNEL");
		loadValue("IGNORED_EMOTE_CHAT");
		loadValue("UNIGNORED_EMOTE_CHAT");
		loadValue("CHANNEL_NOT_EXIST");
		loadValue("SOCIALSPY_ON");
		loadValue("SOCIALSPY_OFF");
		loadValue("DISCONECTED_USER");
		loadValue("RELOAD_CONFIG");
	}
}