package de.jinx.questsystem.objects.QuestTypes.Quests;

import de.jinx.questsystem.objects.QuestTypes.Type;
import org.bukkit.inventory.ItemStack;

public class GatheringType extends Type {

    //Items müssen bei einem GUI abgegeben werden!

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
