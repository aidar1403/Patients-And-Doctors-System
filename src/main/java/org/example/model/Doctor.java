package org.example.model;

import java.util.Objects;


public class Doctor implements Comparable<Doctor>{
    private final long id;
    private final String fullName;
    private final String specialization;

    public Doctor(long id, String fullName, String specialization) {
        this.id = id;
        this.fullName = fullName;
        this.specialization = specialization;
    }
    public long getId() {return id;}
    public String getFullName() {return fullName;}
    public String getSpecialization() {return specialization;}

    @Override
    public int compareTo(Doctor other) {
        return this.fullName.compareToIgnoreCase(other.fullName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Doctor id: "+id+", Full name: "+fullName+", Specialization: "+specialization;
    }
}
