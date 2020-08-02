package com.github.taydeo.playerattributeeditor.modifiers;

import com.github.taydeo.playerattributeeditor.PlayerAttributeEditor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.String.valueOf;

public class StrengthModifier {
    public StrengthModifier(@NotNull ArrayList <String> permissions, @NotNull Player player) { // converts object into string cuz it be annoying
        if (permissions.contains("playeratteditor.strength.")) {
            // looks for "playeratteditor.strength." and stores it in strengthPermission
            int permissionInteger = Integer.parseInt(valueOf(permissions.lastIndexOf(".") + 1));
            // parses the permission to find the integer at the end of the permission node
            double d = (double) permissionInteger * 0.5;

            if (d < 2048) {
                d = 2048; // sanity check since max is 2048
            }
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(d);
            // sets the base value, dont set it to 1 or else your player will be weaker than usual!
        } else {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(1);
            if (PlayerAttributeEditor.debug) {
                System.out.println("Using default 1.0 since no permission node was given for STRENGTH.");
            }
        }
    }
}