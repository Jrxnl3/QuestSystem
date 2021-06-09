package de.jinx.questsystem.handlers;

import de.jinx.questsystem.gui.QuestInv;
import de.jinx.questsystem.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class NPCHandler implements Listener {

    public static ItemStack[] standardLootTable = {
            new ItemBuilder(Material.STICK).setAmount(3).build(),
            new ItemBuilder(Material.STONE).setAmount(5).build(),
            new ItemBuilder(Material.DIRT).setAmount(10).build(),
            new ItemBuilder(Material.DIAMOND).setAmount(1).build()
    };

    @EventHandler
    public void QuestNPCInteract(PlayerInteractAtEntityEvent event){
        if(!(event.getRightClicked() instanceof Villager)) return;

        Villager questNPC = (Villager) event.getRightClicked();

        if(questNPC.getCustomName().equals(QuestVillager.VILLAGER_NAME)){
            event.setCancelled(true);

            Player p = event.getPlayer();

            QuestInv.openInv(p);
        }
    }

/*
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
*/
}
