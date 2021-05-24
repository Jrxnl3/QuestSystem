package de.jinx.questsystem.objects.QuestTypes;

import org.bukkit.inventory.ItemStack;

public class GatheringType extends Type{

    ItemStack itemToGather;

    public GatheringType(int maxCount, int currentCount, ItemStack itemToGather) {
        super(maxCount,currentCount);
        this.itemToGather = itemToGather;
    }

    public GatheringType(int maxCount,ItemStack itemToGather) {
        super(maxCount,0);
        this.itemToGather = itemToGather;
    }

    public ItemStack getItemToGather() {
        return itemToGather;
    }

}
