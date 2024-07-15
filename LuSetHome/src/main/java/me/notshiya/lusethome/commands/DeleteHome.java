package me.notshiya.lusethome.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import static me.notshiya.lusethome.LuSetHome.plugin;

public class DeleteHome implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String path = "home." + player.getUniqueId() + ".";
            plugin.getConfig().set(path + "world", null);
            plugin.getConfig().set(path + "x", null);
            plugin.getConfig().set(path + "y", null);
            plugin.getConfig().set(path + "z", null);
            plugin.saveConfig();

            player.sendMessage(ChatColor.DARK_PURPLE + "Lu" + ChatColor.LIGHT_PURPLE + "SetHome >> " + plugin.getConfigMessage("delete_home"));
            return true;
        }
        return false;
    }
}
