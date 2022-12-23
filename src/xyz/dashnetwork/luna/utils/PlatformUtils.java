/*
 * Copyright (C) 2022 Andrew Bell - All Rights Reserved
 *
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium
 * is strictly prohibited.
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
