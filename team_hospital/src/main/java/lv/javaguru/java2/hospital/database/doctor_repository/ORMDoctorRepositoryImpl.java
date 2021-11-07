package lv.javaguru.java2.hospital.database.doctor_repository;

import lv.javaguru.java2.hospital.domain.Doctor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ORMDoctorRepositoryImpl implements DoctorRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void addDoctor(Doctor doctor) {
        sessionFactory.getCurrentSession().save(doctor);
    }

    @Override
    public boolean deleteDoctorById(Long id) {
        return false;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT d FROM Doctor d", Doctor.class)
                .getResultList();
    }

    @Override
    public boolean editDoctor(Long doctorId, Enum infoToEdit, String input) {
        return false;
    }

    @Override
    public List<Doctor> findByName(String name) {
        return null;
    }

    @Override
    public List<Doctor> findBySurname(String surname) {
        return null;
    }

    @Override
    public List<Doctor> findByNameAndSurname(String name, String surname) {
        return null;
    }

    @Override
    public List<Doctor> findById(Long id) {
        return null;
    }

    @Override
    public List<Doctor> findBySpeciality(String speciality) {
        return null;
    }

    @Override
    public List<Doctor> findByNameAndSpeciality(String name, String speciality) {
        return null;
    }

    @Override
    public List<Doctor> findBySurnameAndSpeciality(String surname, String speciality) {
        return null;
    }

    @Override
    public List<Doctor> findByNameAndSurnameAndSpeciality(String name, String surname, String speciality) {
        return null;
    }
}
