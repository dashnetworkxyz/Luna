package xyz.dashnetwork.luna.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import xyz.dashnetwork.luna.utils.connection.User;

public final class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        User user = User.getUser(event.getPlayer());

        if (!user.canBuild())
            event.setCancelled(true);
    }

}
