package xyz.dashnetwork.luna.utils;

import org.bukkit.Bukkit;

public final class TickUtils {

    private static double version = PlatformUtils.getServerVersion();

    public static double[] getTPS() {
        if (version >= 12)
            return new ClassWrapper(Bukkit.class).callMethod("getTPS");
        else {
            ClassWrapper wrapper = new ClassWrapper("net.minecraft.server", "MinecraftServer");
            wrapper = new ClassWrapper(wrapper.callMethod("getServer"));

            return wrapper.getField("recentTps");
        }
    }

    public static double getAverageTickTime() {
        if (version >= 15)
            return new ClassWrapper(Bukkit.class).callMethod("getAverageTickTime");
        else {
            ClassWrapper wrapper = new ClassWrapper("net.minecraft.server", "MinecraftServer");
            wrapper = new ClassWrapper(wrapper.callMethod("getServer"));
            long[] timings;

            if (version < 13)
                timings = wrapper.getFieldDeclared("h");
            else if (version < 14)
                timings = wrapper.getFieldDeclared("d");
            else
                timings = wrapper.getFieldDeclared("f");

            double average = 0;

            for (long timing : timings)
                average += timing;

            return average / timings.length;
        }
    }

}
