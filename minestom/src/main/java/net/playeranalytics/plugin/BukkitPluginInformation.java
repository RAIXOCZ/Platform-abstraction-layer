package net.playeranalytics.plugin;

import java.io.File;
import java.io.InputStream;

public class BukkitPluginInformation implements PluginInformation {


    @Override
    public InputStream getResourceFromJar(String byName) {
        return this.getClass().getResourceAsStream("plan-" + byName);
    }

    @Override
    public File getDataFolder() {
        return new File("plan/");
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

}
