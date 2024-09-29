package network.olde.olde_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class KillCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        //player
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("oldecore.kill")) { // permission
                if (strings.length == 0) { // command
                    player.sendMessage(ChatColor.YELLOW + "You have killed yourself.");
                    player.setHealth(0);
                }
                else if (strings.length == 1) { // arg 1 -Player
                    String targetUsername = strings[0];
                    Player target = Bukkit.getServer().getPlayerExact(targetUsername);

                    if (target == null) {
                        player.sendMessage(ChatColor.GOLD + "No player specified.");
                    }
                    else if (target.isOp()) {
                        player.sendMessage(ChatColor.GOLD + "You cannot kill this player.");
                    }
                    else {
                        target.sendMessage(ChatColor.YELLOW + "You have been killed by " + ChatColor.RESET + player.getDisplayName());
                        target.setHealth(0);
                        player.sendMessage(ChatColor.YELLOW + "You killed " + ChatColor.RESET + target.getDisplayName());
                    }
                }
                // too many arguments
                else {
                    player.sendMessage(ChatColor.GOLD + "Example: " + ChatColor.RESET + "/kill <player>");
                }
            }
            else {
                player.sendMessage(ChatColor.GOLD + "You don't the required permissions.");
            }
        }
        // console
        else {
            if (strings.length == 0) {
                getLogger().info(ChatColor.GOLD + "Example: " + ChatColor.RESET + "/kill <player>");
            }
            else if (strings.length == 1) {
                String playerUser = strings[0];
                Player player = Bukkit.getPlayerExact(playerUser);
                if (player != null) {
                    player.sendMessage(ChatColor.YELLOW + "You have been killed by " + ChatColor.RESET + "Console" + ChatColor.YELLOW + ".");
                    player.setHealth(0);
                    getLogger().info(ChatColor.YELLOW + "You killed " + ChatColor.RESET + player.getDisplayName() + ChatColor.YELLOW + ".");
                }
                else {
                    getLogger().info(ChatColor.GOLD + "Player not found.");
                }
            }
            else{
                getLogger().info(ChatColor.GOLD + "Example: " + ChatColor.RESET + "/kill <player>");
            }
        }

        return true;
    }
}
