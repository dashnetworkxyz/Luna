package xyz.dashnetwork.luna.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import xyz.dashnetwork.luna.channel.Channel;

import java.util.UUID;

public final class SignChangeListener implements Listener {

    @EventHandler
    public void onSignChance(SignChangeEvent event) {
        Location location = event.getBlock().getLocation();

        UUID uuid = event.getPlayer().getUniqueId();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        String line1 = event.getLine(0);
        String line2 = event.getLine(1);
        String line3 = event.getLine(2);
        String line4 = event.getLine(3);

        Channel.call("signspy",
                uuid,
                x,
                y,
                z,
                line1,
                line2,
                line3,
                line4
        );
    }

}
