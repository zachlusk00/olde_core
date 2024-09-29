package network.olde.olde_core.commands;

import network.olde.olde_core.OldeCore;
import network.olde.olde_core.handlers.MuteConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class UnmuteCommand implements CommandExecutor {

    private final OldeCore plugin;

    public UnmuteCommand(OldeCore plugin) {
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
                    if (target != null){
                        UUID playerUUId = target.getUniqueId();
                        if (muteConfiguration.isPlayerMuted(playerUUId)) {
                            muteConfiguration.unmutePlayer(playerUUId);
                            player.sendMessage(target.getDisplayName() + ChatColor.GREEN + " has been unmuted.");
                            target.sendMessage(ChatColor.GREEN + "You have been unmuted.");
                        }
                        else {
                            player.sendMessage(target.getDisplayName() + ChatColor.YELLOW + " is not muted.");
                        }
                    }
                    else {
                        player.sendMessage(ChatColor.GOLD + "Player not found.");
                    }
                }
                else {
                    player.sendMessage(ChatColor.GOLD + "Example: /unmute <player>");
                }
            }
            // experimenting with not using else statement for permissions.
        }
        // Console
        else {
            if (strings.length == 1) {
                String targetUsername = strings[0];
                Player target = Bukkit.getServer().getPlayerExact(targetUsername);
                if (target != null){
                    UUID playerUUId = target.getUniqueId();
                    if (muteConfiguration.isPlayerMuted(playerUUId)) {
                        muteConfiguration.unmutePlayer(playerUUId);
                        Bukkit.getConsoleSender().sendMessage(target.getDisplayName() + ChatColor.GREEN + " has been unmuted.");
                        target.sendMessage(ChatColor.GREEN + "You have been unmuted.");
                    }
                    else {
                        Bukkit.getConsoleSender().sendMessage(target.getDisplayName() + ChatColor.YELLOW + " is not muted.");
                    }
                }
                else {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Player not found.");
                }

            }
            else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Example: /unmute <player>");
            }
        }

        return true;
    }
}
