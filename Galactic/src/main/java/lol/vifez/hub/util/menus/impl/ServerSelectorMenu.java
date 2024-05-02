package lol.vifez.hub.util.menus.impl;

/*
 *
 * Galactic is property of Vifez
 * 1/13/2024
 *
 */

import com.cryptomorin.xseries.XMaterial;
import lol.vifez.hub.HubCore;
import lol.vifez.hub.util.C;
import lol.vifez.hub.util.menus.Button;
import lol.vifez.hub.util.menus.Menu;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ServerSelectorMenu extends Menu {

    private final HubCore plugin;

    @Override
    public String getTitle(Player player) {
        return C.color(plugin.getConfig().getString("server-selector.title"));
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        // Iterate through the rows
        for (int row = 0; row < 3; row++) {
            String rowPath = "server-selector.rows." + row;

            // Iterate through the items in the row
            List<String> items = plugin.getConfig().getStringList(rowPath);
            for (int i = 0; i < items.size(); i++) {
                String itemConfig = items.get(i);
                String[] itemData = itemConfig.split(",");
                buttons.put(i + row * 9, createButton(itemData, rowPath + "." + i));
            }
        }

        return buttons;
    }

    private ServerSelectorButton createButton(String[] itemData, String path) {
        try {
            XMaterial xMaterial = XMaterial.valueOf(itemData[0].trim());
            Material material = xMaterial.parseMaterial();
            String name = itemData[2].trim();
            boolean addLore = Boolean.parseBoolean(itemData[3].trim());
            String actionType = itemData[4].trim().toUpperCase();
            String actionValue = itemData[5].trim();
            short durability = Short.parseShort(itemData[1].trim());
            List<String> lore = addLore ? plugin.getConfig().getStringList(path + ".lore") : null;

            return new ServerSelectorButton(material, name, actionType, actionValue, durability, addLore, lore);
        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("Invalid material specified in config for path " + path + ": " + itemData[0].trim());
            return null;
        }
    }
}