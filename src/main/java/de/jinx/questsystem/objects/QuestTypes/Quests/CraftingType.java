package de.jinx.questsystem.objects.QuestTypes.Quests;

import de.jinx.questsystem.objects.QuestTypes.Type;
import org.bukkit.inventory.ItemStack;

public class CraftingType extends Type {

    ItemStack itemToCraft;

    public CraftingType(int maxCount, int currentCount,ItemStack itemToCraft) {
        super(maxCount, currentCount);
        this.itemToCraft = itemToCraft;
    }

    public CraftingType(int maxCount,ItemStack itemToCraft) {
        super(maxCount,0);
        this.itemToCraft = itemToCraft;
    }

    public ItemStack getItemToCraft() {
        return itemToCraft;
    }


}