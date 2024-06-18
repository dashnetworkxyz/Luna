package xyz.dashnetwork.luna.utils.chat.builder;

import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.ChatColor;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.function.Predicate;

public interface Section {

    Section hover(String text);

    Section hover(String text, Predicate<User> filter);

    Section click(ClickEvent click);

    Section insertion(String insertion);

    Section color(ChatColor color);

    Section filter(Predicate<User> filter);

}
