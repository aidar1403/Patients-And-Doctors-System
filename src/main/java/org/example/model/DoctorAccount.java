package org.example.model;

public class DoctorAccount extends User{
    private final long doctorId;

    public DoctorAccount(String login, String passwordHash, long doctorId) {
        super(login,passwordHash,Role.DOCTOR);
        this.doctorId = doctorId;
    }
    public long getDoctorId() {return doctorId;}
}
