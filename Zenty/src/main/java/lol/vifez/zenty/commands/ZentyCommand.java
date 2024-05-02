package lol.vifez.zenty.commands;

/*
 * Galactic is property of Kira-Development-Team
 */

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import lol.vifez.zenty.Zenty;
import lol.vifez.zenty.util.C;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
@CommandAlias("galactic|hub")
@CommandPermission("galactic.admin")
public class ZentyCommand extends BaseCommand {

    private final Zenty plugin;

    @Default
    @Subcommand("help")
    @CommandPermission("galactic.admin")
    public void help(CommandSender sender) {
        C.sendMessage(sender, " ");
        C.sendMessage(sender, "&d&lGalactic Hub Core");
        C.sendMessage(sender, " &7* &f/galactic info &7(Show info about the hub core)");
        C.sendMessage(sender, " &7* &f/galactic reload &7(Reload the config files)");
        C.sendMessage(sender, " ");
    }

    @Subcommand("info")
    @CommandPermission("galactic.admin")
    public void info(CommandSender sender) {
        C.sendMessage(sender, " ");
        C.sendMessage(sender, "&d&lGalactic Hub Core");
        C.sendMessage(sender, " &7* &fAuthors: &d" + plugin.getDescription().getAuthors());
        C.sendMessage(sender, " &7* &fVersion: &d" + plugin.getDescription().getVersion());
        C.sendMessage(sender, " &7* &fDescription: &d" + plugin.getDescription().getDescription());
        C.sendMessage(sender, " ");
    }

    @Subcommand("reload")
    @CommandPermission("galactic.admin")
    public void reload(CommandSender sender) {
        plugin.reloadConfig();
        plugin.getMessagesFile().reload();
        C.sendMessage(sender, "&aYou have successfully reloaded Galactic's config files!");
    }
}