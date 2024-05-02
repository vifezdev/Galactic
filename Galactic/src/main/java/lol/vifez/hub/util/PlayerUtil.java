package lol.vifez.hub.util;

/*
 *
 * Galactic is property of Vifez
 * 1/13/2024
 *
 */

import lol.vifez.hub.HubCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PlayerUtil {

    public static void sendToServer(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException e) {
            Bukkit.getLogger().info("You'll never see me!");
        }
        Bukkit.getPlayer(player.getUniqueId()).sendPluginMessage(HubCore.getInstance(), "BungeeCord", b.toByteArray());
    }
}