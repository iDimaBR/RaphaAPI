package com.github.idimabr.utils;

import lombok.SneakyThrows;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import java.io.File;

public class ConfigUtil extends FileConfiguration {

    private final Plugin owningPlugin;
    private final String name;
    protected File rootFile;
    private FileConfiguration configuration;

    public ConfigUtil(Plugin owningPlugin, String name) {
        if (!name.endsWith(".yml")) name += ".yml";

        this.owningPlugin = owningPlugin;
        this.name = name;

        this.rootFile = new File(owningPlugin.getDataFolder(), name);
        load();
    }

    @SneakyThrows
    public ConfigUtil(Plugin owningPlugin, String name, String root) {
        if (!name.endsWith(".yml")) name += ".yml";

        this.owningPlugin = owningPlugin;
        this.name = name;

        File directory = new File(owningPlugin.getDataFolder(), root);
        if (!directory.exists())
            directory.mkdirs();

        File file = new File(directory, name);
        file.createNewFile();

        this.rootFile = file;
        load();
    }

    public void load() {
        if (!rootFile.exists()) {
            rootFile.getParentFile().mkdirs();
            owningPlugin.saveResource(name, false);
        }

        this.configuration = new YamlConfiguration();
        try {
            configuration.load(rootFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String saveToString() {
        return this.configuration.saveToString();
    }

    @Override
    public void loadFromString(String s) throws InvalidConfigurationException {
        this.configuration.loadFromString(s);
    }

    @Override
    protected String buildHeader() {
        return "";
    }
}