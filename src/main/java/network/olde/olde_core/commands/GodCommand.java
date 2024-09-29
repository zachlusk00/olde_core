package network.olde.olde_core.commands;

import network.olde.olde_core.OldeCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.logging.Logger;

import static org.bukkit.Bukkit.getLogger;

public class GodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;
            String username = player.getDisplayName();

            if (player.hasPermission("oldecore.god")) {
                if (strings.length == 0) {
                    if (!player.isInvulnerable()) {
                        player.setInvulnerable(true);
                        player.sendMessage("God Mode: " + ChatColor.GREEN + "Enabled");
                        getLogger().info(username + " has enabled god mode.");
                    } else {
                        player.setInvulnerable(false);
                        player.sendMessage("God Mode: " + ChatColor.RED + "Disabled");
                        getLogger().info(username + " has disabled god mode.");
                    }
                }
                else if (strings.length == 1) {
                    String targetUsername = strings[0];
                    Player target = Bukkit.getServer().getPlayerExact(targetUsername);
                    if (target != null) {
                        if (!target.isInvulnerable()) {
                            target.setInvulnerable(true);
                            target.sendMessage("God Mode: " + ChatColor.GREEN + "Enabled");
                            player.sendMessage(ChatColor.GREEN + "God Mode has been enabled for " + ChatColor.RESET + target.getDisplayName());
                            getLogger().info(username + " has enabled god mode for " + target.getDisplayName() + ".");
                        } else {
                            target.setInvulnerable(false);
                            target.sendMessage("God Mode: " + ChatColor.RED + "Disabled");
                            player.sendMessage(ChatColor.RED + "God Mode has been disabled for " + ChatColor.RESET + target.getDisplayName());
                            getLogger().info(username + " has disabled god mode for " + target.getDisplayName() + ".");
                        }
                    }
                    else {
                        player.sendMessage("Player not found.");
                    }
                }
                else {
                    player.sendMessage(ChatColor.GOLD + "Too many arguments.");
                    player.sendMessage("Example: /god or /god <target>");
                }
            }
            else {
                player.sendMessage(ChatColor.GOLD+ "You do not have the required permissions.");
            }
        }
        // Console
        else {
            if (strings.length == 1) {
                String playerUser = strings[0];
                Player player = Bukkit.getPlayerExact(playerUser);
                if (player != null) {
                    if (!player.isInvulnerable()) {
                        player.setInvulnerable(true);
                        player.sendMessage("God Mode: " + ChatColor.GREEN + "Enabled");
                        getLogger().info(player.getDisplayName() + " has enabled god mode.");
                    } else {
                        player.setInvulnerable(false);
                        player.sendMessage("God Mode: " + ChatColor.RED + "Disabled");
                        getLogger().info(player.getDisplayName() + " has disabled god mode.");
                    }
                }
                else {
                    getLogger().info(ChatColor.GOLD + "Player not found.");
                }
            }
            else{
                getLogger().info(ChatColor.GOLD + "Example: " + ChatColor.RESET + "/god <player>");
            }
        }

        return true;
    }
}
