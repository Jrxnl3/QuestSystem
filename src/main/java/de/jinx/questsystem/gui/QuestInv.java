package de.jinx.questsystem.gui;

import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.util.ItemBuilder;
import de.jinx.questsystem.util.UtilQuest;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class QuestInv {

    public static final String TITLE = "§4§lQuests";

    public static void openInv(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9*4,TITLE);

        //TODO Check if working Quests and Filler Slots

        ArrayList<Quest> playersQuestsList = UtilQuest.getQuestListPlayer(player.getUniqueId());

        //=============================OBERSTE REIHE=============================

        //1te Quest
        inv.setItem(0,new ItemBuilder(playersQuestsList.get(1).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(1,new ItemBuilder(playersQuestsList.get(1).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(2,new ItemBuilder(playersQuestsList.get(1).getSeltenheit().getGlass_Pane()).build());

        //2te Quest
        inv.setItem(3,new ItemBuilder(playersQuestsList.get(2).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(4,new ItemBuilder(playersQuestsList.get(2).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(5,new ItemBuilder(playersQuestsList.get(2).getSeltenheit().getGlass_Pane()).build());

        //3te Quest
        inv.setItem(6,new ItemBuilder(playersQuestsList.get(3).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(7,new ItemBuilder(playersQuestsList.get(3).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(8,new ItemBuilder(playersQuestsList.get(3).getSeltenheit().getGlass_Pane()).build());

        //=============================MITTLERSTE REIHE=============================

        inv.setItem(9,new ItemBuilder(playersQuestsList.get(1).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(10,playersQuestsList.get(1).getDisplayItem());
        inv.setItem(11,new ItemBuilder(playersQuestsList.get(1).getSeltenheit().getGlass_Pane()).build());

        inv.setItem(12,new ItemBuilder(playersQuestsList.get(2).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(13,playersQuestsList.get(2).getDisplayItem());
        inv.setItem(14,new ItemBuilder(playersQuestsList.get(2).getSeltenheit().getGlass_Pane()).build());

        inv.setItem(15,new ItemBuilder(playersQuestsList.get(3).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(16,playersQuestsList.get(3).getDisplayItem());
        inv.setItem(17,new ItemBuilder(playersQuestsList.get(3).getSeltenheit().getGlass_Pane()).build());

        //=============================UNTERSTE REIHE=============================

        //1te Quest
        inv.setItem(18,new ItemBuilder(playersQuestsList.get(1).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(19,new ItemBuilder(playersQuestsList.get(1).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(20,new ItemBuilder(playersQuestsList.get(1).getSeltenheit().getGlass_Pane()).build());

        //2te Quest
        inv.setItem(21,new ItemBuilder(playersQuestsList.get(2).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(22,new ItemBuilder(playersQuestsList.get(2).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(23,new ItemBuilder(playersQuestsList.get(2).getSeltenheit().getGlass_Pane()).build());

        //3te Quest
        inv.setItem(24,new ItemBuilder(playersQuestsList.get(3).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(25,new ItemBuilder(playersQuestsList.get(3).getSeltenheit().getGlass_Pane()).build());
        inv.setItem(26,new ItemBuilder(playersQuestsList.get(3).getSeltenheit().getGlass_Pane()).build());

        //=============================LEERE FELDER=============================

        for (int i = 0; i < inv.getSize(); i++) {
            if(inv.getItem(i) == null){
            inv.setItem(i, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName(" ").build());
            }
        }

        player.openInventory(inv);
    }
}
