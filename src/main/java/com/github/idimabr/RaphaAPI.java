package com.github.idimabr;

import com.github.idimabr.listeners.EntityDamageListener;
import com.github.idimabr.listeners.PlayerArmorListener;
import com.github.idimabr.listeners.PlayerMoveListener;
import com.github.idimabr.listeners.PlayerMoveToUPListener;
import com.github.idimabr.storage.SQLExecutor;
import com.github.idimabr.storage.connectors.SQLConnector;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class RaphaAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new EntityDamageListener(), this);
        pluginManager.registerEvents(new PlayerMoveListener(), this);
        pluginManager.registerEvents(new PlayerMoveToUPListener(), this);
        pluginManager.registerEvents(new PlayerArmorListener(), this);
    }
}
