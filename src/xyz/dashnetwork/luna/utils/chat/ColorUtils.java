package xyz.dashnetwork.luna.utils.chat;

import org.jetbrains.annotations.NotNull;

public final class ColorUtils {

    public static String fromAmpersand(@NotNull String string) {
        return string.replaceAll("&([0-f]|[k-o]|r|x)", "§$1");
    }

    public static String strip(@NotNull String string) {
        return string.replaceAll("[&§]([0-f]|[k-o]|r|x)", "");
    }

}
