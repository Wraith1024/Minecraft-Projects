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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;
        Inventory inventory = plugin.getServer().createInventory(null, 27, "Menu de l'ile");

        // Fill the inventory with some items (just as an example)
        
        ItemStack teleportItem = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta teleportItemMeta = teleportItem.getItemMeta();
        teleportItemMeta.setDisplayName("Go to Home");
        teleportItem.setItemMeta(teleportItemMeta);
        inventory.setItem(13, teleportItem);

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

            if (clickedItem != null && clickedItem.getType() == Material.GRASS_BLOCK) {
                ItemMeta itemMeta = clickedItem.getItemMeta();

                if (itemMeta != null && itemMeta.hasDisplayName() && itemMeta.getDisplayName().equalsIgnoreCase("Go to Home")) {
                    player.sendMessage(ChatColor.GREEN + "Vous avez été téléporté sur votre Île");
                    // Execute teleportation logic here
                }
            }
        }
    }



}