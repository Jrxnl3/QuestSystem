package de.jinx.questsystem.handlers.QuestListeners;

import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.objects.QuestTypes.QuestTypeEnums;
import de.jinx.questsystem.objects.QuestTypes.Quests.CraftingType;
import de.jinx.questsystem.util.UtilQuest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CraftingListener implements Listener {

    @EventHandler
    public void onCraft(InventoryClickEvent e){
        if(!(e.getSlotType() == InventoryType.SlotType.RESULT)) return;


        Player player = (Player) e.getWhoClicked();


        if(!UtilQuest.hasActiveQuest(player.getUniqueId())) return;
        if(!UtilQuest.hasActiveQuest(QuestTypeEnums.CRAFTING,player.getUniqueId())) return;


        ItemStack item = e.getCurrentItem();
        item.setAmount(e.getCurrentItem().getAmount());
        item.setItemMeta(e.getCurrentItem().getItemMeta());


        ArrayList<Quest> craftingTypeList = UtilQuest.getQuestListPlayer(QuestTypeEnums.CRAFTING,player.getUniqueId());

        for (Quest quest: craftingTypeList) {
            CraftingType craftingType = (CraftingType) quest.getQuestType();

            if (craftingType.getItemToCraft().getType() == item.getType()) {

                UtilQuest.questCurrent(quest,craftingType,player, item.getAmount());
            }
        }
    }
}
