package xyz.dashnetwork.luna.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.Luna;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public final class Queue implements Runnable {

    private static List<Queue> queues = new CopyOnWriteArrayList<>();
    private UUID uuid;
    private Consumer<Player> action;

    public Queue(UUID uuid, Consumer<Player> action) {
        this.uuid = uuid;
        this.action = action;

        queues.add(this);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Luna.getInstance(), this, 1200L);
    }

    public static void runQueuedActions(Player player) {
        for (Queue queue : queues) {
            if (queue.uuid.equals(player.getUniqueId())) {
                queue.action.accept(player);
                queues.remove(queue);
            }
        }
    }

    @Override
    public void run() {
        queues.remove(this);
    }

}
