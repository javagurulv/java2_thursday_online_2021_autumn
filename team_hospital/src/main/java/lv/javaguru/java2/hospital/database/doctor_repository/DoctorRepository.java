package lv.javaguru.java2.hospital.database.doctor_repository;

import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {

    void save(Doctor doctor);

    boolean deleteById(Long id);

    List<Doctor> findAll();

    boolean editDoctor(Long doctorId, Enum infoToEdit, String input);

    List<Doctor> findByName(String name);

    List<Doctor> findBySurname(String surname);

    List<Doctor> findByNameAndSurname(String name, String surname);

    List<Doctor> getById(Long id);

    List<Doctor> findBySpeciality(String speciality);

    List<Doctor> findByNameAndSpeciality(String name, String speciality);

    List<Doctor> findBySurnameAndSpeciality(String surname, String speciality);

    List<Doctor> findByNameAndSurnameAndSpeciality(String name, String surname, String speciality);

    Optional<Doctor> findById(Long id);
}
