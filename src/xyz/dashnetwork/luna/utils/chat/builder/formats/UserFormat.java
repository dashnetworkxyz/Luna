package xyz.dashnetwork.luna.utils.chat.builder.formats;

import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.Luna;
import xyz.dashnetwork.luna.utils.chat.builder.Format;
import xyz.dashnetwork.luna.utils.chat.builder.sections.ComponentSection;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class UserFormat implements Format {

    private final List<ComponentSection> sections = new ArrayList<>();
    private String server = Luna.getInstance().getConfig().getString("server-name");

    public UserFormat(User user) {
        Player player = user.getPlayer();
        String username = player.getName();
        String displayname = player.getDisplayName();
        String uuid = player.getUniqueId().toString();
        String address = player.getAddress().getAddress().getHostAddress();

        ComponentSection section = new ComponentSection(displayname);
        section.hover("&6" + username);
        section.hover("&7Address: &6" + address, User::isAdmin); // TODO: Sync with Celest on hide address
        section.hover("&7Server: &6" + server);
        section.insertion(uuid);

        sections.add(section);
    }

    public UserFormat(Collection<User> collection, String separator) {
        for (User user : collection) {
            if (!sections.isEmpty())
                sections.add(new ComponentSection(separator));

            sections.addAll(new UserFormat(user).sections());
        }
    }

    @Override
    public List<ComponentSection> sections() { return sections; }

}
