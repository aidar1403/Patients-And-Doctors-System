package org.example.model;

public enum TriageGroup {
    MILD("Легкие / Несерьезные"),
    MODERATE("Средней тяжести"),
    SEVERE("Тяжелые травмы"),
    EMERGENCY("Чрезвычайные ситуации");

    private final String description;
    TriageGroup(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
