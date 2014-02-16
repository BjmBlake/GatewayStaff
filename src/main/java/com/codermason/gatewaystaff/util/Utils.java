package com.codermason.gatewaystaff.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * created by codermason on 2/16/14
 */
public class Utils {

    public static int getInventorySize(int number) {
        return (number - (number % 9) + 9);
    }

    public static ItemStack setNameAndLore(ItemStack item, String name, List<String> lore) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        im.setLore(lore);
        item.setItemMeta(im);
        return item;
    }

}
