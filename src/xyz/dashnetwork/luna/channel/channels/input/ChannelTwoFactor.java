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

package xyz.dashnetwork.luna.channel.channels.input;

import com.google.common.io.ByteArrayDataInput;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.channel.Channel;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.UUID;

public final class ChannelTwoFactor extends Channel {

    @Override
    protected void receive(ByteArrayDataInput input) {
        UUID uuid = UUID.fromString(input.readUTF());
        boolean authenticated = input.readBoolean();
        Player player = Bukkit.getPlayer(uuid);

        if (player != null)
            User.getUser(player).setAuthenticated(authenticated);
    }

}
