package plugin.screenShare.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.screenShare.ScreenShare;
import plugin.Message;

public class Freeze implements CommandExecutor {

    private final ScreenShare plugin;
    public Freeze(ScreenShare plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Decide where to teleport player when freeze. (from config)
        Location location = null;
        if (plugin.getConfig().contains("freezelocation")) {
            String worldName = plugin.getConfig().getString("freezelocation.world");
            if (worldName != null && Bukkit.getWorld(worldName) != null) {
                location = new Location(
                    Bukkit.getWorld(worldName),
                    plugin.getConfig().getDouble("freezelocation.x"),
                    plugin.getConfig().getDouble("freezelocation.y"),
                    plugin.getConfig().getDouble("freezelocation.z"),
                    (float) plugin.getConfig().getDouble("freezelocation.yaw"),
                    (float) plugin.getConfig().getDouble("freezelocation.pitch")
                );
            }
        }

        if (!(sender instanceof Player)){
            System.out.println("ScreenShare - Only players can execute this command!");
            return true;
        }

        Player p = (Player) sender;

        // Send message if args didn't provided
        if (args.length == 0){
            p.sendMessage(Message.get("messages.freeze.usage"));
            return true;
        }

        //Get target player
        Player target = Bukkit.getPlayer(args[0]);

        // Send message if target not found
        if (target == null){
            p.sendMessage(Message.get("messages.freeze.player_not_found"));
            return true;
        }

        // Send message if target is sender (can't freeze self)
        if (target == p.getPlayer()){
            p.sendMessage(Message.get("messages.freeze.cannot_freeze_self"));
            return true;
        }

        if (!ScreenShare.frozenPlayers.contains(target.getUniqueId())){ // If target is not in frozen list
            if (location == null) {
                p.sendMessage(Message.get("messages.freeze.location_not_set")); // Send error message if location not set
                return true;
            }

            //Add target to frozen players list
            ScreenShare.frozenPlayers.add(target.getUniqueId());
            p.sendMessage(Message.get("messages.freeze.success"));

            // Send messages and tp player to location
            target.sendMessage(Message.get("messages.freeze.success_target", "{player}", p.getName()));
            target.sendTitle(
                Message.get("messages.freeze.title"),
                Message.get("messages.freeze.subtitle"),
                10, 140, 20
            );
            target.playSound(target.getLocation(), Sound.BLOCK_ANVIL_FALL, 1f, 1f);
            target.sendMessage(Message.get("messages.freeze.message", "{player}", p.getName()));
            target.teleport(location);
        } else {
            p.sendMessage(Message.get("messages.freeze.already_frozen")); // Send error message if target is already frozen (dont freeze again if already frozen)
        }

        return true;
    }
}
