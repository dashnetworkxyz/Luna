/*
 * Luna
 * Copyright (C) 2023  DashNetwork
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.dashnetwork.luna.channel;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.plugin.messaging.Messenger;
import xyz.dashnetwork.luna.Luna;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class Channel {

    private static final Map<String, Supplier<Channel>> outputMap = new HashMap<>();
    private static final Messenger messenger = Bukkit.getMessenger();
    private static final Luna plugin = Luna.getInstance();

    @SuppressWarnings("UnstableApiUsage")
    protected final ByteArrayDataOutput output = ByteStreams.newDataOutput();

    @SuppressWarnings("UnstableApiUsage")
    public static void registerIn(String name, Supplier<Channel> supplier) {
        messenger.registerIncomingPluginChannel(plugin, "dn:" + name,
                (s, p, data) -> supplier.get().receive(ByteStreams.newDataInput(data))
        );
    }

    public static void registerOut(String name, Supplier<Channel> supplier) {
        messenger.registerOutgoingPluginChannel(plugin, "dn:" + name);
        outputMap.put(name, supplier);
    }

    public static void call(String name, Object... objects) {
        Supplier<Channel> supplier = outputMap.get(name);

        if (supplier != null) {
            Channel channel = supplier.get();
            channel.send(objects);

            Bukkit.getOnlinePlayers().stream().findAny().ifPresent(player ->
                    player.sendPluginMessage(plugin, "dn:" + name, channel.output.toByteArray())
            );
        }
    }

    protected void receive(ByteArrayDataInput input) {}

    protected void send(Object[] objects) {}

}
