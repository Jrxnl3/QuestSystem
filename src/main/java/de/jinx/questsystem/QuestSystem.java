package de.jinx.questsystem;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import de.jinx.questsystem.command.SpawnNPC;
import de.jinx.questsystem.handlers.NPCHandler;
import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.objects.QuestTypes.*;
import de.jinx.questsystem.objects.QuestTypes.Quests.CraftingType;
import de.jinx.questsystem.objects.QuestTypes.Quests.FishingType;
import de.jinx.questsystem.objects.QuestTypes.Quests.GatheringType;
import de.jinx.questsystem.objects.QuestTypes.Quests.HuntingType;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class QuestSystem extends JavaPlugin {

    private static QuestSystem questSystem;

    //public HashMap<UUID, ArrayList<Quest>> activQuests;

    public Multimap<UUID,Quest> activQuestMultiMap;

    //HASH MAP WITH QUESTS FROM MYSQL (UUID, QUEST Object[sonst einzel speichern])

    private void fillHashMap(){
        activQuestMultiMap.put(UUID.fromString("a9fe96e0-d85e-4146-b08d-9735f244a34f"), new Quest("Creeperslayer","Kill 10 Creepers",new ItemStack(Material.CREEPER_HEAD),new HuntingType(10, EntityType.CREEPER), QuestTypeEnums.HUNTING,10,NPCHandler.standartLootTable));
        activQuestMultiMap.put(UUID.fromString("a9fe96e0-d85e-4146-b08d-9735f244a34f"), new Quest(
                "Pumpkinfan", //<-- Title
                "Gather 10 Pumpkins", //<-- Lore
                new ItemStack(Material.PUMPKIN), //<-- DisplayItem
                new GatheringType( //<--Type
                        10, //<--Wie oft?
                        new ItemStack(Material.PUMPKIN)), //<-- Item to "Hunt"
                QuestTypeEnums.GATHERING, //<-- Enum Type
                5, //<-- Coin Reward
                NPCHandler.standartLootTable)); //<-- LootTable


        activQuestMultiMap.put(UUID.fromString("a9fe96e0-d85e-4146-b08d-9735f244a34f"), new Quest("Fisherman","Fish 5 Salmons",new ItemStack(Material.FISHING_ROD),new FishingType(5, new ItemStack(Material.SALMON)), QuestTypeEnums.FISHING,6,NPCHandler.standartLootTable));
        activQuestMultiMap.put(UUID.fromString("a9fe96e0-d85e-4146-b08d-9735f244a34f"), new Quest("Crafter","Craft 1 Workbench",new ItemStack(Material.CRAFTING_TABLE),new CraftingType(1,new ItemStack(Material.CRAFTING_TABLE)), QuestTypeEnums.CRAFTING,7,NPCHandler.standartLootTable));
    }



    @Override
    public void onEnable() {

        activQuestMultiMap = ArrayListMultimap.create();

        fillHashMap();

        questSystem = this;
        System.out.println("QuestSystem has started");

        getServer().getPluginManager().registerEvents(new NPCHandler(),this);

        this.getCommand("npc").setExecutor(new SpawnNPC());
    }

    @Override
    public void onDisable() {
        System.out.println("QuestSystem has been disabled");
    }

    public static QuestSystem getQuestSystem() {
        return questSystem;
    }
}
