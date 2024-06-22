package xyz.dashnetwork.luna.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.utils.connection.User;

public class VanishUtils {

    public static void hide(Player player) {
        for (User online : User.getUsers()) {
            Player target = online.getPlayer();

            if (!target.equals(player) && !online.isStaff())
                target.hidePlayer(player);
        }
    }

    public static void show(Player player) {
        for (Player online : Bukkit.getOnlinePlayers())
            online.showPlayer(player);
    }

}
