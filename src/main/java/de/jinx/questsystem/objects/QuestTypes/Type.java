package de.jinx.questsystem.objects.QuestTypes;

public class Type{

    int maxCount;
    int currentCount;

    public Type(int maxCount, int currentCount) {
        this.maxCount = maxCount;
        this.currentCount = currentCount;
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
}
