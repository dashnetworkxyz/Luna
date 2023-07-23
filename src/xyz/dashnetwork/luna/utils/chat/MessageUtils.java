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

package xyz.dashnetwork.luna.utils.chat;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import xyz.dashnetwork.luna.Luna;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.function.Function;
import java.util.function.Predicate;

public final class MessageUtils {

    private static final Server server = Luna.getInstance().getServer();
    private static final CommandSender consoleCommandSender = server.getConsoleSender();

    public static void message(CommandSender sender, String message) {
        String colored = ColorUtils.fromAmpersand(message);

        if (sender instanceof Player player)
            player.sendMessage(TextComponent.fromLegacyText(colored));
        else
            sender.sendMessage(colored);
    }

    public static void message(CommandSender sender, BaseComponent... components) {
        if (sender instanceof Player player)
            player.sendMessage(components);
        else
            sender.sendMessage(TextComponent.toLegacyText(components));
    }

    public static void message(CommandSender sender, Function<@Nullable User, BaseComponent[]> function) {
        message(sender, function.apply(User.getUser(sender).orElse(null)));
    }

    public static void broadcast(String message) {
        broadcast(true, message);
    }

    public static void broadcast(BaseComponent... components) {
        server.broadcast(components);
    }

    public static void broadcast(Function<@Nullable User, BaseComponent[]> function) {
        broadcast(true, function);
    }

    public static void broadcast(Predicate<User> predicate, String message) {
        broadcast(true, predicate, message);
    }

    public static void broadcast(Predicate<User> predicate, BaseComponent... components) {
        broadcast(true, predicate, components);
    }

    public static void broadcast(Predicate<User> predicate, Function<@Nullable User, BaseComponent[]> function) {
        broadcast(true, predicate, function);
    }

    public static void broadcast(boolean console, String message) {
        broadcast(console, user -> true, message);
    }

    public static void broadcast(boolean console, BaseComponent... components) {
        broadcast(console, user -> true, components);
    }

    public static void broadcast(boolean console, Function<@Nullable User, BaseComponent[]> function) {
        broadcast(console, user -> true, function);
    }

    public static void broadcast(boolean console, Predicate<User> predicate, String message) {
        for (User user : User.getUsers())
            if (predicate.test(user))
                message(user.getPlayer(), message);

        if (console)
            message(consoleCommandSender, message);
    }

    public static void broadcast(boolean console, Predicate<User> predicate, BaseComponent... components) {
        broadcast(console, predicate, user -> components);
    }

    public static void broadcast(boolean console, Predicate<User> predicate, Function<@Nullable User, BaseComponent[]> function) {
        for (User user : User.getUsers())
            if (predicate.test(user))
                message(user.getPlayer(), function.apply(user));

        if (console)
            message(consoleCommandSender, function.apply(null));
    }

}
