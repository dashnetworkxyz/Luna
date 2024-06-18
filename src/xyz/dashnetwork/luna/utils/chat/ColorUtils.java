package xyz.dashnetwork.luna.utils.chat;

public final class ColorUtils {

    public static String fromAmpersand(String string) {
        return string.replaceAll("&([0-f]|[k-o]|r|x)", "§$1");
    }

    public static String strip(String string) {
        return string.replaceAll("[&§]([0-f]|[k-o]|r|x)", "");
    }

}
