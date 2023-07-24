package xyz.dashnetwork.luna.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import xyz.dashnetwork.luna.utils.connection.User;

public final class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        User user = User.getUser(event.getPlayer());

        if (!user.canBuild())
            event.setCancelled(true);
    }

}
