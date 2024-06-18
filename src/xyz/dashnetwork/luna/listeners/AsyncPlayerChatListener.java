package xyz.dashnetwork.luna.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.dashnetwork.luna.Luna;

public final class AsyncPlayerChatListener implements Listener {

    private boolean local = Luna.getInstance().getConfig().getBoolean("local-chat");

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        event.setFormat("<%s§f> %s");

        if (!local)
            event.setCancelled(true);
    }

}
