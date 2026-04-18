package plugin.screenshare.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import plugin.screenshare.ScreenShare;

public class PlayerLeave implements Listener {

    private final ScreenShare plugin;
    public PlayerLeave(ScreenShare plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (ScreenShare.frozenPlayers.containsKey(player.getUniqueId())){
            ScreenShare.frozenPlayers.remove(player.getUniqueId());
            
            String command = plugin.getConfig().getString("escapescreenshare.bancommand");
            String reason = plugin.getConfig().getString("escapescreenshare.banreason");
            String duration = plugin.getConfig().getString("escapescreenshare.banduration");

            // Execute: command <player> <reason> <duration>
            plugin.getServer().dispatchCommand(
                plugin.getServer().getConsoleSender(),  
                command + " " + player.getName() + " " + reason + " " + duration
            );  
        }
    }
}