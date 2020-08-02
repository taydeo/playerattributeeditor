package com.github.taydeo.playerattributeeditor.modifiers;

import com.github.taydeo.playerattributeeditor.PlayerAttributeEditor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.String.valueOf;

public class ResistModifier {
    public ResistModifier(@NotNull ArrayList <String> permissions, @NotNull Player player) {
        if (permissions.contains("playeratteditor.kbresist.")) {
            // looks for "playeratteditor.resist." and stores it in resistPermission
            int permissionInteger = Integer.parseInt(valueOf(permissions.lastIndexOf(".") + 1));
            // parses the permission to find the integer at the end of the permission node
            double d = (double) permissionInteger * 0.01; // Minecraft uses a 0.0 to 1.0 scale for kb resistance

            if (d < 1.0) {
                d = 1.0; // sanity check
            }

            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(d);
            // doesnt matter what you set it to, as long as its an integer.
        } else {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(0.0);
            if (PlayerAttributeEditor.debug) {
                System.out.println("Using default 0.0 since no permission node was given for KNOCKBACK RESISTANCE.");
            }
        }
    }
}