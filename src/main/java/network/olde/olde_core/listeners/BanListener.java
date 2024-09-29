package network.olde.olde_core.listeners;

import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class BanListener implements Listener {
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        if (event.getResult() == PlayerLoginEvent.Result.KICK_BANNED) {
            Player player = event.getPlayer();
            String ip = event.getAddress().getHostAddress();

            BanList<?> ipBanList = Bukkit.getServer().getBanList(BanList.Type.IP);
            BanList<?> banList = Bukkit.getServer().getBanList(BanList.Type.PROFILE);

            if ((ipBanList.getBanEntry(ip) != null) && (ipBanList.getBanEntry(ip).getReason() != null)) {
                String reason = ipBanList.getBanEntry(ip).getReason();
                event.setKickMessage(ChatColor.RED + "IP Banned: " + reason);
                return;
            }

            if ((banList.getBanEntry(player.getName()) != null) && (banList.getBanEntry(player.getName()).getReason() != null)) {
                String reason = banList.getBanEntry(player.getName()).getReason();
                event.setKickMessage(ChatColor.RED + "Banned: " + reason);
            }




        }
    }
}
