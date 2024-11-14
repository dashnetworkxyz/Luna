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

package xyz.dashnetwork.luna.channel.channels.input;

import com.google.common.io.ByteArrayDataInput;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.channel.Channel;
import xyz.dashnetwork.luna.utils.Queue;

import java.util.UUID;

public final class ChannelInDisplayName extends Channel {

    @Override
    protected void receive(ByteArrayDataInput input) {
        UUID uuid = UUID.fromString(input.readUTF());
        String displayname = input.readUTF();
        Player player = Bukkit.getPlayer(uuid);

        if (player != null) {
            player.setDisplayName(displayname);
            player.setPlayerListName(displayname);
        } else {
            new Queue(uuid, queued -> {
                queued.setDisplayName(displayname);
                queued.setPlayerListName(displayname);
            });
        }
    }

}
