package xyz.dashnetwork.luna.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.dashnetwork.luna.utils.connection.User;

public final class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        User user = User.getUser(event.getPlayer());

        if (!user.isAuthenticated())
            event.setCancelled(true);
    }

}
