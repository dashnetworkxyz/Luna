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

package xyz.dashnetwork.luna.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.command.LunaCommand;
import xyz.dashnetwork.luna.utils.PermissionType;
import xyz.dashnetwork.luna.utils.SelectorUtils;
import xyz.dashnetwork.luna.utils.chat.MessageUtils;
import xyz.dashnetwork.luna.utils.chat.builder.MessageBuilder;
import xyz.dashnetwork.luna.utils.chat.builder.formats.UserFormat;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.ArrayList;
import java.util.List;

public final class CommandBuild extends LunaCommand {

    public CommandBuild() {
        super("build", PermissionType.ADMIN);
    }

    @Override
    protected void execute(CommandSender sender, String label, String[] args) {
        List<User> targets = new ArrayList<>();

        if (args.length > 0 && PermissionType.ADMIN.hasPermission(sender))
            targets.addAll(SelectorUtils.usersOrSelf(sender, args));
        else if (sender instanceof Player player)
            targets.add(User.getUser(player));

        if (targets.isEmpty()) {
            MessageUtils.message(sender, "&6&l» &cNo player was found.");
            return;
        }

        List<User> on = new ArrayList<>();
        List<User> off = new ArrayList<>();
        MessageBuilder builder;

        for (User target : targets) {
            boolean build = !target.canBuild();

            target.setBuild(build);

            builder = new MessageBuilder();
            builder.append("&6&l» &7You can ");
            builder.append(build ? "now" : "no longer");
            builder.append(" build");
            builder.message(target);

            if (target.getPlayer() != sender) {
                if (build)
                    on.add(target);
                else
                    off.add(target);
            }
        }

        int onSize = on.size();
        int offSize = off.size();

        if (onSize > 0) {
            builder = new MessageBuilder();
            builder.append("&6&l»&6 ");
            builder.append(new UserFormat(on, "&7, ")).color(ChatColor.GOLD);
            builder.append("&7 can now build.");
            builder.message(sender);
        }

        if (offSize > 0) {
            builder = new MessageBuilder();
            builder.append("&6&l»&6 ");
            builder.append(new UserFormat(off, "&7, ")).color(ChatColor.GOLD);
            builder.append("&7 can no longer build.");
            builder.message(sender);
        }
    }

}
