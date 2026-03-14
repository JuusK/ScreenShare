package plugin.screenShare.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import plugin.screenShare.ScreenShare;

public class PlayerMove implements Listener {
    private final ScreenShare plugin;

    public PlayerMove(ScreenShare plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        if (ScreenShare.frozenPlayers.contains(e.getPlayer().getUniqueId())){
            boolean allowMove = plugin.getConfig().getBoolean("allow-to-move");
            if (!allowMove) {
                e.setCancelled(true);
            }
        }
    }
}
