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

package xyz.dashnetwork.luna.utils;

import org.bukkit.command.CommandSender;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.function.Predicate;

public enum PermissionType {

    GLOBAL(user -> true),
    STAFF(User::isStaff),
    ADMIN(User::isAdmin),
    OWNER(User::isOwner);

    private final Predicate<User> predicate;

    PermissionType(Predicate<User> predicate) { this.predicate = predicate; }

    public Predicate<User> getPredicate() { return predicate; }

    public boolean hasPermission(CommandSender sender) {
        return User.getUser(sender).map(predicate::test).orElse(true);
    }

}
