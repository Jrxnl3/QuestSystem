package de.jinx.questsystem.handlers;

import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.objects.QuestTypes.QuestTypeEnums;
import de.jinx.questsystem.objects.QuestTypes.Quests.FishingType;
import de.jinx.questsystem.util.UtilQuest;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.ArrayList;

public class FishingListener implements Listener {


    @EventHandler
    public void onFish(PlayerFishEvent e){
        if(!(e.getCaught() instanceof Item)) return;

        Player player = e.getPlayer();

        if(!UtilQuest.hasActiveQuest(player.getUniqueId())) return;
        if(!UtilQuest.hasActiveQuestType(QuestTypeEnums.FISHING,player.getUniqueId())) return;

        ArrayList<Quest> fishingTypeList = UtilQuest.typeList(QuestTypeEnums.FISHING,player.getUniqueId());

        Item fishedFish = (Item) e.getCaught();

        for (Quest quest: fishingTypeList) {

            FishingType fishingType = (FishingType) quest.getQuestType();

            if(fishedFish.getItemStack().getType() == fishingType.getFishToCaught().getType()){
                UtilQuest.questCurrent(fishingType,player);
            }
        }

    }


}
