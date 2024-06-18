package xyz.dashnetwork.luna.utils.chat;

import net.md_5.bungee.api.chat.BaseComponent;

public class StyleUtils {

    public static boolean hasStyle(BaseComponent component) {
        if (component.getColorRaw() != null)
            return true;

        if (component.isObfuscated())
            return true;

        if (component.isBold())
            return true;

        if (component.isStrikethrough())
            return true;

        if (component.isUnderlined())
            return true;

        return component.isItalic();
    }

}
