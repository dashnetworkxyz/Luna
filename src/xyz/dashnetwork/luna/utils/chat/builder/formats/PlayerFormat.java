package xyz.dashnetwork.luna.utils.chat.builder.formats;

import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.utils.chat.builder.Format;
import xyz.dashnetwork.luna.utils.chat.builder.sections.ComponentSection;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class PlayerFormat implements Format {

    private final List<ComponentSection> sections = new ArrayList<>();

    public PlayerFormat(Player player) {
        sections.addAll(new UserFormat(User.getUser(player)).sections());
    }

    public PlayerFormat(Collection<Player> collection, String separator) {
        for (Player player : collection) {
            if (!sections.isEmpty())
                sections.add(new ComponentSection(separator));

            sections.addAll(new PlayerFormat(player).sections());
        }
    }

    @Override
    public List<ComponentSection> sections() { return sections; }

}
