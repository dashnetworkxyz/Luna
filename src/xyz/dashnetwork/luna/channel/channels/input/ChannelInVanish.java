package xyz.dashnetwork.luna.channel.channels.input;

import com.google.common.io.ByteArrayDataInput;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.channel.Channel;
import xyz.dashnetwork.luna.utils.Queue;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.UUID;

public final class ChannelInVanish extends Channel {

    @Override
    protected void receive(ByteArrayDataInput input) {
        UUID uuid = UUID.fromString(input.readUTF());
        boolean vanish = input.readBoolean();

        Player player = Bukkit.getPlayer(uuid);

        if (player != null)
            User.getUser(player).setVanish(vanish);
        else
            new Queue(uuid, queued -> User.getUser(queued).setVanish(vanish));
    }

}
