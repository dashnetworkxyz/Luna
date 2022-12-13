/*
 * Copyright (C) 2022 Andrew Bell - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
 */

package xyz.dashnetwork.luna.test;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.UUID;

public class ChannelListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String id, Player player, byte[] bytes) {
        ByteArrayDataInput input = ByteStreams.newDataInput(bytes);
        String uuid = input.readUTF();
        String displayname = input.readUTF();

        System.out.println("received");

        Bukkit.getPlayer(UUID.fromString(uuid)).setPlayerListName(displayname);
    }

}
