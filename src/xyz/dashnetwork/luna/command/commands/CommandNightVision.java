/*
 * Luna
 * Copyright (C) 2023  DashNetwork
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.dashnetwork.luna.command.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.dashnetwork.luna.command.LunaCommand;
import xyz.dashnetwork.luna.utils.PermissionType;
import xyz.dashnetwork.luna.utils.PlatformUtils;
import xyz.dashnetwork.luna.utils.chat.MessageUtils;

public final class CommandNightVision extends LunaCommand {

    public CommandNightVision() {
        super("nightvision", PermissionType.STAFF);
    }

    @Override
    protected void execute(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION))
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            else {
                int duration = PlatformUtils.getServerVersion() >= 19.4 ? -1 : Integer.MAX_VALUE;

                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, duration, 0, true, false));
            }
        } else
            MessageUtils.message(sender, "&6&l»&c Only players can use this command.");
    }

}
