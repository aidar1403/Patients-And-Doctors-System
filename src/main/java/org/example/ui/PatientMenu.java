package org.example.ui;

import org.example.model.Patient;
import org.example.model.PatientAccount;
import org.example.model.User;
import org.example.service.PatientService;
import org.example.service.PrescriptionService;

import java.util.Scanner;

public class PatientMenu {
    private final PatientService patientService;
    private final PrescriptionService prescriptionService;
    private final Scanner sc;
    private final long patientId;

    public PatientMenu(User user, PatientService patientService,
                       PrescriptionService prescriptionService, Scanner sc) {
        this.patientId = ((PatientAccount) user).getPatientId();
        this.patientService = patientService;
        this.prescriptionService = prescriptionService;
        this.sc = sc;
    }

    public void run() {
        while (true) {
            System.out.println("PatientMenu");
            System.out.println("1. Submit request for Medicine");
            System.out.println("2. Add Allergy");
            System.out.println("0. leave to Main Menu");
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Medicine name: ");
                    String med = sc.nextLine();
                    prescriptionService.submitRequest(patientId, med);
                }
                case "2" -> {
                    Patient p = patientService.findById(patientId);
                    System.out.print("Enter allergy: ");
                    String allergy = sc.nextLine();
                    p.addAllergy(allergy);
                    System.out.println("The allergy has been added successfully!");
                }
                case "0" -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}