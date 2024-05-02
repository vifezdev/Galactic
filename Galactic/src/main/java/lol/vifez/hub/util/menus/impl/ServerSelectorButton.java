package lol.vifez.hub.util.menus.impl;

import lol.vifez.hub.util.C;
import lol.vifez.hub.util.PlayerUtil;
import lol.vifez.hub.util.menus.Button;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.List;

@RequiredArgsConstructor
public class ServerSelectorButton extends Button {

    private final Material material;
    private final String name, action, actionValue;
    private final short durability;
    private final boolean addLore;
    private final List<String> lore;

    @Override
    public String getName(Player player) {
        return C.color(name);
    }

    @Override
    public List<String> getDescription(Player player) {
        return addLore ? C.color(lore) : null;
    }

    @Override
    public Material getMaterial(Player player) {
        return material;
    }

    @Override
    public short getDurability(Player player) {
        return durability;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType) {
        switch (action.toUpperCase()) {
            case "SERVER":
                player.closeInventory();
                PlayerUtil.sendToServer(player, actionValue);
                break;
            case "COMMAND":
                player.closeInventory();
                player.performCommand(actionValue);
                break;
            default:
                player.closeInventory();
                break;
        }
    }
}