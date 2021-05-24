package de.jinx.questsystem;

import de.jinx.questsystem.command.SpawnNPC;
import de.jinx.questsystem.handlers.NPCHandler;
import de.jinx.questsystem.objects.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class QuestSystem extends JavaPlugin {

    private static QuestSystem questSystem;

    //HASH MAP WITH QUESTS

    @Override
    public void onEnable() {
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
