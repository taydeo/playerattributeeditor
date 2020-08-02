package com.github.taydeo.playerattributeeditor.modifiers;

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
            Objects.requireNonNull(player.getPlayer()).getLocation().getDirection().multiply((double) permissionInteger);
            // any value you put will increase the players jump height in all directions.
        } else {
            Objects.requireNonNull(player.getPlayer()).getLocation().getDirection().multiply(1);
            System.out.println("Using default 1.0 since no permission node was given for JUMP.");
        }
    }
}