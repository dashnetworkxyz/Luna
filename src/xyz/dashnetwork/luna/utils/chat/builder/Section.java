package xyz.dashnetwork.luna.utils.chat.builder;

import net.md_5.bungee.api.chat.ClickEvent;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.function.Predicate;

public interface Section {

    Section hover(String text);

    Section hover(String text, Predicate<User> filter);

    Section click(ClickEvent click);

    Section insertion(String insertion);

    Section filter(Predicate<User> filter);

}
