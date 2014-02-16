package com.codermason.gatewaystaff.staff;

import com.codermason.gatewaystaff.GatewayStaff;
import com.codermason.gatewaystaff.staff.Staff;
import com.codermason.gatewaystaff.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by codermason on 2/16/14
 */
public class StaffManager {

    private GatewayStaff plugin;

    private List<Staff> staffList = new ArrayList<Staff>();

    public StaffManager(GatewayStaff plugin) {
        this.plugin = plugin;
    }

    public void loadStaff() {
        for(String name : this.plugin.getConfig().getConfigurationSection("staff").getKeys(false)) {
            String rank = this.plugin.getConfig().getString("staff." + name + ".rank");
            Staff staff = new Staff(name, rank);
            staffList.add(staff);
        }
    }

    public void addStaff(String name, String prefix) {
        if(isStaff(name)) return;

        Staff staff = new Staff(name, prefix);
        staffList.add(staff);
    }

    public void removeStaff(String name) {
        if(!isStaff(name)) return;

        Staff staff = getStaff(name);
        staffList.remove(staff);
    }

    public boolean isStaff(String player) {
        return getStaff(player) != null;
    }

    public Staff getStaff(String player) {
        for(Staff s : staffList)
            if(s.getName().equals(player))
                return s;
        return null;
    }

    public void saveStaff() {
        plugin.getConfig().set("staff", null);
        for(Staff s : getStaffList()) {
            plugin.getConfig().set("staff."+s.getName(), s.getRank());
        }
        plugin.saveConfig();
    }

    public List<Staff> getStaffList() {
        return this.staffList;
    }

    public void showMenu(Player player) {
        Inventory menu = Bukkit.createInventory(player, Utils.getInventorySize(getStaffList().size()), "Staff Menu");
        for(Staff staff : getStaffList()) {
            String name = staff.getName();
            String lore = staff.getRank();
            ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            item = Utils.setNameAndLore(item, name, Arrays.asList(lore));
            menu.addItem(item);
        }
        player.openInventory(menu);
    }

}
