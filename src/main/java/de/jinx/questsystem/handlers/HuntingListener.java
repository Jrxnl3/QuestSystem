package de.jinx.questsystem.handlers;

import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.objects.QuestTypes.QuestTypeEnums;
import de.jinx.questsystem.objects.QuestTypes.Quests.HuntingType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;


public class HuntingListener implements Listener {

    //<!-- WAS IST WENN 2 SPIELER DAS EVENT GLEICHZEITIG AUSFÜHREN --!>
    //<!-- SPIELER BEI QUEST IN ARRAYLIST BEI EVENT ABFRAGEN OB SPIELER IN ARRAYLIST?? (PERFORMANCE)--!>
    @EventHandler
    public void onKill(EntityDeathEvent e){
        if(!(e.getEntity().getKiller() instanceof Player)) return; //<-- Player is online

        Player player = e.getEntity().getKiller(); //Spieler = Killer

        if(!NPCHandler.hasActiveQuest(player.getUniqueId())) return; //Schauen ob Spieler Quest hat

        if(!NPCHandler.hasActiveQuestType(QuestTypeEnums.HUNTING,player.getUniqueId())) return; //Ob Quest = HuntingType

        Entity victim = e.getEntity(); //Entity bekommen

        ArrayList<Quest> huntingList = NPCHandler.typeList(QuestTypeEnums.HUNTING,player.getUniqueId());  //<-- For Spieler mit UUID und HuntingType Quests

        for (Quest quest: huntingList) {        //Für alle HuntingQuests der Spieler hat

            HuntingType huntingTypeQuest = (HuntingType) quest.getQuestType();

            if (huntingTypeQuest.getMobToKill() == victim.getType()) {
                NPCHandler.questCurrent(huntingTypeQuest,player);
            }
        }
    }



}
