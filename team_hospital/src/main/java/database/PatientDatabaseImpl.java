package database;

import domain.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDatabaseImpl implements PatientDatabase {

    private final List<Patient> patientsList = new ArrayList<>();

    @Override
    public void add(Patient patient) {
        patientsList.add(patient);
    }

    @Override
    public void findById(int id) {
        for (Patient patient : patientsList) {
            if (id == patient.getId()) {
                System.out.println(patient);
            }
        }
    }

    @Override
    public void deleteById(int id) {
        patientsList.removeIf(patient -> patient.getId() == id);
    }

    @Override
    public List<Patient> showAllPatients() {
        return patientsList;
    }

    @Override
    public void editActions(int patientID, int userInput, String input) {
        for (Patient patient : patientsList) {
            if (patient.getId() == patientID) {
                switch (userInput) {
                    case 1 -> patient.setName(input);
                    case 2 -> patient.setSurname(input);
                    case 3 -> patient.setDateOut(LocalDate.now());
                }
            }
        }
    }

    @Override
    public boolean patientExists(int id) {
        for (Patient patient : patientsList) {
            if (patient.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
