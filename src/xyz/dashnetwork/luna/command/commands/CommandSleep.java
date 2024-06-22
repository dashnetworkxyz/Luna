package xyz.dashnetwork.luna.command.commands;

import org.bukkit.Statistic;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dashnetwork.luna.command.LunaCommand;
import xyz.dashnetwork.luna.utils.PermissionType;
import xyz.dashnetwork.luna.utils.PlatformUtils;
import xyz.dashnetwork.luna.utils.SelectorUtils;
import xyz.dashnetwork.luna.utils.chat.MessageUtils;
import xyz.dashnetwork.luna.utils.chat.builder.MessageBuilder;
import xyz.dashnetwork.luna.utils.chat.builder.formats.PlayerFormat;

import java.util.ArrayList;
import java.util.List;

public final class CommandSleep extends LunaCommand {

    public CommandSleep() { super("sleep", PermissionType.ADMIN); }

    @Override
    protected void execute(CommandSender sender, String label, String[] args) {
        if (PlatformUtils.getServerVersion() < 13) {
            MessageUtils.message(sender, "&6&l» &7This command is &61.13+&7 only");
            return;
        }

        List<Player> targets = new ArrayList<>();

        if (args.length > 0 && PermissionType.ADMIN.hasPermission(sender))
            targets.addAll(SelectorUtils.playersOrSelf(sender, args));
        else if (sender instanceof Player player)
            targets.add(player);

        if (targets.isEmpty()) {
            MessageUtils.message(sender, "&6&l» &cNo player was found.");
            return;
        }

        List<Player> reset = new ArrayList<>();

        for (Player target : targets) {
            target.setStatistic(Statistic.valueOf("TIME_SINCE_REST"), 0);

            MessageUtils.message(target, "&6&l» &7Your sleep timer has been reset");

            if (target != sender)
                reset.add(target);
        }

        if (!reset.isEmpty()) {
            MessageBuilder builder = new MessageBuilder();
            builder.append("&6&l»&7 sleep timer reset for ");
            builder.append(new PlayerFormat(reset, "&7, &6"));
            builder.append("&7.");
            builder.message(sender);
        }
    }

}
