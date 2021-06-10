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
        Inventory inv = Bukkit.createInventory(null, 9*5,TITLE);

        ArrayList<Quest> playersQuestsList = UtilQuest.getQuestListPlayer(player.getUniqueId());

        inv.setItem(0,new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(1,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(2,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(3,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(4,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(5,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(6,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(7,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(8,new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").build());


        for (int i = 0; i < 3; i++) {
            try{
                inv.setItem(9+ i *3,new ItemBuilder(playersQuestsList.get(i).getSeltenheit().getGlass_Pane()).build());
                inv.setItem(10+ i *3,new ItemBuilder(playersQuestsList.get(i).getSeltenheit().getGlass_Pane()).build());
                inv.setItem(11+ i *3,new ItemBuilder(playersQuestsList.get(i).getSeltenheit().getGlass_Pane()).build());

                inv.setItem(18+ i *3,new ItemBuilder(playersQuestsList.get(i).getSeltenheit().getGlass_Pane()).build());
                inv.setItem(19+ i *3,playersQuestsList.get(i).getDisplayItem());
                inv.setItem(20+ i *3,new ItemBuilder(playersQuestsList.get(i).getSeltenheit().getGlass_Pane()).build());

                inv.setItem(27+ i *3,new ItemBuilder(playersQuestsList.get(i).getSeltenheit().getGlass_Pane()).build());
                inv.setItem(28+ i *3,new ItemBuilder(Material.PAPER).setName("§6Goal: (§c"+playersQuestsList.get(i).getQuestType().getCurrentCount() + "§6/§a" + playersQuestsList.get(i).getQuestType().getMaxCount()+ "§6)").build());
                inv.setItem(29+ i *3,new ItemBuilder(playersQuestsList.get(i).getSeltenheit().getGlass_Pane()).build());

            }catch (IndexOutOfBoundsException e){
                inv.setItem(9 + i *3,new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).build());
                inv.setItem(10+ i *3,new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).build());
                inv.setItem(11+ i *3,new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).build());

                inv.setItem(18+ i *3,new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).build());
                inv.setItem(19+ i *3,new ItemBuilder(Material.BEDROCK).setName("§aAlready Completed").build());
                inv.setItem(20+ i *3,new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).build());

                inv.setItem(27+ i *3,new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).build());
                inv.setItem(28+ i *3,new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).build());
                inv.setItem(29+ i *3,new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).build());

            }
        }

        inv.setItem(36,new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(37,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(38,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(39,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(40,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(41,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(42,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(43,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        inv.setItem(44,new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").build());

        player.openInventory(inv);
    }
}
