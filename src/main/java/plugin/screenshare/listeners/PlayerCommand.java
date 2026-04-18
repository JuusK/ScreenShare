package plugin.screenshare.listeners;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import plugin.screenshare.ScreenShare;
import plugin.Message;

public class PlayerCommand implements Listener {

    private final List<String> allowedCommands;

    public PlayerCommand(ScreenShare plugin) {
        this.allowedCommands = plugin.getConfig().getStringList("freeze.allowed-commands"); // Took allowed commands from config
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if (ScreenShare.frozenPlayers.containsKey(e.getPlayer().getUniqueId())) { // If player frozen and command isn't in config cancel event and send message.
            String command = e.getMessage().toLowerCase();

            for (String allowed : allowedCommands) {
                if (command.startsWith(allowed.toLowerCase() + " ") || command.equalsIgnoreCase(allowed)) {
                    return;
                }
            }

            e.setCancelled(true);
            e.getPlayer().sendMessage(Message.get("messages.freeze.message"));
        }
    }
}