package me.rocket.broadcast.commands;

import me.rocket.broadcast.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHeal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length < 1) {
                sender.sendMessage(Utils.format("&cMust be a player to self-heal! Use /heal player!"));
                return false;
            } else {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    target.setHealth(20);
                    Utils.send(target, "&7CONSOLE &ahealed you!");
                    sender.sendMessage(Utils.format("&aYou healed &7" + target.getName() + "&a!"));
                    return true;
                }
            }
        }

        Player player = (Player) sender;
        if (args.length < 1) {
            if (player.hasPermission("rocket.heal")) {
                player.setHealth(20);
                Utils.send(player, "&aYou were healed!");
            }
        } else {
            if (player.hasPermission("rocket.heal.others")) {
                Player target = Bukkit.getPlayer(args[0]);

                target.setHealth(20);
                Utils.send(player, "&aYou healed &7" + target.getName() + "&a!");
                Utils.send(target, "&aYou were healed by &7" + player.getName() + "&a!");
            }
        }

        return false;
    }
}
