package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.List;

public interface DoctorDatabase {

    void addDoctor(Doctor doctor);

    boolean findDoctorById(long id);

    boolean deleteDoctorById(long id);

    List<Doctor> showAllDoctors();

    boolean editDoctor(long doctorId, int userInput, String input);

    boolean doctorExists(long id);

}
