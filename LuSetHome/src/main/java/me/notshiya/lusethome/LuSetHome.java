package me.notshiya.lusethome;

import me.notshiya.lusethome.commands.DeleteHome;
import me.notshiya.lusethome.commands.setHomeCommand;
import me.notshiya.lusethome.commands.TeleportToHome;
import org.bukkit.plugin.java.JavaPlugin;

public final class LuSetHome extends JavaPlugin {
    public static LuSetHome plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        getCommand("sethome").setExecutor(new setHomeCommand());
        getCommand("home").setExecutor(new TeleportToHome());
        getCommand("deletehome").setExecutor(new DeleteHome());
    }

    public String getConfigMessage(String path) {
        return getConfig().getString("messages." + path);
    }
}
