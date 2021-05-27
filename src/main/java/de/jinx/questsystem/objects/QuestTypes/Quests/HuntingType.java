package de.jinx.questsystem.objects.QuestTypes.Quests;

import de.jinx.questsystem.objects.QuestTypes.Type;
import org.bukkit.entity.EntityType;

public class HuntingType extends Type {

    EntityType mobToKill;

    public HuntingType(int maxCount, int currentCount, EntityType mobToKill) {
        super(maxCount,currentCount);
        this.mobToKill = mobToKill;
    }

    public HuntingType(int maxCount,EntityType mobToKill) {
        super(maxCount,0);
        this.mobToKill = mobToKill;
    }

    public EntityType getMobToKill() {
        return mobToKill;
    }
}
