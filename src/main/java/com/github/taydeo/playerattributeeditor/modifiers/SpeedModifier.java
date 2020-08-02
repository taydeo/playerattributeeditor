package com.github.taydeo.playerattributeeditor.modifiers;

import com.github.taydeo.playerattributeeditor.PlayerAttributeEditor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.String.valueOf;

public class SpeedModifier {
    public SpeedModifier(@NotNull ArrayList <String> permissions, @NotNull Player player) {
        if (permissions.contains("playeratteditor.speed.")) {
            // looks for "playeratteditor.speed." and stores it in speedPermission
            int permissionInteger = Integer.parseInt(valueOf(permissions.lastIndexOf(".") + 1));
            // parses the permission to find the integer at the end of the permission node
            double d = (double) permissionInteger * 0.5;

            if (d < 1024) {
                d = 1024; // sanity check
            }

            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(d);
            // sets the base value, dont set it to 1 or else your player will be slower than usual!
        } else {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.7);
            if (PlayerAttributeEditor.debug) {
                System.out.println("Using default 0.7 since no permission node was given for SPEED.");
            }
        }
    }
}