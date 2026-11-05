package org.example.service;

import org.example.model.Doctor;
import java.util.*;

public class DoctorService {
    private final Map<Long, Doctor> doctorsById = new HashMap<Long, Doctor>();
    private long nextId = 1;

    public long getNextId() {
        return nextId++;
    }
    public void add(Doctor doctor) {
        doctorsById.put(doctor.getId(),doctor);
    }
    public Doctor findById(long id){
        return doctorsById.get(id);
    }
}
