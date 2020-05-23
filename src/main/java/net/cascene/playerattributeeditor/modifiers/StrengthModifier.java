package net.cascene.playerattributeeditor.modifiers;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class StrengthModifier {

    public StrengthModifier(@NotNull ArrayList permissions, Player whoWasHit) {
        int i = 0;
        if (permissions.contains("playeratteditor.strength.")) {
            String strengthPermissionNode = String.valueOf(permissions);
            i = Integer.parseInt(String.valueOf(strengthPermissionNode.lastIndexOf(".") + 1));
            // returns the strength permission node and gets the number after the last "."
            // it was hell trying to figure this out.
        }
        double dBeforeMath = i;
        double d = dBeforeMath / 2;

        Objects.requireNonNull(whoWasHit.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(d);
    }
}