package fr.syrql.botentry.utils.config;

import fr.syrql.botentry.BotEntry;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private BotEntry botEntry;
    private final FileConfiguration config;

    public ConfigManager(BotEntry botEntry) {
        this.botEntry = botEntry;
        this.config = botEntry.getConfig();
    }

    public String getString(String path) {
        return ChatColor.translateAlternateColorCodes('&', botEntry.getConfig().getString(path));
    }

    public List<String> getStringList(String path) {

        List<String> stringList = botEntry.getConfig().getStringList(path);
        ArrayList<String> toReturn = new ArrayList<>();

        stringList.forEach(line -> toReturn.add(ChatColor.translateAlternateColorCodes('&', line)));

        return toReturn;
    }

    public void setDouble(String path, double value) { config.set(path, value); }

    public int getInt(String path) {
        return config.getInt(path);
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    private double getDouble(String path) {
        return config.getDouble(path);
    }

    public double getFloat(String path) {
        return config.getDouble(path);
    }
    public long getLong(String path){
        return config.getLong(path);
    }

    public FileConfiguration getConfig() { return config; }

    public void updateConfig() {
        botEntry.saveConfig();
        botEntry.reloadConfig();
    }
}
