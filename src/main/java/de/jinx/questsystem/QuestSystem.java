package de.jinx.questsystem;

import de.jinx.questsystem.command.SpawnNPC;
import de.jinx.questsystem.handlers.NPCHandler;
import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.objects.QuestTypes.CraftingType;
import de.jinx.questsystem.objects.QuestTypes.FishingType;
import de.jinx.questsystem.objects.QuestTypes.GatheringType;
import de.jinx.questsystem.objects.QuestTypes.HuntingType;
import de.jinx.questsystem.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class QuestSystem extends JavaPlugin {

    private static QuestSystem questSystem;

    public HashMap<UUID, List<Quest>> activQuests;

    //HASH MAP WITH QUESTS FROM MYSQL (UUID, QUEST Object[sonst einzel speichern])

    private void fillHashMap(){
        activQuests.put(UUID.fromString("Spieler1"), questToArray(new Quest("Creeperslayer","Kill 10 Creepers",new ItemStack(Material.CREEPER_HEAD),new HuntingType(10, EntityType.CREEPER),10,NPCHandler.standartLootTable)));
        activQuests.put(UUID.fromString("Spieler2"), questToArray(new Quest("Pumpkinfan","Gather 10 Pumpkins",new ItemStack(Material.PUMPKIN),new GatheringType(10, new ItemStack(Material.PUMPKIN)),5,NPCHandler.standartLootTable)));
        activQuests.put(UUID.fromString("Spieler3"), questToArray(new Quest("Fisherman","Fish 5 Salmons",new ItemStack(Material.FISHING_ROD),new FishingType(5, new ItemStack(Material.SALMON)),6,NPCHandler.standartLootTable)));
        activQuests.put(UUID.fromString("Spieler4"), questToArray(new Quest("Crafter","Craft 1 Workbench",new ItemStack(Material.CRAFTING_TABLE),new CraftingType(1,new ItemStack(Material.CRAFTING_TABLE)),7,NPCHandler.standartLootTable)));
    }

    public static List<Quest> questToArray(Quest quest){
        return Arrays.asList(quest);
    }


    @Override
    public void onEnable() {

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
