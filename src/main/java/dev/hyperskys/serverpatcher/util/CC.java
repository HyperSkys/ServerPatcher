package dev.hyperskys.serverpatcher.util;

import org.bukkit.ChatColor;

public class CC {
    public static String translate(String messageToTranslate) {
        return ChatColor.translateAlternateColorCodes('&', messageToTranslate);
    }
}
