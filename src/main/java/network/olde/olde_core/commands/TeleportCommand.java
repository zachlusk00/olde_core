package network.olde.olde_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

import static org.bukkit.Bukkit.getLogger;

// TODO: Add Chatcolor to the messages.

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("oldecore.teleport")){
                // Command
                if (strings.length == 0) {
                    player.sendMessage(ChatColor.GOLD + "Example: /teleport <target> <target>");
                }
                // Args 1 -Player/Target
                else if (strings.length == 1) {
                    String targetUsername = strings[0];
                    Player target = Bukkit.getServer().getPlayerExact(targetUsername);
                    if (target == null) {
                        player.sendMessage(ChatColor.GOLD + "Player cannot be found.");
                    }
                    else {
                        Location targetLocation = target.getLocation();

                        if (target.getDisplayName().equals(player.getDisplayName())){
                            player.sendMessage(ChatColor.GOLD + "You cannot teleport to yourself.");
                        }
                        else {
                            player.sendMessage(ChatColor.YELLOW + "You teleported to " + ChatColor.RESET + target.getDisplayName() + ChatColor.YELLOW + ".");
                            player.teleport(targetLocation);
                            target.sendMessage(player.getDisplayName() + ChatColor.GOLD + " has teleported to you.");
                        }
                    }
                }
                // Arg 2 -Target
                else if (strings.length == 2) {
                    String firstTargetUsername = strings[0];
                    String secondTargetUsername = strings[1];
                    Player firstTarget = Bukkit.getServer().getPlayerExact(firstTargetUsername);
                    Player secondTarget = Bukkit.getServer().getPlayerExact(secondTargetUsername);

                    if (firstTarget == null) {
                        player.sendMessage(ChatColor.GOLD + firstTargetUsername + " cannot be found.");
                    }
                    else if (secondTarget == null) {
                        player.sendMessage(ChatColor.GOLD + secondTargetUsername + " cannot be found.");
                    }
                    else {
                        Location targetLocation = secondTarget.getLocation();

                        if (firstTarget.getDisplayName().equals(player.getDisplayName())) {
                            player.sendMessage(ChatColor.YELLOW + "You teleported to " + ChatColor.RESET + secondTarget.getDisplayName() + ChatColor.YELLOW + ".");
                            player.teleport(targetLocation);
                            secondTarget.sendMessage(player.getDisplayName() + ChatColor.GOLD + " has teleported to you.");
                        }
                        else if (secondTarget.getDisplayName().equals(player.getDisplayName())) {
                            firstTarget.teleport(player.getLocation());
                            secondTarget.sendMessage(firstTarget.getDisplayName() + ChatColor.GOLD + " has teleported to you.");
                            firstTarget.sendMessage(secondTarget.getDisplayName() + ChatColor.GOLD + " has teleported you to them.");
                        }
                        else {
                            player.sendMessage(ChatColor.YELLOW + "You teleported " + ChatColor.RESET + firstTarget.getDisplayName() + ChatColor.YELLOW + " to " + ChatColor.RESET + secondTarget.getDisplayName() + ChatColor.YELLOW + ".");
                            firstTarget.teleport(targetLocation);
                            secondTarget.sendMessage(firstTarget.getDisplayName() + ChatColor.GOLD + " has teleported to you.");
                        }
                    }
                }
                // Too many arguments
                else {
                    player.sendMessage(ChatColor.GOLD + "Example: " + ChatColor.RESET + "/teleport <target> <target>");
                }
            }
            else {
                player.sendMessage(ChatColor.GOLD + "You don't have the required permissions.");
            }
        }
        // Console
        else {
            if (strings.length == 0) {
                getLogger().info(ChatColor.GOLD + "Example: " + ChatColor.RESET + "/teleport <target> <target>");
            }
            else if (strings.length == 2) {
                String firstTargetUsername = strings[0];
                String secondTargetUsername = strings[1];
                Player firstTarget = Bukkit.getPlayerExact(firstTargetUsername);
                Player secondTarget = Bukkit.getPlayerExact(secondTargetUsername);
                if (firstTarget == null) {
                    getLogger().info(ChatColor.GOLD + firstTargetUsername + " cannot be found.");
                }
                else if (secondTarget == null) {
                    getLogger().info(ChatColor.GOLD + secondTargetUsername + " cannot be found.");
                }
                else {
                    Location targetLocation = secondTarget.getLocation();
                    firstTarget.teleport(targetLocation);
                    firstTarget.sendMessage(ChatColor.YELLOW + "You teleported to " + ChatColor.RESET + secondTarget.getDisplayName() + ChatColor.YELLOW + ".");
                    secondTarget.sendMessage(firstTarget.getDisplayName() + ChatColor.GOLD + " has teleported to you.");
                    getLogger().info(ChatColor.YELLOW + "You teleported " + ChatColor.RESET + firstTarget.getDisplayName() + ChatColor.YELLOW + " to " + ChatColor.RESET + secondTarget.getDisplayName() + ChatColor.YELLOW + ".");
                }
            }
            else{
                getLogger().info(ChatColor.GOLD + "Example: " + ChatColor.RESET + "/teleport <target> <target>");
            }
        }


        return true;
    }
}
