package fr.julien.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.julien.main.main;

public class SetHomeCommand implements CommandExecutor {
	
	public static void send_location_msg(Player player, String title, String subtitle) {
        int fadeIn = 20;  // Number of ticks for the title to fade in
        int stay = 80;    // Number of ticks for the title to stay on the screen
        int fadeOut = 20; // Number of ticks for the title to fade out

        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
            sender.sendMessage("§c Erreur : cette commande ne doit être éxecuté que par un joueur ! ");
            return true;
        }
		
		if (command.getName().equalsIgnoreCase("sethome")) {
			Player player = (Player) sender;
	        double x = player.getLocation().getX();
	        double y = player.getLocation().getY();
	        double z = player.getLocation().getZ();

	        main.getInstance().updatePlayerConfig(player, x, y, z);
	        send_location_msg(player, "§e Position de la Maison enrengistré", "§c/home pour vous téléporter la bas");
	        return true;
		}
		
		if (command.getName().equalsIgnoreCase("home")) {
			Player player = (Player) sender;
	        Location homeLocation = main.getInstance().getPlayerHomeLocation(player);

	        if (homeLocation != null) {
	            player.teleport(homeLocation);
	            send_location_msg(player, "§c Téléporté", "§ePosition de maison atteinte");
	        } else {
	        	send_location_msg(player, "§c Erreur", "§cPosition de maison non définie");
	        	player.sendMessage("Erreur : utilisez /sethome");
		        
	        }

	        return true;
		}

        

        return true;
    }

}
