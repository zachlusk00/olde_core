package network.olde.olde_core.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

///  possibly make this a BackListener, just for the back command????

public class TeleportListener implements Listener {
    private Location teleportFrom;
    private Location teleportTo;
    private Long teleportTime;

    @EventHandler
    public void onLastTeleport(PlayerTeleportEvent event) {
        teleportFrom = event.getFrom();
        teleportTo = event.getTo();
        teleportTime = System.currentTimeMillis();
    }

    public Location getTeleportFrom() {
        return teleportFrom;
    }

    public Location getTeleportTo() {
        return teleportTo;
    }

    public Long getTeleportTime() {
        return teleportTime;
    }
}
