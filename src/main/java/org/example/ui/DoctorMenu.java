package org.example.ui;

import org.example.model.*;
import org.example.service.*;
import org.example.service.MedicineService;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.List;


public class DoctorMenu {
    private final PatientService patientService;
    private final PrescriptionService prescriptionService;
    private final Scanner sc;

    public DoctorMenu(PatientService patientService, PrescriptionService prescriptionService, Scanner sc) {
        this.patientService = patientService;
        this.prescriptionService = prescriptionService;
        this.sc = sc;
    }

    public void run() {
        while (true) {
            System.out.println("Doctor Menu");
            System.out.println("1. take a next request from Queue");
            System.out.println("2. Show the Queue");
            System.out.println("3. List of patients (sorted by name)");
            System.out.println("4. View all medicines");
            System.out.println("5. Change the Triage Group of patients");
            System.out.println("6. View All patients by Triage group");
            System.out.println("7. Complete a present request (give a medicine)");
            System.out.println("0. leave to main menu");
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    Prescription p = prescriptionService.processNext(); //
                    if (p != null) {
                        System.out.println("You took: " + p);
                    }
                }
                case "2" -> prescriptionService.showQueue();
                case "3" -> {
                    System.out.println("Patient list:");
                    for (Patient p : patientService.getAllSortedByName()) {
                        System.out.println(" - " + p);
                    }
                }
                case "4" -> {
                    System.out.println("Medicine list:");
                    for (MedicineCategory cat : MedicineCategory.values()) {
                        List<Medicine> meds = MedicineService.getByCategory(cat);
                        if (!meds.isEmpty()) {
                            System.out.println(" Category: " + cat.getDescription());
                            for (Medicine m : meds) {
                                System.out.println("  - " + m);
                            }
                        }
                    }
                }
                case "5" -> {
                    System.out.print("Enter Patient ID: ");
                    long pid = Long.parseLong(sc.nextLine());
                    Patient p = patientService.findById(pid);
                    if (p != null) {
                        System.out.println("Choose the group : 1-MILD, 2-MODERATE, 3-SEVERE, 4-EMERGENCY");
                        String grp = sc.nextLine();
                        switch (grp) {
                            case "1" -> p.setTriageGroup(TriageGroup.MILD);
                            case "2" -> p.setTriageGroup(TriageGroup.MODERATE);
                            case "3" -> p.setTriageGroup(TriageGroup.SEVERE);
                            case "4" -> p.setTriageGroup(TriageGroup.EMERGENCY);
                        }
                        System.out.println("Patient " + p.getFullName() + " In the group " + p.getTriageGroup());
                    } else {
                        System.out.println("Patient is not found.");
                    }
                }
                case "6" -> {
                    Map<TriageGroup, List<Patient>> grouped = patientService.getPatientsByTriageGroup();
                    for (TriageGroup group : TriageGroup.values()) {
                        System.out.println("--- " + group.getDescription() + " ---");
                        List<Patient> list = grouped.getOrDefault(group, new ArrayList<>());
                        if (list.isEmpty()) {
                            System.out.println("  (Empty)");
                        } else {
                            for (Patient p : list) System.out.println("  - " + p.getFullName());
                        }
                    }
                }
                case "7" -> {
                    System.out.print("Введите SKU (артикул) лекарства для выдачи: ");
                    String sku = sc.nextLine().trim();
                    prescriptionService.completeCurrentRequest(sku);
                }

                case "0" -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}