package de.jinx.questsystem.util;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemCoder {

    //TODO Enchantments

    public static String encode(ItemStack itemToEncode){
        if(itemToEncode != null) {

            Material material = itemToEncode.getType();
            int amount = itemToEncode.getAmount();

            //NAME
            
            String name = "null";

            if(!itemToEncode.getItemMeta().getDisplayName().equals("") || !itemToEncode.getItemMeta().getDisplayName().equals(""))
                name = itemToEncode.getItemMeta().getDisplayName();

            String encodedString = "material:" + material;

            
            
            encodedString += ", amount:" + amount;
            encodedString += ", name:" + name;
            encodedString += ", lore:";

            //LORE
            
            if (itemToEncode.getItemMeta().getLore() != null) {

                for (String word : itemToEncode.getItemMeta().getLore()) {
                    encodedString += word;
                }
            } else
                encodedString += "null";

            //ENCHANTMENTS

            for (int i = 0; i < itemToEncode.getItemMeta().getEnchants().size(); i++) {

                List<Enchantment> enchantments = new ArrayList<>(itemToEncode.getItemMeta().getEnchants().keySet());

                encodedString += ", "+i+"_enchantment:"+enchantments.get(i).getKey()+":"+itemToEncode.getItemMeta().getEnchantLevel(enchantments.get(i));
            }
            
            
            return encodedString;
        }
        return null;
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

        //BUILDING OF ITEM
        ItemStack decodedItem = new ItemBuilder(Material.valueOf(material)).setAmount(amount).build();
        ItemMeta itemMeta = decodedItem.getItemMeta();

        if(name != "null"){
            decodedItem.getItemMeta().setDisplayName(name);
        }

        if(!lore.startsWith("null")){
            decodedItem.getItemMeta().setLore(Arrays.asList(lore));
        }

        for (int i = 4; i < statements.length; i++) {

            String[] enchantment = statements[i].split(":");

            itemMeta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(enchantment[2])),Integer.parseInt(enchantment[3]),false);
        }

        decodedItem.setItemMeta(itemMeta);

        return decodedItem;
    }
}
