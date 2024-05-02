package lol.vifez.zenty.util;

/*
 *
 * Zenty is a property of Kira-Development-Team
 * 1/13/2024
 * Coded by the founders of Kira-Development-Team
 * EmpireMTR & Vifez
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