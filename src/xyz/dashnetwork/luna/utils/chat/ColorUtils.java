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

package xyz.dashnetwork.luna.utils.chat;

import org.jetbrains.annotations.NotNull;

public final class ColorUtils {

    public static String fromAmpersand(@NotNull String string) {
        return string.replaceAll("&([0-f]|[k-o]|r|x)", "§$1");
    }

    public static String strip(@NotNull String string) {
        return string.replaceAll("[&§]([0-f]|[k-o]|r|x)", "");
    }

}
