package lol.vifez.zenty.listener;

import lol.vifez.zenty.Zenty;
import lol.vifez.zenty.util.C;
import lol.vifez.zenty.util.menus.impl.ServerSelectorMenu;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

/*
 *
 * Zenty is a property of Kira-Development-Team
 * 1/13/2024
 * Coded by the founders of Kira-Development-Team
 * EmpireMTR & Vifez
 *
 */

public class PlayerListener implements Listener {

    private final Zenty plugin;

    public PlayerListener(Zenty plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setGameMode(GameMode.ADVENTURE);
        player.setAllowFlight(true);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            player.showPlayer(onlinePlayer);
        }

        player.getInventory().setItem(2, plugin.getItems().getEnderPearl());
        player.getInventory().setItem(4, plugin.getItems().getServerSelector());
        player.getInventory().setItem(6, plugin.getItems().getPlayerVisibilityOn());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(event.getItem() == null || event.getItem().getType().equals(Material.AIR)) return;
        if(event.getItem().isSimilar(plugin.getItems().getEnderPearl())) {
            event.setCancelled(true);

            if (player.getVehicle() != null && player.getVehicle() instanceof EnderPearl) {
                player.getVehicle().remove();
            }

            EnderPearl enderPearl = player.launchProjectile(EnderPearl.class);
            enderPearl.setPassenger(player);
            enderPearl.setVelocity(player.getLocation().getDirection().multiply(plugin.getConfig().getDouble("ender-butt.power")));
            player.updateInventory();
        } else if (event.getItem().isSimilar(plugin.getItems().getPlayerVisibilityOn())) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                player.hidePlayer(players);
            }

            C.sendMessage(player, "&cYou have toggled off player visibility!");
            player.getInventory().setItem(6, plugin.getItems().getPlayerVisibilityOff());
            player.updateInventory();
        } else if (event.getItem().isSimilar(plugin.getItems().getPlayerVisibilityOff())) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                player.showPlayer(players);
            }

            C.sendMessage(player, "&aYou have toggled on player visibility!");
            player.getInventory().setItem(6, plugin.getItems().getPlayerVisibilityOn());
            player.updateInventory();
        } else if (event.getItem().isSimilar(plugin.getItems().getServerSelector())) {
            new ServerSelectorMenu(plugin).openMenu(player);
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof EnderPearl) {
            if (event.getEntity().getShooter() instanceof Player) {
                ((Player) event.getEntity().getShooter()).getLocation().add(0, 2, 0);
            }
        }
    }

    @EventHandler
    public void onEntityDismount(EntityDismountEvent event) {
        if (event.getEntity() instanceof Player) {
            event.getDismounted().remove();
            event.getEntity().getLocation().add(0, 2, 0);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (event.getPlayer().isInsideVehicle()) {
            event.getPlayer().getVehicle().remove();
        }
    }
}