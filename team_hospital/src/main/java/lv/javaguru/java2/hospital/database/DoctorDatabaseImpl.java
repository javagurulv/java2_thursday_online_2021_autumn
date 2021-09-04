package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorDatabaseImpl implements DoctorDatabase {

    private final List<Doctor> doctorsList = new ArrayList<>();

    @Override
    public void addDoctor(Doctor doctor) {
        doctorsList.add(doctor);
    }

    @Override
    public void findDoctorById(int id) {
        for (Doctor doctor : doctorsList) {
            if (doctor.getId() == id) {
                System.out.println(doctor);
            }
        }
    }

    @Override
    public void deleteDoctorById(int id) {
        doctorsList.removeIf(doctor -> doctor.getId() == id);
    }

    @Override
    public List<Doctor> showAllDoctors() {
        return doctorsList;
    }

    @Override
    public void editDoctor(int doctorId, int userInput, String changes) {
        for (Doctor doctor : doctorsList) {
            if (doctor.getId() == doctorId) {
                switch (userInput) {
                    case 1 -> doctor.setName(changes);
                    case 2 -> doctor.setSurname(changes);
                    case 3 -> doctor.setSpeciality(changes);
                }
            }
        }
    }

    @Override
    public boolean doctorExists(int id) {
        for (Doctor doctor : doctorsList) {
            if (doctor.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
