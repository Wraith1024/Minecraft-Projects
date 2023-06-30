package fr.skyblock.main;

import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

	@Override
    public void onEnable() {
        getLogger().info("Skyblock Manager is enabled !");
    }
	
	@Override
    public void onDisable() {
		getLogger().info("Skyblock Manager is disabled !");
    }
	
}
