package net.cascene.playerattributeeditor.modifiers;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.String.valueOf;

public class ResistModifier {
    public ResistModifier(@NotNull ArrayList permissions, @NotNull Player whoWasHit) {
        for (Object permission: permissions) {
            String resistPermission = (String) permission; // converts object into string cuz it be annoying
            if (resistPermission.contains("playeratteditor.resist.")) {
                // looks for "playeratteditor.resist." and stores it in resistPermission
                int permissionInteger = Integer.parseInt(valueOf(resistPermission.lastIndexOf(".") + 1));
                // parses the permission to find the integer at the end of the permission node
                double d = (double) permissionInteger * 0.; // this is one of the few that require a special formula
                Objects.requireNonNull(whoWasHit.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(d);
                // sets the base value, dont set it to 1 or else your player will be slower than usual!

            } else {
                Objects.requireNonNull(whoWasHit.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(0.0);
                System.out.println("Machine broke, using default 0.0.");
            }
        }
    }
}
