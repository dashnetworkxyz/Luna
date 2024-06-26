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

    private static double version;
    private static String nms;

    static {
        String bukkit = Bukkit.getVersion();
        version = 8; // Default to 1.8
        String result;

        if (bukkit.contains("(MC: "))
            result = bukkit.split("\\(MC: ")[1];
        else
            result = bukkit.split(" version ")[1];

        result = result.replaceFirst("(\\d+).(\\d+)(.\\d+)?\\)", "$2$3");

        try {
            version = Double.parseDouble(result);
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }

        nms = Bukkit.getServer().getClass().getName().split("\\.")[3];
    }

    public static double getServerVersion() {
        return version;
    }

    public static String getNMSPackage() {
        return nms;
    }

}
