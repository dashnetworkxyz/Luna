/*
 * Copyright (C) 2022 Andrew Bell - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 */

package xyz.dashnetwork.luna;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import xyz.dashnetwork.luna.listeners.PlayerJoinListener;
import xyz.dashnetwork.luna.listeners.PlayerQuitListener;
import xyz.dashnetwork.luna.test.ChannelListener;
import xyz.dashnetwork.luna.utils.PlatformUtils;

public final class Luna extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new PlayerJoinListener(), this);
        manager.registerEvents(new PlayerQuitListener(), this);

        Messenger messenger = getServer().getMessenger();
        messenger.registerIncomingPluginChannel(this, "dn:displayname", new ChannelListener());

        int version = PlatformUtils.getServerVersion();

        if (version >= 12) {
            // 1.12
        }

        if (version >= 17) {
            // 1.17
        }
    }

}
