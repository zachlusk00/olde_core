package network.olde.olde_core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;

public class ItemBreakListener implements Listener {
    @EventHandler
    public void onItemBreak(PlayerItemBreakEvent itemBreakEvent) {
        Player player = itemBreakEvent.getPlayer();
        Location playerLocation = player.getLocation();
        player.getWorld().strikeLightning(playerLocation);

        String worldName = player.getWorld().getName();;
        World world = Bukkit.getWorld(worldName);

        if (world != null) {
            world.setWeatherDuration(30);
            player.sendMessage(ChatColor.BLUE + "It is now raining lol.");
        }

    }
}
