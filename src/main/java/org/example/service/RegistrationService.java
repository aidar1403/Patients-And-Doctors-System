package org.example.service;

import org.example.model.*;
import org.example.util.PasswordHasher;
import java.time.LocalDate;
import java.util.Scanner;

public class RegistrationService {
    private final AuthService authService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final Scanner sc;
    public RegistrationService(AuthService authService, PatientService patientService,
                               DoctorService doctorService, Scanner sc) {
      this.authService = authService;
      this.patientService = patientService;
      this.doctorService = doctorService;
      this.sc = sc;
    }

    public void registerPatient() {
        System.out.println("Patient registration");
        System.out.print("Enter Login: ");
        String login = sc.nextLine().trim();

        if (authService.loginExists(login)) {
            System.out.println("Login already exists");
            return;
        }
        System.out.print("Enter Password: ");
        String pass = sc.nextLine().trim();

        System.out.print("Enter Full name: ");
        String fullName = sc.nextLine();

        System.out.print("Enter policy number: ");
        String policy = sc.nextLine();

        long patientId = patientService.getNextId();
        Patient patient = new Patient(patientId,fullName,policy,LocalDate.now(),"Unknown");
        patientService.add(patient);

        PatientAccount account = new PatientAccount(login,PasswordHasher.hash(pass),patientId);
        authService.register(account);

        System.out.println("Patient account registered successfully! Your ID: " + patientId);
    }

    public void registerDoctor() {
        System.out.println("Doctor registration");
        System.out.print("Enter Login: ");
        String login = sc.nextLine().trim();

        if (authService.loginExists(login)) {
            System.out.println("Login already exists");
            return;
        }
        System.out.print("Enter Password: ");
        String pass = sc.nextLine().trim();
        System.out.print("Enter Full name: ");
        String fullName = sc.nextLine();
        System.out.print("Enter specialization: ");
        String spec = sc.nextLine();

        long doctorId = doctorService.getNextId();
        Doctor doctor = new Doctor(doctorId,fullName,spec);
        doctorService.add(doctor);

        DoctorAccount account = new DoctorAccount(login,PasswordHasher.hash(pass),doctorId);
        authService.register(account);
        System.out.println("Doctor account registered successfully! Your ID: " + doctorId);
    }
}
