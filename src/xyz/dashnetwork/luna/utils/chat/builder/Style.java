package xyz.dashnetwork.luna.utils.chat.builder;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.ChatColor;

public record Style(ChatColor color,
        boolean obfuscated,
        boolean bold,
        boolean strikethrough,
        boolean underlined,
        boolean italic) {

    public static final Style NONE = new Style(ChatColor.WHITE, false, false, false, false, false);

    public static Style from(BaseComponent component) {
        ChatColor color = component.getColorRaw() == null ? null : ChatColor.valueOf(component.getColor().name());

        return new Style(
                color,
                component.isObfuscated(),
                component.isBold(),
                component.isStrikethrough(),
                component.isUnderlined(),
                component.isItalic()
        );
    }

}
