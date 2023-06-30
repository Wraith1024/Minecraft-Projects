package fr.julien.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Advert_Command implements CommandExecutor {

	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("advert")) {
            if (args.length == 3) {
                String username = args[0];
                String title = ChatColor.translateAlternateColorCodes('&', args[1]);
                String subtitle = ChatColor.translateAlternateColorCodes('&', args[2]);

                Player targetPlayer = Bukkit.getPlayerExact(username);
                if (targetPlayer != null && targetPlayer.isOnline()) {
                    sendTitle(targetPlayer, title, subtitle);
                    sender.sendMessage(ChatColor.GREEN + "Message envoyé a :  " + targetPlayer.getName());
                } else {
                    sender.sendMessage(ChatColor.RED + "Joueur introuvable : " + username);
                }
                return true;
            }
        }
        return false;
    }

    private void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(title, subtitle, 10, 70, 20);
    }

}
