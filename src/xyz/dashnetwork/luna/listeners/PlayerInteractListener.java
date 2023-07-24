package xyz.dashnetwork.luna.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import xyz.dashnetwork.luna.utils.connection.User;

public final class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        User user = User.getUser(event.getPlayer());

        if (!user.canBuild())
            event.setCancelled(true);
    }

}
