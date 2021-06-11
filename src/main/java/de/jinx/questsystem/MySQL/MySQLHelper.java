package de.jinx.questsystem.MySQL;


import de.jinx.questsystem.QuestSystem;
import de.jinx.questsystem.objects.Quest;
import de.jinx.questsystem.objects.QuestTypes.QuestTypeEnums;
import de.jinx.questsystem.objects.QuestTypes.Quests.CraftingType;
import de.jinx.questsystem.objects.QuestTypes.Quests.FishingType;
import de.jinx.questsystem.objects.QuestTypes.Quests.GatheringType;
import de.jinx.questsystem.objects.QuestTypes.Quests.HuntingType;
import de.jinx.questsystem.objects.QuestTypes.Seltenheit;
import de.jinx.questsystem.util.ItemBuilder;
import de.jinx.questsystem.util.ItemCoder;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLHelper {

    //TODO: CHECK IF WORKING

    public static Quest getRandomQuest() throws SQLException {

        MySQL mySQL = QuestSystem.getMySQL();

        Quest quest = null;

        ResultSet rs = mySQL.query("SELECT * from questpool order by rand() limit 1 ");

        while (rs.next()){
            String title = rs.getString("title");
            String lore = rs.getString("lore");
            String encodedDisplayItem = rs.getString("encodedItem");


            String questType = rs.getString("questType");
            String questGoal = rs.getString("questGoal");
            int questMaxGoal = rs.getInt("questMaxCount");



            int seltenheitID = rs.getInt("seltenheit");
            int coinReward = rs.getInt("coinReward");
            int lootTableID = rs.getInt("lootTable");


            //================> LootTable <================
            ResultSet lootTable = mySQL.query("SELECT * from loottable where lootID = "+lootTableID);

            int groupID = 0;

            while(lootTable.next()) {
                groupID = lootTable.getInt("groupID");
            }

            //================> LootTable Amount + Storing <================
            int itemAmount = 0;

            ResultSet lootItemAmount = mySQL.query("Select COUNT(*) from items where groupID = "+groupID);

            if(lootItemAmount.next()){
                itemAmount = lootItemAmount.getInt("COUNT(*)");
                System.out.println("Loot Item Amounts: "+lootItemAmount.getInt("COUNT(*)"));
            }

            ItemStack[] loot = new ItemStack[itemAmount];

            ResultSet lootItems = mySQL.query("Select * from items where groupID = "+groupID);


            while(lootItems.next()){
                loot[0] = ItemCoder.decode(lootItems.getString("encodedItem"));
            }


            //================> Seltenheit <================

            ResultSet seltenheitRS = mySQL.query("SELECT * from seltenheit where id = "+seltenheitID);

            Seltenheit seltenheit = null;

            while(seltenheitRS.next()) {
                seltenheit = new Seltenheit(seltenheitRS.getString("name"), Material.valueOf(seltenheitRS.getString("glass_pane")));
            }

            //Quest creating


            quest = new Quest(title,lore,ItemCoder.decode(encodedDisplayItem),seltenheit,coinReward,loot);

            switch (questType){
                case "HuntingType":
                    HuntingType huntingType = new HuntingType(questMaxGoal, EntityType.valueOf(questGoal));
                    quest.setQuestType(huntingType);
                    quest.setEnumType(QuestTypeEnums.HUNTING);
                    break;
                case "FishingType":
                    FishingType fishingType = new FishingType(questMaxGoal, new ItemBuilder(Material.valueOf(questGoal)).build());
                    quest.setQuestType(fishingType);
                    quest.setEnumType(QuestTypeEnums.FISHING);
                    break;
                case "GatheringType":
                    GatheringType gatheringType = new GatheringType(questMaxGoal, new ItemBuilder(Material.valueOf(questGoal)).build());
                    quest.setQuestType(gatheringType);
                    quest.setEnumType(QuestTypeEnums.GATHERING);
                    break;
                case "CraftingType":
                    CraftingType craftingType = new CraftingType(questMaxGoal, new ItemBuilder(Material.valueOf(questGoal)).build());
                    quest.setQuestType(craftingType);
                    quest.setEnumType(QuestTypeEnums.CRAFTING);
                    break;
            }
        }
        return quest;
    }

}
