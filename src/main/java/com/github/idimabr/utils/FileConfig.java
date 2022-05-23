package com.github.idimabr.utils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileConfig extends YamlConfiguration {

    private final JavaPlugin plugin;

    private final File file;
    private final String defaults;

    public FileConfig(JavaPlugin plugin, String fileName, String defaultsName) {
        this.plugin = plugin;
        this.defaults = defaultsName;
        this.file = new File(plugin.getDataFolder(), fileName);
    }

    public boolean createIfNotExists() {
        try {
            if (file.exists()) {
                load(file);
                return true;
            }

            file.getParentFile().mkdirs();
            file.createNewFile();

            load(file);
            if (defaults != null) {
                InputStreamReader reader = new InputStreamReader(plugin.getResource(defaults));
                FileConfiguration defaultsConfig = YamlConfiguration.loadConfiguration(reader);

                setDefaults(defaultsConfig);
                options().copyDefaults(true);

                reader.close();
                save();
            }
        } catch (IOException | InvalidConfigurationException exception) {
            return false;
        }

        return true;
    }

    public boolean reload() {
        try {
            load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            return false;
        }

        return true;
    }

    public boolean save() {
        try {
            options().indent(2);
            save(file);
        } catch (IOException exception) {
            return false;
        }
        return true;
    }
}