package net.cascene.playerattributeeditor.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerHitEvent implements Listener {

    private Player whoWasHit = null;

    @EventHandler
    public Player onHit(EntityDamageByEntityEvent e) {
        whoWasHit = null;
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
             whoWasHit = (Player) e.getEntity();
        }
        return whoWasHit;
    }

    public Player getWhoWasHit() {
        return whoWasHit;
    }
}
