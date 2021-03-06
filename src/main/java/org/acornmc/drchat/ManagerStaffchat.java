package org.acornmc.drchat;

import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ManagerStaffchat {
    ConfigManager configManager;
    private static Set<UUID> staffchatToggled = new HashSet<>();

    public ManagerStaffchat(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public static boolean isToggled(UUID uuid) {
        return staffchatToggled.contains(uuid);
    }

    public static void toggle(Player player) {
        if (staffchatToggled.contains(player.getUniqueId())) {
            staffchatToggled.remove(player.getUniqueId());
        } else {
            staffchatToggled.add(player.getUniqueId());
        }
    }

    public static void sendMinecraft(String message, Player player, String format) {
        format = format.replace("%name%", player.getName());
        format = format.replace("%nickname%", player.getDisplayName());
        format = format.replace("%message%", message);
        format = ChatColor.translateAlternateColorCodes('&', format);
        Bukkit.broadcast(format, "drchat.staffchat");

    }

    public static void sendDiscord(String message, Player player) {
        if (Bukkit.getPluginManager().isPluginEnabled("DiscordSRV")) {
            message = ChatColor.translateAlternateColorCodes('&', message);
            DiscordSRV.getPlugin().processChatMessage(player, message, "staff-chat", false);
        }
    }
}
