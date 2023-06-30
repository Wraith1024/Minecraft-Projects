package fr.julien.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.julien.main.main;

public class Rank_Commands implements CommandExecutor {

    private main plugin;

    public Rank_Commands(main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setrank")) {
            if (args.length == 2) {
                String playerName = args[0];
                String newRank = args[1];

                Player targetPlayer = plugin.getServer().getPlayerExact(playerName);
                if (targetPlayer != null) {
                    setPlayerRank(targetPlayer, newRank);
                    sender.sendMessage(ChatColor.GREEN + "Rank set successfully for player: " + targetPlayer.getName());
                } else {
                    sender.sendMessage(ChatColor.RED + "Player not found or offline: " + playerName);
                }
                return true;
            }
        }
        return true;
    }

    private void setPlayerRank(Player player, String rank) {
        String playerName = player.getName();

        // Create or load the player's data file
        File playerDataFile = new File(plugin.getDataFolder(), playerName + ".yml");
        FileConfiguration playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);

        // Set the rank in the player's data file
        playerDataConfig.set("player.rank", rank.replace("&", "§"));

        // Save the player's data file
        try {
            playerDataConfig.save(playerDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
