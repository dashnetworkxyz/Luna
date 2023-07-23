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

import org.bukkit.Bukkit;

public final class PlatformUtils {

    public static double getServerVersion() {
        String result = Bukkit.getVersion().split("\\(MC: ")[1].replaceFirst("(\\d+).(\\d+)(.\\d+)?\\)", "$2$3");
        double version = 8; // Default to 1.8

        try {
            version = Double.parseDouble(result);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }

        return version;
    }

}
