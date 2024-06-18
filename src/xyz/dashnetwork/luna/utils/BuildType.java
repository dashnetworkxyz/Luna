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

package xyz.dashnetwork.luna.utils;

import xyz.dashnetwork.luna.utils.connection.User;

import java.util.function.Predicate;

public enum BuildType {

    ALLOWED(user -> true),
    STAFF(User::isStaff),
    ADMIN(User::isAdmin),
    OWNER(User::isOwner),
    DENIED(user -> false);

    private final Predicate<User> predicate;

    BuildType(Predicate<User> predicate) { this.predicate = predicate; }

    public Predicate<User> getPredicate() { return predicate; }

    public static BuildType parse(String string) {
        try {
            return BuildType.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException exception) {
            return BuildType.DENIED;
        }
    }

}
