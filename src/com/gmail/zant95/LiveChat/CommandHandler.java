package com.gmail.zant95.LiveChat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandExecutor;

public class CommandHandler implements CommandExecutor {
	protected static CommandExecutor CommandExecutor;

	LiveChat plugin;

	public CommandHandler(LiveChat instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String playerName = null;
		String targetName = null;
		Player target = null;
		Player player = null;

		String defaultchannel = MemStorage.conf.getString("default-chat");

		if (!Utils.isConsole(sender)) {
			player = (Player)sender;
			playerName = player.getName();
		}

		if (command.getName().equalsIgnoreCase("global")) {
			if (Utils.isConsole(sender)) {
				plugin.getLogger().info(MemStorage.locale.get("NOT_AS_CONSOLE"));
				return true;
			}
			if (LiveChat.perms.has(sender, "livechat.global")) {
				if (args.length != 0) {
					String msg = Utils.getMsg(args, 0, sender);
					Sender.main(player, null, msg, "global");
					return true;
				} else if (MemStorage.global.containsKey(playerName) && !defaultchannel.equalsIgnoreCase("global")) {
					MemStorage.global.remove(playerName);
					sender.sendMessage("\u00A7e" + MemStorage.locale.get("ENDED_GLOBAL_CONVERSATION"));
					return true;
				} else {
					Utils.closeChannels((Player)sender);
					MemStorage.global.put(playerName, targetName);
					sender.sendMessage("\u00A7e" + MemStorage.locale.get("STARTED_GLOBAL_CONVERSATION"));
					return true;
				}
			} else {
				sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_PERMISSION"));
				return true;
			}
		}

		if (command.getName().equalsIgnoreCase("tell")) {
			if (Utils.isConsole(sender)) {
				plugin.getLogger().info(MemStorage.locale.get("NOT_AS_CONSOLE"));
				return true;
			}
			if (LiveChat.perms.has(sender, "livechat.private")) {
				if (args.length != 0) {
					target = sender.getServer().getPlayer(args[0]);
					if (target == null) {
						sender.sendMessage("\u00A7c" + MemStorage.locale.get("PLAYER_NOT_FOUND"));
						return true;
					}
					targetName = target.getName();
					if (args.length >= 2) {
						if (targetName != playerName) {
							Sender.main((Player)sender, target, Utils.getMsg(args, 1, sender), "private");
							return true;
						} else {
							sender.sendMessage("\u00A7c" + MemStorage.locale.get("TALK_TO_YOURSELF"));
							return true;
						}
					}
					if (playerName != targetName) {
						Utils.closeChannels((Player)sender);
						MemStorage.speaker.put(playerName, targetName);
						sender.sendMessage("\u00A7e" + MemStorage.locale.get("CONVERSATION_WITH") + " " + target.getDisplayName() + "\u00A7e.");
						return true;
					} else {
						sender.sendMessage("\u00A7c" + MemStorage.locale.get("CONVERSATION_WITH_YOURSELF"));
						return true;
					}
				} else if (MemStorage.speaker.containsKey(playerName)) {
					MemStorage.speaker.remove(playerName);
					sender.sendMessage("\u00A7e" + MemStorage.locale.get("END_CONVERSATION"));
					return true;
				} else {
					sender.sendMessage("\u00A7c" + MemStorage.locale.get("ANY_CONVERSATION"));
					return true;
				}
			} else {
				sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_PERMISSION"));
				return true;
			}
		}

		if (command.getName().equalsIgnoreCase("r")) {
			if (Utils.isConsole(sender)) {
				plugin.getLogger().info(MemStorage.locale.get("NOT_AS_CONSOLE"));
				return true;
			}
			if (LiveChat.perms.has(sender, "livechat.private")) {
				if (MemStorage.reply.get(playerName) == null) {
					sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOBODY_REPLY"));
					return true;
				} else {
					target = Bukkit.getServer().getPlayer(MemStorage.reply.get(playerName));
					if (args.length != 0) {
						Sender.main((Player)sender, target, Utils.getMsg(args, 0, sender), "private");
						return true;
					} else {
						if (target == null) {
							sender.sendMessage("\u00A7c" + MemStorage.locale.get("PLAYER_NOT_FOUND"));
							return true;
						} else {
							Utils.closeChannels((Player)sender);
							MemStorage.speaker.put(playerName, target.getName());
							sender.sendMessage("\u00A7e" + MemStorage.locale.get("CONVERSATION_WITH") + " " + target.getDisplayName() + "\u00A7e.");
							return true;
						}
					}
				}
			} else {
				sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_PERMISSION"));
				return true;
			}
		}

		if (command.getName().equalsIgnoreCase("me")) {
			if (Utils.isConsole(sender)) {
				plugin.getLogger().info(MemStorage.locale.get("NOT_AS_CONSOLE"));
				return true;
			}
			if (LiveChat.perms.has(sender, "livechat.emote")) {
				if (args.length == 0) {
					sender.sendMessage("\u00A7c" + MemStorage.locale.get("EMOTE_USAGE"));
					return true;
				} else {
					String msg = Utils.getMsg(args, 0, sender);
					Sender.main(player, null, msg, "emote");
					return true;
				}
			} else {
				sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_PERMISSION"));
				return true;
			}
		}

		if (command.getName().equalsIgnoreCase("map")) {
			if (Utils.isConsole(sender)) {
				plugin.getLogger().info(MemStorage.locale.get("NOT_AS_CONSOLE"));
				return true;
			}
			if (LiveChat.perms.has(sender, "livechat.map")) {
				if (args.length != 0) {
					String msg = Utils.getMsg(args, 0, sender);
					Sender.main(player, null, msg, "map");
					return true;
				} else if (MemStorage.map.containsKey(playerName) && !defaultchannel.equalsIgnoreCase("map")) {
					MemStorage.map.remove(playerName);
					sender.sendMessage("\u00A7e" + MemStorage.locale.get("ENDED_MAP_CONVERSATION"));
					return true;
				} else {
					Utils.closeChannels((Player)sender);
					MemStorage.map.put(playerName, "");
					sender.sendMessage("\u00A7e" + MemStorage.locale.get("STARTED_MAP_CONVERSATION"));
					return true;
				}
			} else {
				sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_PERMISSION"));
				return true;
			}
		}

		if (command.getName().equalsIgnoreCase("local")) {
			if (Utils.isConsole(sender)) {
				plugin.getLogger().info(MemStorage.locale.get("NOT_AS_CONSOLE"));
				return true;
			}
			if (LiveChat.perms.has(sender, "livechat.local")) {
				if (args.length != 0) {
					String msg = Utils.getMsg(args, 0, sender);
					Sender.main(player, null, msg, "local");
					return true;
				} else if (MemStorage.local.containsKey(playerName) && !defaultchannel.equalsIgnoreCase("local")) {
					MemStorage.local.remove(playerName);
					sender.sendMessage("\u00A7e" + MemStorage.locale.get("ENDED_LOCAL_CONVERSATION"));
					return true;
				} else {
					Utils.closeChannels((Player)sender);
					MemStorage.local.put(playerName, "");
					sender.sendMessage("\u00A7e" + MemStorage.locale.get("STARTED_LOCAL_CONVERSATION"));
					return true;
				}
			} else {
				sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_PERMISSION"));
				return true;
			}
		}

		if (command.getName().equalsIgnoreCase("admin")) {
			if (Utils.isConsole(sender)) {
				plugin.getLogger().info(MemStorage.locale.get("NOT_AS_CONSOLE"));
				return true;
			}
			if (LiveChat.perms.has(sender, "livechat.admin")) {
				if (args.length != 0) {
					String msg = Utils.getMsg(args, 0, sender);
					Sender.main(player, null, msg, "admin");
					return true;
				} else if (MemStorage.admin.containsKey(playerName)) {
					MemStorage.admin.remove(playerName);
					sender.sendMessage("\u00A7e" + MemStorage.locale.get("ENDED_ADMIN_CONVERSATION"));
					return true;
				} else {
					Utils.closeChannels((Player)sender);
					MemStorage.admin.put(playerName, "");
					sender.sendMessage("\u00A7e" + MemStorage.locale.get("STARTED_ADMIN_CONVERSATION"));
					return true;
				}
			} else {
				sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_PERMISSION"));
				return true;
			}
		}

		if (command.getName().equalsIgnoreCase("mute")) {
			if (LiveChat.perms.has(sender, "livechat.mute")) {
				if (args.length != 1) {
					sender.sendMessage("\u00A7c" + MemStorage.locale.get("MUTE_USAGE"));
					return true;	
				} else {
					target = sender.getServer().getPlayer(args[0]);
					if (target == null) {
						sender.sendMessage("\u00A7c" + MemStorage.locale.get("PLAYER_NOT_FOUND"));
						return true;
					} else if (target.getName() == playerName) {
						sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_WITH_YOURSELF"));
						return true;
					} else if (MemStorage.mute.containsKey(target.getName())) {
						MemStorage.mute.remove(target.getName());
						sender.sendMessage(target.getDisplayName() + " \u00A7e" + MemStorage.locale.get("UNMUTED_PLAYER"));
						return true;
					} else {
						MemStorage.mute.put(target.getName(), "");
						sender.sendMessage(target.getDisplayName() + " \u00A7e" + MemStorage.locale.get("MUTED_PLAYER"));
						return true;
					}
				}
			} else {
				sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_PERMISSION"));
				return true;
			}
		}

		if (command.getName().equalsIgnoreCase("block")) {
			if (LiveChat.perms.has(sender, "livechat.block")) {
				if (args.length != 1) {
					sender.sendMessage("\u00A7c" + MemStorage.locale.get("BLOCK_USAGE"));
					return true;
				} else {
					target = sender.getServer().getPlayer(args[0]);
					if (target == null) {
						sender.sendMessage("\u00A7c" + MemStorage.locale.get("PLAYER_NOT_FOUND"));
						return true;
					} else if (target.getName() == playerName) {
						sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_WITH_YOURSELF"));
						return true;
					} else if (MemStorage.block.containsKey(target.getName())) {
						MemStorage.block.remove(target.getName());
						sender.sendMessage(target.getDisplayName() + " \u00A7e" + MemStorage.locale.get("UNBLOCKED_PLAYER"));
						return true;
					} else {
						MemStorage.block.put(target.getName(), "");
						sender.sendMessage(target.getDisplayName() + " \u00A7e" + MemStorage.locale.get("BLOCKED_PLAYER"));
						return true;
					}
				}
			} else {
				sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_PERMISSION"));
				return true;
			}
		}

		if (command.getName().equalsIgnoreCase("ignore")) {
			if (Utils.isConsole(sender)) {
				plugin.getLogger().info(MemStorage.locale.get("NOT_AS_CONSOLE"));
				return true;
			}
			if (LiveChat.perms.has(sender, "livechat.ignore")) {
				if (args.length == 1) {
					target = sender.getServer().getPlayer(args[0]);
					if (target == null) {
						sender.sendMessage("\u00A7c" + MemStorage.locale.get("PLAYER_NOT_FOUND"));
						return true;
					} else if (target.getName() == playerName) {
						sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_WITH_YOURSELF"));
						return true;
					} else if (Utils.isIgnored(target, (Player)sender)) {
						MemStorage.ignore.remove(playerName + "." + target.getName());
						sender.sendMessage(target.getDisplayName() + " \u00A7e" + MemStorage.locale.get("UNIGNORED_PLAYER"));
						return true;
					} else {
						MemStorage.ignore.put(playerName + "." + target.getName(), "");
						sender.sendMessage(target.getDisplayName() + " \u00A7e" + MemStorage.locale.get("IGNORED_PLAYER"));
						return true;
					}
				} else if (args.length == 2 && args[0].equalsIgnoreCase("ch")) {
					if (args[1].equalsIgnoreCase("global")) {
						if (MemStorage.globalignore.containsKey(sender.getName())) {
							MemStorage.globalignore.remove(sender.getName());
							sender.sendMessage("\u00A7e" + MemStorage.locale.get("UNIGNORED_GLOBAL_CHANNEL"));
							return true;
						} else {
							MemStorage.globalignore.put(sender.getName(), "");
							sender.sendMessage("\u00A7e" + MemStorage.locale.get("IGNORED_GLOBAL_CHANNEL"));
							return true;
						}
					} else if (args[1].equalsIgnoreCase("private")) {
						if (MemStorage.privateignore.containsKey(sender.getName())) {
							MemStorage.privateignore.remove(sender.getName());
							sender.sendMessage("\u00A7e" + MemStorage.locale.get("UNIGNORED_PRIVATE_CHANNEL"));
							return true;
						} else {
							MemStorage.privateignore.put(sender.getName(), "");
							sender.sendMessage("\u00A7e" + MemStorage.locale.get("IGNORED_PRIVATE_CHANNEL"));
							return true;
						}
					} else if (args[1].equalsIgnoreCase("map")) {
						if (MemStorage.mapignore.containsKey(sender.getName())) {
							MemStorage.mapignore.remove(sender.getName());
							sender.sendMessage("\u00A7e" + MemStorage.locale.get("UNIGNORED_MAP_CHANNEL"));
							return true;
						} else {
							MemStorage.mapignore.put(sender.getName(), "");
							sender.sendMessage("\u00A7e" + MemStorage.locale.get("IGNORED_MAP_CHANNEL"));
							return true;
						}
					} else if (args[1].equalsIgnoreCase("local")) {
						if (MemStorage.localignore.containsKey(sender.getName())) {
							MemStorage.localignore.remove(sender.getName());
							sender.sendMessage("\u00A7e" + MemStorage.locale.get("UNIGNORED_LOCAL_CHANNEL"));
							return true;
						} else {
							MemStorage.localignore.put(sender.getName(), "");
							sender.sendMessage("\u00A7e" + MemStorage.locale.get("IGNORED_LOCAL_CHANNEL"));
							return true;
						}
					} else if (args[1].equalsIgnoreCase("emote")) {
						if (MemStorage.emoteignore.containsKey(sender.getName())) {
							MemStorage.emoteignore.remove(sender.getName());
							sender.sendMessage("\u00A7e" + MemStorage.locale.get("UNIGNORED_EMOTE_CHAT"));
							return true;
						} else {
							MemStorage.emoteignore.put(sender.getName(), "");
							sender.sendMessage("\u00A7e" + MemStorage.locale.get("IGNORED_EMOTE_CHAT"));
							return true;
						}
					} else {
						sender.sendMessage("\u00A7c" + MemStorage.locale.get("CHANNEL_NOT_EXIST"));
						return true;
					}
				} else {
					sender.sendMessage("\u00A7c" + MemStorage.locale.get("IGNORE_USAGE"));
					return true;
				}
			} else {
				sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_PERMISSION"));
				return true;
			}
		}

		if (command.getName().equalsIgnoreCase("livechat")) {
			if ((args.length == 1) && args[0].equalsIgnoreCase("reload")) {
				if (LiveChat.perms.has(sender, "livechat.admin") || Utils.isConsole(sender)) {
					plugin.reloadConfig();
					MemStorage.conf = plugin.getConfig();
					Config.load();
					sender.sendMessage("\u00A7e" + MemStorage.locale.get("RELOAD_CONFIG"));
					return true;
				} else {
					sender.sendMessage("\u00A7c" + MemStorage.locale.get("NOT_PERMISSION"));
					return true;
				}
			} else {
				sender.sendMessage("\u00A77-----------------------\u00A76Live\u00A79Chat\u00A77-----------------------");
				sender.sendMessage("\u00A77 * \u00A76A complete chat suite by \u00A7e" + plugin.getDescription().getAuthors() + "\u00A76.");
				sender.sendMessage("\u00A77 * \u00A76Version: \u00A7e" + plugin.getDescription().getVersion());
				sender.sendMessage("\u00A77-----------------------------------------------------");
				return true;
			}
		}
		return false;
	}
}
