package fr.skyblock.main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryCommand implements CommandExecutor, Listener {
    private final JavaPlugin plugin;

    public InventoryCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack new_item(String Name, Material mat) {
    	ItemStack item = new ItemStack(mat);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Name);
        item.setItemMeta(itemMeta);
		return item;	
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;
        Inventory inventory = plugin.getServer().createInventory(null, 27, "Menu de l'ile");

        inventory.setItem(11, new_item("Réglages de L'ile", Material.NETHER_STAR));
        inventory.setItem(13, new_item("Votre profil", Material.PLAYER_HEAD));
        inventory.setItem(15, new_item("Métiers | Objectifs", Material.EXPERIENCE_BOTTLE));
        inventory.setItem(26, new_item("Fermer", Material.ARROW));

        // Open the inventory for the player
        player.openInventory(inventory);

        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Menu de l'ile")) {
            event.setCancelled(true); // Cancel the event to prevent item pickup
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.getType() == Material.NETHER_STAR) {
                ItemMeta itemMeta = clickedItem.getItemMeta();

                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().equalsIgnoreCase("Réglages de L'ile")) {
                    player.sendMessage(ChatColor.GREEN + "Réglages");
                }
            }
            
            if (clickedItem != null && clickedItem.getType() == Material.PLAYER_HEAD) {
                ItemMeta itemMeta = clickedItem.getItemMeta();

                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().equalsIgnoreCase("Votre profil")) {
                    player.sendMessage(ChatColor.GREEN + "Profil");
                }
            }
            
            if (clickedItem != null && clickedItem.getType() == Material.EXPERIENCE_BOTTLE) {
                ItemMeta itemMeta = clickedItem.getItemMeta();

                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().equalsIgnoreCase("Métiers | Objectifs")) {
                    player.sendMessage(ChatColor.GREEN + "Metier | Objectifs");
                }
            }
            
            if (clickedItem != null && clickedItem.getType() == Material.ARROW) {
                ItemMeta itemMeta = clickedItem.getItemMeta();

                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().equalsIgnoreCase("Fermer")) {
                    player.closeInventory();
                }
            }
        }
    }



}