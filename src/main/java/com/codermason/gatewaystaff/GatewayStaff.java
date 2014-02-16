package com.codermason.gatewaystaff;

import com.codermason.gatewaystaff.cmds.StaffCommand;
import com.codermason.gatewaystaff.staff.StaffManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * created by codermason on 2/16/14
 */
public class GatewayStaff  extends JavaPlugin {

    private static StaffManager manager;

    public void onEnable() {
        this.saveConfig();

        this.getCommand("staff").setExecutor(new StaffCommand(this));

        manager = new StaffManager(this);

        getManager().loadStaff();

        this.getLogger().info("Loaded "+getManager().getStaffList().size()+" different staff members!");

        this.getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onClick(InventoryClickEvent e) {
                if(e.getInventory().getName().equals("Staff Menu")) e.setCancelled(true);
            }

        }, this);
    }

    public void onDisable() {
        getManager().saveStaff();
    }

    public static StaffManager getManager() {
        return manager;
    }

}
