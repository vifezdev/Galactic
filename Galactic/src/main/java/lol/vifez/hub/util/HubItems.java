package lol.vifez.hub.util;

/*
 *
 * Galactic is property of Vifez
 * 1/13/2024
 *
 */

import com.cryptomorin.xseries.XMaterial;
import lol.vifez.hub.HubCore;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public class HubItems {

    private final HubCore plugin;
    private final ItemStack enderPearl, serverSelector, playerVisibilityOff, playerVisibilityOn;

    public HubItems(HubCore galactic) {
        this.plugin = galactic;

        // Items
        this.enderPearl = new ItemBuilder(Material.ENDER_PEARL)
                .name("&bEnder Butt")
                .lore("&7Right click to ride on a pearl!")
                .build();
        this.serverSelector = new ItemBuilder(Material.NETHER_STAR)
                .name("&bServer Selector")
                .lore("&7Right click to select a game to play on!")
                .build();
        this.playerVisibilityOff = new ItemBuilder(XMaterial.GRAY_DYE.parseItem())
                .name("&7Visibility: &cOFF")
                .lore("&7Right click to &ashow &7all of the players!")
                .build();
        this.playerVisibilityOn = new ItemBuilder(XMaterial.LIME_DYE.parseItem())
                .name("&7Visibility: &aON")
                .lore("&7Right click to &chide &7all of the players!")
                .build();
    }
}