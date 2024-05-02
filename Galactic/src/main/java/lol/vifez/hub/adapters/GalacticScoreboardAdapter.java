package lol.vifez.hub.adapters;

/*
 *
 * Galactic is property of Vifez
 * 1/13/2024
 *
 */

import io.github.thatkawaiisam.assemble.AssembleAdapter;
import lol.vifez.hub.HubCore;
import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.List;

@RequiredArgsConstructor
public class GalacticScoreboardAdapter implements AssembleAdapter {

    private final HubCore plugin;

    @Override
    public String getTitle(Player player) {
        return plugin.getScoreboardFile().getString("title");
    }

    @Override
    public List<String> getLines(Player player) {
        if(plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            return PlaceholderAPI.setPlaceholders(player, plugin.getScoreboardFile().getStringList("lines"));
        } else {
            return plugin.getScoreboardFile().getStringList("lines");
        }
    }
}
