package fr.syrql.botentry;

import fr.syrql.botentry.bots.provider.BotProvider;
import fr.syrql.botentry.commands.BotEntryCommand;
import fr.syrql.botentry.listener.PlayerListener;
import fr.syrql.botentry.utils.config.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BotEntry extends JavaPlugin {

    private ConfigManager configManager;
    private BotProvider botProvider;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.registerManager();
        this.registerProvider();
        this.registerListener();
        this.registerCommand();
    }

    @Override
    public void onDisable() {
    }

    private void registerProvider() {
        this.botProvider = new BotProvider(this);
        this.botProvider.read();
    }

    private void registerManager() {
        this.configManager = new ConfigManager(this);
    }

    private void registerListener() {
        this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

    }

    private void registerCommand() {
        new BotEntryCommand(this);

    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public BotProvider getBotProvider() {
        return botProvider;
    }

}
