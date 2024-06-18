package xyz.dashnetwork.luna.commands;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.dashnetwork.luna.utils.chat.MessageUtils;

public final class CommandPeek implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            ItemStack item = player.getItemInHand();

            if (item != null) {
                ItemMeta meta = item.getItemMeta();

                if (meta instanceof BlockStateMeta stateMeta) {
                    BlockState state = stateMeta.getBlockState();

                    if (state instanceof InventoryHolder holder) {
                        Inventory inventory = holder.getInventory();
                        int size = inventory.getSize();

                        Inventory created = Bukkit.createInventory(null, size);

                        for (int i = 0; i < size; i++)
                            created.setItem(i, inventory.getItem(i));

                        player.openInventory(created);
                        return true;
                    }
                }
            }

            MessageUtils.message(sender, "&6&l» &cYou must be holding a chest");
        } else
            MessageUtils.message(sender, "&6&l»&c Only players can use this command.");

        return true;
    }

}
