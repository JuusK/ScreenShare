package plugin.screenShare;

import org.bukkit.plugin.java.JavaPlugin;

import plugin.Message;
import plugin.screenShare.Commands.Freeze;
import plugin.screenShare.Commands.UnFreeze;
import plugin.screenShare.Listeners.PlayerCommand;
import plugin.screenShare.Listeners.PlayerLeave;
import plugin.screenShare.Listeners.PlayerMove;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class ScreenShare extends JavaPlugin {

    public static Set<UUID> frozenPlayers = new HashSet<>();

    @Override
    public void onEnable() {
        System.out.println("ScreenShare Plugin has been enabled!");

        saveResource("messages.yml", false);

        Message.load(this);

        saveDefaultConfig();        

        getServer().getPluginManager().registerEvents(new PlayerMove(this),this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(this),this);
        getServer().getPluginManager().registerEvents(new PlayerCommand(this),this);


        getCommand("ssfreeze").setExecutor(new Freeze(this));
        getCommand("ssunfreeze").setExecutor(new UnFreeze(this));

    }
}
