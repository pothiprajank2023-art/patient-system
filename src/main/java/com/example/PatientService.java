package com.example;

import java.util.*;

public class PatientService {

    private Map<Integer, Patient> records = new HashMap<>();

    // Add patient with validation
    public boolean addPatient(Patient p) {
        if (p == null || p.getName() == null || p.getName().isEmpty() || p.getAge() <= 0) {
            return false;
        }

        // Prevent duplicate ID
        if (records.containsKey(p.getId())) {
            return false;
        }

        records.put(p.getId(), p);
        return true;
    }

    // Retrieve patient by ID
    public Patient getPatient(int id) {
        return records.get(id);
    }

    // Display all patients
    public void displayAllPatients() {
        if (records.isEmpty()) {
            System.out.println("No patient records found!");
            return;
        }

        for (Patient p : records.values()) {
            System.out.println("ID: " + p.getId() +
                    ", Name: " + p.getName() +
                    ", Age: " + p.getAge());
        }
    }

    // Update patient
    public boolean updatePatient(int id, String name, int age) {
        Patient p = records.get(id);

        if (p == null || name == null || name.isEmpty() || age <= 0) {
            return false;
        }

        records.put(id, new Patient(id, name, age));
        return true;
    }

    // Delete patient
    public boolean deletePatient(int id) {
        if (records.containsKey(id)) {
            records.remove(id);
            return true;
        }
        return false;
    }
}