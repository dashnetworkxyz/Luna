package xyz.dashnetwork.luna.utils.chat.builder.sections;

import net.md_5.bungee.api.chat.ClickEvent;
import xyz.dashnetwork.luna.utils.chat.builder.Format;
import xyz.dashnetwork.luna.utils.chat.builder.Section;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class FormatSection implements Section {

    private final List<ComponentSection> sections;

    public FormatSection(Format format) {
        this.sections = format.sections();
    }

    private void forEach(Consumer<Section> consumer) {
        for (Section section : sections)
            consumer.accept(section);
    }

    @Override
    public Section hover(String text) {
        forEach(section -> section.hover(text));
        return this;
    }

    @Override
    public Section hover(String text, Predicate<User> filter) {
        forEach(section -> section.hover(text, filter));
        return this;
    }

    @Override
    public Section click(ClickEvent event) {
        forEach(section -> section.click(event));
        return this;
    }

    @Override
    public Section insertion(String insertion) {
        forEach(section -> section.insertion(insertion));
        return this;
    }

    @Override
    public Section filter(Predicate<User> filter) {
        forEach(section -> section.filter(filter));
        return this;
    }

}
