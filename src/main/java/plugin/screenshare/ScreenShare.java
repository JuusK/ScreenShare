package plugin.screenshare;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import plugin.Message;
import plugin.screenshare.commands.Freeze;
import plugin.screenshare.commands.UnFreeze;
import plugin.screenshare.listeners.PlayerCommand;
import plugin.screenshare.listeners.PlayerLeave;
import plugin.screenshare.listeners.PlayerMove;

import java.util.*;

public final class ScreenShare extends JavaPlugin {

    public static Map<UUID, Location> frozenPlayers = new HashMap<>();

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
