package com.gmail.zant95.LiveChat;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class MemStorage {
	public static HashMap<String, String> global = new HashMap<String, String>();
	public static HashMap<String, String> speaker = new HashMap<String, String>();
	public static HashMap<String, String> map = new HashMap<String, String>();
	public static HashMap<String, String> local = new HashMap<String, String>();
	public static HashMap<String, String> admin = new HashMap<String, String>();
	public static HashMap<String, String> reply = new HashMap<String, String>();
	public static HashMap<String, String> mute = new HashMap<String, String>();
	public static HashMap<String, String> block = new HashMap<String, String>();
	public static HashMap<String, String> ignore = new HashMap<String, String>();
	public static HashMap<String, String> globalignore = new HashMap<String, String>();
	public static HashMap<String, String> privateignore = new HashMap<String, String>();
	public static HashMap<String, String> mapignore = new HashMap<String, String>();
	public static HashMap<String, String> localignore = new HashMap<String, String>();
	public static HashMap<String, String> emoteignore = new HashMap<String, String>();
	public static HashMap<String, String> socialspyoff = new HashMap<String, String>();
	public static HashMap<String, String> locale = new HashMap<String, String>();
	public static Plugin plugin = Bukkit.getPluginManager().getPlugin("LiveChat");
	public static FileConfiguration conf = plugin.getConfig();
}
