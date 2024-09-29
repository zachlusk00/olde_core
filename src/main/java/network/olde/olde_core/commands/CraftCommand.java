package network.olde.olde_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class CraftCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (!player.hasPermission("oldecore.craft")) {
                player.sendMessage(ChatColor.GOLD + "You don't have the required permissions.");
            }
            else {
                if (strings.length > 0) {
                    player.sendMessage(ChatColor.GOLD + "Example: /craft");
                } else {
                    try {
                        player.openWorkbench(player.getLocation(), true);
                    } catch (Exception e) {
                        player.sendMessage("Issue with craft command.");
                        getLogger().info(e.toString());
                    }
                }
            }
        }
        //Console
        else {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "You must be a player to run this command.");
        }


        return true;
    }
}
