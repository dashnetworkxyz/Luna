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
