package de.jinx.questsystem.objects;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Quest {

    String title;
    String lore;
    ItemStack item;
    ItemMeta itemMeta;

    QuestType questType;

    int coinReward;
    ItemStack[] rewards;

    boolean completed;

    public Quest(String title, String lore, ItemStack item, ItemMeta itemMeta, QuestType questType, int coinReward, ItemStack[] rewards,boolean completed) {
        this.title = title;
        this.lore = lore;
        this.item = item;
        this.itemMeta = itemMeta;
        this.questType = questType;
        this.coinReward = coinReward;
        this.rewards = rewards;
        this.completed = completed;
    }

    public Quest(String title, String lore, ItemStack item, QuestType questType, int coinReward, ItemStack[] rewards) {
        this.title = title;
        this.lore = lore;
        this.item = item;
        this.itemMeta = item.getItemMeta();
        this.questType = questType;
        this.coinReward = coinReward;
        this.rewards = rewards;
        this.completed = false;
    }

    public Quest(String title, String lore, ItemStack item, ItemMeta itemMeta, QuestType questType, int coinReward, ItemStack[] rewards) {
        this.title = title;
        this.lore = lore;
        this.item = item;
        this.itemMeta = itemMeta;
        this.questType = questType;
        this.coinReward = coinReward;
        this.rewards = rewards;
        this.completed = false;
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

    public QuestType getQuestType() {
        return questType;
    }

    public int getCoinReward() {
        return coinReward;
    }

    public ItemStack[] getRewards() {
        return rewards;
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

    public void setQuestType(QuestType questType) {
        this.questType = questType;
    }

    public void setCoinReward(int coinReward) {
        this.coinReward = coinReward;
    }

    public void setRewards(ItemStack[] rewards) {
        this.rewards = rewards;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}