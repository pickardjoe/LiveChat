package com.gmail.zant95;

import java.io.*;
import org.bukkit.configuration.file.FileConfiguration;

public class Log {
	static FileConfiguration conf = MemStorage.plugin.getConfig();
	static String dir = MemStorage.plugin.getDataFolder() + File.separator + "logs" + File.separator;

	public static void publicchat(String text) {
		if (conf.getBoolean("log-public-chat")) {
			java.util.Date date = new java.util.Date(); 
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
			String time = sdf.format(date);
			try {
			Writer out;
			out = new BufferedWriter(new FileWriter(dir + "public.log", true));
			out.append("["+time+"]");
			out.append(text+"\n");
			out.close();
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}

	public static void privatechat(String text) {
		if (conf.getBoolean("log-private-chat")) {
			java.util.Date date = new java.util.Date(); 
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
			String time = sdf.format(date);
			try {
			Writer out;
			out = new BufferedWriter(new FileWriter(dir + "private.log", true));
			out.append("["+time+"]");
			out.append(text+"\n");
			out.close();
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}

	public static void command(String text) {
		if (conf.getBoolean("log-commands")) {
			java.util.Date date = new java.util.Date(); 
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
			String time = sdf.format(date);
			try {
			Writer out;
			out = new BufferedWriter(new FileWriter(dir + "command.log", true));
			out.append("["+time+"]");
			out.append(text+"\n");
			out.close();
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}
}