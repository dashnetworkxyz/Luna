package xyz.dashnetwork.luna.command;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import xyz.dashnetwork.luna.Luna;
import xyz.dashnetwork.luna.utils.PermissionType;
import xyz.dashnetwork.luna.utils.PlatformUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class LunaCommand implements CommandExecutor {

    private static final List<LunaCommand> commands = new ArrayList<>();
    private Command remap;
    private String label;

    public LunaCommand(String label, PermissionType permission) {
        PluginCommand command = Luna.getInstance().getCommand(label);
        command.setExecutor(this);

        String node = permission.toPermissionNode();

        if (node != null)
            command.setPermission(node);

        this.label = label;
        this.remap = null;

        commands.add(this);
    }

    public static void setupRemaps() {
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins())
            if (!plugin.equals(Luna.getInstance()))
                for (Command command : getPluginCommands(plugin))
                    remapCommand(plugin, command);
    }

    private static List<Command> getPluginCommands(Plugin plugin) {
        List<Command> commands = new ArrayList<>();
        CommandMap map = Bukkit.getCommandMap();
        Map<String, Command> known;

        try {
            if (PlatformUtils.getServerVersion() >= 16) {
                Method getKnownCommands = map.getClass().getMethod("getKnownCommands");
                known = (Map<String, Command>) getKnownCommands.invoke(map);
            } else {
                Field knownCommands = map.getClass().getDeclaredField("knownCommands");
                knownCommands.setAccessible(true);
                known = (Map<String, Command>) knownCommands.get(map);
            }
        } catch (ReflectiveOperationException exception) {
            exception.printStackTrace();
            return Collections.emptyList();
        }

        for (Map.Entry<String, Command> entry : known.entrySet())
            if (entry.getValue() instanceof PluginIdentifiableCommand command && command.getPlugin().equals(plugin))
                commands.add(entry.getValue());

        return commands;
    }

    private static void remapCommand(Plugin plugin, Command command) {
        String label = command.getLabel();

        if (label.contains(":"))
            label = label.split(":")[1];

        for (LunaCommand luna : commands) {
            if (luna.remap == null && label.equalsIgnoreCase(luna.label)) {
                luna.remap = command;
                Luna.getInstance().getLogger().info("Remapped /" + luna.label + " to " + plugin.getName());
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (remap != null) {
            if (!remap.execute(sender, label, args))
                sender.sendMessage(remap.getUsage());
        } else
            execute(sender, label, args);

        return true;
    }

    protected abstract void execute(CommandSender sender, String label, String[] args);

}
