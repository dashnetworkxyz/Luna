package xyz.dashnetwork.luna.command.commands;

import com.sun.management.OperatingSystemMXBean;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import xyz.dashnetwork.luna.command.LunaCommand;
import xyz.dashnetwork.luna.utils.PermissionType;
import xyz.dashnetwork.luna.utils.StringUtils;
import xyz.dashnetwork.luna.utils.TickUtils;
import xyz.dashnetwork.luna.utils.chat.builder.MessageBuilder;

import java.lang.management.ManagementFactory;

public final class CommandServerInfo extends LunaCommand {

    private OperatingSystemMXBean bean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    private Runtime runtime = Runtime.getRuntime();

    public CommandServerInfo() { super("serverinfo", PermissionType.ADMIN); }

    @Override
    protected void execute(CommandSender sender, String label, String[] args) {
        String cpuLoad = StringUtils.shortenNumber(bean.getCpuLoad() * 100, 2) + "%";
        String processLoad = StringUtils.shortenNumber(bean.getProcessCpuLoad() * 100, 2) + "%";

        String totalMemory = StringUtils.byteCount(runtime.maxMemory());
        String usedMemory = StringUtils.byteCount(runtime.totalMemory() - runtime.freeMemory());

        double[] tps = TickUtils.getTPS();
        String tps0 = StringUtils.shortenNumber(tps[0], 2);
        String tps1 = StringUtils.shortenNumber(tps[1], 2);
        String tps2 = StringUtils.shortenNumber(tps[2], 2);
        String mspt = StringUtils.shortenNumber(TickUtils.getAverageTickTime(), 2);

        int chunks = 0;
        int entities = 0;

        for (World world : Bukkit.getWorlds()) {
            chunks += world.getLoadedChunks().length;
            entities += world.getEntities().size();
        }

        MessageBuilder builder = new MessageBuilder();
        builder.append("&6&l»&7 CPU: &6" + cpuLoad + "&7 Process: &6" + processLoad);
        builder.append("\n&6&l»&7 Memory: &6" + usedMemory + "&7/&6" + totalMemory);
        builder.append("\n&6&l»&7 TPS: &6" + tps0 + "&7, &6" + tps1 + "&7, &6" + tps2 + "&7 MSPT: &6" + mspt);
        builder.append("\n&6&l»&7 Chunks: &6" + chunks + " &7Entities: &6" + entities);
        builder.message(sender);
    }

}
