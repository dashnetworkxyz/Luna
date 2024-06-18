/*
 * Luna
 * Copyright (C) 2023  DashNetwork
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.dashnetwork.luna.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.utils.connection.User;

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

    public static List<User> usersOrSelf(CommandSender sender, String[] args) {
        List<Player> players = playersOrSelf(sender, args);
        List<User> users = new ArrayList<>();

        for (Player player : players)
            users.add(User.getUser(player));

        return users;
    }

}
