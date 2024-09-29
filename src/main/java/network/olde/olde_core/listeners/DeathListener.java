package network.olde.olde_core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private Location deathLocation;
    private Long deathTime;
    @EventHandler
    public void onDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();

        if (player != null) {
            deathLocation = player.getLocation();
            deathTime = System.currentTimeMillis();

            String username = player.getDisplayName();

            if (player.getKiller() == null) {
                event.setDeathMessage(username + ChatColor.RED + " has passed away.");
            }
            else {
                String killer = player.getKiller().getDisplayName();
                event.setDeathMessage(username + ChatColor.RED + " was murdered by " + ChatColor.RESET + killer + ChatColor.RED + ".");
            }

        }
    }

    public Location getDeathLocation() {
        return deathLocation;
    }

    public Long getDeathTime() {
        return deathTime;
    }
}
