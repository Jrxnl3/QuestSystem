package de.jinx.questsystem.gui;

import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RewardInv {

    public static final String TITLE = "§aREWARD";

    public static void openRewardUI(Player player, Quest quest){
        //Amount wird in ItemMeta gespeichert! sonst nur Material und itemMeta?

        Inventory inv = Bukkit.createInventory(null, 9*5,TITLE);

        ItemStack[] rewards = quest.getLootTable();

        int maxItems =(int) (Math.random() * rewards.length + 1);

        for (int i = 0; i < maxItems; i++) {
            int randItem = (int) (Math.random() * rewards.length);

            int randomSlot = (int) (Math.random() * inv.getSize());

            if (inv.getItem(randomSlot) == null){
                inv.setItem(randomSlot, rewards[randItem]);
            } else i--;
        }

       player.openInventory(inv);
    }
}
