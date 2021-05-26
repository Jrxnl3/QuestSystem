package de.jinx.questsystem.handlers;

import de.jinx.questsystem.QuestSystem;
import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.objects.QuestTypes.HuntingType;
import de.jinx.questsystem.objects.QuestTypes.Type;
import de.jinx.questsystem.objects.QuestTypes.TypeEnums;
import de.jinx.questsystem.util.ItemBuilder;
import org.bukkit.Material;
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
import java.util.LinkedList;
import java.util.List;

public class NPCHandler implements Listener {

    public static ItemStack[] standartLootTable = {new ItemBuilder(Material.STICK).build(),new ItemBuilder(Material.STONE).build(),new ItemBuilder(Material.DIRT).build()};

    Quest kill = new Quest("Monsterslayer","Kill 10 Zombies", new ItemStack(Material.WOODEN_AXE),new HuntingType(10,0,EntityType.ZOMBIE),TypeEnums.HUNTING ,60, standartLootTable);

    public ArrayList<Quest> typeList(TypeEnums typeEnums, Player player){
        ArrayList<Quest> genericList = new ArrayList<>();

        for (Quest quest: QuestSystem.getQuestSystem().activQuestMultiMap.get(player.getUniqueId())) {
            System.out.println(quest.getQuestType());
            System.out.println(typeEnums);

            if (quest.getType() == typeEnums){

                genericList.add(quest);
                System.out.println("Added Quest: " + quest);
            }
        }
        return genericList;
    }

    @EventHandler
    public void interact(PlayerInteractAtEntityEvent event){
        if(!(event.getRightClicked() instanceof Villager)) return;

        Villager questNPC = (Villager) event.getRightClicked();

        if(questNPC.getCustomName().equals(QuestVillager.VILLAGER_NAME)){
            event.setCancelled(true);

            Player p = event.getPlayer();
            //OPTIC
            p.sendMessage(kill.getTitle());
            p.sendMessage(kill.getLore());
            //ITEM
            p.sendMessage(kill.getDisplayItem().toString());
            p.sendMessage(kill.getDisplayItemMeta().toString());
            //QUEST TYPE

            p.sendMessage("&6Type: " + kill.getType());

            HuntingType type = (HuntingType) kill.getQuestType();
            p.sendMessage(type.getMobToKill().getName());
            //LOOT
            p.sendMessage(String.valueOf(kill.getCoinReward()));
            p.sendMessage(kill.getLootTable().toString());
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent e){
        if(!(e.getEntity().getKiller() instanceof Player)) return; //<-- Player is online //Vom Spieler die Quests bekommen

        Player player = e.getEntity().getKiller();
        Entity victim = e.getEntity();


        ArrayList<Quest> huntingList = typeList(TypeEnums.HUNTING,player);  //<-- For Spieler mit UUID und Hunting Quests


        /*
        for (Quest quest: QuestSystem.getQuestSystem().activQuests.get(player.getUniqueId())) {
            if (quest.getQuestType() == TypeEnums.HUNTING){
                if(huntingList.equals(null))
                    huntingList.set(0,quest);
                else
                    huntingList.add(quest);
            }
        }
        */

        for (Quest quest: huntingList) {
            HuntingType type = (HuntingType) quest.getQuestType();

            if (type.getMobToKill() == victim.getType()) {
                type.setCurrentCount(type.getCurrentCount() + 1);
                player.sendMessage("Goal: (" + type.getCurrentCount() + "/" + type.getMaxCount() + ")");
            }
        }
    }

    @EventHandler
    public void onCraft(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();



        if(e.getSlotType() == InventoryType.SlotType.RESULT){
            ItemStack item = e.getCurrentItem();


        }
    }

    @EventHandler
    public void onFish(PlayerFishEvent e){
        if(e.getCaught() instanceof Item){
            Item item = (Item) e.getCaught();
            if(item.getItemStack().getType().equals(Material.LEGACY_RAW_FISH)){
                ItemStack caughtFish = item.getItemStack();


            }
        }
    }

}
