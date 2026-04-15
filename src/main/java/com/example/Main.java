package com.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        PatientService service = new PatientService();

        // ✅ Kubernetes / Non-interactive mode
        if (System.console() == null) {

            System.out.println("Running in Kubernetes mode...\n");

            Patient p1 = new Patient(1, "John", 25);
            Patient p2 = new Patient(2, "Alice", 30);

            service.addPatient(p1);
            service.addPatient(p2);

            System.out.println("Patient Records:");
            service.displayAllPatients();

            // 🔥 KEEP CONTAINER ALIVE (IMPORTANT)
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // ✅ Local interactive mode
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Patient Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patient by ID");
            System.out.println("3. View All Patients");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Exiting...");
                return;
            }

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    Patient p = new Patient(id, name, age);

                    if (service.addPatient(p)) {
                        System.out.println("Patient added successfully!");
                    } else {
                        System.out.println("Invalid or duplicate patient data!");
                    }
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    int searchId = sc.nextInt();

                    Patient result = service.getPatient(searchId);

                    if (result != null) {
                        System.out.println("ID: " + result.getId() +
                                ", Name: " + result.getName() +
                                ", Age: " + result.getAge());
                    } else {
                        System.out.println("Patient not found!");
                    }
                    break;

                case 3:
                    service.displayAllPatients();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}