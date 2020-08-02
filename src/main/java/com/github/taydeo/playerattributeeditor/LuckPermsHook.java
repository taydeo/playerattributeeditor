package com.github.taydeo.playerattributeeditor;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.user.UserLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LuckPermsHook {

    private final PlayerAttributeEditor plugin;

    public LuckPermsHook(PlayerAttributeEditor plugin, LuckPerms luckPerms) {
        this.plugin = plugin;

        EventBus eventBus = luckPerms.getEventBus();

        eventBus.subscribe(UserLoadEvent.class, this::onUserDataLoad);
    }

    private void onUserDataLoad(UserLoadEvent event) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            Player player = Bukkit.getPlayer(event.getUser().getUniqueId());
            System.out.println(player);
            if (player != null) {
                PlayerAttributeEditor.setPlayerAttributes(player);
            }
        });
    }
}
