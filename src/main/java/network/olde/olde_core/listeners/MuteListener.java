package network.olde.olde_core.listeners;

import network.olde.olde_core.OldeCore;
import network.olde.olde_core.handlers.MuteConfiguration;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;
import java.util.UUID;

public class MuteListener implements Listener {
    private final OldeCore plugin;

    public MuteListener(OldeCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Mute(AsyncPlayerChatEvent playerChatEvent) {

        MuteConfiguration muteConfiguration = plugin.getMuteConfiguration();

        Player player = playerChatEvent.getPlayer();
        UUID playerUniqueId = player.getUniqueId();

        if (muteConfiguration.isPlayerMuted(playerUniqueId)) {
            playerChatEvent.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You are currently muted.");
        }
    }
}
