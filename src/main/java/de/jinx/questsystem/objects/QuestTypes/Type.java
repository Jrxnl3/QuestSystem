package de.jinx.questsystem.objects.QuestTypes;

public class Type{

    int maxCount;
    int currentCount;
    TypeEnums typeEnums;

    public Type(int maxCount, int currentCount,TypeEnums typeEnums) {
        this.maxCount = maxCount;
        this.currentCount = currentCount;
        this.typeEnums = typeEnums;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public TypeEnums getEnumType() {
        return typeEnums;
    }
}
