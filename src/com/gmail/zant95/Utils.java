package com.gmail.zant95;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {
	public static String getMSG(String[] args, int start, CommandSender sender) {
		StringBuffer buff = new StringBuffer();
		for (int i = start; i < args.length; i++) {
			buff.append(args[i] + " ");
		}
		String msg = buff.toString();
		if (LiveChat.perms.has(sender, "livechat.msg.color")) {
			msg = msg.replaceAll("(?i)&([a-fA-F0-9])", "\u00A7$1");
		}
		if (LiveChat.perms.has(sender, "livechat.msg.format")) {
			msg = msg.replaceAll("(?i)&([l-oL-OrR])", "\u00A7$1");
		}
		if (LiveChat.perms.has(sender, "livechat.msg.magic")) {
			msg = msg.replaceAll("(?i)&([kK])", "\u00A7$1");
		}
		return msg;
	}

	public static String getEMOTE(String[] args, int start, CommandSender sender) {
		StringBuffer buff = new StringBuffer();
		for (int i = start; i < args.length; i++) {
			buff.append(args[i] + " ");
		}
		String msg = buff.toString();
		return msg;
	}

	public static void socialSpy(Player sender, Player target, String msg) {
		String[] socialSpyList = MemStorage.plugin.getConfig().getString("socialspy-players").replaceAll(" ", "").split(",");
		for (int i = 0; i < socialSpyList.length; i++) {
			Player socialSpyUser = Bukkit.getServer().getPlayer(socialSpyList[i]);
			if (socialSpyUser != sender && socialSpyUser != target) {
				socialSpyUser.sendMessage("\u00A76 + \u00A7r" + sender.getDisplayName() + "\u00A77 -> \u00A7r" + target.getDisplayName() + "\u00A7r\u00A77: " + msg);
			}
		}
	}

	public static boolean isConsole(CommandSender sender) {
		if (sender instanceof Player) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isIgnored(Player sender, Player target) {
		if (MemStorage.ignore.containsKey(target.getName() + "." + sender.getName())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isPrivate(String senderName) {
		if (MemStorage.speaker.containsKey(senderName)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean containsIgnoreCase(String[] string, String element) {
		List<String> list = Arrays.asList(string);
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			if(it.next().equalsIgnoreCase(element))
			return true;
		}
		return false;
	}

	public static void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while((len=in.read(buf))>0){
				out.write(buf,0,len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
