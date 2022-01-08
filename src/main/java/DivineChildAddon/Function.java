package DivineChildAddon;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static DivineChildAddon.DataBase.PlayerData.playerData;

public class Function {
    public static void Log(String log) {
        Log(log, false);
    }
    public static void Log(String log, boolean stackTrace) {
        Bukkit.getLogger().info(log);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (playerData(player).SystemLog) {
                player.sendMessage("§c[SystemLog]§f" + log);
            }
        }
        if (stackTrace) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String boolToString(boolean bool) {
        return boolToString(bool, false);
    }
    public static String boolToString(boolean bool, boolean bold) {
        String boldText = "";
        if (bold) boldText = "§l";
        if (bool) return "§b" + boldText + "有効";
        else return "§c" + boldText + "無効";
    }
}
