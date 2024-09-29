package network.olde.olde_core.commands;

import network.olde.olde_core.OldeCore;
import network.olde.olde_core.handlers.MuteConfiguration;
import network.olde.olde_core.listeners.MuteListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MuteCommand implements CommandExecutor {

    private final OldeCore plugin;

    public MuteCommand(OldeCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        MuteConfiguration muteConfiguration = plugin.getMuteConfiguration();

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;


            if (player.hasPermission("oldecore.mute")) {
                if (strings.length == 1) {
                    String targetUsername = strings[0];
                    Player target = Bukkit.getServer().getPlayerExact(targetUsername);

                    if (target != null) {
                        UUID playerUUID = target.getUniqueId();
                        if (muteConfiguration.isPlayerMuted(playerUUID)) {
                            player.sendMessage(target.getDisplayName() + ChatColor.GREEN + " has already been muted.");
                        }
                        else {
                            muteConfiguration.mutePlayer(playerUUID);
                            player.sendMessage(target.getDisplayName() + ChatColor.RED + " has been muted.");
                            target.sendMessage(ChatColor.RED + "You have been muted.");
                        }
                    }
                    else {
                        player.sendMessage(ChatColor.GOLD + "Player not found.");
                    }
                }
                else {
                    player.sendMessage(ChatColor.GOLD + "Example: /mute <player>");
                }
            }
            // Warning: permission else statement has been removed for optimization.
            // could be future issue.
        }
        else {
            if (strings.length == 1) {
                String targetUsername = strings[0];
                Player target = Bukkit.getServer().getPlayerExact(targetUsername);

                if (target != null) {
                    UUID playerUUID = target.getUniqueId();
                    if (muteConfiguration.isPlayerMuted(playerUUID)) {
                        Bukkit.getConsoleSender().sendMessage(target.getDisplayName() + ChatColor.GREEN + " has already been muted.");
                    }
                    else {
                        muteConfiguration.mutePlayer(playerUUID);
                        Bukkit.getConsoleSender().sendMessage(target.getDisplayName() + ChatColor.RED + " has been muted.");
                        target.sendMessage(ChatColor.RED + "You have been muted.");
                    }
                }
                else {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Player not found.");
                }
            }
            else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Example: /mute <player>");
            }
        }

        return true;
    }
}
