package network.olde.olde_core.commands;

import network.olde.olde_core.OldeCore;
import network.olde.olde_core.listeners.DeathListener;
import network.olde.olde_core.listeners.JoinLeaveListener;
import network.olde.olde_core.listeners.TeleportListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCommand implements CommandExecutor {

    private final OldeCore plugin;
    private final TeleportListener teleportListener;
    private final DeathListener deathListener;
    public BackCommand(OldeCore plugin, TeleportListener teleportListener, DeathListener deathListener) {
        this.plugin = plugin;
        this.teleportListener = teleportListener;
        this.deathListener = deathListener;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("oldecore.back")) {
                Location lastDeathlocation = deathListener.getDeathLocation();
                Location lastTPlocation = teleportListener.getTeleportFrom();
                Long lastDeathLocationTime = deathListener.getDeathTime();
                Long lastTpLocationTime = teleportListener.getTeleportTime();

                if ((lastDeathlocation == null) && (lastTPlocation == null)){
                    player.sendMessage(ChatColor.GOLD + "No location found");
                }
                else {
                    if ((lastDeathlocation != null) && (lastTPlocation != null)) {
                        if (lastDeathLocationTime > lastTpLocationTime) {
                            player.sendMessage(ChatColor.YELLOW + "Teleporting back to last death location");
                            player.teleport(lastDeathlocation);
                        }
                        else {
                            player.sendMessage(ChatColor.YELLOW + "Teleporting back to last teleport location");
                            player.teleport(lastTPlocation);
                        }
                    }
                    else if (lastDeathlocation == null) {
                        player.sendMessage(ChatColor.YELLOW + "Teleporting back to last teleport location");
                        player.teleport(lastTPlocation);
                    }
                    else {
                        player.sendMessage(ChatColor.YELLOW + "Teleporting back to last death location");
                        player.teleport(lastDeathlocation);
                    }
                }
            }
            else {
                player.sendMessage(ChatColor.GOLD + "You don't have the required permissions.");
            }
        }
        else {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "You must be a player to run this command.");
        }



        return true;
    }
}
