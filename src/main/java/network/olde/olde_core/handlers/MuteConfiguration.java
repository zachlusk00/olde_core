package network.olde.olde_core.handlers;

import network.olde.olde_core.OldeCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;

public class MuteConfiguration {

    private OldeCore plugin;
    private final Set<UUID> mutedPlayers;
    private final File file;
    private FileConfiguration config;

    public MuteConfiguration(OldeCore plugin) {
        this.plugin = plugin;
        this.mutedPlayers = new HashSet<>();
        this.file = new File(Bukkit.getServer().getPluginManager().getPlugin("olde_core").getDataFolder(), "muteconfig.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                getLogger().info(e.getMessage());
            }
        }

        loadMutedPlayers();
    }
    public void loadMutedPlayers() {
        config = YamlConfiguration.loadConfiguration(file);

        if (config.getConfigurationSection("muted_players") == null) {
            config.createSection("muted_players");
        }

        mutedPlayers.clear();
        for (String key : config.getConfigurationSection("muted_players").getKeys(false)) {
            mutedPlayers.add(UUID.fromString(key));
        }
    }

    public void saveMutedPlayers() {
        config = YamlConfiguration.loadConfiguration(file);

        // remove players from config (legacy)
//        if (config.getConfigurationSection("muted_players") != null) {
//            for (String key : config.getConfigurationSection("muted_players").getKeys(false)) {
//                config.set("muted_players." + key, null);
//            }
//        }

        // removes players from config (optimized)
        config.set("muted_player", null);

        // add players to config
        for (UUID uuid : mutedPlayers) {
            config.set("muted_players." + uuid.toString(), true);
        }
        try {
            config.save(file);
        } catch (IOException e) {
            getLogger().info(e.getMessage());
        }
    }

    public boolean isPlayerMuted(UUID playerUUID) {
        return mutedPlayers.contains(playerUUID);
    }

    public void mutePlayer(UUID playerUUID) {
        mutedPlayers.add(playerUUID);
        saveMutedPlayers();
    }

    public void unmutePlayer(UUID playerUUID) {
        mutedPlayers.remove(playerUUID);
        saveMutedPlayers();
    }

}
