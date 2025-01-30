package xyz.dashnetwork.luna.command.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import xyz.dashnetwork.luna.command.LunaCommand;
import xyz.dashnetwork.luna.utils.LazyUtils;
import xyz.dashnetwork.luna.utils.PermissionType;
import xyz.dashnetwork.luna.utils.chat.builder.MessageBuilder;
import xyz.dashnetwork.luna.utils.chat.builder.formats.SenderFormat;

public final class CommandClearLag extends LunaCommand {

    public CommandClearLag() { super("clearlag", PermissionType.ADMIN); }

    @Override
    protected void execute(CommandSender sender, String label, String[] args) {
        int removed = 0;

        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getCustomName() == null) {
                    EntityType type = entity.getType();

                    if (LazyUtils.anyEquals(type, EntityType.DROPPED_ITEM, EntityType.EXPERIENCE_ORB)
                            || (type.equals(EntityType.ARROW) && entity.isOnGround())) {
                        entity.remove();
                        removed++;
                    }
                }
            }
        }

        MessageBuilder builder = new MessageBuilder();
        builder.append("&6&l»&7 ");
        builder.append(new SenderFormat(sender));
        builder.append("&7 cleared &6" + removed +  "&7 entities");
        builder.broadcast();
    }

}
