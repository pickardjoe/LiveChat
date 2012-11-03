package com.gmail.zant95.LiveChat;

public class FormatTool {
	public static String all(String text) {
		//return text.replaceAll("&([a-fk-or0-9])", "\u00A7$1");
		return color(format(magic(text)));
	}

	public static String color(String text) {
		//return text.replaceAll("&([a-fA-F0-9])", "\u00A7$1");
		return text	.replace("&0","\u00A70")
					.replace("&1","\u00A71")
					.replace("&2","\u00A72")
					.replace("&3","\u00A73")
					.replace("&4","\u00A74")
					.replace("&5","\u00A75")
					.replace("&6","\u00A76")
					.replace("&7","\u00A77")
					.replace("&8","\u00A78")
					.replace("&9","\u00A79")
					.replace("&a","\u00A7a")
					.replace("&b","\u00A7b")
					.replace("&c","\u00A7c")
					.replace("&d","\u00A7d")
					.replace("&e","\u00A7e")
					.replace("&f","\u00A7f")
					.replace("&r","\u00A7r");
	}

	public static String format(String text) {
		//return text.replaceAll("&([l-oL-OrR])", "\u00A7$1");
		return text	.replace("&l","\u00A7l")
					.replace("&m","\u00A7m")
					.replace("&n","\u00A7n")
					.replace("&o","\u00A70");
	}

	public static String magic(String text) {
		//return text.replaceAll("&([kK])", "\u00A7$1");
		return text	.replace("&k","\u00A7k");
	}
}