package fr.syrql.botentry.listener;

import fr.syrql.botentry.BotEntry;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final BotEntry botEntry;

    public PlayerListener(BotEntry botEntry) {
        this.botEntry = botEntry;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        this.botEntry.getBotProvider().getBots().forEach(e -> e.showInTab(player));
    }
}
