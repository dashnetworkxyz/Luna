package xyz.dashnetwork.luna.utils;

public final class StringUtils {

    public static boolean matchesUuid(String string) {
        return string.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
    }

}
