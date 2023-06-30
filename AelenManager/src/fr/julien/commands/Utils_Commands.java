package fr.julien.commands;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils_Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
            sender.sendMessage("§c Erreur : cette commande ne doit être éxecuté que par un joueur ! ");
            return true;
        }
		
		if (command.getName().equalsIgnoreCase("day")) {
			World world = sender.getServer().getWorlds().get(0);
            world.setTime(1000);
		}

		if (command.getName().equalsIgnoreCase("night")) {
			World world = sender.getServer().getWorlds().get(0);
            world.setTime(13000);
		}
		
		if (command.getName().equalsIgnoreCase("helper")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("sign")) {
					sender.sendMessage(ChatColor.GREEN + "Comment utiliser les couleurs sur un panneau :");
	                sender.sendMessage(ChatColor.YELLOW + "Placez un panneau et écrivez votre texte dessus.");
	                sender.sendMessage(ChatColor.YELLOW + "Utilisez le caractère esperluette (&) suivi d'un code couleur pour appliquer la couleur.");
	                sender.sendMessage(ChatColor.YELLOW + "Par exemple, &cBonjour affichera le mot 'Bonjour' en rouge.");
	                sender.sendMessage(ChatColor.YELLOW + "Voici quelques codes couleurs que vous pouvez utiliser :");
	                sender.sendMessage(ChatColor.RED + "&cRouge");
	                sender.sendMessage(ChatColor.GREEN + "&aVert");
	                sender.sendMessage(ChatColor.BLUE + "&9Bleu");
	                sender.sendMessage(ChatColor.YELLOW + "&eJaune");
	                sender.sendMessage(ChatColor.WHITE + "&fBlanc");
	                sender.sendMessage(ChatColor.GRAY + "&7Gris");
	                sender.sendMessage(ChatColor.GOLD + "&6Or");
	                sender.sendMessage(ChatColor.BOLD + "&lGras");
	                sender.sendMessage(ChatColor.ITALIC + "&oItalique");
	                sender.sendMessage(ChatColor.UNDERLINE + "&nSouligné");
	                sender.sendMessage(ChatColor.STRIKETHROUGH + "&mBarré");
	                return true;
				}
			} else {
				sender.sendMessage("Afin d'utiliser cette commande : ");
				sender.sendMessage("/helper sign -> Comment utiliser des couleurs sur des panneaux");
			}
           }
		
		return true;
	}

}
