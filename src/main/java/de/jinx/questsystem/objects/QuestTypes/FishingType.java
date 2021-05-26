package de.jinx.questsystem.objects.QuestTypes;

import org.bukkit.inventory.ItemStack;

public class FishingType extends Type{

    ItemStack fishToCaught;

    public FishingType(int maxCount, int currentCount,ItemStack fishToCaught) {
        super(maxCount, currentCount,TypeEnums.FISHING);
        this.fishToCaught = fishToCaught;
    }

    public FishingType(int maxCount, ItemStack fishToCaught) {
        super(maxCount,0,TypeEnums.FISHING);
        this.fishToCaught = fishToCaught;
    }

    public ItemStack getFishToCaught() {
        return fishToCaught;
    }
}
