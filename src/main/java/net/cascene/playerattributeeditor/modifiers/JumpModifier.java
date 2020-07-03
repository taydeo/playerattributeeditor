package net.cascene.playerattributeeditor.modifiers;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.String.valueOf;
// EXPERIMENTAL METHOD TO CHANGE PLAYER JUMP HEIGHT.
public class JumpModifier {
    public JumpModifier(@NotNull ArrayList <String> permissions, Player player) {
        if (permissions.contains("playeratteditor.jump.")) {
            int permissionInteger = Integer.parseInt(valueOf(permissions.lastIndexOf(".") + 1));
            // parses the permission to find the integer at the end of the permission node
            double d = (double) permissionInteger * 0.5;
            Objects.requireNonNull(player.getPlayer()).getLocation().getDirection().multiply(d);
            // any value you put will increase the players jump height in all directions.
        } else {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(1.0);
            System.out.println("Machine broke, using default 1.0.");
        }
    }
}