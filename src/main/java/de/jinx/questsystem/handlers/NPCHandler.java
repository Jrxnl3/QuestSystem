package de.jinx.questsystem.handlers;

import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.objects.QuestTypes.HuntingType;
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

public class NPCHandler implements Listener {

    public static ItemStack[] standartLootTable = {new ItemBuilder(Material.STICK).build(),new ItemBuilder(Material.STONE).build(),new ItemBuilder(Material.DIRT).build()};

    Quest kill = new Quest("Monsterslayer","Kill 10 Zombies", new ItemStack(Material.WOODEN_AXE),new HuntingType(10,0,EntityType.ZOMBIE), 60, standartLootTable);

    @EventHandler
    public void interact(PlayerInteractAtEntityEvent event){
        if(!(event.getRightClicked() instanceof Villager)) return;

        Villager questNPC = (Villager) event.getRightClicked();

        if(questNPC.getCustomName().equals(QuestVillager.VILLAGER_NAME)){
            event.setCancelled(true);

            Player p = event.getPlayer();

            p.sendMessage(kill.getTitle());
            p.sendMessage(kill.getLore());
            p.sendMessage(kill.getItem().toString());
            p.sendMessage(kill.getItemMeta().toString());
            HuntingType type = (HuntingType) kill.getQuestType();
            p.sendMessage(type.getMobToKill().getName());
            p.sendMessage(String.valueOf(kill.getCoinReward()));
            p.sendMessage(kill.getLootTable().toString());
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent e){
        if(!(e.getEntity().getKiller() instanceof Player)) return;

        Player player = (Player) e.getEntity().getKiller();
        Entity victim = e.getEntity();

        //Liste nur mit HuntingTypes

        HuntingType type = (HuntingType) kill.getQuestType();

        if(type.getMobToKill() == victim.getType()) {
            type.setCurrentCount(type.getCurrentCount() + 1);
            player.sendMessage("Goal: ("+ type.getCurrentCount()+"/"+type.getMaxCount()+")");

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
