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

import org.bukkit.Bukkit;

public final class PlatformUtils {

    public static int getServerVersion() {
        String result = Bukkit.getVersion().replaceFirst(" \\(MC: (\\d+).(\\d+)(.\\d+)?", "$2");
        int version = 8; // Default to 1.8

        try {
            version = Integer.getInteger(result);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }

        return version;
    }

}