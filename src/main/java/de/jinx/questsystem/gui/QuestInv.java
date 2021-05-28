package de.jinx.questsystem.gui;

import de.jinx.questsystem.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class QuestInv {

    public static final String TITLE = "§4§lQuests";

    public static void openInv(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9*5,TITLE);

        //BEGINN
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, new ItemBuilder(Material.ACACIA_STAIRS).setName(" ").build());
        }

        //MISSING QUESTS AND FILLER SLOTS

        player.openInventory(inv);
    }
}
