package de.jinx.questsystem.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemCoder {

    //TODO Check if working

    public static String encode(ItemStack itemToEncode){

        Material material = itemToEncode.getType();
        int amount = itemToEncode.getAmount();
        String name = itemToEncode.getItemMeta().getDisplayName();
        List<String> lore = itemToEncode.getItemMeta().getLore();


        String encodedString = "material:" + material.toString();
        encodedString.concat(",amount:"+amount);
        encodedString.concat(",name:"+name);
        encodedString.concat(",lore:");

        for (String word:lore) {
            encodedString.concat(word);
        }
        return encodedString;
    }

    public static ItemStack decode(String stringToEncode){

        String[] statements = stringToEncode.split(",");

        String material = statements[0].split(":")[1];
        int amount = Integer.parseInt(statements[1].split(":")[1]);
        String name = statements[2].split(":")[1];

        String lore = "";
        for (int i = 1; i < statements[3].split(":").length; i++) {

            lore = lore.concat(statements[3].split(":")[i]);

        }

        ItemStack decodedItem = new ItemBuilder(Material.valueOf(material)).setAmount(amount).setName(name).setLore(lore).build();
        return decodedItem;
    }
}
