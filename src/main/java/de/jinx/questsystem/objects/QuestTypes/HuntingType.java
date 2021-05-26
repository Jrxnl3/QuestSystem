package de.jinx.questsystem.objects.QuestTypes;

import org.bukkit.entity.EntityType;

public class HuntingType extends Type{

    EntityType mobToKill;

    public HuntingType(int maxCount, int currentCount, EntityType mobToKill) {
        super(maxCount,currentCount,TypeEnums.HUNTING);
        this.mobToKill = mobToKill;
    }

    public HuntingType(int maxCount,EntityType mobToKill) {
        super(maxCount,0,TypeEnums.HUNTING);
        this.mobToKill = mobToKill;
    }

    public EntityType getMobToKill() {
        return mobToKill;
    }
}
