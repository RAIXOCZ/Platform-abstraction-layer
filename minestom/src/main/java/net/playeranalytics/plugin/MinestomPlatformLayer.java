package net.playeranalytics.plugin;

import de.themoep.minedown.adventure.MineDown;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.provider.MinestomPlainTextComponentSerializerProvider;
import net.playeranalytics.plugin.scheduling.MinestomRunnableFactory;
import net.playeranalytics.plugin.scheduling.RunnableFactory;
import net.playeranalytics.plugin.server.JavaUtilPluginLogger;
import net.playeranalytics.plugin.server.Listeners;
import net.playeranalytics.plugin.server.MinestomListeners;
import net.playeranalytics.plugin.server.PluginLogger;

import java.util.logging.Logger;

public class MinestomPlatformLayer implements PlatformAbstractionLayer {

    private PluginLogger pluginLogger;
    private Listeners listeners;
    private RunnableFactory runnableFactory;
    private PluginInformation pluginInformation;
    private final PlainTextComponentSerializer plainTextComponentSerializer = new MinestomPlainTextComponentSerializerProvider().plainTextSimple();

    @Override
    public PluginLogger getPluginLogger() {
        if (pluginLogger == null) {
            pluginLogger = new JavaUtilPluginLogger(
                    Logger.getLogger("Plan"),
                    coloredMessage -> System.out.println(plainTextComponentSerializer.serialize(MineDown.parse(coloredMessage)))
            );
        }
        return pluginLogger;
    }

    @Override
    public Listeners getListeners() {
        if (listeners == null) listeners = new MinestomListeners(MinecraftServer.getGlobalEventHandler());
        return listeners;
    }

    @Override
    public RunnableFactory getRunnableFactory() {
        if (runnableFactory == null) runnableFactory = new MinestomRunnableFactory();
        return runnableFactory;
    }

    @Override
    public PluginInformation getPluginInformation() {
        if (pluginInformation == null) pluginInformation = new BukkitPluginInformation();
        return pluginInformation;
    }

}
