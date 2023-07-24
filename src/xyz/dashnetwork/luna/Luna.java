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

package xyz.dashnetwork.luna;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.dashnetwork.luna.channel.Channel;
import xyz.dashnetwork.luna.channel.channels.input.ChannelDisplayName;
import xyz.dashnetwork.luna.channel.channels.input.ChannelTwoFactor;
import xyz.dashnetwork.luna.commands.CommandBuild;
import xyz.dashnetwork.luna.commands.CommandNightVision;
import xyz.dashnetwork.luna.listeners.*;
import xyz.dashnetwork.luna.listeners.mc112.PaperServerListPingListener;
import xyz.dashnetwork.luna.listeners.protocollib.ServerInfoListener;
import xyz.dashnetwork.luna.utils.PlatformUtils;

public final class Luna extends JavaPlugin {

    private static Luna instance;

    public static Luna getInstance() { return instance; }

    @Override
    public void onEnable() {
        getLogger().info("Starting...");
        long start = System.currentTimeMillis();

        instance = this;

        saveDefaultConfig();

        getLogger().info("Registering channels...");
        Channel.registerIn("displayname", ChannelDisplayName::new);
        Channel.registerIn("twofactor", ChannelTwoFactor::new);

        getLogger().info("Registering listeners...");
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new BlockBreakListener(), this);
        manager.registerEvents(new BlockPlaceListener(), this);
        manager.registerEvents(new PlayerInteractListener(), this);
        manager.registerEvents(new PlayerJoinListener(), this);
        manager.registerEvents(new PlayerLoginListener(), this);
        manager.registerEvents(new PlayerMoveListener(), this);
        manager.registerEvents(new PlayerQuitListener(), this);

        if (PlatformUtils.getServerVersion() >= 12)
            manager.registerEvents(new PaperServerListPingListener(), this);
        else if (manager.isPluginEnabled("ProtocolLib"))
            new ServerInfoListener(this);

        getLogger().info("Registering commands...");
        getCommand("build").setExecutor(new CommandBuild());
        getCommand("nightvision").setExecutor(new CommandNightVision());

        getLogger().info("Scheduling tasks...");
        // TODO: Tasks

        getLogger().info("Startup complete. (took " + (System.currentTimeMillis() - start) + "ms)");
    }

}
