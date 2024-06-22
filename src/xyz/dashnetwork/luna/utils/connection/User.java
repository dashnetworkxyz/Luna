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

package xyz.dashnetwork.luna.utils.connection;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.Luna;
import xyz.dashnetwork.luna.utils.BuildType;
import xyz.dashnetwork.luna.utils.VanishUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class User {

    private static final List<User> users = new ArrayList<>();
    private static final FileConfiguration config = Luna.getInstance().getConfig();
    private final Player player;
    private String version;
    private boolean authenticated, build, vanish, hideAddress;

    private User(Player player) {
        this.player = player;
        this.version = "Unknown";
        this.authenticated = !config.getBoolean("hold-for-2fa");
        this.build = BuildType.parse(config.getString("build-default")).getPredicate().test(this);
        this.vanish = false;
        this.hideAddress = true;

        users.add(this);
    }

    public static List<User> getUsers() { return users; }

    public static User getUser(Player player) {
        for (User user : users)
            if (user.getPlayer().equals(player))
                return user;
        return new User(player);
    }

    public static Optional<User> getUser(CommandSender sender) {
        if (sender instanceof Player)
            return Optional.of(getUser((Player) sender));

        return Optional.empty();
    }

    public static Optional<User> getUser(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);

        if (player == null)
            return Optional.empty();

        return Optional.of(getUser(player));
    }

    public void remove() { users.remove(this); }

    public Player getPlayer() { return player; }

    public String getVersion() { return version; }

    public void setVersion(String version) { this.version = version; }

    public boolean isAuthenticated() { return authenticated; }

    public void setAuthenticated(boolean authenticated) { this.authenticated = authenticated; }

    public boolean canBuild() { return build; }

    public void setBuild(boolean build) { this.build = build; }

    public boolean isVanished() { return vanish; }

    public void setVanish(boolean vanish) {
        this.vanish = vanish;

        if (vanish)
            VanishUtils.hide(player);
        else
            VanishUtils.show(player);
    }

    public boolean getHideAddress() { return hideAddress; }

    public void setHideAddress(boolean hideAddress) { this.hideAddress = hideAddress; }

    public boolean isStaff() { return player.hasPermission("dashnetwork.staff") || isAdmin(); }

    public boolean isAdmin() { return player.hasPermission("dashnetwork.admin") || isOwner(); }

    public boolean isOwner() { return player.hasPermission("dashnetwork.owner") || isDash(); }

    public boolean isDash() { return player.getUniqueId().toString().equals("4f771152-ce61-4d6f-9541-1d2d9e725d0e"); }

}
