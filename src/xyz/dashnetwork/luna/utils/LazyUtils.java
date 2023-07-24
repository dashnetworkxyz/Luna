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

import java.util.function.Predicate;

public final class LazyUtils {

    @SafeVarargs
    public static <T>boolean anyTrue(Predicate<T> predicate, T... objects) {
        for (T t : objects)
            if (predicate.test(t))
                return true;
        return false;
    }

    public static boolean anyEquals(Object compare, Object... objects) {
        return anyTrue(object -> object.equals(compare), objects);
    }

    public static boolean anyEqualsIgnoreCase(String compare, String... strings) {
        return anyTrue(string -> string.equalsIgnoreCase(compare), strings);
    }

}
