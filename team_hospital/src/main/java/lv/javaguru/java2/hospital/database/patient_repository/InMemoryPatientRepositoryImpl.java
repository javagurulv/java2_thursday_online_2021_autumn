package lv.javaguru.java2.hospital.database.patient_repository;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class InMemoryPatientRepositoryImpl implements PatientRepository {

    private Long nextId = 1L;
    private final List<Patient> patientsList = new ArrayList<>();

    @Override
    public void save(Patient patient) {
        patient.setId(nextId);
        nextId++;
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
    public boolean deleteById(Long id) {
       return patientsList.removeIf(patient -> Objects.equals(patient.getId(), id));
    }

    @Override
    public List<Patient> findAll() {
        return patientsList;
    }

    @Override
    public boolean editPatient(Long patientID, String userInput, String input) {
        for (Patient patient : patientsList) {
            if (Objects.equals(patient.getId(), patientID)) {
                if ("name".equals(userInput)) {
                    patient.setName(input);
                    return true;
                } else if ("surname".equals(userInput)) {
                    patient.setSurname(input);
                    return true;
                } else if ("personal_code".equals(userInput)) {
                    patient.setPersonalCode(input);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Patient> findByName(String name) {
        return patientsList.stream()
                .filter(patient -> patient.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findBySurname(String surname) {
        return patientsList.stream()
                .filter(patient -> patient.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findByPersonalCode(String personalCode) {
        return patientsList.stream()
                .filter(patient -> patient.getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> findByNameAndSurname(String name, String surname) {
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
    public List<Patient> findByNameAndPersonalCode(String name, String personal_code) {
        List<Patient> list = new ArrayList<>();
        for (Patient patient : patientsList) {
            if (name.equals(patient.getName())
                    && personal_code.equals(patient.getPersonalCode())) {
                list.add(patient);
                break;
            }
        }
        return list;
    }

    @Override
    public List<Patient> findBySurnameAndPersonalCode(String surname, String personal_code) {
        List<Patient> list = new ArrayList<>();
        for (Patient patient : patientsList) {
            if (surname.equals(patient.getSurname())
                    && personal_code.equals(patient.getPersonalCode())) {
                list.add(patient);
                break;
            }
        }
        return list;
    }

    @Override
    public List<Patient> findByNameAndSurnameAndPersonalCode(String name, String surname, String personal_code) {
        List<Patient> list = new ArrayList<>();
        for (Patient p : patientsList) {
            if (p.getName().equals(name)
                    && p.getSurname().equals(surname)
                    && p.getPersonalCode().equals(personal_code)) {
                list.add(p);
            }
        }
        return list;
    }
}
