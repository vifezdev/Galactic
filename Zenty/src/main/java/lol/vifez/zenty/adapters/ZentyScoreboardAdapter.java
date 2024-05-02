package lol.vifez.zenty.adapters;

/*
 *
 * Zenty is a property of Kira-Development-Team
 * 1/14/2024
 * Coded by the founders of Kira-Development-Team
 * EmpireMTR & Vifez
 *
 */

import io.github.thatkawaiisam.assemble.AssembleAdapter;
import lol.vifez.zenty.Zenty;
import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.List;

@RequiredArgsConstructor
public class ZentyScoreboardAdapter implements AssembleAdapter {

    private final Zenty plugin;

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
