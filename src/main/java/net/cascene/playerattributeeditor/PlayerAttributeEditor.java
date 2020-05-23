package net.cascene.playerattributeeditor;

import net.cascene.playerattributeeditor.listeners.PlayerHitEvent;
import net.cascene.playerattributeeditor.modifiers.StrengthModifier;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
        if (e.getEntity() instanceof Player) {
            new StrengthModifier().ChangePlayerStrengthStat();
        }
    }

    @Override
    public void onDisable() {
        console.sendMessage(prefix + "Plugin unloaded.");
    }
}
