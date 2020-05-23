package net.cascene.playerattributeeditor;

import net.cascene.playerattributeeditor.modifiers.StrengthModifier;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class PlayerAttributeEditor extends JavaPlugin implements Listener {

    public static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    public static String prefix = "[PlayerAttEditor] ";
    PluginManager pm = getServer().getPluginManager();

    @Override
    public void onEnable() {
        console.sendMessage(prefix + "Plugin successfully loaded. Made by taydeo for the Cascene Network, released to SpigotMC.org");
        pm.registerEvents(this, this);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        ArrayList permissions = new ArrayList < String > (); // For storing permissions
        Player whoWasHit = null;

        if (e.getEntity() instanceof Player) {
            whoWasHit = (Player) e.getEntity(); // obviously gets player who was damaged lol
        }

        assert whoWasHit != null;
  /*
  Here's where shit gets interesting
  So I basically made it cycle through all the players perms and
  it gets all the permission nodes that start with "playeratteditor."
   */
        for (PermissionAttachmentInfo permission: whoWasHit.getEffectivePermissions())
            if (permission.getPermission().startsWith("playeratteditor.")) {
                permissions.add(String.valueOf(permission.getPermission().startsWith("playeratteditor.")));
            }
        new StrengthModifier(permissions, whoWasHit); // then sends the array list to StrengthModifier
    }

    @Override
    public void onDisable() {
        console.sendMessage(prefix + "Plugin unloaded.");
    }
}