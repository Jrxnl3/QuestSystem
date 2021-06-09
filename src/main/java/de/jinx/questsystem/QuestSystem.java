package de.jinx.questsystem;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import de.jinx.questsystem.command.Commands;
import de.jinx.questsystem.command.SpawnNPC;
import de.jinx.questsystem.handlers.*;
import de.jinx.questsystem.handlers.QuestListeners.CraftingListener;
import de.jinx.questsystem.handlers.QuestListeners.FishingListener;
import de.jinx.questsystem.handlers.QuestListeners.GatheringListener;
import de.jinx.questsystem.handlers.QuestListeners.HuntingListener;
import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.objects.QuestTypes.QuestTypeEnums;
import de.jinx.questsystem.objects.QuestTypes.Quests.CraftingType;
import de.jinx.questsystem.objects.QuestTypes.Quests.FishingType;
import de.jinx.questsystem.objects.QuestTypes.Quests.GatheringType;
import de.jinx.questsystem.objects.QuestTypes.Quests.HuntingType;
import de.jinx.questsystem.objects.QuestTypes.Seltenheit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class QuestSystem extends JavaPlugin {

    private static QuestSystem questSystem;

    public Multimap<UUID,Quest> activeQuestMultiMap;

    //HASH MAP WITH QUESTS FROM MYSQL (UUID, QUEST Object[sonst einzel speichern. title,lore,...])

    private void fillHashMap(){
        activeQuestMultiMap.put(UUID.fromString("a9fe96e0-d85e-4146-b08d-9735f244a34f"), new Quest("Creeperslayer","Kill 10 Creepers",new ItemStack(Material.CREEPER_HEAD),new HuntingType(10, EntityType.CREEPER), QuestTypeEnums.HUNTING,new Seltenheit(Material.WHITE_STAINED_GLASS_PANE,"Common"),10,NPCHandler.standardLootTable));
        activeQuestMultiMap.put(UUID.fromString("a9fe96e0-d85e-4146-b08d-9735f244a34f"), new Quest(
                "Pumpkinfan",                                  //<-- Title
                "Gather 10 Pumpkins",                          //<--Lore

                new ItemStack(Material.PUMPKIN),                    //<-- DisplayItem

                new GatheringType(                                  //<--Type
                        10,                                //<--Wie oft?
                        new ItemStack(Material.PUMPKIN)),           //<-- Item to "Hunt"

                QuestTypeEnums.GATHERING,                           //<-- Enum Type

                new Seltenheit(Material.WHITE_STAINED_GLASS_PANE,   //<-- Seltenheit (Farbe)
                        "Common"),                            //<-- Name

                5,                                        //<-- Coin Reward
                NPCHandler.standardLootTable));                     //<-- LootTable


        activeQuestMultiMap.put(UUID.fromString("a9fe96e0-d85e-4146-b08d-9735f244a34f"), new Quest("Fisherman","Fish 5 Salmons",new ItemStack(Material.FISHING_ROD),new FishingType(5, new ItemStack(Material.SALMON)), QuestTypeEnums.FISHING,new Seltenheit(Material.WHITE_STAINED_GLASS_PANE,"Common"),6,NPCHandler.standardLootTable));
        activeQuestMultiMap.put(UUID.fromString("a9fe96e0-d85e-4146-b08d-9735f244a34f"), new Quest("Crafter","Craft 1 Workbench",new ItemStack(Material.CRAFTING_TABLE),new CraftingType(1,new ItemStack(Material.CRAFTING_TABLE)),QuestTypeEnums.CRAFTING,new Seltenheit(Material.WHITE_STAINED_GLASS_PANE,"Common"),7,NPCHandler.standardLootTable));
    }

    @Override
    public void onEnable() {
        questSystem = this;

        //TODO MySQL part

        activeQuestMultiMap = ArrayListMultimap.create();

        fillHashMap();

        System.out.println("QuestSystem has started");

        getServer().getPluginManager().registerEvents(new NPCHandler(),this);
        getServer().getPluginManager().registerEvents(new HuntingListener(),this);
        getServer().getPluginManager().registerEvents(new CraftingListener(),this);
        getServer().getPluginManager().registerEvents(new FishingListener(),this);
        getServer().getPluginManager().registerEvents(new GatheringListener(),this);

        this.getCommand("npc").setExecutor(new SpawnNPC());
        this.getCommand("quest").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        System.out.println("QuestSystem has been disabled");
    }

    public static QuestSystem getQuestSystem() {
        return questSystem;
    }
}
