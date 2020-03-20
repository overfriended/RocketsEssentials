package me.rocket.broadcast.commands;

import me.rocket.broadcast.utils.Utils;
import me.rocket.broadcast.utils.Variables;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandPM implements TabExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.log("&cMust be a player!");
            return false;
        }

        Player player = (Player) sender;

        if (Variables.isIsSelfServer()) {
            if (args.length >= 2) {
                Player target = Bukkit.getServer().getPlayer(args[0]);

                if (target != null) {
                    StringBuilder str = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        str.append(args[i] + " ");
                    }
                    String s = str.toString();
                    Utils.send(target, "&7[&a" + player.getName() + "&7-> &aYou&7]: &f" + s);
                    Utils.send(player, "&7[&aYou &7-> &a" + target.getName() + "&7]: &f" + s);
                } else {
                    Utils.send(player, "&cTarget is invalid: &7" + args[0]);
                }
            } else {
                Utils.send(player, "&cToo few arguments: &7/pm name message");
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                List<String> names = new ArrayList<String>();
                names.add(player.getName());

                if (names.size() == Bukkit.getOnlinePlayers().size()) {
                    return names;
                }
            }
        } else {
            return null;
        }
        return null;
    }
}
