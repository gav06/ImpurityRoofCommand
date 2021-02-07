package me.gavin.impurityroofpatch;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("stuck").setExecutor(new StuckCMD());
    }

    public static Main getPlugin() {
        return getPlugin(Main.class);
    }
}
