package com.gmail.zant95.LiveChat;

public class FormatTool {
	public static String all(String text) {
		return text.replaceAll("&([a-fk-or0-9])", "\u00A7$1");
	}

	public static String color(String text) {
		return text.replaceAll("&([a-fA-F0-9])", "\u00A7$1");
	}

	public static String format(String text) {
		return text.replaceAll("&([l-oL-OrR])", "\u00A7$1");
	}

	public static String magic(String text) {
		return text.replaceAll("&([kK])", "\u00A7$1");
	}
}