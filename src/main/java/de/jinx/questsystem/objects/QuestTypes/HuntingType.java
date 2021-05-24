package de.jinx.questsystem.objects.QuestTypes;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class HuntingType {

    int count;
    EntityType mobToKill;

    public HuntingType(int count, EntityType mobToKill) {
        this.count = count;
        this.mobToKill = mobToKill;
    }

    public int getCount() {
        return count;
    }

    public EntityType getMobToKill() {
        return mobToKill;
    }
}
