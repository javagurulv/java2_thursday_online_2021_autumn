package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorDatabaseImpl implements DoctorDatabase {

    private final List<Doctor> doctorsList = new ArrayList<>();

    @Override
    public void addDoctor(Doctor doctor) {
        doctorsList.add(doctor);
    }

    @Override
    public boolean findDoctorById(long id) {
        boolean isDoctorFound = false;
        Optional<Doctor> doctorToFindOpt = doctorsList.stream()
                .filter(doctor -> doctor.getId() == id)
                .findFirst();
        if (doctorToFindOpt.isPresent()) {
            Doctor doctorToFind = doctorToFindOpt.get();
            System.out.println(doctorToFind);
            isDoctorFound = true;
        }
        return isDoctorFound;
    }

    @Override
    public boolean deleteDoctorById(long id) {
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
    public boolean editDoctor(long doctorId, int userInput, String changes) {
        boolean isDoctorEdited = false;
        Optional<Doctor> doctorToEditOpt = doctorsList.stream()
                .filter(doctor -> doctor.getId() == doctorId)
                .findFirst();
        if (doctorToEditOpt.isPresent()) {
            Doctor doctorToEdit = doctorToEditOpt.get();
            switch (userInput) {
                case 1 -> doctorToEdit.setName(changes);
                case 2 -> doctorToEdit.setSurname(changes);
                case 3 -> doctorToEdit.setSpeciality(changes);
            }
            isDoctorEdited = true;
        }
        return isDoctorEdited;
    }

    @Override
    public boolean doctorExists(long id) {
        for (Doctor doctor : doctorsList) {
            if (doctor.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
