package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.doctor.core.requests.EditOption;
import lv.javaguru.java2.hospital.domain.Doctor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DoctorDatabaseImpl implements DoctorDatabase {

    private List<Doctor> doctorsList = new ArrayList<>();

    @Override
    public void addDoctor(Doctor doctor) {
        doctorsList.add(doctor);
    }


    @Override
    public boolean deleteDoctorById(Long id) {
        boolean isDoctorDeleted = false;
        Optional<Doctor> doctorToDeleteOpt = doctorsList.stream()
                .filter(doctor -> doctor.getId() == id)
                .findFirst();
        if (doctorToDeleteOpt.isPresent()) {
            Doctor doctorToDelete = doctorToDeleteOpt.get();
            isDoctorDeleted = doctorsList.remove(doctorToDelete);
        }
        return isDoctorDeleted;
    }

    @Override
    public List<Doctor> showAllDoctors() {
        return doctorsList;
    }

    @Override
    public boolean editDoctor(Long doctorId, EditOption infoToEdit, String changes) {
        boolean isDoctorEdited = false;
        Optional<Doctor> doctorToEditOpt = doctorsList.stream()
                .filter(doctor -> doctor.getId() == doctorId)
                .findFirst();
        if (doctorToEditOpt.isPresent()) {
            Doctor doctorToEdit = doctorToEditOpt.get();
            switch (infoToEdit) {
                case NAME -> doctorToEdit.setName(changes);
                case SURNAME -> doctorToEdit.setSurname(changes);
                case SPECIALITY -> doctorToEdit.setSpeciality(changes);
            }
            isDoctorEdited = true;
        }
        return isDoctorEdited;
    }

    @Override
    public List<Doctor> findByName(String name) {
        return doctorsList.stream()
                .filter(doctor -> doctor.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findBySurname(String surname) {
        return doctorsList.stream()
                .filter(doctor -> doctor.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findByNameAndSurname(String name, String surname) {
        return doctorsList.stream()
                .filter(doctor -> doctor.getName().equals(name))
                .filter(doctor -> doctor.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findById(Long id) {
        return doctorsList.stream()
                .filter(doctor -> doctor.getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findBySpeciality(String speciality) {
        return doctorsList.stream()
                .filter(doctor -> doctor.getSpeciality().equals(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findByNameAndSpeciality(String name, String speciality) {
        return doctorsList.stream()
                .filter(doctor -> doctor.getName().equals(name))
                .filter(doctor -> doctor.getSpeciality().equals(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findBySurnameAndSpeciality(String surname, String speciality) {
        return doctorsList.stream()
                .filter(doctor -> doctor.getSurname().equals(surname))
                .filter(doctor -> doctor.getSpeciality().equals(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findByNameAndSurnameAndSpeciality(String name, String surname, String speciality) {
        return doctorsList.stream()
                .filter(doctor -> doctor.getName().equals(name))
                .filter(doctor -> doctor.getSurname().equals(surname))
                .filter(doctor -> doctor.getSpeciality().equals(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> getDoctorsList() {
        return doctorsList;
    }
}
