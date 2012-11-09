package com.gmail.zant95.LiveChat;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class MVCore {
	private LiveChat plugin;
	private MVWorldManager MVWorldManager;

	public MVCore(LiveChat instance) {
		this.plugin = instance;
		Plugin MV = this.plugin.getServer().getPluginManager().getPlugin("Multiverse-Core");
		if (MV != null) {
			MultiverseCore multiverse = (MultiverseCore)MV;
			this.MVWorldManager = multiverse.getMVWorldManager();
		}	}

	public String getAlias(World world) {
		if ((this.MVWorldManager != null) && (this.MVWorldManager.isMVWorld(world))) {
			return this.MVWorldManager.getMVWorld(world).getColoredWorldString();
		}
		return world.getName();
	}

	public void clean() {
		this.MVWorldManager = null;
	}
}