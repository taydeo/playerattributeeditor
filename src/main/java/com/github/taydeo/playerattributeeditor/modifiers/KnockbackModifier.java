package com.github.taydeo.playerattributeeditor.modifiers;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.String.valueOf;

public class KnockbackModifier {
    public KnockbackModifier(@NotNull ArrayList<String> permissions, Player player) {
        if (permissions.contains("playeratteditor.knockback.")) {
            int permissionInteger = Integer.parseInt(valueOf(permissions.lastIndexOf(".") + 1));

            double d = permissionInteger * 0.01;

            if (d > 8.0) {
                d = 8.0; // sanity check, since knockback is capped at 8
            }

            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK)).setBaseValue(d);
        } else {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK)).setBaseValue(0.0);
            System.out.println("Using default 1.0 since no permission node was given for JUMP.");
        }
    }
}
