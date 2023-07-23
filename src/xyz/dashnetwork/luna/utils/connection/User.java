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

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.Luna;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class User {

    private static final List<User> users = new ArrayList<>();
    private static final Luna plugin = Luna.getInstance();
    private final Player player;
    private boolean authenticated;

    private User(Player player) {
        this.player = player;
        this.authenticated = !plugin.getConfig().getBoolean("hold-for-2fa");

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

    public void remove() { users.remove(this); }

    public Player getPlayer() { return player; }

    public boolean isAuthenticated() { return authenticated; }

    public void setAuthenticated(boolean authenticated) { this.authenticated = authenticated; }

    public boolean isStaff() {
        throw new UnsupportedOperationException(); // TODO
    }

    public boolean isAdmin() {
        throw new UnsupportedOperationException(); // TODO
    }

    public boolean isOwner() {
        throw new UnsupportedOperationException(); // TODO
    }

}
