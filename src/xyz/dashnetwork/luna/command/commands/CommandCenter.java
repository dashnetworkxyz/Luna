package xyz.dashnetwork.luna.command.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.command.LunaCommand;
import xyz.dashnetwork.luna.utils.PermissionType;
import xyz.dashnetwork.luna.utils.chat.MessageUtils;

public final class CommandCenter extends LunaCommand {

    public CommandCenter() {
        super("center", PermissionType.ADMIN);
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player player) {
            Location location = player.getLocation().clone();

            location.setX(location.getBlockX() + 0.5);
            location.setZ(location.getBlockZ() + 0.5);

            if (!player.isOnGround())
                location.setY(Math.round(location.getY()));

            player.teleport(location);
        } else
            MessageUtils.message(sender, "&6&l»&c Only players can use this command.");
    }

}
