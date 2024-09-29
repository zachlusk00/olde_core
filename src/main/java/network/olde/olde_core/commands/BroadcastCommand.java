package network.olde.olde_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        StringBuilder stringBuilder = new StringBuilder();
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("oldecore.broadcast")) {
                if (!(strings.length > 0)) {
                    player.sendMessage(ChatColor.GOLD + "Example: /broadcast <message>");
                }
                else {
                    for (String word : strings) {
                        stringBuilder.append(word).append(" ");
                    }

                    Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "BROADCAST: " + stringBuilder);
                }
            }
        }
        else {
            if (!(strings.length > 0)) {
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "Example: broadcast <message>");
            }
            else {
                for (String word : strings) {
                    stringBuilder.append(word).append(" ");
                }

                Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "BROADCAST: " + stringBuilder);
            }
        }

        return true;
    }
}
