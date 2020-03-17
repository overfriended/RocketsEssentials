package me.rocket.broadcast;

import me.rocket.broadcast.commands.CommandBroadcast;
import me.rocket.broadcast.commands.CommandFly;
import me.rocket.broadcast.commands.CommandHome;
import me.rocket.broadcast.commands.CommandTP;
import me.rocket.broadcast.events.Events;
import me.rocket.broadcast.utils.Utils;
import me.rocket.broadcast.utils.Variables;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Broadcast extends JavaPlugin {

    private static Broadcast instance;

    public static Broadcast getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        Events.setPlayerColor("tipooo", 'b');
        Events.setPlayerColor("olvine", 'd');
        Events.setPlayerColor("SupremeGodOfGods", 'c');
        Events.setPlayerColor("Bri_Is_Baby", 'a');

        getCommand("broadcast").setExecutor(new CommandBroadcast());
        getCommand("home").setExecutor(new CommandHome());
        getCommand("fly").setExecutor(new CommandFly());
        getCommand("tp").setExecutor(new CommandTP());

        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new Events(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
