package de.jinx.questsystem.objects;

import de.jinx.questsystem.objects.QuestTypes.QuestTypeEnums;
import de.jinx.questsystem.objects.QuestTypes.Seltenheit;
import de.jinx.questsystem.objects.QuestTypes.Type;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;

public class Quest <T extends Type>{

    String title;
    String lore;

    ItemStack displayItem;
    ItemMeta displayItemMeta;

    T questType;
    QuestTypeEnums type;

    Seltenheit seltenheit;

    int coinReward;
    ItemStack[] lootTable;

    public Quest(String title, String lore, ItemStack displayItem, T questType, QuestTypeEnums questTypeEnums,Seltenheit seltenheit,int coinReward, ItemStack[] lootTable) {
        this.title = title;

        this.lore = lore;

        this.displayItem = displayItem;
        this.displayItemMeta = displayItem.getItemMeta();
        this.displayItemMeta.setDisplayName(title);
        this.displayItemMeta.setLore(Arrays.asList(lore));

        this.questType = questType;
        this.type = questTypeEnums;

        this.seltenheit = seltenheit;
        this.displayItemMeta.setLore(Arrays.asList(this.displayItemMeta.getLore() + "\n" + seltenheit.getName()));


        this.coinReward = coinReward;
        this.lootTable = lootTable;

        this.displayItem.setItemMeta(this.displayItemMeta);
    }
    public Quest(String title, String lore, ItemStack displayItem,Seltenheit seltenheit,int coinReward, ItemStack[] lootTable) {
        this.title = title;

        this.lore = lore;

        this.displayItem = displayItem;
        this.displayItemMeta = displayItem.getItemMeta();
        this.displayItemMeta.setDisplayName(title);
        this.displayItemMeta.setLore(Arrays.asList(lore));

        this.seltenheit = seltenheit;
        this.displayItemMeta.setLore(Arrays.asList(this.displayItemMeta.getLore() + "\n" + seltenheit.getName()));


        this.coinReward = coinReward;
        this.lootTable = lootTable;

        this.displayItem.setItemMeta(this.displayItemMeta);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public ItemStack getDisplayItem() {
        return displayItem;
    }

    public void setDisplayItem(ItemStack displayItem) {
        this.displayItem = displayItem;
    }

    public ItemMeta getDisplayItemMeta() {
        return displayItemMeta;
    }

    public void setDisplayItemMeta(ItemMeta displayItemMeta) {
        this.displayItemMeta = displayItemMeta;
    }

    public T getQuestType() {
        return questType;
    }

    public void setQuestType(T questType) {
        this.questType = questType;
    }

    public QuestTypeEnums getEnumType() {
        return type;
    }

    public void setEnumType(QuestTypeEnums type) {
        this.type = type;
    }

    public Seltenheit getSeltenheit() {
        return seltenheit;
    }

    public void setSeltenheit(Seltenheit seltenheit) {
        this.seltenheit = seltenheit;
    }

    public int getCoinReward() {
        return coinReward;
    }

    public void setCoinReward(int coinReward) {
        this.coinReward = coinReward;
    }

    public ItemStack[] getLootTable() {
        return lootTable;
    }

    public void setLootTable(ItemStack[] lootTable) {
        this.lootTable = lootTable;
    }
}