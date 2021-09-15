package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<Patient> findPatientsByName(String name) {
        return patientsList.stream()
                .filter(patient -> patient.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findPatientsBySurname(String surname) {
        return patientsList.stream()
                .filter(patient -> patient.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findPatientsByPersonalCode(String personalCode) {
        return patientsList.stream()
                .filter(patient -> patient.getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findPatientsByNameAndSurname(String name, String surname) {
        List<Patient> list = new ArrayList<>();
        for (Patient patient : patientsList) {
            if (name.equals(patient.getName())
                    && surname.equals(patient.getSurname())) {
                list.add(patient);
            }
        }
        return list;
    }

    @Override
    public List<Patient> findPatientsByNameAndPersonalCode(String name, String personalCode) {
        List<Patient> list = new ArrayList<>();
        for (Patient patient : patientsList) {
            if (name.equals(patient.getName())
                    && personalCode.equals(patient.getPersonalCode())) {
                list.add(patient);
                break;
            }
        }
        return list;
    }

    @Override
    public List<Patient> findPatientsBySurnameAndPersonalCode(String surname, String personalCode) {
        List<Patient> list = new ArrayList<>();
        for (Patient patient : patientsList) {
            if (surname.equals(patient.getSurname())
                    && personalCode.equals(patient.getPersonalCode())) {
                list.add(patient);
                break;
            }
        }
        return list;
    }
}
