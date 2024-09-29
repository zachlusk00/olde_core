package network.olde.olde_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;

import java.io.IOException;

import static org.bukkit.Bukkit.getLogger;

public class SpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        String exampleMsg = "Example: /speed <speed> <player>";

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("oldecore.speed")) {

                if (strings.length == 1) {
                    try {
                        float speed = Float.parseFloat(strings[0]);

                        if ((speed >= -1) && (speed <= 1)) {
                            player.setWalkSpeed(speed);
                        } else {
                            player.sendMessage(ChatColor.GOLD + "Speed Values are between -1 and 1.");
                        }
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.GOLD + exampleMsg);
                    }

                }
                else if (strings.length == 2) {
                    String username = strings[1];
                    Player target = Bukkit.getServer().getPlayerExact(username);
                    try {
                        if (target != null) {

                            float speed = Float.parseFloat(strings[0]);
                            if ((speed >= -1) && (speed <= 1)) {
                                target.setWalkSpeed(speed);
                                player.sendMessage(ChatColor.YELLOW + "You set " + ChatColor.RESET + target.getDisplayName() + ChatColor.YELLOW + " speed to " + ChatColor.RESET + speed);
                                target.sendMessage(ChatColor.YELLOW + "Walk speed has been set to " + ChatColor.RESET + speed);
                            } else {
                                player.sendMessage(ChatColor.GOLD + "Speed Values are between -1 and 1.");
                            }
                        } else {
                            player.sendMessage(ChatColor.GOLD + "No player found.");
                        }
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.GOLD + exampleMsg);
                    }
                }
                else {
                    player.sendMessage(ChatColor.GOLD + exampleMsg);
                }
            } else {
                player.sendMessage(ChatColor.GOLD + "You don't have the required permissions.");
            }
        }
        // Console
        else {
            if (strings.length == 2) {
                String username = strings[1];
                Player target = Bukkit.getServer().getPlayerExact(username);
                if (target != null) {
                    try {
                        float speed = Float.parseFloat(strings[0]);
                        if ((speed >= -1) && (speed <= 1)) {
                            target.setWalkSpeed(speed);
                            getLogger().info(ChatColor.YELLOW + "You set " + ChatColor.RESET + target.getDisplayName() + ChatColor.YELLOW + " speed to " + ChatColor.RESET + speed);
                            target.sendMessage(ChatColor.YELLOW + "Walk speed has been set to " + ChatColor.RESET + speed);
                        }
                        else {
                            getLogger().info(ChatColor.GOLD + "Speed Values are between -1 and 1.");
                        }
                    } catch (NumberFormatException e) {
                        getLogger().info(ChatColor.GOLD + exampleMsg);
                    }

                }
                else {
                    getLogger().info(ChatColor.GOLD + "No player found.");
                }
            }
            else {
                getLogger().info(ChatColor.GOLD + exampleMsg);
            }
        }

        return true;
    }
}
