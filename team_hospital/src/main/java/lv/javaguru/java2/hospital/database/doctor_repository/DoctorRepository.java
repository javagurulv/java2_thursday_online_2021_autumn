package lv.javaguru.java2.hospital.database.doctor_repository;

import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.List;

public interface DoctorRepository {

    void addDoctor(Doctor doctor);

    boolean deleteDoctorById(String id);

    List<Doctor> getAllDoctors();

    boolean editDoctor(String doctorId, Enum infoToEdit, String input);

    List<Doctor> findByName(String name);

    List<Doctor> findBySurname(String surname);

    List<Doctor> findByNameAndSurname(String name, String surname);

    List<Doctor> findById(Long id);

    List<Doctor> findBySpeciality(String speciality);

    List<Doctor> findByNameAndSpeciality(String name, String speciality);

    List<Doctor> findBySurnameAndSpeciality(String surname, String speciality);

    List<Doctor> findByNameAndSurnameAndSpeciality(String name, String surname, String speciality);

}
