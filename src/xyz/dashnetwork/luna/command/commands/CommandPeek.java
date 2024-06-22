package xyz.dashnetwork.luna.command.commands;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.dashnetwork.luna.command.LunaCommand;
import xyz.dashnetwork.luna.utils.PermissionType;
import xyz.dashnetwork.luna.utils.chat.MessageUtils;

public final class CommandPeek extends LunaCommand {

    public CommandPeek() {
        super("peek", PermissionType.STAFF);
    }

    @Override
    protected void execute(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player player) {
            ItemStack item = player.getItemInHand();

            if (item != null) {
                ItemMeta meta = item.getItemMeta();

                if (meta instanceof BlockStateMeta stateMeta) {
                    BlockState state = stateMeta.getBlockState();

                    if (state instanceof InventoryHolder holder) {
                        Inventory inventory = holder.getInventory();
                        int size = inventory.getSize();
                        double multiplier = (double) size / 9d;

                        Inventory created = Bukkit.createInventory(null, (int) Math.ceil(multiplier) * 9);

                        for (int i = 0; i < size; i++)
                            created.setItem(i, inventory.getItem(i));

                        player.openInventory(created);
                        return;
                    }
                }
            }

            MessageUtils.message(sender, "&6&l» &cYou must be holding a container");
        } else
            MessageUtils.message(sender, "&6&l»&c Only players can use this command.");
    }

}
