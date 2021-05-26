package de.jinx.questsystem.objects;

import de.jinx.questsystem.objects.QuestTypes.Type;
import de.jinx.questsystem.objects.QuestTypes.TypeEnums;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;

public class Quest <T extends Type>{

    String title;
    String lore;

    ItemStack displayItem;
    ItemMeta displayItemMeta;

    T questType;
    TypeEnums type;

    int coinReward;
    ItemStack[] lootTable;

    public Quest(String title, String lore, ItemStack displayItem, T questType, int coinReward, ItemStack[] lootTable) {
        this.title = title;

        this.lore = lore;

        this.displayItem = displayItem;
        this.displayItemMeta = displayItem.getItemMeta();
        this.displayItemMeta.setDisplayName(title);
        this.displayItemMeta.setLore(Arrays.asList(lore));

        this.questType = questType;
        this.type = questType.getEnumType();

        this.coinReward = coinReward;
        this.lootTable = lootTable;
    }


    public String getTitle() {
        return title;
    }

    public String getLore() {
        return lore;
    }

    public ItemStack getDisplayItem() {
        return displayItem;
    }

    public ItemMeta getDisplayItemMeta() {
        return displayItemMeta;
    }

    public Object getQuestType() {
        return questType;
    }

    public int getCoinReward() {
        return coinReward;
    }

    public ItemStack[] getLootTable() {
        return lootTable;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public void setDisplayItem(ItemStack displayItem) {
        this.displayItem = displayItem;
    }

    public void setDisplayItemMeta(ItemMeta displayItemMeta) {
        this.displayItemMeta = displayItemMeta;
    }

    public void setCoinReward(int coinReward) {
        this.coinReward = coinReward;
    }

    public void setLootTable(ItemStack[] lootTable) {
        this.lootTable = lootTable;
    }

}