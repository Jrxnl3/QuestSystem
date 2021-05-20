package de.jinx.questsystem.handlers;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class QuestVillager {

    public static final String VILLAGER_NAME = "§b§lQUESTS";

    public QuestVillager(Location loc){
        Villager questNPC = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        questNPC.setCustomName(VILLAGER_NAME);
        questNPC.setCustomNameVisible(true);
        questNPC.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,Integer.MAX_VALUE,500));
    }


}
