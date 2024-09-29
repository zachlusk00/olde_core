package network.olde.olde_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            // Player
            Player player = (Player) commandSender;
            double playerHealth = player.getHealth();

            if (player.hasPermission("oldecore.heal")) { // Permission
                if (strings.length == 0) { // command
                    if (!(playerHealth == 20)) {
                        player.setHealth(20);
                        player.sendMessage(ChatColor.YELLOW + "You have been healed");
                    }
                    else {
                        player.sendMessage(ChatColor.YELLOW + "You don't need to be healed.");
                    }
                }
                else if (strings.length == 1){ // arg 1 -Player
                    String targetUsername = strings[0];
                    Player target = Bukkit.getServer().getPlayerExact(targetUsername);

                    if (target != null) {
                        if (!(target.getHealth() == 10)) {
                            target.setHealth(20);
                            target.sendMessage(ChatColor.YELLOW + "You have been healed.");
                            player.sendMessage(ChatColor.YELLOW + "You have healed " + ChatColor.RESET + target.getDisplayName() + ChatColor.YELLOW + ".");
                        }
                        else {
                            player.sendMessage(target.getDisplayName() + ChatColor.YELLOW + " does not need to be healed.");
                        }
                    }
                    else {
                        player.sendMessage(ChatColor.GOLD + "Player not found.");
                    }
                }
                // too many args
                else {
                    player.sendMessage(ChatColor.GOLD + "Example" + ChatColor.RESET + ": /heal or /heal <target>");
                }
            }
            else {
                player.sendMessage(ChatColor.GOLD+ "You do not have the required permissions.");
            }
        }
        //console
        else {
            if (strings.length == 0) {
                getLogger().info(ChatColor.GOLD + "Example: " + ChatColor.RESET + "/heal <player>");
            }
            else if (strings.length == 1) {
                String playerUser = strings[0];
                Player player = Bukkit.getPlayerExact(playerUser);
                if (player != null) {
                    if (player.getHealth() == 10) {
                        getLogger().info(player.getDisplayName() + ChatColor.GOLD + " does not need to be healed.");
                    }
                    else {
                        player.setHealth(10);
                        player.sendMessage(ChatColor.YELLOW + "You have been healed.");
                        getLogger().info(ChatColor.YELLOW + "You have healed " + ChatColor.RESET + player.getDisplayName() + ChatColor.YELLOW + ".");
                    }
                }
                else {
                    getLogger().info(ChatColor.GOLD + "Player not found.");
                }
            }
            else{
                getLogger().info(ChatColor.GOLD + "Example: " + ChatColor.RESET + "/heal <player>");
            }
        }

        return true;
    }
}
