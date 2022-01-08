package DivineChildAddon.DataBase;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import static DivineChildAddon.Main.DataBasePath;
import static DivineChildAddon.Function.Log;

public class PlayerData {
    public static HashMap<UUID, PlayerData> PlayerData = new HashMap<>();

    public static PlayerData playerData(Player player) {
        if (player.isOnline()) {
            if (!PlayerData.containsKey(player.getUniqueId())) {
                PlayerData.put(player.getUniqueId(), new PlayerData(player));
            }
            return PlayerData.get(player.getUniqueId());
        }
        Log("Non-existent player or NPC", true);
        return new PlayerData((Player) Bukkit.getOfflinePlayer("ff826016-0aed-4af9-b754-2a5841edf334"));
    }

    private final Player player;

    public PlayerData(Player player) {
        this.player = player;
    }

    public boolean SystemLog = false;
    public boolean CommandLog = false;

    public void save() {
        File playerDirectory = new File(DataBasePath, "PlayerData");
        if (!playerDirectory.exists()) {
            try {
                playerDirectory.mkdirs();
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error creating " + playerDirectory.getPath() + "!");
            }
        }
        File playerFile = new File(DataBasePath, "PlayerData/" + player.getUniqueId() + ".yml");
        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error creating " + playerFile.getPath() + "!");
            }
        }
        FileConfiguration data = YamlConfiguration.loadConfiguration(playerFile);

        data.set("Setting.SystemLog", SystemLog);
        data.set("Setting.CommandLog", CommandLog);

        try {
            data.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        File playerFile = new File(DataBasePath, "PlayerData/" + player.getUniqueId() + ".yml");
        if (playerFile.exists()) {
            FileConfiguration data = YamlConfiguration.loadConfiguration(playerFile);
            SystemLog = data.getBoolean("Setting.SystemLog", false);
            CommandLog = data.getBoolean("Setting.CommandLog", false);
        }
    }
}
