package fr.julien.event;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.julien.main.main;

public class EventManager implements Listener {

	private final main plugin;

    public EventManager(main plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String rank = main.getInstance().getPlayerRank(player);

        String formattedMessage = rank + " " + player.getName() + " : §f" + event.getMessage();
        event.setFormat(formattedMessage);
    }
	
	public static void send_welcome_message(Player player) {
        String title = "§eBonjour " + player.getName();
        String subtitle = "§cJe te souhaite un bon jeu :D";
        int fadeIn = 20;  // Number of ticks for the title to fade in
        int stay = 150;    // Number of ticks for the title to stay on the screen
        int fadeOut = 20; // Number of ticks for the title to fade out

        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }
	
	private void createPlayerConfig(Player player, File playerFile) {
        YamlConfiguration playerConfig = new YamlConfiguration();

        // Set the player's details in the configuration
        playerConfig.set("player.name", player.getName());
        playerConfig.set("player.rank", "§a[Joueur]");
        playerConfig.set("player.warp.x", 0);
        playerConfig.set("player.warp.y", 0);
        playerConfig.set("player.warp.z", 0);

        // Save the configuration to the file
        try {
            playerConfig.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
     // Check if the player's username.yml already exists
        File playerFile = new File(plugin.getDataFolder(), player.getName() + ".yml");
        if (!playerFile.exists()) {
            createPlayerConfig(player, playerFile);
            event.setJoinMessage("§a[+++]§c " + player.getName() + "§a est nouveau sur notre serveur !");
            return;
        }
        send_welcome_message(player);
        event.setJoinMessage("§a[+] " + main.getInstance().getPlayerRank(player) + " " + player.getName());
        
    }
	
	@EventHandler
    public void onSignChange(SignChangeEvent event) {
        // Parse color codes on the sign lines
        for (int i = 0; i < event.getLines().length; i++) {
            String line = ChatColor.translateAlternateColorCodes('&', event.getLine(i));
            event.setLine(i, line);
        }
    }

	@EventHandler
    public void onEntityDeath(PlayerDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            // Retrieve the player's rank
            String playerRank = main.getInstance().getPlayerRank(player);

            // Modify the death message
            String deathMessage = "§c[MORT] " + playerRank + " " + player.getName() + " is dead.";

            // Set the modified death message
            event.setDeathMessage(deathMessage);
        }
    }
	
}
