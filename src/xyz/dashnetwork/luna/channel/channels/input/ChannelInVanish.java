package xyz.dashnetwork.luna.channel.channels.input;

import com.google.common.io.ByteArrayDataInput;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.channel.Channel;
import xyz.dashnetwork.luna.utils.connection.User;

import java.util.UUID;

public final class ChannelInVanish extends Channel {

    @Override
    protected void receive(ByteArrayDataInput input) {
        UUID uuid = UUID.fromString(input.readUTF());
        boolean vanish = input.readBoolean();

        Player player = Bukkit.getPlayer(uuid);

        if (player != null) {
            if (vanish) {
                for (User online : User.getUsers()) {
                    Player target = online.getPlayer();

                    if (!target.equals(player) && !online.isStaff())
                        target.hidePlayer(player);
                }
            } else
                for (Player online : Bukkit.getOnlinePlayers())
                    online.showPlayer(player);
        }
    }

}
