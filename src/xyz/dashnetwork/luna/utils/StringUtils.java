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

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public final class StringUtils {

    public static String shortenNumber(Number number, int places) {
        String string = number.toString();
        String[] split = string.split("\\.");

        if (split.length != 2 || split[1].length() < places)
            return string;

        StringBuilder shortened = new StringBuilder(split[0] + ".");

        for (int i = 0; i < places; i++)
            shortened.append(split[1].charAt(i));

        return shortened.toString();
    }

    public static String byteCount(long bytes) {
        long abs = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);

        if (abs < 1024)
            return bytes + " B";

        long value = abs;
        CharacterIterator iterator = new StringCharacterIterator("KMGTPE");

        for (int i = 40; i >= 0 && abs > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            iterator.next();
        }

        value *= Long.signum(bytes);

        return String.format("%.1f %ciB", value / 1024.0, iterator.current());
    }

    public static boolean matchesUuid(String string) {
        return string.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
    }

}
