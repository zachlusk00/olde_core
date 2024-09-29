package network.olde.olde_core.commands;

import network.olde.olde_core.OldeCore;
import network.olde.olde_core.handlers.BackpackHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class BackPackCommand implements CommandExecutor, Listener {
    private final OldeCore plugin;
    //public static Map<String, ItemStack[]> backpack = new HashMap<String, ItemStack[]>();

    public BackPackCommand(OldeCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        BackpackHandler backpackHandler = plugin.getBackpackHandler();
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Inventory inventory = Bukkit.createInventory(player, 27, player.getDisplayName() + "'s Backpack");

            if (backpackHandler.getBackpack().containsKey(player.getUniqueId().toString())) {
                inventory.setContents(backpackHandler.getBackpack().get(player.getUniqueId().toString()));
            }

            player.openInventory(inventory);
        }
        else {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "You must be a player to run this command.");
        }
        return true;
    }

    @EventHandler
    public void onGUIClose(InventoryCloseEvent event) {
        BackpackHandler backpackHandler = plugin.getBackpackHandler();
        if (event.getView().getTitle().contains(event.getPlayer().getName() + "'s Backpack")) {
            backpackHandler.getBackpack().put(event.getPlayer().getUniqueId().toString(), event.getInventory().getContents());
        backpackHandler.saveBackpack();
        }
    }
}
