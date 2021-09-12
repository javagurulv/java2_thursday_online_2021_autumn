package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PatientDatabaseImpl implements PatientDatabase {

    private final List<Patient> patientsList = new ArrayList<>();

    @Override
    public void add(Patient patient) {
        patientsList.add(patient);
    }

   @Override
    public Optional<Patient> findById(Long id) {
        for (Patient patient : patientsList) {
            if (Objects.equals(id, patient.getId())) {
                return Optional.of(patient);
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        patientsList.removeIf(patient -> Objects.equals(patient.getId(), id));
    }

    @Override
    public List<Patient> showAllPatients() {
        return patientsList;
    }

    @Override
    public void editActions(Long patientID, int userInput, String input) {
        for (Patient patient : patientsList) {
            if (Objects.equals(patient.getId(), patientID)) {
                switch (userInput) {
                    case 1 -> patient.setName(input);
                    case 2 -> patient.setSurname(input);
                    case 3 -> patient.setPersonalCode(input);
                }
            }
        }
    }

    @Override
    public boolean patientExists(Long id) {
        for (Patient patient : patientsList) {
            if (Objects.equals(patient.getId(), id)) {
                return true;
            }
        }
        return false;
    }
}
