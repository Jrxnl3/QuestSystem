package de.jinx.questsystem.util;

import de.jinx.questsystem.QuestSystem;
import de.jinx.questsystem.gui.RewardInv;
import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.objects.QuestTypes.QuestTypeEnums;
import de.jinx.questsystem.objects.QuestTypes.Type;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class UtilQuest {

    public static ArrayList<Quest> getQuestListPlayer(QuestTypeEnums questTypeEnums, UUID playerUUID){
        ArrayList<Quest> genericList = new ArrayList<>();

        for (Quest quest: QuestSystem.getQuestSystem().activeQuestMultiMap.get(playerUUID)) {
            if (quest.getEnumType() == questTypeEnums){
                genericList.add(quest);
                System.out.println("Added Quest: " + quest);
            }
        }
        return genericList;
    }

    public static ArrayList<Quest> getQuestListPlayer(UUID playerUUID){
        ArrayList<Quest> genericList = new ArrayList<>();

        for (Quest quest: QuestSystem.getQuestSystem().activeQuestMultiMap.get(playerUUID)) {
                genericList.add(quest);
                System.out.println("Added Quest: " + quest);
        }
        return genericList;
    }

    public static boolean hasActiveQuest(UUID playerUUID){
        if(QuestSystem.getQuestSystem().activeQuestMultiMap.get(playerUUID).size() >= 1){
            return true;
        }
        return false;
    }

    public static boolean hasActiveQuest(QuestTypeEnums type, UUID playerUUID){
        for (Quest quest: QuestSystem.getQuestSystem().activeQuestMultiMap.get(playerUUID)) {
            if (quest.getEnumType() == type){
                return true;
            }
        }
        return false;
    }

    public static <T extends Type> void questCurrent(Quest quest, T questType, Player player){
        if(questType.getCurrentCount() + 1 < questType.getMaxCount()){
            questType.setCurrentCount(questType.getCurrentCount() + 1);
            player.sendMessage("Goal: (" + questType.getCurrentCount() + "/" + questType.getMaxCount() + ")");
        }else {
            complete(player, quest);
        }
    }

    public static <T extends Type> void questCurrent(Quest quest, T questType, Player player, int amount){
        if(questType.getCurrentCount() < questType.getMaxCount() && questType.getCurrentCount() + amount < questType.getMaxCount()){
            questType.setCurrentCount(questType.getCurrentCount() + amount);
            player.sendMessage("Goal: (" + questType.getCurrentCount() + "/" + questType.getMaxCount() + ")");

        }else {
            //TODO MySQL Saving?
            complete(player, quest);
        }
    }

    public static void complete(Player player,Quest quest){
        player.getWorld().playEffect(player.getLocation(), Effect.VILLAGER_PLANT_GROW, 10);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK,10,1); //<--TODO: FINAL QUEST COMPLETE HERE
        player.sendMessage(QuestSystem.getQuestSystem().PREFIX + "§6 >> §aYou completed the Quest: "+quest.getTitle()+"! :)");
        player.sendMessage(QuestSystem.getQuestSystem().PREFIX + "§6 >> §aYou've recieved : "+quest.getCoinReward()+" coins! :)");
        RewardInv.openRewardUI(player,quest);
        removeQuestFromList(quest,player);

    }

    public static void questChatCheck(Player p){
        for (Quest quest: QuestSystem.getQuestSystem().activeQuestMultiMap.get(p.getUniqueId())) {
            p.sendMessage("<|====================|>\n"
                    +"§bTitle: §6" + quest.getTitle() + "\n"
                    +"§bLore: §6" + quest.getLore() + "\n"
                    +"§bShowCaseItem: §6" + quest.getDisplayItem() + "\n"
                    +"§bShowCase*ItemType: §6" + quest.getDisplayItem().getType()+ "\n"
                    +"§bEnumType: §6" + quest.getEnumType() + "\n"
                    +"§bQuestType (Obj): §6" + quest.getQuestType() + "\n"
                    +"§bRarity: " + quest.getSeltenheit().getName() + "\n"
                    +"§bGlass Pain: " + quest.getSeltenheit().getGlass_Pane() + "\n"
                    +"§bCoin Reward: §6" + quest.getCoinReward() + "\n"
                    +"§bLootTable: §6" + quest.getLootTable() + "\n"
            );

            p.sendMessage("\n");

            if (quest.getEnumType().equals(QuestTypeEnums.CRAFTING)) {
                p.sendMessage("§6Crafting Quest!");
            }else if(quest.getEnumType().equals(QuestTypeEnums.FISHING)){
                p.sendMessage("§bFishing Quest!");
            }else if(quest.getEnumType().equals(QuestTypeEnums.HUNTING)){
                p.sendMessage("§cHunting Quest!");
            }else if(quest.getEnumType().equals(QuestTypeEnums.GATHERING))
                p.sendMessage("§aGathering Quest!");

        }
        p.sendMessage("<|====================|>");
    }

    public static void removeQuestFromList(Quest quest, Player player){
        System.out.println("Removed Player Form List");
        QuestSystem.getQuestSystem().activeQuestMultiMap.remove(player.getUniqueId(),quest);
    }
}
