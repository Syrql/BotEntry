package fr.syrql.botentry.bots.provider;

import com.fasterxml.jackson.core.type.TypeReference;
import fr.syrql.botentry.BotEntry;
import fr.syrql.botentry.commons.Bot;
import fr.syrql.botentry.io.readable.IReadable;
import fr.syrql.botentry.io.writeable.IWriteable;
import fr.syrql.botentry.utils.IOUtil;
import org.bukkit.Bukkit;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BotProvider implements IProvider<String, Bot>, IWriteable, IReadable {

    private Map<String, Bot> botMap;
    private final Path path;

    public BotProvider(BotEntry botEntry) {
        this.botMap = new HashMap<>();
        this.path = Paths.get(botEntry.getDataFolder() + "/data/bots.json");
    }

    @Override
    public void provide(String key, Bot value) {
        this.botMap.put(key, value);
        this.write();

        if (value == null) return;

        Bukkit.getOnlinePlayers().forEach(value::showInTab);
    }

    @Override
    public void remove(String key) {
        Bot bot = this.botMap.get(key);

        if (bot == null) return;

        Bukkit.getOnlinePlayers().forEach(bot::removeInTab);
        this.botMap.remove(key);
        this.write();
    }

    @Override
    public Bot get(String key) {
        return this.botMap.get(key);
    }

    @Override
    public void read() {
        if (!Files.exists(path)) {
            this.botMap = new HashMap<>();
            return;
        }

        this.botMap = IOUtil.readObject(path, new TypeReference<HashMap<String, Bot>>() {
        });

        System.out.println(this.botMap);
    }

    @Override
    public void write() {
        new Thread(() -> IOUtil.writeObject(path, this.botMap)).start();
    }

    public Collection<Bot> getBots() {
        return this.botMap.values();
    }
}
