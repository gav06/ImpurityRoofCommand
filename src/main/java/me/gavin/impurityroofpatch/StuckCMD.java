package me.gavin.impurityroofpatch;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StuckCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.getWorld().getName().equalsIgnoreCase(Main.getPlugin().getConfig().getString("world-name"))) {
                if (p.getLocation().getY() >= Main.getPlugin().getConfig().getInt("allowed-y-level")) {

                    // horrible monkey code
                    for (int i = Main.getPlugin().getConfig().getInt("allowed-y-level"); i > 0; i--) {
                        System.out.println(i);
                        if (p.getWorld().getBlockAt(p.getLocation().getBlockX(), i, p.getLocation().getBlockZ()).getType() == Material.AIR
                        && p.getWorld().getBlockAt(p.getLocation().getBlockX(), i - 1, p.getLocation().getBlockZ()).getType() != Material.AIR) {
                            p.teleport(p.getLocation().subtract(0, p.getLocation().getY() - i, 0));
                            break;
                        }
                    }

                } else {
                    sendErr(p);
                    return true;
                }
            } else {
                sendErr(p);
                return true;
            }
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "You must be a player to execute this command");
            return true;
        }
    }

    private void sendErr(Player p) {
        p.sendMessage(ChatColor.RED + "You must be stuck on the nether roof to execute this command");
    }
}
