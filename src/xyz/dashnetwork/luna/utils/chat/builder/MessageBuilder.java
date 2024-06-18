package xyz.dashnetwork.luna.utils.chat.builder;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.dashnetwork.luna.utils.chat.MessageUtils;
import xyz.dashnetwork.luna.utils.chat.builder.sections.ComponentSection;
import xyz.dashnetwork.luna.utils.chat.builder.sections.FormatSection;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public final class MessageBuilder {

    private final List<ComponentSection> sections = new ArrayList<>();

    public int size() { return sections.size(); }

    public Section append(@NotNull String text) {
        ComponentSection section = new ComponentSection(getStyleFromPrevious() + text);
        sections.add(section);

        return section;
    }

    public Section append(@NotNull Format format) {
        sections.addAll(format.sections());
        return new FormatSection(format);
    }

    public Section append(@NotNull ComponentSection section) {
        sections.add(section);
        return section;
    }

    public BaseComponent[] build(@Nullable User user) {
        List<BaseComponent> list = new ArrayList<>();

        for (ComponentSection section : sections) {
            if (user == null || section.getFilter().test(user)) {
                List<BaseComponent[]> hovers = new ArrayList<>();
                BaseComponent[] components = section.getComponents();

                for (ComponentSection hover : section.getHovers())
                    if (user != null && hover.getFilter().test(user))
                        hovers.add(hover.getComponents());

                if (!hovers.isEmpty()) {
                    List<BaseComponent> hoverLines = new ArrayList<>();

                    for (BaseComponent[] line : hovers) {
                        if (!hoverLines.isEmpty())
                            hoverLines.addAll(Arrays.asList(TextComponent.fromLegacyText("\n")));

                        hoverLines.addAll(Arrays.asList(line));
                    }

                    for (BaseComponent component : components)
                        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverLines.toArray(BaseComponent[]::new)));
                }

                list.addAll(Arrays.asList(components));
            }
        }

        return list.toArray(BaseComponent[]::new);
    }

    public void message(@NotNull CommandSender audience) { MessageUtils.message(audience, this::build); }

    public void message(@NotNull User user) { MessageUtils.message(user.getPlayer(), this::build); }

    public void broadcast(@NotNull Predicate<User> filter) { MessageUtils.broadcast(filter, this::build); }

    public void broadcast() { MessageUtils.broadcast(this::build); }

    private String getStyleFromPrevious() {
        if (sections.isEmpty())
            return "";

        ComponentSection section = sections.get(sections.size() - 1);
        StringBuilder builder = new StringBuilder();
        Style style = section.getLastStyle();
        ChatColor color = style.color();

        if (color != null)
            builder.append("§").append(color.getChar());

        if (style.obfuscated())
            builder.append("§k");

        if (style.bold())
            builder.append("§l");

        if (style.strikethrough())
            builder.append("§m");

        if (style.underlined())
            builder.append("§n");

        if (style.italic())
            builder.append("§o");

        return builder.toString();
    }

}
