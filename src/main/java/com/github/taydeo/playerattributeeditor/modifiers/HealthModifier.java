package com.github.taydeo.playerattributeeditor.modifiers;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.String.valueOf;

public class HealthModifier {
    public HealthModifier(@NotNull ArrayList<String> permissions, Player player) {
        if (permissions.contains("playeratteditor.maxhealth.")) {
            int permissionInteger = Integer.parseInt(valueOf(permissions.lastIndexOf(".") + 1));

            if (permissionInteger >= 1024) {
                permissionInteger = 1024; // max value you can set it to in Minecraft
            }

            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(permissionInteger);
        } else {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20.0);
            System.out.println("Using default 20.0 since no permission node was given for HEALTH.");
        }
    }
}