package org.example.model;

public enum PrescriptionStatus {
    NEW("New"),
    PROCESSING("Processing"),
    ISSUED("Issued"),
    REJECTED("Rejected"),;

    private final String description;
    PrescriptionStatus(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
