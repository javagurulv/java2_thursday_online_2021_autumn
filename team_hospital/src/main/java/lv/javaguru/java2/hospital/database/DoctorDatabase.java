package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Visit;

import java.util.List;

public interface DoctorDatabase {

    void addDoctor(Doctor doctor);

    boolean deleteDoctorById(Long id);

    List<Doctor> showAllDoctors();

    boolean editDoctor(Long doctorId, String infoToEdit, String input);

    List<Doctor> findByName(String name);

    List<Doctor> findBySurname(String surname);

    List<Doctor> findByNameAndSurname(String name, String surname);

    List<Doctor> findById(Long id);

    List<Doctor> findBySpeciality(String speciality);

    List<Doctor> findByNameAndSpeciality(String name, String speciality);

    List<Doctor> findBySurnameAndSpeciality(String surname, String speciality);

    List<Doctor> findByNameAndSurnameAndSpeciality(String name, String surname, String speciality);

    List<Visit> getDoctorVisits(Long id);

}
