package me.notshiya.lusethome.commands;

import me.notshiya.lusethome.PlaceholderUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

import static me.notshiya.lusethome.LuSetHome.plugin;

public class setHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!plugin.getConfig().getBoolean("enable_sethome")) {
            sender.sendMessage(ChatColor.DARK_PURPLE + "Lu" + ChatColor.LIGHT_PURPLE + "SetHome >> " + ChatColor.RED + "This command is disabled.");
            return true;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location loc = player.getLocation();
            String path = "home." + player.getUniqueId() + ".";
            plugin.getConfig().set(path + "world", loc.getWorld().getName());
            plugin.getConfig().set(path + "x", loc.getX());
            plugin.getConfig().set(path + "y", loc.getY());
            plugin.getConfig().set(path + "z", loc.getZ());
            plugin.saveConfig();

            Map<String, String> placeholders = new HashMap<>();
            placeholders.put("x", String.valueOf(loc.getBlockX()));
            placeholders.put("y", String.valueOf(loc.getBlockY()));
            placeholders.put("z", String.valueOf(loc.getBlockZ()));
            placeholders.put("world", loc.getWorld().getName());

            String message = plugin.getConfigMessage("home_set");
            message = PlaceholderUtil.replacePlaceholders(message, placeholders);
            player.sendMessage(ChatColor.DARK_PURPLE + "Lu" + ChatColor.LIGHT_PURPLE + "SetHome >> " + ChatColor.GREEN + message);

            return true;
        }
        return false;
    }
}
