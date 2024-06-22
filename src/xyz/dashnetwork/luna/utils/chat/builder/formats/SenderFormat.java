package xyz.dashnetwork.luna.utils.chat.builder.formats;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.utils.chat.builder.Format;
import xyz.dashnetwork.luna.utils.chat.builder.sections.ComponentSection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class SenderFormat implements Format {

    private List<ComponentSection> sections = new ArrayList<>();

    public SenderFormat(CommandSender sender) {
        if (sender instanceof Player player)
            sections.addAll(new PlayerFormat(player).sections());
        else
            sections.add(new ComponentSection("&6Console"));
    }

    public SenderFormat(Collection<CommandSender> collection, String separator) {
        for (CommandSender sender : collection) {
            if (!sections.isEmpty())
                sections.add(new ComponentSection(separator));

            sections.addAll(new SenderFormat(sender).sections());
        }
    }

    @Override
    public List<ComponentSection> sections() { return sections; }

}
