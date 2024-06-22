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
import xyz.dashnetwork.luna.channel.channels.input.ChannelInDisplayName;
import xyz.dashnetwork.luna.channel.channels.input.ChannelInUserData;
import xyz.dashnetwork.luna.channel.channels.input.ChannelInVanish;
import xyz.dashnetwork.luna.channel.channels.output.ChannelOutBroadcast;
import xyz.dashnetwork.luna.channel.channels.output.ChannelOutSignSpy;
import xyz.dashnetwork.luna.command.LunaCommand;
import xyz.dashnetwork.luna.command.commands.CommandBuild;
import xyz.dashnetwork.luna.command.commands.CommandCenter;
import xyz.dashnetwork.luna.command.commands.CommandNightVision;
import xyz.dashnetwork.luna.command.commands.CommandPeek;
import xyz.dashnetwork.luna.listeners.*;
import xyz.dashnetwork.luna.listeners.mc112.PaperServerListPingListener;
import xyz.dashnetwork.luna.listeners.protocollib.ServerInfoAdapter;
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
        Channel.registerIn("displayname", ChannelInDisplayName::new);
        Channel.registerIn("twofactor", ChannelInUserData::new);
        Channel.registerIn("vanish", ChannelInVanish::new);
        Channel.registerOut("broadcast", ChannelOutBroadcast::new);
        Channel.registerOut("signspy", ChannelOutSignSpy::new);

        getLogger().info("Registering listeners...");
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new AsyncPlayerChatListener(), this);
        manager.registerEvents(new BlockBreakListener(), this);
        manager.registerEvents(new BlockPlaceListener(), this);
        manager.registerEvents(new PlayerInteractListener(), this);
        manager.registerEvents(new PlayerJoinListener(), this);
        manager.registerEvents(new PlayerLoginListener(), this);
        manager.registerEvents(new PlayerMoveListener(), this);
        manager.registerEvents(new PlayerQuitListener(), this);
        manager.registerEvents(new SignChangeListener(), this);

        if (!getConfig().getBoolean("spawn-chunks"))
            manager.registerEvents(new WorldInitListener(), this);

        if (PlatformUtils.getServerVersion() >= 12)
            manager.registerEvents(new PaperServerListPingListener(), this);
        else if (manager.isPluginEnabled("ProtocolLib"))
            new ServerInfoAdapter(this);

        getLogger().info("Registering commands...");
        new CommandBuild();
        new CommandCenter();
        new CommandNightVision();
        new CommandPeek();

        // Wait until all plugins have loaded.
        getServer().getScheduler().scheduleSyncDelayedTask(this, LunaCommand::setupRemaps);

        getLogger().info("Startup complete. (took " + (System.currentTimeMillis() - start) + "ms)");
    }

}
