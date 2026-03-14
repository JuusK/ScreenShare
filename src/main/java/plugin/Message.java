package plugin;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import plugin.screenShare.ScreenShare;

public class Message {
    private static FileConfiguration messages;

    public static void load(ScreenShare plugin){
        plugin.saveResource("messages.yml",false);
        File file = new File(plugin.getDataFolder(),"messages.yml");
        messages = YamlConfiguration.loadConfiguration(file);
    }

    public static String get(String path) {
        String msg = messages.getString(path, "&cMessage not found: " + path);
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
    
    public static String get(String path, String... replacements) {
        String msg = get(path);
        for (int i = 0; i < replacements.length - 1; i += 2) {
            msg = msg.replace(replacements[i], replacements[i + 1]);
        }
        return msg;
    }
}
