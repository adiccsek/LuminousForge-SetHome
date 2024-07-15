package me.notshiya.lusethome.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.notshiya.lusethome.LuSetHome.plugin;

public class TeleportToHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!plugin.getConfig().getBoolean("enable_home")) {
            sender.sendMessage(ChatColor.DARK_PURPLE + "Lu" + ChatColor.LIGHT_PURPLE + "SetHome >> " + ChatColor.RED + "This command is disabled.");
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            String path = "home." + player.getUniqueId() + ".";
            String worldName = plugin.getConfig().getString(path + "world");

            if (worldName == null) {
                player.sendMessage(ChatColor.DARK_PURPLE + "Lu" + ChatColor.LIGHT_PURPLE + "SetHome >> " + ChatColor.RED + plugin.getConfigMessage("no_home"));
                return true;
            }

            double x = plugin.getConfig().getDouble(path + "x");
            double y = plugin.getConfig().getDouble(path + "y");
            double z = plugin.getConfig().getDouble(path + "z");

            World world = Bukkit.getWorld(worldName);
            if (world == null) {
                player.sendMessage(ChatColor.DARK_PURPLE + "Lu" + ChatColor.LIGHT_PURPLE + "SetHome >> " + ChatColor.RED + plugin.getConfigMessage("world_not_exist"));
                return true;
            }

            Location location = new Location(world, x, y, z);
            player.teleport(location);

            player.sendMessage(ChatColor.DARK_PURPLE + "Lu" + ChatColor.LIGHT_PURPLE + "SetHome >> " + plugin.getConfigMessage("home_teleport"));
            return true;
        }
        return false;
    }
}
