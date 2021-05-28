package de.jinx.questsystem.util;

import de.jinx.questsystem.QuestSystem;
import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.objects.QuestTypes.QuestTypeEnums;
import de.jinx.questsystem.objects.QuestTypes.Quests.CraftingType;
import de.jinx.questsystem.objects.QuestTypes.Quests.FishingType;
import de.jinx.questsystem.objects.QuestTypes.Quests.GatheringType;
import de.jinx.questsystem.objects.QuestTypes.Quests.HuntingType;
import de.jinx.questsystem.objects.QuestTypes.Type;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class UtilQuest {

    public static ArrayList<Quest> typeList(QuestTypeEnums questTypeEnums, UUID playerUUID){
        ArrayList<Quest> genericList = new ArrayList<>();

        for (Quest quest: QuestSystem.getQuestSystem().activeQuestMultiMap.get(playerUUID)) {
            if (quest.getEnumType() == questTypeEnums){
                genericList.add(quest);
                System.out.println("Added Quest: " + quest);
            }
        }
        return genericList;
    }

    public static boolean hasActiveQuest(UUID playerUUID){
        if(QuestSystem.getQuestSystem().activeQuestMultiMap.get(playerUUID).size() >= 1){
            return true;    //Wenn in der Liste = true
        }else
            return false; //Sonst = false
    }

    public static boolean hasActiveQuestType(QuestTypeEnums type,UUID playerUUID){
        for (Quest quest: QuestSystem.getQuestSystem().activeQuestMultiMap.get(playerUUID)) {
            if (quest.getEnumType() == type){
                return true;        //Wird bei dem ersten mit dem Type returnen
            }
        }
        return false;   //Falls es keine gibt wird am Ende erst return.
    }

    public static <T extends Type> void questCurrent(T questType, Player player){
        if(questType.getCurrentCount() < questType.getMaxCount()){
            questType.setCurrentCount(questType.getCurrentCount() + 1);
            player.sendMessage("Goal: (" + questType.getCurrentCount() + "/" + questType.getMaxCount() + ")");
        }else {
            player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
            player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK,10,1); //<-- FINAL QUEST COMPLETE HERE
            player.sendMessage("You completed a Quest! :)");
        }
    }

    public static void questChatCheck(Player p){
        for (Quest quest: QuestSystem.getQuestSystem().activeQuestMultiMap.get(p.getUniqueId())) {

            p.sendMessage("====================\n"
                    +"§bTitle: §6" + quest.getTitle() + "\n"
                    +"§bLore: §6" + quest.getLore() + "\n"
                    +"§bShowCaseItem: §6" + quest.getDisplayItem() + "\n"
                    +"§bShowCaseItemType: §6" + quest.getDisplayItem().getType()+ "\n"
                    +"§bEnumType: §6" + quest.getEnumType() + "\n"
                    +"§bQuestType (Obj): §6" + quest.getQuestType() + "\n"
                    +"§bCoin Reward: §6" + quest.getCoinReward() + "\n"
                    +"§bLootTable: §6" + quest.getLootTable() + "\n"
            );

            if (quest.getQuestType().equals(CraftingType.class)) {
                p.sendMessage("§6Crafting Quest!");
            }else if(quest.getQuestType().equals(FishingType.class)){
                p.sendMessage("§bFishing Quest!");
            }else if(quest.getQuestType().equals(HuntingType.class)){
                p.sendMessage("§cHunting Quest!");
            }else if(quest.getQuestType().equals(GatheringType.class))
                p.sendMessage("§aGathering Quest!");

        }
    }

}
