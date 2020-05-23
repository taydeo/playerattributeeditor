package net.cascene.playerattributeeditor.listeners;

import net.cascene.playerattributeeditor.PlayerAttributeEditor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class PermissionArrayList implements Callable {

    @Override
    public Object call() {
        ArrayList permissions = new ArrayList<String>();

        Player player = new PlayerHitEvent().getWhoWasHit();
        for(PermissionAttachmentInfo permission : player.getEffectivePermissions())
            if(permission.getPermission().startsWith("playeratteditor.")) {
                permissions.add(permission.getPermission().startsWith("playeratteditor."));
        }
        return permissions;
    }

}
