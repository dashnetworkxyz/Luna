package xyz.dashnetwork.luna.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class ItemBuilder<T extends ItemMeta> {

    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(@NotNull Material material) {
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }

    public ItemBuilder<T> durability(int durability) {
        item.setDurability((short) durability);
        return this;
    }

    public ItemBuilder<T> name(@NotNull String name) {
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        return this;
    }

    public ItemBuilder<T> lore(@NotNull String... lore) {
        List<String> list = new ArrayList<>();

        for (String each : lore)
            list.add(ChatColor.translateAlternateColorCodes('&', each));

        meta.setLore(list);
        return this;
    }

    public ItemBuilder<T> enchant(@NotNull Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder<T> unbreakable() {
        meta.spigot().setUnbreakable(true);
        return this;
    }

    public ItemBuilder<T> flags(@NotNull ItemFlag... flags) {
        meta.addItemFlags(flags);
        return this;
    }

    public ItemBuilder<T> flags() {
        return flags(ItemFlag.values());
    }

    public ItemBuilder<T> meta(@NotNull Consumer<T> consumer) {
        consumer.accept((T) meta);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);

        return item;
    }

}
