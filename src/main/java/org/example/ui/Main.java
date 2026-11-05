package org.example.ui;

import org.example.model.*;
import org.example.service.*;
import org.example.util.AuthException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();
        MedicineService medicineService = new MedicineService();
        PrescriptionService prescriptionService = new PrescriptionService(medicineService);

        medicineService.add(new Medicine(
                medicineService.getNextId(),
                "Аспирин",
                "SKU-001",
                MedicineCategory.ANALGESIC,
                new java.math.BigDecimal("150.50"),
                100
        ));

        medicineService.add(new Medicine(
                medicineService.getNextId(),
                "Амоксициллин",
                "SKU-002",
                MedicineCategory.ANTIBIOTIC,
                new java.math.BigDecimal("400.00"),
                50
        ));

        medicineService.add(new Medicine(
                medicineService.getNextId(),
                "Витамин C",
                "SKU-003",
                MedicineCategory.VITAMIN,
                new java.math.BigDecimal("99.90"),
                200
        ));

        medicineService.add(new Medicine(
                medicineService.getNextId(),
                "Парацетамол",
                "SKU-004",
                MedicineCategory.ANALGESIC,
                new java.math.BigDecimal("120.00"),
                300
        ));

        Scanner sc = new Scanner(System.in);
        RegistrationService regService = new RegistrationService(authService, patientService, doctorService, sc);

        while(true){
            System.out.println("\nHospital");
            System.out.println("1. Sign in");
            System.out.println("2. Sign up as a patient");
            System.out.println("3. Sign up as a doctor");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine();

            switch (choice){
                case "1" -> {
                    System.out.print("Login: ");
                    String login = sc.nextLine();
                    System.out.print("Password: ");
                    String password = sc.nextLine();
                    try{
                        User currentUser = authService.login(login,password);
                        System.out.println("Login successful! Welcome, " + currentUser.getLogin() + "!");


                        if(currentUser.getRole() == Role.PATIENT){
                            new PatientMenu(currentUser, patientService, prescriptionService, sc).run();
                        } else if(currentUser.getRole() == Role.DOCTOR){
                            new DoctorMenu(patientService, prescriptionService, sc).run();
                        }
                        authService.logout();
                    } catch (AuthException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "2" -> regService.registerPatient();
                case "3" -> regService.registerDoctor();
                case "0" ->{
                    System.out.println("Good bye!");
                    return;
                }
                default -> System.out.println("Wrong choice");
            }
        }
    }
}