package network.olde.olde_core.commands;

import network.olde.olde_core.OldeCore;
import network.olde.olde_core.listeners.JoinLeaveListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class HideCommand implements CommandExecutor {

    private final OldeCore plugin;

    public HideCommand(OldeCore plugin) {
        this.plugin = plugin;

    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("oldecore.hide")) {
                if (strings.length == 0) {
                    if (plugin.getPlayersVanished().contains(player.getUniqueId())) {
                        plugin.getPlayersVanished().remove(player.getUniqueId());
                        for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                            onlinePlayer.showPlayer(plugin, player);
                        }
                        player.sendMessage(ChatColor.YELLOW + "You are now visible.");
                    }
                    else {
                        plugin.getPlayersVanished().add(player.getUniqueId());
                        for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                            if (!onlinePlayer.hasPermission("oldecore.hide.see")) {
                                onlinePlayer.hidePlayer(plugin, player);
                            }
                        }
                        player.sendMessage(ChatColor.YELLOW + "You are now invisible.");
                    }
                }
                else if (strings.length == 1) {
                    String username = strings[0];
                    Player target = Bukkit.getServer().getPlayerExact(username);
                    if (target != null) {
                        if (plugin.getPlayersVanished().contains(target.getUniqueId())) {
                            plugin.getPlayersVanished().remove(target.getUniqueId());
                            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                                onlinePlayer.showPlayer(plugin, target);
                            }
                            target.sendMessage(ChatColor.YELLOW + "You are now visible.");
                            player.sendMessage(target.getDisplayName() + ChatColor.YELLOW + " is now visible.");
                        }
                        else {
                            plugin.getPlayersVanished().add(target.getUniqueId());
                            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                                if (!onlinePlayer.hasPermission("oldecore.hide.see")) {
                                    onlinePlayer.hidePlayer(plugin, target);
                                }
                            }
                            target.sendMessage(ChatColor.YELLOW + "You are now invisible.");
                            player.sendMessage(target.getDisplayName() + ChatColor.YELLOW + " is now invisible.");
                        }
                    }
                    else {
                        player.sendMessage(ChatColor.GOLD + "Player not found.");
                    }
                }
                else {
                    player.sendMessage(ChatColor.GOLD + "Example: /hide");
                }

            }
            else {
                player.sendMessage(ChatColor.GOLD + "You don't have the required permissions");
            }
        }
        else {
            getLogger().info(ChatColor.GOLD + "You must be a player to run this command.");
            if (strings.length == 1) {
                String username = strings[0];
                Player target = Bukkit.getServer().getPlayerExact(username);
                if (target != null) {
                    if (plugin.getPlayersVanished().contains(target.getUniqueId())) {
                        plugin.getPlayersVanished().remove(target.getUniqueId());
                        for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                            onlinePlayer.showPlayer(plugin, target);
                        }
                        target.sendMessage(ChatColor.YELLOW + "You are now visible.");
                        getLogger().info(target.getDisplayName() + ChatColor.YELLOW + " is now visible.");
                    }
                    else {
                        plugin.getPlayersVanished().add(target.getUniqueId());
                        for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                            if (!onlinePlayer.hasPermission("oldecore.hide.see")) {
                                onlinePlayer.hidePlayer(plugin, target);
                            }
                        }
                        target.sendMessage(ChatColor.YELLOW + "You are now invisible.");
                        getLogger().info(target.getDisplayName() + ChatColor.YELLOW + " is now invisible.");
                    }
                }
                else {
                    getLogger().info(ChatColor.GOLD + "Player not found.");
                }
            }
        }


        return true;
    }
    }
