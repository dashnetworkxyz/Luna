/*
 * Luna
 * Copyright (C) 2023  DashNetwork
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.dashnetwork.luna.utils.chat.builder;

import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.function.Predicate;

public interface Section {

    Section hover(String hover);

    Section hover(String hover, Predicate<User> filter);

    Section click(ClickEvent click);

    Section insertion(String insertion);

    Section color(TextColor color);

    Section filter(Predicate<User> filter);


}
