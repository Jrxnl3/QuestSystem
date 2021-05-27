package de.jinx.questsystem.handlers;

import de.jinx.questsystem.QuestSystem;
import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.objects.QuestTypes.Quests.CraftingType;
import de.jinx.questsystem.objects.QuestTypes.Quests.FishingType;
import de.jinx.questsystem.objects.QuestTypes.Quests.GatheringType;
import de.jinx.questsystem.objects.QuestTypes.Quests.HuntingType;
import de.jinx.questsystem.objects.QuestTypes.QuestTypeEnums;
import de.jinx.questsystem.objects.QuestTypes.Type;
import de.jinx.questsystem.util.ItemBuilder;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class NPCHandler implements Listener {

    public static ItemStack[] standartLootTable = {new ItemBuilder(Material.STICK).build(),new ItemBuilder(Material.STONE).build(),new ItemBuilder(Material.DIRT).build()};

    public ArrayList<Quest> typeList(QuestTypeEnums questTypeEnums, UUID playerUUID){
        ArrayList<Quest> genericList = new ArrayList<>();

        for (Quest quest: QuestSystem.getQuestSystem().activQuestMultiMap.get(playerUUID)) {
            if (quest.getEnumType() == questTypeEnums){
                genericList.add(quest);
                System.out.println("Added Quest: " + quest);
            }
        }
        return genericList;
    }

    public boolean hasActiveQuest(UUID playerUUID){
        if(QuestSystem.getQuestSystem().activQuestMultiMap.get(playerUUID).size() >= 1){
            return true;    //Wenn in der Liste = true
        }else
            return false; //Sonst = false
    }

    public boolean hasActiveQuestType(QuestTypeEnums type,UUID playerUUID){
        for (Quest quest: QuestSystem.getQuestSystem().activQuestMultiMap.get(playerUUID)) {
            if (quest.getEnumType() == type){
                return true;        //Wird bei dem ersten mit dem Type returnen
            }
        }
        return false;   //Falls es keine gibt wird am Ende erst return.
    }

    public <T extends Type> void questCurrent(T questType, Player player){
        if(questType.getCurrentCount() < questType.getMaxCount()){
            questType.setCurrentCount(questType.getCurrentCount() + 1);
            player.sendMessage("Goal: (" + questType.getCurrentCount() + "/" + questType.getMaxCount() + ")");
        }else {
            player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
            player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK,10,1); //<-- FINAL QUEST COMPLETE HERE
            player.sendMessage("You completed a Quest! :)");
        }
    }

    @EventHandler
    public void interact(PlayerInteractAtEntityEvent event){
        if(!(event.getRightClicked() instanceof Villager)) return;

        Villager questNPC = (Villager) event.getRightClicked();

        if(questNPC.getCustomName().equals(QuestVillager.VILLAGER_NAME)){
            event.setCancelled(true);

            ArrayList<Quest> playerQuests= new ArrayList<>();

            Player p = event.getPlayer();

            for (Quest quest: QuestSystem.getQuestSystem().activQuestMultiMap.get(event.getPlayer().getUniqueId())) {
                playerQuests.add(quest);

                //OPTIC
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


    //<!-- WAS IST WENN 2 SPIELER DAS EVENT GLEICHZEITIG AUSFÜHREN --!>
    //<!-- SPIELER BEI QUEST IN ARRAYLIST BEI EVENT ABFRAGEN OB SPIELER IN ARRAYLIST?? (PERFORMANCE)--!>
    @EventHandler
    public void onKill(EntityDeathEvent e){
        if(!(e.getEntity().getKiller() instanceof Player)) return; //<-- Player is online

        Player player = e.getEntity().getKiller(); //Spieler = Killer

        if(!hasActiveQuest(player.getUniqueId())) return; //Schauen ob Spieler Quest hat

        if(hasActiveQuestType(QuestTypeEnums.HUNTING,player.getUniqueId())) return; //Ob Quest = HuntingType

        Entity victim = e.getEntity(); //Entity bekommen

        ArrayList<Quest> huntingList = typeList(QuestTypeEnums.HUNTING,player.getUniqueId());  //<-- For Spieler mit UUID und HuntingType Quests

        for (Quest quest: huntingList) {        //Für alle HuntingQuests der Spieler hat

            HuntingType huntingTypeQuest = (HuntingType) quest.getQuestType();

            if (huntingTypeQuest.getMobToKill() == victim.getType()) {
                huntingTypeQuest.setCurrentCount(huntingTypeQuest.getCurrentCount() + 1);
                player.sendMessage("Goal: (" + huntingTypeQuest.getCurrentCount() + "/" + huntingTypeQuest.getMaxCount() + ")");
            }
        }
    }

    @EventHandler
    public void onCraft(InventoryClickEvent e){
        if(!(e.getSlotType() == InventoryType.SlotType.RESULT)) return;

        Player player = (Player) e.getWhoClicked();

        if(!hasActiveQuest(player.getUniqueId())) return;

        if(hasActiveQuestType(QuestTypeEnums.CRAFTING,player.getUniqueId())) return;

        ItemStack item = e.getCurrentItem();

        ArrayList<Quest> craftingTypeList = typeList(QuestTypeEnums.CRAFTING,player.getUniqueId());

        for (Quest quest: craftingTypeList) {

            CraftingType craftingType = (CraftingType) quest.getQuestType();

            if (craftingType.getItemToCraft().getType() == item.getType()) {
                questCurrent(craftingType,player);
            }
        }
    }

    @EventHandler
    public void onFish(PlayerFishEvent e){
        if(!(e.getCaught() instanceof Item)) return;

        Player player = e.getPlayer();

        if(!hasActiveQuest(player.getUniqueId())) return;

        if(hasActiveQuestType(QuestTypeEnums.FISHING,player.getUniqueId())) return;

        ArrayList<Quest> fishingTypeList = typeList(QuestTypeEnums.FISHING,player.getUniqueId());

        Item item = (Item) e.getCaught();


        for (Quest quest: fishingTypeList) {

            FishingType fishingType = (FishingType) quest.getQuestType();

            if(item.getItemStack().getType() == fishingType.getFishToCaught().getType()){
                questCurrent(fishingType,player);
            }
        }

    }

}
