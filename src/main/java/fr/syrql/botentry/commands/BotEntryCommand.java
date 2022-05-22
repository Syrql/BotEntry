package fr.syrql.botentry.commands;

import fr.syrql.botentry.BotEntry;
import fr.syrql.botentry.bots.factory.BotFactory;
import fr.syrql.botentry.utils.command.ACommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BotEntryCommand extends ACommand {

    private final BotEntry botEntry;

    public BotEntryCommand(BotEntry botEntry) {
        super(botEntry, "botentry", "permission.botentry", false);
        this.botEntry = botEntry;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage("/botentry remove <name>");
            player.sendMessage("/botentry add <name>");
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "remove":
                if (this.botEntry.getBotProvider().get(args[1]) != null) {
                    botEntry.getBotProvider().remove(args[1]);
                }
                break;
            case "add":
                if (this.botEntry.getBotProvider().get(args[1]) != null){
                    player.sendMessage("Un bot existe déjà...");
                    return true;
                }
                if (args[1].length() > 12){
                    player.sendMessage("Le nom du bot est trop long");
                    return true;
                }
                botEntry.getBotProvider().provide(args[1], new BotFactory().create(args[1]));
                break;
            default:
                break;
        }

        return true;
    }
}
