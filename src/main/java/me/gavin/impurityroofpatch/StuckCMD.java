package me.gavin.impurityroofpatch;

import org.bukkit.ChatColor;
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

            Main.getPlugin().runStuck(p);
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "You must be a player to execute this command");
            return true;
        }
    }

    public static void sendErr(Player p) {
        p.sendMessage(ChatColor.RED + "You must be stuck on the nether roof to execute this command");
    }
}
