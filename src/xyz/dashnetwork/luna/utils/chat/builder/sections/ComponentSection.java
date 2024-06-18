package xyz.dashnetwork.luna.utils.chat.builder.sections;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import xyz.dashnetwork.luna.utils.chat.ColorUtils;
import xyz.dashnetwork.luna.utils.chat.StyleUtils;
import xyz.dashnetwork.luna.utils.chat.builder.Section;
import xyz.dashnetwork.luna.utils.chat.builder.Style;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class ComponentSection implements Section {

    private final BaseComponent[] components;
    private final List<ComponentSection> hovers = new ArrayList<>();
    private Predicate<User> filter = user -> true;

    public ComponentSection(String text) {
        components = TextComponent.fromLegacyText(ColorUtils.fromAmpersand(text));
    }

    public BaseComponent[] getComponents() { return components; }

    public List<ComponentSection> getHovers() { return hovers; }

    public Predicate<User> getFilter() { return filter; }

    public Style getLastStyle() {
        Style style = null;

        for (int i = components.length - 1; i >= 0; i--) {
            if (StyleUtils.hasStyle(components[i])) {
                style = Style.from(components[i]);
                break;
            }
        }

        if (style == null)
            style = Style.NONE;

        return style;
    }

    @Override
    public Section hover(String text) {
        hovers.add(new ComponentSection(text));
        return this;
    }

    @Override
    public Section hover(String text, Predicate<User> filter) {
        hovers.add((ComponentSection) new ComponentSection(text).filter(filter));
        return this;
    }

    @Override
    public Section click(ClickEvent click) {
        for (BaseComponent component : components)
            component.setClickEvent(click);
        return this;
    }

    @Override
    public Section insertion(String insertion) {
        for (BaseComponent component : components)
            component.setInsertion(insertion);
        return this;
    }

    @Override
    public Section filter(Predicate<User> filter) {
        this.filter = filter;
        return this;
    }

}
