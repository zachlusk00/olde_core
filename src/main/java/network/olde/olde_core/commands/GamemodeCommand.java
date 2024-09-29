package network.olde.olde_core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class GamemodeCommand implements CommandExecutor, TabCompleter {
    String exampleMessage = "Example: /gamemode <gamemode> <player>";
    String permission = "You don't have the required permissions.";

    // Player
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("oldecore.gamemode")) { // Permission
                // no args
                if (strings.length == 0) {
                    player.sendMessage(ChatColor.GOLD + exampleMessage);
                }
                // 1 arg = gamemode
                else if (strings.length == 1) {
                    String gamemode = strings[0].toUpperCase();
                    GameMode playerGamemode = selectGamemode(gamemode);
                    if ((gamemode.equalsIgnoreCase("c")) || (gamemode.equalsIgnoreCase("spectator"))) {
                        if (player.hasPermission("oldecore.gamemode.spectator")) {
                            changeGamemode(playerGamemode, player);
                        }
                        else {
                            player.sendMessage(ChatColor.GOLD + permission);
                        }
                    }
                    else {
                        changeGamemode(playerGamemode, player);
                    }

                }
                // 2 arg = player
                else if (strings.length == 2){
                    if (player.hasPermission("oldecore.gamemode.others")) {
                        String targetUsername = strings[1];
                        Player target = Bukkit.getServer().getPlayerExact(targetUsername);

                        if (target != null) {
                            String gamemode = strings[0].toUpperCase();
                            GameMode playerGamemode = selectGamemode(gamemode);

                            changeGamemode(playerGamemode, target);
                            player.sendMessage(ChatColor.YELLOW + "Set " + target.getDisplayName() + "'s gamemode to " + playerGamemode);
                        }
                        else {
                            player.sendMessage(ChatColor.GOLD + "No target found.");
                        }
                    }
                    else {
                        player.sendMessage(ChatColor.GOLD + permission);
                    }
                }
                else {
                    player.sendMessage(ChatColor.GOLD + exampleMessage);
                }
            }
            else {
                player.sendMessage(ChatColor.GOLD + permission);
            }
        }
        // Console
        else {
            if (strings.length == 2) {
                String targetUsername = strings[1];
                Player target = Bukkit.getServer().getPlayerExact(targetUsername);

                if (target != null) {
                    String gamemode = strings[0];
                    GameMode targetGamemode = selectGamemode(gamemode);
                    changeGamemode(targetGamemode, target);
                    getLogger().info(ChatColor.YELLOW + "Set " + target.getDisplayName() + "'s gamemode to " + targetGamemode);
                }
                else {
                    getLogger().info(ChatColor.GOLD + "No target found.");
                }
            }
            else {
                getLogger().info(ChatColor.GOLD + exampleMessage);
            }

        }
        return true;
    }

    List<String> args = new ArrayList<String>();
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            List<String> result = new ArrayList<String>();

            if (player.hasPermission("olde.gamemode.spectator")) {
                if (args.isEmpty()) {
                    args.add("Survival");
                    args.add("Creative");
                    args.add("Adventure");
                    args.add("Spectator");
                }
                if (strings.length == 1) {
                    for (String a : args) {
                        if (a.toLowerCase().startsWith(strings[0].toLowerCase())) {
                            result.add(a);
                        }
                    }
                    return result;
                }
            }
            else {
                if (args.isEmpty()) {
                    args.add("Survival");
                    args.add("Creative");
                    args.add("Adventure");
                }
                if (strings.length == 1) {
                    for (String a : args) {
                        if (a.toLowerCase().startsWith(strings[0].toLowerCase())) {
                            result.add(a);
                        }
                    }
                    return result;
                }
            }
        }
        // console
        else {
            List<String> result = new ArrayList<String>();
            if (args.isEmpty()) {
                args.add("Survival");
                args.add("Creative");
                args.add("Adventure");
                args.add("Spectator");
            }
            if (strings.length == 1) {
                for (String a : args) {
                    if (a.toLowerCase().startsWith(strings[0].toLowerCase())) {
                        result.add(a);
                    }
                }
                return result;
            }
        }
        return null;
    }

    private void changeGamemode(GameMode gameMode, Player player) {
        if (gameMode != null) {
            player.setGameMode(gameMode);
            player.sendMessage("Gamemode: " + ChatColor.YELLOW + gameMode);
        }
        else {
            player.sendMessage(ChatColor.GOLD + exampleMessage);
        }
    }

    private GameMode selectGamemode(String gamemode) {
        switch (gamemode.toLowerCase()) {
            case "survival":
            case "s":
            case "0":
                return GameMode.SURVIVAL;
            case "creative":
            case "c":
            case "1":
                return GameMode.CREATIVE;
            case "adventure":
            case "a":
            case "2":
                return GameMode.ADVENTURE;
            case "spectator":
            case "sp":
            case "3":
                return GameMode.SPECTATOR;
            default:
                return null;
        }
    }

}
