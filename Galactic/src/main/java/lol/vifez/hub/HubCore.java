package lol.vifez.hub;

import co.aikar.commands.BukkitCommandManager;
import io.github.thatkawaiisam.assemble.Assemble;
import lol.vifez.hub.adapters.GalacticScoreboardAdapter;
import lol.vifez.hub.commands.GalacticCommand;
import lol.vifez.hub.listener.PlayerListener;
import lol.vifez.hub.util.ConfigFile;
import lol.vifez.hub.util.HubItems;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import lombok.Getter;

/*
 *
 * HubCore is property of Kira Development
 * 15/11/2023
 * Author: Vifez
 *
 */

@Getter
public final class HubCore extends JavaPlugin {

    @Getter private static HubCore instance;

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

        new Assemble(this, new GalacticScoreboardAdapter(this));
    }

    private void registerListeners() {
        new PlayerListener(this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    private void registerCommands() {
        BukkitCommandManager commandManager = new BukkitCommandManager(this);

        commandManager.registerCommand(new GalacticCommand(this));
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(messagesFile.getString("plugin-disabled"));
    }
}