package network.olde.olde_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class KickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String example = "Example: /kick <player> <reason>";
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("oldecore.kick")) {
                if (strings.length == 1) {
                    // /kick <player>
                    String username = strings[0];
                    Player target = Bukkit.getServer().getPlayerExact(username);
                    if (target != null) {
                        target.kickPlayer(ChatColor.RED + "You have been kicked.");
                        player.sendMessage(ChatColor.GOLD + "You have kicked " + ChatColor.RESET + target.getDisplayName());
                    }
                    else {
                        player.sendMessage(ChatColor.GOLD + "Player not found.");
                    }
                }
                else if (strings.length >= 2) {
                    // /kick <player> <reason>
                    String username = strings[0];
                    Player target = Bukkit.getServer().getPlayerExact(username);
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int i = 1; i < strings.length; i++ ) {
                        String word = strings[i];
                        stringBuilder.append(word).append(" ");
                    }

                    if (target != null) {
                        target.kickPlayer(ChatColor.RED + "Kicked: " + stringBuilder);
                        player.sendMessage(ChatColor.GOLD + "You have kicked " + ChatColor.RESET + target.getDisplayName());
                    }
                    else {
                        player.sendMessage(ChatColor.GOLD + "Player not found.");
                    }
                }
                else {
                    player.sendMessage(ChatColor.GOLD + example);
                }
            }
            else {
                player.sendMessage(ChatColor.GOLD + "You don't have the required permissions.");
            }

        }
        // Console
        else {
            if (strings.length == 1) {
                String username = strings[0];
                Player target = Bukkit.getServer().getPlayerExact(username);
                if (target != null) {
                    target.kickPlayer(ChatColor.RED + "You have been kicked.");
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "You have kicked " + ChatColor.RESET + target.getDisplayName());
                }
                else {
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "Player not found.");
                }
            }
            else if (strings.length >= 2){
                String username = strings[0];
                Player target = Bukkit.getServer().getPlayerExact(username);
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 1; i < strings.length; i++ ) {
                    String word = strings[i];
                    stringBuilder.append(word).append(" ");
                }

                if (target != null) {
                    target.kickPlayer(ChatColor.RED + "Kicked: " + stringBuilder);
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "You have kicked " + ChatColor.RESET + target.getDisplayName());
                }
                else {
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "Player not found.");
                }
            }
            else {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + example);
            }

        }



        return true;
    }

}
