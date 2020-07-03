package net.cascene.playerattributeeditor;

import net.cascene.playerattributeeditor.modifiers.JumpModifier;
import net.cascene.playerattributeeditor.modifiers.ResistModifier;
import net.cascene.playerattributeeditor.modifiers.SpeedModifier;
import net.cascene.playerattributeeditor.modifiers.StrengthModifier;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.logging.Logger;

public final class PlayerAttributeEditor extends JavaPlugin implements Listener {

    Logger console = Bukkit.getLogger();
    String prefix = "[PlayerAttEditor] ";
    PluginManager pm = getServer().getPluginManager();
    public static boolean debug;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        pm.registerEvents(this, this);
        config.addDefault("debug", false);
        config.options().copyDefaults(true);
        saveConfig();
        debug = getConfig().getBoolean("debug");
        if (debug) {
            System.out.println(prefix + "Debug mode enabled.");
        }
        console.info(ChatColor.GOLD + prefix + "Plugin successfully loaded. Made by taydeo for the Cascene Network, released to SpigotMC.org");
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        @NotNull ArrayList <String> permissions = new ArrayList <>(); // For storing permissions
        Player whoWasHit;

        if (e.getEntity() instanceof Player) {
            whoWasHit = (Player) e.getEntity(); // obviously gets player who was damaged lol
   /*
       Here's where shit gets interesting
       So I basically made it cycle through all the players perms and
       it gets all the permission nodes that start with "playeratteditor."
   */
            for (PermissionAttachmentInfo permInfo : whoWasHit.getEffectivePermissions()) {
                if (permInfo.getPermission().startsWith("playeratteditor.")) {
                    permissions.add(permInfo.getPermission());
                    new StrengthModifier(permissions, whoWasHit); // then sends the array list to StrengthModifier
                    new ResistModifier(permissions, whoWasHit); // theoretically should also send it to ResistModifier
                    if (debug) {
                        System.out.println(permissions);
                        System.out.println(whoWasHit);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent p) { // now uses PlayerMoveEvent
        @NotNull ArrayList <String> permissions = new ArrayList <>(); // For storing permissions... again
        Player player = p.getPlayer();
        Location from = p.getFrom();
        Location to = p.getTo();

        for (PermissionAttachmentInfo permInfo: player.getEffectivePermissions()) {
            assert to != null;
            if (from.getBlockY() < to.getBlockY() && !player.isSwimming() && !player.isFlying()) { // wasnt there a player.isJumping method?
                if (permInfo.getPermission().startsWith("playeratteditor.")) {
                    permissions.add(permInfo.getPermission());
                    new JumpModifier(permissions, player); // then sends the array list to SpeedModifier
                    if (debug) {
                        System.out.println(permissions);
                        System.out.println(player);
                    }
                }
            }

            if (player.isSprinting()) { // obviously checks if the player is running
                if (permInfo.getPermission().startsWith("playeratteditor.")) {
                    permissions.add(permInfo.getPermission());
                    new SpeedModifier(permissions, player); // then sends the array list to SpeedModifier
                    if (debug) {
                        System.out.println(permissions);
                        System.out.println(player);
                    }
                }
            }
        }
    }

    @Override
    public void onDisable() {
        console.info(prefix + "Plugin unloaded.");
    }
}