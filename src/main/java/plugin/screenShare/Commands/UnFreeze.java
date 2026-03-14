package plugin.screenShare.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.screenShare.ScreenShare;
import plugin.Message;

public class UnFreeze implements CommandExecutor {

    private final ScreenShare plugin;
    public UnFreeze(ScreenShare plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Decide where to teleport player when unfreeze. (from config)
        Location location = null;
        if (plugin.getConfig().contains("unfreezelocation")) {
            String worldName = plugin.getConfig().getString("unfreezelocation.world");
            if (worldName != null && Bukkit.getWorld(worldName) != null) {
                location = new Location(
                    Bukkit.getWorld(worldName),
                    plugin.getConfig().getDouble("unfreezelocation.x"),
                    plugin.getConfig().getDouble("unfreezelocation.y"),
                    plugin.getConfig().getDouble("unfreezelocation.z"),
                    (float) plugin.getConfig().getDouble("unfreezelocation.yaw"),
                    (float) plugin.getConfig().getDouble("unfreezelocation.pitch")
                );
            }
        }

        if (!(sender instanceof Player)){
            System.out.println("ScreenShare Only players can execute this command!");
            return true;
        }

        Player p = (Player) sender;
        if (args.length == 0){
            p.sendMessage(Message.get("messages.unfreeze.usage"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        // Send message if target not found
        if (target == null){
            p.sendMessage(Message.get("messages.unfreeze.player_not_found"));
            return true;
        }

        // Send message if target is sender (can't unfreeze self)
        if (target == p.getPlayer()){
            p.sendMessage(Message.get("messages.unfreeze.cannot_unfreeze_self"));
            return true;
        }

        if (ScreenShare.frozenPlayers.contains(target.getUniqueId())){ // If target is in frozen list
            ScreenShare.frozenPlayers.remove(target.getUniqueId());
            p.sendMessage(Message.get("messages.unfreeze.success"));
            target.sendMessage(Message.get("messages.unfreeze.success_target", "{player}", p.getName()));
            if (location != null) {
                target.teleport(location);
            } else {
                p.sendMessage(Message.get("messages.unfreeze.spawn_not_set")); // Send error message if spawn not set
            }
        } else {
            p.sendMessage(Message.get("messages.unfreeze.already_unfrozen")); // Send error message if target is already unfrozen (dont unfreeze again)
        }

        return true;
    }
}
