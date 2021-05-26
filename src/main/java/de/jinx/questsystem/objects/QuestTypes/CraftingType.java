package de.jinx.questsystem.objects.QuestTypes;

import org.bukkit.inventory.ItemStack;

public class CraftingType extends Type{

    ItemStack itemToCraft;

    public CraftingType(int maxCount, int currentCount,ItemStack itemToCraft) {
        super(maxCount, currentCount,TypeEnums.CRAFTING);
        this.itemToCraft = itemToCraft;
    }

    public CraftingType(int maxCount,ItemStack itemToCraft) {
        super(maxCount,0,TypeEnums.CRAFTING);
        this.itemToCraft = itemToCraft;
    }

    public ItemStack getItemToCraft() {
        return itemToCraft;
    }


}