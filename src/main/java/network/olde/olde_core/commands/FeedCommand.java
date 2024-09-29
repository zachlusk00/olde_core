package network.olde.olde_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;


public class FeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //player
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("oldecore.feed")) {
                if (strings.length == 0) {
                    if (!(player.getFoodLevel() == 20)) {
                        player.setFoodLevel(20);
                        player.sendMessage(ChatColor.YELLOW + "You have been fed.");
                    }
                    else {
                        player.sendMessage(ChatColor.YELLOW + "You are full.");
                    }
                }
                else if (strings.length == 1) {
                    String targetUsername = strings[0];
                    Player target = Bukkit.getServer().getPlayerExact(targetUsername);
                    if (target != null) {
                        if (!(target.getFoodLevel() == 20)) {
                            target.setFoodLevel(20);
                            target.sendMessage(ChatColor.YELLOW + "You have been fed.");
                            player.sendMessage(ChatColor.YELLOW + "You have fed " + target.getDisplayName() + ".");
                        }
                        else {
                            player.sendMessage(ChatColor.YELLOW + target.getDisplayName() + " is full.");
                        }
                    }
                    else {
                        player.sendMessage(ChatColor.GOLD + "Player not found.");
                    }
                }
                else {
                    player.sendMessage(ChatColor.GOLD + "Example: /feed <target>");
                }
            }
            else {
                player.sendMessage(ChatColor.GOLD + "You don't have the required permissions.");
            }
        // console
        }
        else {
            if (strings.length == 0) {
                getLogger().info(ChatColor.GOLD + "Example: /feed <target>");
            }
            else if (strings.length == 1) {
                String targetUsername = strings[0];
                Player target = Bukkit.getServer().getPlayerExact(targetUsername);
                if (target != null) {
                    target.sendMessage(ChatColor.YELLOW + "You have been fed.");
                    getLogger().info(ChatColor.YELLOW+ "You have fed " + target.getDisplayName() + ".");
                }
                else {
                    getLogger().info(ChatColor.GOLD + "Player not found.");
                }
            }
            else {
                getLogger().info(ChatColor.GOLD + "Example: /feed <target>");
            }
        }
        return true;
    }
}
