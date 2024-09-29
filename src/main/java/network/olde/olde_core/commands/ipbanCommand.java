package network.olde.olde_core.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ipbanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String example = "Example: /ipban <player> <reason>";
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("oldecore.ipban")) {
                if (strings.length == 1) {
                    // /ipban <player>
                    String username = strings[0];
                    Player target = Bukkit.getServer().getPlayerExact(username);

                    if (target != null) {
                        target.kickPlayer(ChatColor.RED + "You have been IP banned.");
                        player.sendMessage( target.getDisplayName() + ChatColor.RED + "has been IP banned ");
                        String userIpAddress = target.getAddress().getAddress().getHostAddress();
                        BanList<?> banList = Bukkit.getServer().getBanList(BanList.Type.IP);
                        banList.addBan(userIpAddress, "Banned by " + player.getDisplayName(), null, player.getDisplayName());
                    }
                    else {
                        player.sendMessage(ChatColor.GOLD + "Player not found.");
                    }
                }
                else if (strings.length >= 2) {
                    // /ipban <player> <reason>
                    String username = strings[0];
                    Player target = Bukkit.getServer().getPlayerExact(username);
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int i = 1; i < strings.length; i++ ) {
                        String word = strings[i];
                        stringBuilder.append(word).append(" ");
                    }

                    if (target != null) {
                        target.kickPlayer(ChatColor.RED + "IP Banned: " + stringBuilder);
                        player.sendMessage(target.getDisplayName() + ChatColor.RED + "has been IP banned ");
                        String userIpAddress = target.getAddress().getAddress().getHostAddress();
                        BanList<?> banList = Bukkit.getServer().getBanList(BanList.Type.IP);
                        banList.addBan(userIpAddress,ChatColor.RED + stringBuilder.toString(), null, player.getDisplayName());
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
            Bukkit.getServer().getConsoleSender().sendMessage("not available");
            if (strings.length == 1) {
                // /ban <player>
                String username = strings[0];
                Player target = Bukkit.getServer().getPlayerExact(username);
                if (target != null) {
                    target.kickPlayer(ChatColor.RED + "You have been banned.");
                    Bukkit.getServer().getConsoleSender().sendMessage(target.getDisplayName() + ChatColor.RED + " has been banned ");
                    String userIpAddress = target.getAddress().getAddress().getHostAddress();
                    BanList<?> banList = Bukkit.getServer().getBanList(BanList.Type.IP);
                    banList.addBan(userIpAddress, "Banned by console.", null, "Console");
                }
                else {
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "Player not found.");
                }
            }
            else if (strings.length >= 2) {
                // /ban <player> <reason>
                String username = strings[0];
                Player target = Bukkit.getServer().getPlayerExact(username);
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 1; i < strings.length; i++ ) {
                    String word = strings[i];
                    stringBuilder.append(word).append(" ");
                }

                if (target != null) {
                    target.kickPlayer(ChatColor.RED + "Banned: " + stringBuilder);
                    Bukkit.getServer().getConsoleSender().sendMessage(target.getDisplayName() + ChatColor.RED + "has been banned ");
                    String userIpAddress = target.getAddress().getAddress().getHostAddress();
                    BanList<?> banList = Bukkit.getServer().getBanList(BanList.Type.IP);
                    banList.addBan(userIpAddress,ChatColor.RED + stringBuilder.toString(), null, "Console");
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
