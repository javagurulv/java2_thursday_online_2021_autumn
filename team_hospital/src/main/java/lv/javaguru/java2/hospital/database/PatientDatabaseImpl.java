package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//@Component
public class PatientDatabaseImpl implements PatientDatabase {

    private Long nextId = 1L;
    private final List<Patient> patientsList = new ArrayList<>();

    @Override
    public void add(Patient patient) {
        patient.setId(nextId);
        nextId++;
        patientsList.add(patient);
    }

    @Override
    public List<Patient> findById(Long id) {
        List<Patient> patients = new ArrayList<>();
        for (Patient patient : patientsList) {
            if (Objects.equals(id, patient.getId())) {
                patients.add(patient);
                return patients;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public boolean deleteById(Long id) {
       return patientsList.removeIf(patient -> Objects.equals(patient.getId(), id));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientsList;
    }

    @Override
    public boolean editActions(Long patientID, EditPatientEnum userInput, String input) {
        for (Patient patient : patientsList) {
            if (Objects.equals(patient.getId(), patientID)) {
                if (EditPatientEnum.NAME.equals(userInput)) {
                    patient.setName(input);
                    return true;
                } else if (EditPatientEnum.SURNAME.equals(userInput)) {
                    patient.setSurname(input);
                    return true;
                } else if (EditPatientEnum.PERSONAL_CODE.equals(userInput)) {
                    patient.setPersonalCode(input);
                    return true;
                }
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
    public List<Patient> findPatientsByNameAndPersonalCode(String name, String personal_code) {
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
    public List<Patient> findPatientsBySurnameAndPersonalCode(String surname, String personal_code) {
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
    public List<Patient> findPatientByNameSurnamePersonalCode(String name, String surname, String personal_code) {
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
