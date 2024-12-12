package xyz.dashnetwork.luna.utils;

import org.bukkit.Bukkit;

public final class TickUtils {

    private static final double version = PlatformUtils.getServerVersion();

    public static double[] getTPS() {
        if (version >= 12)
            return new ClassWrapper(Bukkit.class).callMethod("getTPS");
        else {
            ClassWrapper wrapper = new ClassWrapper("net.minecraft.server", "MinecraftServer");
            wrapper = new ClassWrapper((Object) wrapper.callMethod("getServer"));

            return wrapper.getField("recentTps");
        }
    }

    public static double getAverageTickTime() {
        if (version >= 15)
            return new ClassWrapper(Bukkit.class).callMethod("getAverageTickTime");
        else {
            ClassWrapper wrapper = new ClassWrapper("net.minecraft.server", "MinecraftServer");
            wrapper = new ClassWrapper((Object) wrapper.callMethod("getServer"));
            long[] timings;

            if (version < 13)
                timings = wrapper.getField("h");
            else if (version < 14)
                timings = wrapper.getField("d");
            else
                timings = wrapper.getField("f");

            double average = 0;

            for (long timing : timings)
                average += timing;

            return (average / timings.length) * 1.0E-6D;
        }
    }

}
