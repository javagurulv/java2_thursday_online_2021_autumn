package doctor.database;

import doctor.Doctor;

import java.util.List;

public interface DoctorDatabase {

    void addDoctor(Doctor doctor);

    void findDoctorById(int id);

    void deleteDoctorById(int id);

    List<Doctor> showAllDoctors();

    void editDoctor(int doctorId, int userInput, String input);

    boolean doctorExists(int id);

}
