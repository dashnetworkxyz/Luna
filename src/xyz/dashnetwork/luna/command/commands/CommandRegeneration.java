package xyz.dashnetwork.luna.command.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.dashnetwork.luna.command.LunaCommand;
import xyz.dashnetwork.luna.utils.PermissionType;
import xyz.dashnetwork.luna.utils.PlatformUtils;
import xyz.dashnetwork.luna.utils.chat.MessageUtils;

public final class CommandRegeneration extends LunaCommand {

    public CommandRegeneration() { super("regeneration", PermissionType.ADMIN); }

    @Override
    protected void execute(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPotionEffect(PotionEffectType.REGENERATION)) {
                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                player.removePotionEffect(PotionEffectType.SATURATION);
            } else {
                int duration = PlatformUtils.getServerVersion() >= 19.4 ? -1 : Integer.MAX_VALUE;

                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration, 0, true, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, duration, 0, true, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, duration, 0, true, false));
            }
        } else
            MessageUtils.message(sender, "&6&l»&c Only players can use this command.");
    }

}
