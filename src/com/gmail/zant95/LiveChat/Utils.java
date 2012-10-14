package com.gmail.zant95.LiveChat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.NetServerHandler;
import net.minecraft.server.Packet20NamedEntitySpawn;
import net.minecraft.server.Packet29DestroyEntity;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Utils {
	public static String getMsg(String[] args, int start, CommandSender sender) {
		StringBuffer buff = new StringBuffer();
		for (int i = start; i < args.length; i++) {
			buff.append(args[i] + " ");
		}
		String msg = buff.toString();
		return msg;
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

	// For future use. Now >> TagAPI.
	public static void setTagName(Player player) {
		Location loc;
		Location myLoc = player.getLocation();
		String origName = player.getName();
		String newName = player.getPlayerListName();
		double myX = myLoc.getX();
		double myZ = myLoc.getZ();
		String myWorld = myLoc.getWorld().getName();
		double d0;
		double d1;
		EntityHuman e = ((CraftPlayer)player).getHandle();
		Packet29DestroyEntity packet29 = new Packet29DestroyEntity(e.id);
		Packet20NamedEntitySpawn packet20 = new Packet20NamedEntitySpawn(e);
		packet20.b = newName;
		NetServerHandler ns;
		for(Player pl: MemStorage.plugin.getServer().getOnlinePlayers()) {
			if(pl.getName().equals(origName))
				continue;
			loc = pl.getLocation();
			if(!myWorld.equals(loc.getWorld().getName()))
				continue;
			d0 = loc.getX() - (double) (myX / 32);
			d1 = loc.getZ() - (double) (myZ / 32);
			if(d0 >= -512.0D && d0 <= 512.0D && d1 >= -512.0D && d1 <= 512.0D) {
				ns = ((CraftPlayer)pl).getHandle().netServerHandler;
				ns.sendPacket(packet29);
				ns.sendPacket(packet20);
			}
		}
	}
}
