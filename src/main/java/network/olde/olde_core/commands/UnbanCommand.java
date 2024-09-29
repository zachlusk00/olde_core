package network.olde.olde_core.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.Date;

public class UnbanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String example = "Example: /unban <player>";
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("oldecore.unban")) {
                if (strings.length == 1) {
                    String targetUsername = strings[0];
                    BanList<?> banList = Bukkit.getServer().getBanList(BanList.Type.PROFILE);
                    BanList<?> ipbanList = Bukkit.getServer().getBanList(BanList.Type.IP);

                    if (banList.getBanEntry(targetUsername) != null) {
                        banList.pardon(targetUsername);
                        player.sendMessage(targetUsername + ChatColor.YELLOW + " has been unbanned.");
                    }
                    else if(ipbanList.getBanEntry(targetUsername) != null) {
                        Bukkit.getServer().unbanIP(targetUsername);
                        player.sendMessage(targetUsername + ChatColor.YELLOW + " has been ip unbanned.");
                    }
                    else {
                        player.sendMessage(ChatColor.GOLD + "Player not found.");
                    }
                }
                else {
                    player.sendMessage(ChatColor.GOLD + example);
                }
            }
        }
        // Console
        else {
            if (strings.length == 1) {
                String targetUsername = strings[0];
                BanList<?> banList = Bukkit.getServer().getBanList(BanList.Type.PROFILE);
                BanList<?> ipbanList = Bukkit.getServer().getBanList(BanList.Type.IP);
                //String ip = getBannedIP(targetUsername);

                if (banList.getBanEntry(targetUsername) != null) {
                    banList.pardon(targetUsername);
                    Bukkit.getServer().getConsoleSender().sendMessage(targetUsername + ChatColor.YELLOW + " has been unbanned.");
                }
                else if(ipbanList.getBanEntry(targetUsername) != null) {
                    System.out.println("IP UNbanned: " + targetUsername);
                    Bukkit.getServer().unbanIP(targetUsername);
                    Bukkit.getServer().getConsoleSender().sendMessage(targetUsername + ChatColor.YELLOW + " has been ip unbanned.");
                }
                else {
                    Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "Player not found or is not banned.");
                }
            }
            else {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + example);
            }
        }
        return true;
    }

    public String getBannedIP(String targetUsername) {
        BanList<?> ipbanList = Bukkit.getServer().getBanList(BanList.Type.IP);
        for (BanEntry<?> entry : ipbanList.getEntries()) {
            if (entry.getSource().equals(targetUsername)) {
                return entry.getBanTarget().toString();
            }
        }
        return null;
    }
}
