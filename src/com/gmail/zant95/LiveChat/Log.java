package com.gmail.zant95.LiveChat;

import java.io.*;

import org.bukkit.Bukkit;

public class Log {
	static String dir = MemStorage.plugin.getDataFolder() + File.separator + "logs" + File.separator;
	
	public static void main(String fmsg, String type) {
		if (MemStorage.conf.getBoolean("log." + type)) {
			String msg = FormatTool.undo(fmsg);
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
			String time = sdf.format(date);
			try {
				Writer out;
				out = new BufferedWriter(new FileWriter(dir + type + ".log", true));
				out.append("["+time+"]");
				out.append(msg+"\n");
				out.close();
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
		if (MemStorage.conf.getBoolean("console.debug." + type)) {
			if (!MemStorage.conf.getBoolean("console.color")) {
				fmsg = FormatTool.undo(fmsg);
			}
			Bukkit.getConsoleSender().sendMessage(fmsg);
		}
	}
}