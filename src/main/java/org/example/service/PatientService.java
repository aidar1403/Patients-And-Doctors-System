package org.example.service;

import org.example.model.Patient;
import org.example.model.TriageGroup;

import java.util.*;

public class PatientService {
    private final List<Patient> patientsList = new ArrayList<>();
    private final Map<Long, Patient> patientsById = new HashMap<>();
    private long nextId = 1;

    public Map<TriageGroup, List<Patient>> getPatientsByTriageGroup() {
        Map<TriageGroup, List<Patient>> grouped = new EnumMap<>(TriageGroup.class);
        for (Patient p : patientsList) {
            grouped.computeIfAbsent(p.getTriageGroup(), k -> new ArrayList<>()).add(p);
        }
        return grouped;
    }

    public long getNextId() {
        return nextId++;
    }
    public void add(Patient patient) {
        patientsList.add(patient);
        patientsById.put(patient.getId(), patient);
    }

    public Patient findById(long id) {
        return patientsById.get(id);
    }

    public List<Patient> getAllSortedByName() {
        List<Patient> sorted = new ArrayList<>(patientsList);
        Collections.sort(sorted);
        return sorted;
    }

    public List <Patient> getAll(){
        return new ArrayList<>(patientsList);
    }

    public void updatePatient(Patient p) {
        patientsById.put(p.getId(), p);
    }
}

