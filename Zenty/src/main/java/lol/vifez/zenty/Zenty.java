package lol.vifez.zenty;

import co.aikar.commands.BukkitCommandManager;
import io.github.thatkawaiisam.assemble.Assemble;
import lol.vifez.zenty.adapters.ZentyScoreboardAdapter;
import lol.vifez.zenty.commands.ZentyCommand;
import lol.vifez.zenty.listener.PlayerListener;
import lol.vifez.zenty.util.ConfigFile;
import lol.vifez.zenty.util.HubItems;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import lombok.Getter;

/*
 *
 * Zenty is property of Kira Development
 * 15/11/2023
 * Author: Vifez
 *
 */

@Getter
public final class Zenty extends JavaPlugin {

    @Getter private static Zenty instance;

    private ConfigFile messagesFile, scoreboardFile;
    private HubItems items;

    @Override
    public void onEnable() {
        instance = this;

        loadFiles();
        loadOthers();
        registerListeners();
        registerCommands();

        getServer().getConsoleSender().sendMessage(messagesFile.getString("plugin-enabled"));
    }

    private void loadFiles() {
        saveDefaultConfig();

        this.messagesFile = new ConfigFile(this, "messages.yml");
        this.scoreboardFile = new ConfigFile(this, "scoreboard.yml");
    }

    private void loadOthers() {
        this.items = new HubItems(this);

        new Assemble(this, new ZentyScoreboardAdapter(this));
    }

    private void registerListeners() {
        new PlayerListener(this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    private void registerCommands() {
        BukkitCommandManager commandManager = new BukkitCommandManager(this);

        commandManager.registerCommand(new ZentyCommand(this));
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(messagesFile.getString("plugin-disabled"));
    }
}
