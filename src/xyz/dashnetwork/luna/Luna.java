/*
 * Luna
 * Copyright (C) 2023  DashNetwork
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
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
import xyz.dashnetwork.luna.listeners.PlayerJoinListener;
import xyz.dashnetwork.luna.listeners.PlayerQuitListener;
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
        // TODO: Channels

        getLogger().info("Registering listeners...");
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new PlayerJoinListener(), this);
        manager.registerEvents(new PlayerQuitListener(), this);

        boolean protocolLib = manager.isPluginEnabled("ProtocolLib");
        int version = PlatformUtils.getServerVersion();

        if (version >= 12)
            manager.registerEvents(new PaperServerListPingListener(), this);
        else if (protocolLib)
            new ServerInfoListener(this);

        getLogger().info("Registering commands...");
        // TODO: Commands

        getLogger().info("Scheduling tasks...");
        // TODO: Tasks

        getLogger().info("Startup complete. (took " + (System.currentTimeMillis() - start) + "ms)");
    }

}
