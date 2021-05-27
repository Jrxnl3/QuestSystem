package de.jinx.questsystem.objects.QuestTypes.Quests;

import de.jinx.questsystem.objects.QuestTypes.Type;
import org.bukkit.inventory.ItemStack;

public class FishingType extends Type {

    ItemStack fishToCaught;

    public FishingType(int maxCount, int currentCount,ItemStack fishToCaught) {
        super(maxCount, currentCount);
        this.fishToCaught = fishToCaught;
    }

    public FishingType(int maxCount, ItemStack fishToCaught) {
        super(maxCount,0);
        this.fishToCaught = fishToCaught;
    }

    public ItemStack getFishToCaught() {
        return fishToCaught;
    }
}
