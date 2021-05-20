package de.jinx.questsystem.command;

import de.jinx.questsystem.handlers.QuestVillager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnNPC implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("npc")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(args.length == 0) {
                    QuestVillager questNPC = new QuestVillager(player.getLocation());

                    return true;
                }
            }
        }
        return false;
    }
}
