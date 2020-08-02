package com.github.taydeo.playerattributeeditor.modifiers;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.String.valueOf;

public class ResistModifier {
    public ResistModifier(@NotNull ArrayList <String> permissions, @NotNull Player player) {
        if (permissions.contains("playeratteditor.jump.")) {
            // looks for "playeratteditor.resist." and stores it in resistPermission
            int permissionInteger = Integer.parseInt(valueOf(permissions.lastIndexOf(".") + 1));
            // parses the permission to find the integer at the end of the permission node
            double d = (double) permissionInteger * 0.01;
            /*
              So lets say you have 4 as your integer, it makes the 4 into 0.04. Then that becomes your Knockback Resist Modifier.
              For any noticable effect, I would recommend using 10s. Multiplying by 0.01 lets you be more precise
              and calculated for your specific Knockback Resist values.
             */
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(d);
            // doesnt matter what you set it to, as long as its an integer.
        } else {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(0.0);
            System.out.println("Machine broke, using default 0.0.");
        }
    }
}