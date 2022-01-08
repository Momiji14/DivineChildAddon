package DivineChildAddon;

import DivineChildAddon.DataBase.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static DivineChildAddon.DataBase.PlayerData.playerData;
import static DivineChildAddon.Function.boolToString;

public final class Main extends JavaPlugin {

    public static String DataBasePath;

    @Override
    public void onEnable() {
        DataBasePath = getDataFolder().getAbsolutePath() + "\\";
        saveDefaultConfig();
        new Events(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerData playerData = playerData(player);
            if (cmd.getName().equalsIgnoreCase("systemLog")) {
                playerData.SystemLog = !playerData.SystemLog;
                player.sendMessage("§c[システムログ]§aが" + boolToString(playerData.SystemLog) + "§aになりました");
                return true;
            } else if (cmd.getName().equalsIgnoreCase("commandLog")) {
                playerData.CommandLog = !playerData.CommandLog;
                player.sendMessage("§c[コマンドログ]§aが" + boolToString(playerData.CommandLog) + "§aになりました");
                return true;
            }
        }
        return false;
    }
}
