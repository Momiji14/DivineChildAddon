package DivineChildAddon;

import DivineChildAddon.DataBase.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.command.UnknownCommandEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import static DivineChildAddon.DataBase.PlayerData.playerData;
import static DivineChildAddon.Function.Log;

public class Events implements Listener {

    public Events(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.playerData(player);
        playerData.load();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = PlayerData.playerData(player);
        playerData.save();
    }

    @EventHandler
    public void onCommand(UnknownCommandEvent event) {
        if (event.getSender() instanceof Player) {
            String log = "§c[CmdLog]§c" + event.getSender().getName() + "§7: §c" + event.getCommandLine();
            Log(log);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (playerData(player).CommandLog) {
                    player.sendMessage(log);
                }
            }
        }
    }
}
