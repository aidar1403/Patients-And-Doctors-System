package org.example.model;

public enum MedicineCategory {
    ANTIBIOTIC("Antibiotic"),
    ANALGESIC("Analgesic"),
    ANTIVIRAL("Antiviral"),
    VITAMIN("Vitamin"),
    OTHER("Other");

    private final String description;
    MedicineCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

