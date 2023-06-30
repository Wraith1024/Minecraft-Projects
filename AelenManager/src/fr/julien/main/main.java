package fr.julien.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.julien.commands.Advert_Command;
import fr.julien.commands.Rank_Commands;
import fr.julien.commands.SetHomeCommand;
import fr.julien.commands.Utils_Commands;
import fr.julien.event.EventManager;

public class main extends JavaPlugin {
	
	private static main instance;
	
	// Config Manager //
	
	public void updatePlayerConfig(Player player, double x, double y, double z) {
        File playerFile = new File(getDataFolder(), player.getName() + ".yml");
        if (!playerFile.exists()) {
            // Player file does not exist, handle appropriately
            return;
        }

        YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
        playerConfig.set("player.warp.x", x);
        playerConfig.set("player.warp.y", y);
        playerConfig.set("player.warp.z", z);

        try {
            playerConfig.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public Location getPlayerHomeLocation(Player player) {
        File playerFile = new File(getDataFolder(), player.getName() + ".yml");
        if (!playerFile.exists()) {
            // Player file does not exist, handle appropriately
            return null;
        }

        YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
        double x = playerConfig.getDouble("player.warp.x");
        double y = playerConfig.getDouble("player.warp.y");
        double z = playerConfig.getDouble("player.warp.z");

        return new Location(player.getWorld(), x, y, z);
    }
	
	public String getPlayerRank(Player player) {
        File playerFile = new File(main.getInstance().getDataFolder(), player.getName() + ".yml");
        if (!playerFile.exists()) {
            // Player file does not exist, return default rank or handle appropriately
            return "§a[Joueur]";
        }

        YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
        return playerConfig.getString("player.rank", "default");
    }
	// Config Manager //
	
	@Override
    public void onEnable() {
		instance = this;
        getLogger().info("AelenManager plugin has been enabled!");
        getServer().getPluginManager().registerEvents(new EventManager(this), this);
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("home").setExecutor(new SetHomeCommand());
        getCommand("day").setExecutor(new Utils_Commands());
        getCommand("night").setExecutor(new Utils_Commands());
        getCommand("helper").setExecutor(new Utils_Commands());
        getCommand("advert").setExecutor(new Advert_Command());
        getCommand("setrank").setExecutor(new Rank_Commands(this));
        // Perform any initialization tasks or startup logic here
    }

	public static main getInstance() {
        return instance;
    }
	
	
    @Override
    public void onDisable() {
        getLogger().info("AelenManager plugin has been disabled!");
        instance = null;

        // Perform any cleanup tasks or shutdown logic here
    }
	
}
