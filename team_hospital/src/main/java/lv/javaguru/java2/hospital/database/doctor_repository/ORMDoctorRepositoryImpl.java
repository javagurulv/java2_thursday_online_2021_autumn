package lv.javaguru.java2.hospital.database.doctor_repository;

import lv.javaguru.java2.hospital.domain.Doctor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

//@Component
public class ORMDoctorRepositoryImpl implements DoctorRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void save(Doctor doctor) {
        sessionFactory.getCurrentSession().save(doctor);
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Doctor where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Doctor> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT d FROM Doctor d", Doctor.class)
                .getResultList();
    }

    @Override
    public boolean editDoctor(Long doctorId, Enum infoToEdit, String input) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE Doctor d SET "
                        + infoToEdit.toString().toLowerCase(Locale.ROOT)
                        + " = :" + input +" WHERE id = :id");
        query.setParameter("id", doctorId);
        query.setParameter(input, input);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Doctor> findByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Doctor d WHERE name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Doctor> findBySurname(String surname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Doctor d WHERE surname = :surname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public List<Doctor> findByNameAndSurname(String name, String surname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Doctor d WHERE name = :name AND surname = :surname");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public List<Doctor> getById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Doctor d WHERE id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Doctor> findBySpeciality(String speciality) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Doctor d WHERE speciality = :speciality");
        query.setParameter("speciality", speciality);
        return query.getResultList();
    }

    @Override
    public List<Doctor> findByNameAndSpeciality(String name, String speciality) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Doctor d WHERE name = :name AND speciality = :speciality");
        query.setParameter("name", name);
        query.setParameter("speciality", speciality);
        return query.getResultList();
    }

    @Override
    public List<Doctor> findBySurnameAndSpeciality(String surname, String speciality) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Doctor d WHERE surname = :surname AND speciality = :speciality");
        query.setParameter("surname", surname);
        query.setParameter("speciality", speciality);
        return query.getResultList();
    }

    @Override
    public List<Doctor> findByNameAndSurnameAndSpeciality(String name, String surname, String speciality) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Doctor d WHERE name = :name AND " +
                        "surname = :surname AND " +
                        "speciality = :speciality");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        query.setParameter("speciality", speciality);
        return query.getResultList();
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        Doctor doctor = sessionFactory.getCurrentSession().get(Doctor.class, id);
        if (doctor == null) {
            return Optional.empty();
        } else {
            return Optional.of(doctor);
        }
    }
}
