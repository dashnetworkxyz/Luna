package xyz.dashnetwork.luna.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class ItemBuilder<T extends ItemMeta> {

    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material) {
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }

    public ItemBuilder<T> durability(int durability) {
        item.setDurability((short) durability);
        return this;
    }

    public ItemBuilder<T> name(String name) {
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        return this;
    }

    public ItemBuilder<T> lore(String... lore) {
        List<String> list = new ArrayList<>();

        for (String each : lore)
            list.add(ChatColor.translateAlternateColorCodes('&', each));

        meta.setLore(list);
        return this;
    }

    public ItemBuilder<T> enchant(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder<T> unbreakable() {
        try {
            if (PlatformUtils.getServerVersion() >= 10) {
                Method setUnbreakable = meta.getClass().getMethod("setUnbreakable", boolean.class);
                setUnbreakable.setAccessible(true);
                setUnbreakable.invoke(meta, true);
            } else {
                Method spigot = meta.getClass().getMethod("spigot");
                spigot.setAccessible(true);

                Object spigotObject = spigot.invoke(meta);

                Method setUnbreakable = spigotObject.getClass().getMethod("setUnbreakable", boolean.class);
                setUnbreakable.setAccessible(true);
                setUnbreakable.invoke(spigotObject, true);
            }
        } catch (ReflectiveOperationException exception) {
            exception.printStackTrace();
        }

        return this;
    }

    public ItemBuilder<T> flags(ItemFlag... flags) {
        meta.addItemFlags(flags);
        return this;
    }

    public ItemBuilder<T> flags() {
        return flags(ItemFlag.values());
    }

    public ItemBuilder<T> meta(Consumer<T> consumer) {
        consumer.accept((T) meta);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);

        return item;
    }

}
