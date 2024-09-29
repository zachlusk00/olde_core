package network.olde.olde_core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class KickListener implements Listener {
    @EventHandler
    public void onPlayerKicked(PlayerKickEvent playerKickEvent) {
        Player player = playerKickEvent.getPlayer();
        Bukkit.broadcastMessage(player.getDisplayName() + ChatColor.RED + " has been kicked!");
    }
}
