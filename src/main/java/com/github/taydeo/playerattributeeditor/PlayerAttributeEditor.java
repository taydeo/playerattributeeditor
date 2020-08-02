package com.github.taydeo.playerattributeeditor;

import com.github.taydeo.playerattributeeditor.modifiers.JumpModifier;
import com.github.taydeo.playerattributeeditor.modifiers.ResistModifier;
import com.github.taydeo.playerattributeeditor.modifiers.SpeedModifier;
import com.github.taydeo.playerattributeeditor.modifiers.StrengthModifier;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.logging.Logger;

public final class PlayerAttributeEditor extends JavaPlugin implements Listener {
    public static boolean debug;
    Logger console = Bukkit.getLogger();
    String prefix = "[PlayerAttEditor] ";
    FileConfiguration config = getConfig();
    PlayerAttributeEditor plugin;
    LuckPerms luckPerms;

    @Override
    public void onEnable() {
        // finds luckperms instance
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPerms = provider.getProvider();
        }

        new LuckPermsHook(plugin, luckPerms);

        config.addDefault("debug", false);
        config.options().copyDefaults(true);
        saveConfig();
        debug = getConfig().getBoolean("debug");

        if (debug) {
            System.out.println(prefix + "Debug mode enabled.");
        }

        console.info(ChatColor.GOLD + prefix + "PlayerAttributeEditor, made by taydeo!");
    }

    @EventHandler // event independent of luckperms, supposed to load all previous permission nodes
    public void onPlayerJoin(PlayerJoinEvent p) {
        Player player = p.getPlayer();

        setPlayerAttributes(player);
    }

    public static void setPlayerAttributes(Player player) { // searches through player perm nodes
        ArrayList<String> permissions = new ArrayList<>();
        for (PermissionAttachmentInfo permInfo : player.getEffectivePermissions()) {
            if (permInfo.getPermission().startsWith("playeratteditor.")) {
                permissions.add(permInfo.getPermission());
                new StrengthModifier(permissions, player); // then sends the array list to all modifiers
                new ResistModifier(permissions, player);
                new JumpModifier(permissions, player);
                new SpeedModifier(permissions, player);
                if (debug) {
                    System.out.println(permissions);
                    System.out.println(player);
                }
            }
        }
    }

    @Override
    public void onDisable() {
        console.info(prefix + "Plugin unloaded.");
    }
}