package com.codermason.gatewaystaff.cmds;

import com.codermason.gatewaystaff.GatewayStaff;
import com.codermason.gatewaystaff.perms.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * created by codermason on 2/16/14
 */
public class StaffCommand implements CommandExecutor {

    private GatewayStaff plugin;

    public StaffCommand(GatewayStaff plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0) {
            if(Permissions.GATEWAY_USE.validate(sender)) {
                if(sender instanceof Player) {
                    plugin.getManager().showMenu(((Player) sender));
                }else{
                    Messenger.noConsole(sender);
                }
            }else{
                Messenger.noPermissions(sender);
            }
        }else if(args.length == 1) {
            if(args[0].equalsIgnoreCase("help")) {
                Messenger.sendMessage("----------"+ChatColor.GRAY+"GatewayStaff"+ChatColor.RESET+"----------", sender);
                for(Permissions perm : Permissions.values()) {
                    if(perm.validate(sender))
                        Messenger.sendMessage("/"+perm.getCommand()+" - "+perm.getDescription(), sender);
                }
            }
        }else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("remove")) {
                if(Permissions.GATEWAY_REMOVE.validate(sender)) {
                    String name = args[1];
                    if(plugin.getManager().isStaff(name)) {
                        this.plugin.getManager().removeStaff(name);
                        Messenger.sendMessage("Removed "+name+" as a staff member!", sender);
                    }else{
                        Messenger.sendMessage("Player "+name+" is not a staff member!", sender);
                    }
                }else{
                    Messenger.noPermissions(sender);
                }
        }
        }else if(args.length == 3) {
            if(args[0].equalsIgnoreCase("add")) {
                if(Permissions.GATEWAY_ADD.validate(sender)) {
                    String name = args[1], rank = args[2];
                    if(!plugin.getManager().isStaff(name)) {
                        this.plugin.getManager().addStaff(name, rank);
                        Messenger.sendMessage("Added "+name+" with a prefix of "+rank+"!", sender);
                    }else{
                        Messenger.sendMessage("Player "+name+" is already a staff member!", sender);
                    }
                }else{
                    Messenger.noPermissions(sender);
                }
            }
        }else{
            Messenger.sendMessage("Use /staff help for help!", sender);
        }

        return false;
    }

    private static class Messenger {

        private static String prefix = ChatColor.GOLD+"["+ChatColor.GRAY+"GatewayStaff"+ChatColor.GOLD+"]"+ChatColor.RESET+" ";

        public static void sendMessage(String msg, CommandSender sender) {
            sender.sendMessage(prefix+msg);
        }

        public static void noConsole(CommandSender sender) {
            sendMessage("Only players can execute this command!", sender);
        }

        public static void noPermissions(CommandSender sender) {
            sendMessage("You do not have permission!", sender);
        }

    }

}
