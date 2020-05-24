package net.cascene.playerattributeeditor;

import net.cascene.playerattributeeditor.modifiers.ResistModifier;
import net.cascene.playerattributeeditor.modifiers.SpeedModifier;
import net.cascene.playerattributeeditor.modifiers.StrengthModifier;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class PlayerAttributeEditor extends JavaPlugin implements Listener {

    public static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    public static String prefix = "[PlayerAttEditor] ";
    PluginManager pm = getServer().getPluginManager();
    public static boolean debug;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        console.sendMessage(prefix + "Plugin successfully loaded. Made by taydeo for the Cascene Network, released to SpigotMC.org");
        pm.registerEvents(this, this);
        config.addDefault("debug", false);
        config.options().copyDefaults(true);
        saveConfig();
        debug = getConfig().getBoolean("debug");
        if (debug) {
            System.out.println(prefix + "Debug mode enabled.");
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        @NotNull ArrayList < String > permissions = new ArrayList < > (); // For storing permissions
        Player whoWasHit;

        if (e.getEntity() instanceof Player) {
            whoWasHit = (Player) e.getEntity(); // obviously gets player who was damaged lol
   /*
       Here's where shit gets interesting
       So I basically made it cycle through all the players perms and
       it gets all the permission nodes that start with "playeratteditor."
   */
            for (PermissionAttachmentInfo permInfo: whoWasHit.getEffectivePermissions()) {
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
    public void onSprint(EntityEvent e) {
        @NotNull ArrayList < String > permissions = new ArrayList < > (); // For storing permissions... again
        Player daSprinter = (Player) e.getEntity();

        if (daSprinter.isSprinting()) { // obviously checks if the player is running
            for (PermissionAttachmentInfo permInfo: daSprinter.getEffectivePermissions()) {
                if (permInfo.getPermission().startsWith("playeratteditor.")) {
                    permissions.add(permInfo.getPermission());
                    new SpeedModifier(permissions, daSprinter); // then sends the array list to SpeedModifier
                    if (debug) {
                        System.out.println(permissions);
                        System.out.println(daSprinter);
                    }
                }
            }
        }
    }

    @Override
    public void onDisable() {
        console.sendMessage(prefix + "Plugin unloaded.");
    }
}
