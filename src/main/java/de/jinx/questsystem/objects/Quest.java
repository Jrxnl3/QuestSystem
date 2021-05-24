package de.jinx.questsystem.objects;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Quest {

    String title;
    String lore;
    ItemStack item;
    ItemMeta itemMeta;

    Object questType;

    int coinReward;
    ItemStack[] lootTable;

    public Quest(String title, String lore, ItemStack item, Object questType, int coinReward, ItemStack[] lootTable) {
        this.title = title;

        this.lore = lore;

        this.item = item;
        this.itemMeta = item.getItemMeta();

        this.questType = questType;

        this.coinReward = coinReward;
        this.lootTable = lootTable;
    }


    public String getTitle() {
        return title;
    }

    public String getLore() {
        return lore;
    }

    public ItemStack getItem() {
        return item;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
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

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public void setItemMeta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
    }

    public void setQuestType(Object questType) {
        this.questType = questType;
    }

    public void setCoinReward(int coinReward) {
        this.coinReward = coinReward;
    }

    public void setLootTable(ItemStack[] lootTable) {
        this.lootTable = lootTable;
    }

}