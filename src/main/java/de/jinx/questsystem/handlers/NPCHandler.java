package de.jinx.questsystem.handlers;

import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.objects.QuestType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class NPCHandler implements Listener {

    Quest kill = new Quest("Monsterslayer","Kill 10 Mobs", new ItemStack(Material.WOODEN_AXE),QuestType.HUNTING, 60, null);

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
            p.sendMessage(String.valueOf(kill.getCoinReward()));
            p.sendMessage(kill.getRewards().toString());
        }
    }
}
