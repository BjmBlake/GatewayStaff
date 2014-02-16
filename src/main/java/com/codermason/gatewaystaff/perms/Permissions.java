package com.codermason.gatewaystaff.perms;

import org.bukkit.command.CommandSender;

/**
 * created by codermason on 2/16/14
 */
public enum Permissions {

    GATEWAY_USE("staff", "display the staff menu"),
    GATEWAY_ADD("staff add <name> <rank>", "add a new staff member"),
    GATEWAY_REMOVE("staff remove <name>", "remove a staff member");

    private String command, description;

    Permissions(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return this.command;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean validate(CommandSender sender) {
        return sender.hasPermission(this.toString());
    }

    public String toString() {
        return this.name().toLowerCase().replace("_", ".");
    }

}
