package com.gmail.zant95;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class MemStorage {
	public static HashMap<String, String> speaker = new HashMap<String, String>();
	public static HashMap<String, String> reply = new HashMap<String, String>();
	public static HashMap<String, String> mute = new HashMap<String, String>();
	public static HashMap<String, String> block = new HashMap<String, String>();
	public static HashMap<String, String> ignore = new HashMap<String, String>();
	public static HashMap<String, String> locale = new HashMap<String, String>();
	public static Plugin plugin = Bukkit.getPluginManager().getPlugin("LiveChat");
}
