package me.gavin.impurityroofpatch;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (getConfig().getInt("mode") == 2) {
            this.getCommand("stuck").setExecutor(new StuckCMD());
        } else {
            this.getServer().getPluginManager().registerEvents(new EventHook(), this);
        }
    }

    public static Main getPlugin() {
        return getPlugin(Main.class);
    }

    public void runStuck(Player p) {
        if (p.getWorld().getName().equalsIgnoreCase(Main.getPlugin().getConfig().getString("world-name"))) {
            if (p.getLocation().getY() >= Main.getPlugin().getConfig().getInt("allowed-y-level")) {

                // horrible monkey code
                for (int i = Main.getPlugin().getConfig().getInt("allowed-y-level"); i > 0; i--) {
                    //System.out.println(i);
                    if (p.getWorld().getBlockAt(p.getLocation().getBlockX(), i, p.getLocation().getBlockZ()).getType() == Material.AIR
                            && p.getWorld().getBlockAt(p.getLocation().getBlockX(), i - 1, p.getLocation().getBlockZ()).getType() != Material.AIR) {
                        p.teleport(p.getLocation().subtract(0, p.getLocation().getY() - i, 0));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("unstuck-msg")));
                        break;
                    }
                }
            } else {
                StuckCMD.sendErr(p);
            }
        } else {
            StuckCMD.sendErr(p);
        }
    }
}
