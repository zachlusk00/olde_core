package network.olde.olde_core.handlers;

import network.olde.olde_core.OldeCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bukkit.Bukkit.getLogger;

public class BackpackHandler {
    private OldeCore plugin;
    private final File file;
    private FileConfiguration config;
    private final Map<String, ItemStack[]> backpack;

    public Map<String, ItemStack[]> getBackpack() {
        return backpack;
    }

    public BackpackHandler(OldeCore plugin) {
        this.plugin = plugin;
        this.file = new File(Bukkit.getServer().getPluginManager().getPlugin("olde_core").getDataFolder(), "backpack.yml");
        this.backpack = new HashMap<>();

        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                getLogger().info(e.getMessage());
            }
        }
    }

    public void saveBackpack() {
        config = YamlConfiguration.loadConfiguration(file);

        for (Map.Entry<String, ItemStack[]> entry : backpack.entrySet()) {
            config.set("data." + entry.getKey(), entry.getValue());
        }

        try {
            config.save(file);
        }
        catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(e.getMessage());
        }
    }

    // https://www.youtube.com/watch?v=XVfAt65UTgc - video for reference, this stuff is confusing.
    public void restoreBackpack() {
        config = YamlConfiguration.loadConfiguration(file);

        if (config.getConfigurationSection("data") == null) {
            config.createSection("data");
        }

        backpack.clear();

        for (String Key : config.getConfigurationSection("data").getKeys(false)) {
            //Object rawContent = config.get("data." + Key);
            if (config.get("data." + Key) instanceof List<?>) {
                @SuppressWarnings("unchecked")
                List<ItemStack> itemList = (List<ItemStack>) config.get("data." + Key);
                if (itemList != null) {
                    ItemStack[] content = itemList.toArray(new ItemStack[0]);

                    // Add the restored backpack data to the `backpack` map
                    backpack.put(Key, content);
                }
            } else {
                getLogger().warning("Data for key " + Key + " is not a List<ItemStack>");
            }
        }
    }

}
