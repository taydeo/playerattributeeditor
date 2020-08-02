package com.github.taydeo.playerattributeeditor;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.user.UserDataRecalculateEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LuckPermsHook {

    private final PlayerAttributeEditor plugin;

    public LuckPermsHook(PlayerAttributeEditor plugin, LuckPerms luckPerms) {
        this.plugin = plugin;
        EventBus eventBus = luckPerms.getEventBus();

        eventBus.subscribe(UserDataRecalculateEvent.class, this::onUserDataRecalc);
    }

    private void onUserDataRecalc(UserDataRecalculateEvent event) {
        Bukkit.getScheduler().runTask(plugin, () -> { // gets us back to the main thread so it can be threadsafe
            Player player = (Player) event.getUser();
            PlayerAttributeEditor.setPlayerAttributes(player);
        });
    }
}
