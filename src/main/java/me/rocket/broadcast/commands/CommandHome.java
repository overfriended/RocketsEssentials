package me.rocket.broadcast.commands;

import me.rocket.broadcast.utils.Utils;
import me.rocket.broadcast.utils.Variables;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandHome implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.log("&cYou must be a player!");
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("home")) {
            if (args.length == 0) {
                player.teleport(Variables.getHome(0));
                Utils.send(player, "&aTeleported to default home!");
            } else if (args.length >= 1) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("set")) {
                        Utils.send("&cNot implemented.");
                    } else if(args[0].equalsIgnoreCase("back")) {
                        player.teleport(Variables.getLastLocation(player));
                        Utils.send(player, "&aSent back to your last position before teleport!");
                    } else if (NumberUtils.isNumber(args[0])) {
                        if (Variables.isHomeExist(NumberUtils.toInt(args[0]))) {
                            player.teleport(Variables.getHome(NumberUtils.toInt(args[0])));
                            Utils.send(player, "&aTeleported to home: &7" + args[0]);
                        } else {
                            Utils.send(player, "&cHome &7" + args[0] + "&c doesn't exist!");
                        }
                    } else if (args[0].equalsIgnoreCase("add")) {
                        int size = Variables.getHomes().size();

                        Variables.setHome(size, player.getLocation());
                        Utils.send(player, "&aSet home position for " + size + "!");
                        Utils.send("&aA new home position (" + size + ") has been set! Use &7/home " + (size) + "&a to access it.");
                    } else {
                        Utils.send(player, "&cNot a sub-command!");
                    }
                } else {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (args.length > 1) {
                            if (NumberUtils.isNumber(args[1])) {
                                if (Variables.isHomeExist(NumberUtils.toInt(args[1]))) {
                                    Variables.setHome(NumberUtils.toInt(args[1]), player.getLocation());
                                    Utils.send(player, "&aSet new home: " + args[1]);
                                } else {
                                    Utils.send(player, "&cHome doesn't exist: " + args[1]);
                                    return false;
                                }
                            } else {
                                Utils.send(player, "&cNot a number: " + args[1]);
                                return false;
                            }
                        } else {
                            Utils.send(player, "&cMust specify home number: " + args[1]);
                            return false;
                        }
                    } else if(args[0].equalsIgnoreCase("back")) {
                        player.teleport(Variables.getLastLocation(player));
                        Utils.send(player, "&aSent back to your last position before teleport!");
                    }
                }
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
