package org.example.model;

import java.util.Objects;

public class Prescription implements Comparable<Prescription>{
    private long id;
    private final long patientId;
    private final String medicineName;
    private PrescriptionStatus status;

    public Prescription(long id, long patientId, String medicineName) {
        this.id = id;
        this.patientId = patientId;
        this.medicineName = medicineName;
        this.status = PrescriptionStatus.NEW;
    }

    public long getId() {return this.id;}
    public long getPatientId() {return this.patientId;}
    public String getMedicineName() {return this.medicineName;}
    public PrescriptionStatus getStatus() {return this.status;}

    public void setStatus(PrescriptionStatus status) {this.status = status;}

    @Override
    public int compareTo(Prescription other) {
        return Long.compare(this.id, other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Prescription #" + id + " | Patient ID: " + patientId +
                " | Medicine: " + medicineName + " | Status: " + status;
    }
}
