package me.gavin.impurityroofpatch;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventHook implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase(Main.getPlugin().getConfig().getString("world-name"))) {
            if (event.getPlayer().getLocation().getY() >= Main.getPlugin().getConfig().getInt("allowed-y-level")) {
                for (int i = Main.getPlugin().getConfig().getInt("allowed-y-level"); i > 0; i--) {
                    Player p = (Player) event.getPlayer();
                    if (p.getWorld().getBlockAt(p.getLocation().getBlockX(), i, p.getLocation().getBlockZ()).getType() == Material.AIR
                            && p.getWorld().getBlockAt(p.getLocation().getBlockX(), i - 1, p.getLocation().getBlockZ()).getType() != Material.AIR) {
                        p.teleport(p.getLocation().subtract(0, p.getLocation().getY() - i, 0));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("unstuck-msg")));
                        break;
                    }
                }
            }
        }
    }
}
