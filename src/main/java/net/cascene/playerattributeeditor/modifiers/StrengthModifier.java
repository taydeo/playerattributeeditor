package net.cascene.playerattributeeditor.modifiers;

import net.cascene.playerattributeeditor.PlayerAttributeEditor;
import net.cascene.playerattributeeditor.listeners.PermissionArrayList;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.Objects;

public class StrengthModifier {
    PlayerInteractEvent event;
    Player player = event.getPlayer();

    ArrayList strengthPermissionsList;

    {
        try {
            strengthPermissionsList = (ArrayList) new PermissionArrayList().call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int StrengthModifier() {
        int ModifierInt = 0;
        if (strengthPermissionsList.contains("playeratteditor.strength.")) {
            PermissionAttachmentInfo perms = (PermissionAttachmentInfo) player.getEffectivePermissions();
            if (perms.getPermission().startsWith("playeratteditor.strength.")) {
                ModifierInt = Integer.parseInt(perms.getPermission().split("team.maxsize.")[2]);
            }

        } else {
            System.out.println(PlayerAttributeEditor.prefix + "Error encountered.");
        }
        return ModifierInt;
    }

    public void ChangePlayerStrengthStat() {
        double dBeforeMath = StrengthModifier();
        double d = dBeforeMath/2;

        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(d);
    }

}
