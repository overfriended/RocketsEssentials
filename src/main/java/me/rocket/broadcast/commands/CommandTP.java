package me.rocket.broadcast.commands;

import me.rocket.broadcast.utils.Utils;
import me.rocket.broadcast.utils.Variables;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTP implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.log("&cMust be a player.");
            return false;
        }

        Player player = (Player) sender;

        if (Variables.isIsSelfServer()) {
            if (args.length == 1) {
                Player target = Bukkit.getServer().getPlayer(args[0]);

                if (target != null) {
                    player.teleport(target.getLocation());
                    Utils.log("&aPlayer &7" + player.getName() + "&a teleported to &7" + target.getName() + "&a!");
                    Utils.send(player, "&aTeleported you to &7" + target.getName());
                } else {
                    Utils.send(player, "&cPlayer &7" + args[0] + "&c doesn't exist.");
                    return false;
                }
            } else if (args.length == 2) {
                Player targetA = Bukkit.getServer().getPlayer(args[0]);
                Player targetB = Bukkit.getServer().getPlayer(args[1]);

                if (targetA != null || targetB != null) {
                    targetA.teleport(targetB.getLocation());
                    Utils.log("&aPlayer &7" + player.getName() + "&a teleported &7" + targetA.getName() + "&a to &7" + targetB.getName() + "&a!");
                    Utils.send(player, "&aTeleported &7" + targetA.getName() + " &ato &7" + targetB.getName() + "&a!" );
                } else {
                    Utils.send(player, "&cPlayers you specified may not exist!");
                    return false;
                }
            }
        }

        return false;
    }
}
