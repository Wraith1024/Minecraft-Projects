package fr.skyblock.main;

import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

	@Override
    public void onEnable() {
        getLogger().info("Skyblock Manager is enabled !");
        getCommand("is").setExecutor(new InventoryCommand(this));
        
        getServer().getPluginManager().registerEvents(new InventoryCommand(this), this);
    }
	
	@Override
    public void onDisable() {
		getLogger().info("Skyblock Manager is disabled !");
    }
	
}
