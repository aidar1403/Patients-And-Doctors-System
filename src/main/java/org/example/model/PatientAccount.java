package org.example.model;

public class PatientAccount extends User{
    private final long patientId;

    public PatientAccount(String login, String passwordHash, long patientId){
        super(login,passwordHash, Role.PATIENT);
        this.patientId = patientId;
    }
    public long getPatientId(){return patientId;}
}
