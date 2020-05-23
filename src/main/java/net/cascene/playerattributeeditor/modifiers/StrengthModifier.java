package net.cascene.playerattributeeditor.modifiers;

import net.cascene.playerattributeeditor.PlayerAttributeEditor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.String.valueOf;

public class StrengthModifier {

    public StrengthModifier(@NotNull ArrayList permissions, @NotNull Player whoWasHit) {

        for (Object permission : permissions) {
            if ("playeratteditor.strength." == permission) {
                // looks for "playeratteditor.strength." and stores it in strengthPermission
                String strengthPermission = (String) permission;
                int permissionInteger = Integer.parseInt(valueOf(strengthPermission.lastIndexOf(".") + 1));
                // parses the permission to find the integer at the end of the permission node
                double d = (double) permissionInteger / 2;
                if (PlayerAttributeEditor.debug) {
                    System.out.println("Successfully modified generic attack damage. DOUBLE - " + d);
                }
                Objects.requireNonNull(whoWasHit.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(d);
                // sets the base value, dont set it to 1 or else your player will be weaker than usual!
            }
        }
    }
}