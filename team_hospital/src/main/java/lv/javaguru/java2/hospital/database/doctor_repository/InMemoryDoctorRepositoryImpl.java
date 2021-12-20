package lv.javaguru.java2.hospital.database.doctor_repository;

import lv.javaguru.java2.hospital.doctor.core.requests.EditDoctorEnum;
import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class InMemoryDoctorRepositoryImpl implements DoctorRepository {

    private Long nextId = 1L;
    private List<Doctor> doctors = new ArrayList<>();

    @Override
    public void save(Doctor doctor) {
        doctor.setId(nextId);
        nextId++;
        doctors.add(doctor);
    }


    @Override
    public boolean deleteById(Long id) {
        boolean isDoctorDeleted = false;
        Optional<Doctor> doctorToDeleteOpt = doctors.stream()
                .filter(doctor -> doctor.getId().equals(id))
                .findFirst();
        if (doctorToDeleteOpt.isPresent()) {
            Doctor doctorToDelete = doctorToDeleteOpt.get();
            isDoctorDeleted = doctors.remove(doctorToDelete);
        }
        return isDoctorDeleted;
    }

    @Override
    public List<Doctor> findAll() {
        return doctors;
    }

    @Override
    public boolean editDoctor(Long doctorId, Enum infoToEdit, String changes) {
        boolean isDoctorEdited = false;
        Optional<Doctor> doctorToEditOpt = doctors.stream()
                .filter(doctor -> doctor.getId().equals(doctorId))
                .findFirst();
        if (doctorToEditOpt.isPresent()) {
            Doctor doctorToEdit = doctorToEditOpt.get();
            if (EditDoctorEnum.NAME.equals(infoToEdit)) {
                doctorToEdit.setName(changes);
                isDoctorEdited = true;
            } else if (EditDoctorEnum.SURNAME.equals(infoToEdit)) {
                doctorToEdit.setSurname(changes);
                isDoctorEdited = true;
            } else if (EditDoctorEnum.SPECIALITY.equals(infoToEdit)) {
                doctorToEdit.setSpeciality(changes);
                isDoctorEdited = true;
            }
        }
        return isDoctorEdited;
    }

    @Override
    public List<Doctor> findByName(String name) {
        return doctors.stream()
                .filter(doctor -> doctor.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findBySurname(String surname) {
        return doctors.stream()
                .filter(doctor -> doctor.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findByNameAndSurname(String name, String surname) {
        return doctors.stream()
                .filter(doctor -> doctor.getName().equals(name))
                .filter(doctor -> doctor.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> getById(Long id) {
        return doctors.stream()
                .filter(doctor -> doctor.getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findBySpeciality(String speciality) {
        return doctors.stream()
                .filter(doctor -> doctor.getSpeciality().equals(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findByNameAndSpeciality(String name, String speciality) {
        return doctors.stream()
                .filter(doctor -> doctor.getName().equals(name))
                .filter(doctor -> doctor.getSpeciality().equals(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findBySurnameAndSpeciality(String surname, String speciality) {
        return doctors.stream()
                .filter(doctor -> doctor.getSurname().equals(surname))
                .filter(doctor -> doctor.getSpeciality().equals(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findByNameAndSurnameAndSpeciality(String name, String surname, String speciality) {
        return doctors.stream()
                .filter(doctor -> doctor.getName().equals(name))
                .filter(doctor -> doctor.getSurname().equals(surname))
                .filter(doctor -> doctor.getSpeciality().equals(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return Optional.empty();
    }

}
