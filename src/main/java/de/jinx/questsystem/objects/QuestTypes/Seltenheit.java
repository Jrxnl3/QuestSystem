package de.jinx.questsystem.objects.QuestTypes;

import org.bukkit.Material;

public class Seltenheit {

    Material glass_pane;
    String name;

    public Seltenheit(Material glass_pane, String name) {
        this.glass_pane = glass_pane;
        this.name = name;
    }

    public Material getGlass_Pane() {
        return glass_pane;
    }

    public String getName() {
        return name;
    }
}
