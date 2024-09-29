package network.olde.olde_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("oldecore.fly")) {
                if (player.getAllowFlight()){
                    player.setFlying(false);
                    player.setAllowFlight(false);
                    player.sendMessage("Flight: " + ChatColor.RED + "Disabled");
                }
                else {
                    player.setAllowFlight(true);
                    player.sendMessage("Flight: " + ChatColor.GREEN +  "Enabled");
                }
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "You must be a player to run this command.");
        }


        return true;
    }
}
