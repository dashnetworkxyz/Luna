package xyz.dashnetwork.luna.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class SelectorUtils {

    public static List<Player> playersOrSelf(CommandSender sender, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player player)
                return List.of(player);

            return Collections.emptyList();
        }

        if (args[0].equalsIgnoreCase("@a"))
            return new ArrayList<>(Bukkit.getOnlinePlayers());

        List<Player> list = new ArrayList<>();

        for (String split : args[0].split(",")) {
            if (LazyUtils.anyEqualsIgnoreCase(split, "@p", "@s") && sender instanceof Player player)
                list.add(player);
            else {
                Player player;

                if (StringUtils.matchesUuid(split))
                    player = Bukkit.getPlayer(UUID.fromString(split));
                else
                    player = Bukkit.getPlayer(split);

                if (player != null)
                    list.add(player);
            }
        }

        return list;
    }

}
