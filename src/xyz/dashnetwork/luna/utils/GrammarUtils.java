package xyz.dashnetwork.luna.utils;

public class GrammarUtils {

    public static String possessive(String string) {
        if (string.toLowerCase().endsWith("s"))
            return string + "'";
        return string + "'s";
    }

    public static String capitalization(String string) {
        if (string.length() > 1)
            return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
        return string.toUpperCase();
    }

}
