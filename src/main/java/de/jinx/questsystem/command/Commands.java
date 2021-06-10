package de.jinx.questsystem.command;

import de.jinx.questsystem.gui.QuestInv;
import de.jinx.questsystem.util.ItemCoder;
import de.jinx.questsystem.util.UtilQuest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    String encoded = "";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("qs") || label.equalsIgnoreCase("q") || label.equalsIgnoreCase("quest")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(0 < args.length) {
                    //TODO Quests Commands

                    if(args[0].equalsIgnoreCase("check")){
                        UtilQuest.questChatCheck(player);
                    }else if(args[0].equalsIgnoreCase("encode")){
                        encoded = ItemCoder.encode(player.getInventory().getItemInMainHand());
                        System.out.println(encoded);
                    }else if(args[0].equalsIgnoreCase("decode")){
                        player.getInventory().addItem(ItemCoder.decode(encoded));
                    }

                    return true;
                }else {
                    QuestInv.openInv(player);
                }
            }
        }
        return false;
    }
}
