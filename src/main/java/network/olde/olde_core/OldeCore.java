package network.olde.olde_core;

import network.olde.olde_core.commands.*;
import network.olde.olde_core.handlers.BackpackHandler;
import network.olde.olde_core.handlers.MuteConfiguration;
import network.olde.olde_core.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class OldeCore extends JavaPlugin {
    private Set<UUID> playersVanished;
    private MuteConfiguration muteConfiguration;
    private BackpackHandler backpackHandler;
    public Set<UUID> getPlayersVanished() {
        return playersVanished;
    }
    public MuteConfiguration getMuteConfiguration() {
        return muteConfiguration;
    }
    public BackpackHandler getBackpackHandler() {
        return backpackHandler;
    }

    @Override
    public void onEnable() {
        //setup default config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        playersVanished = new HashSet<>();

        muteConfiguration = new MuteConfiguration(this);
        muteConfiguration.loadMutedPlayers();
        backpackHandler = new BackpackHandler(this);
        backpackHandler.restoreBackpack();

        TeleportListener teleportListener = new TeleportListener();
        DeathListener deathListener = new DeathListener();
        JoinLeaveListener joinLeaveListener = new JoinLeaveListener(this);
        KickListener kickListener = new KickListener();
        MuteListener muteListener = new MuteListener(this);

        getServer().getPluginManager().registerEvents(joinLeaveListener, this);
        getServer().getPluginManager().registerEvents(teleportListener, this);
        getServer().getPluginManager().registerEvents(deathListener, this);
        getServer().getPluginManager().registerEvents(kickListener, this);
        getServer().getPluginManager().registerEvents(muteListener, this);
        getServer().getPluginManager().registerEvents(new ItemBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BackPackCommand(this), this);
        getServer().getPluginManager().registerEvents(new BanListener(), this);

        getCommand("god").setExecutor(new GodCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("speed").setExecutor(new SpeedCommand());
        getCommand("hide").setExecutor(new HideCommand(this));
        getCommand("kill").setExecutor(new KillCommand());
        getCommand("back").setExecutor(new BackCommand(this, teleportListener, deathListener));
        getCommand("teleport").setExecutor(new TeleportCommand());
        getCommand("kick").setExecutor(new KickCommand());
        getCommand("craft").setExecutor(new CraftCommand());
        getCommand("mute").setExecutor(new MuteCommand(this));
        getCommand("unmute").setExecutor(new UnmuteCommand(this));
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("backpack").setExecutor(new BackPackCommand(this));
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("unban").setExecutor(new UnbanCommand());
        getCommand("ipban").setExecutor(new ipbanCommand());

        Bukkit.getConsoleSender().sendMessage("[olde_core] " + ChatColor.GREEN + "OldeCore Started.");
    }

    @Override
    public void onDisable() {
        playersVanished.clear();
        muteConfiguration.saveMutedPlayers();
        backpackHandler.saveBackpack();

        Bukkit.getConsoleSender().sendMessage("[olde_core] " + ChatColor.RED + "OldeCore Stopped.");
    }
}
