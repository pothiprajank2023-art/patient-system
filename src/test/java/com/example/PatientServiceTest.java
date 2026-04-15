package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientServiceTest {

    @Test
    public void testValidPatient() {
        PatientService service = new PatientService();
        Patient p = new Patient(1, "John", 25);

        assertTrue(service.addPatient(p));
    }

    @Test
    public void testInvalidPatient() {
        PatientService service = new PatientService();
        Patient p = new Patient(2, "", -5);

        assertFalse(service.addPatient(p));
    }

    @Test
    public void testRetrievePatient() {
        PatientService service = new PatientService();
        Patient p = new Patient(3, "Alice", 30);
        service.addPatient(p);

        assertEquals("Alice", service.getPatient(3).getName());
    }
}