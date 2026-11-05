package org.example.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Patient implements Comparable<Patient> {
    private final long id;
    private final String fullName;
    private final String policyNumber;
    private final LocalDate BirthDate;
    private String decease;
    private final Set<String> allergies;
    private TriageGroup triageGroup = TriageGroup.MILD; // по умолчанию легкие



    public Patient(long id, String fullName, String policyNumber, LocalDate BirthDate, String decease) {
        this.id = id;
        this.fullName = fullName;
        this.policyNumber = policyNumber;
        this.BirthDate = BirthDate;
        this.decease = decease;
        this.allergies = new HashSet<>();
    }
    public long getId() {return this.id;}
    public String getFullName() {return this.fullName;}
    public String getPolicyNumber() {return this.policyNumber;}
    public LocalDate getBirthDate() {return this.BirthDate;}
    public String getDecease() {return this.decease;}
    public TriageGroup getTriageGroup() { return triageGroup; }

    public void addAllergy(String allergy) {this.allergies.add(allergy);}
    public void setTriageGroup(TriageGroup triageGroup) { this.triageGroup = triageGroup; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(policyNumber, patient.policyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber);
    }

    @Override
    public String toString() {
        return "Patient Id: "+id+", Full name: "+fullName+", Policy number: "+policyNumber+", Decease: "+decease+" Allergies: "+allergies;
    }

    @Override
    public int compareTo(Patient other) {
        return this.fullName.compareToIgnoreCase(other.fullName);
    }
}
