package lol.vifez.hub.util;

/*
 *
 * Galactic is property of Vifez
 * 1/13/2024
 *
 */

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

@UtilityClass
public class C {

    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public List<String> color(List<String> list) {
        list.forEach(C::color);
        return list;
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(color(message));
    }
}