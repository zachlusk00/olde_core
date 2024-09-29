package network.olde.olde_core.listeners;

import network.olde.olde_core.OldeCore;
import network.olde.olde_core.commands.HideCommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {

    private final OldeCore plugin;

    public JoinLeaveListener(OldeCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String username = player.getDisplayName();

        if (!player.hasPlayedBefore()) {
            event.setJoinMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "+ " + ChatColor.RESET + username);
            event.setJoinMessage("Welcome to " + ChatColor.YELLOW + "OldeMC, " + ChatColor.RESET + username + "!");
        }
        else {
            event.setJoinMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "+ " + ChatColor.RESET + username);
        }

        removeGod(player);
        showPlayer(player);
    }

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String username = player.getDisplayName();

        event.setQuitMessage(ChatColor.BOLD + "" + ChatColor.RED + "- " + ChatColor.RESET + username);
    }

    public void removeGod(Player player) {
        if (player.isInvulnerable()) {
            player.setInvulnerable(false);
        }
    }

    public void showPlayer(Player player) {
        if (plugin.getPlayersVanished().contains(player.getUniqueId())) {
            plugin.getPlayersVanished().remove(player.getUniqueId());
            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                onlinePlayer.showPlayer(plugin, player);
            }
        }
    }
}
