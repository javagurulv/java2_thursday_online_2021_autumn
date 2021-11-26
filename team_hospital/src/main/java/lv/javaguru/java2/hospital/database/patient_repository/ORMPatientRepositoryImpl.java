package lv.javaguru.java2.hospital.database.patient_repository;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class ORMPatientRepositoryImpl implements PatientRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void add(Patient patient) {
        sessionFactory.getCurrentSession().save(patient);
    }

    @Override
    public List<Patient> findById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Patient p WHERE id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Patient where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Patient> getAllPatients() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM Patient p", Patient.class)
                .getResultList();
    }

    @Override
    public boolean editActions(Long patientID, EditPatientEnum userInput, String input) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE Patient p SET "
                        + userInput.toString().toLowerCase(Locale.ROOT)
                        + " = :input WHERE id = :id");
        query.setParameter("id", patientID);
        query.setParameter("input", input);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Patient> findPatientsByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Patient p WHERE name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Patient> findPatientsBySurname(String surname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Patient p WHERE surname = :surname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public List<Patient> findPatientsByPersonalCode(String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Patient p WHERE personal_code = :personal_code");
        query.setParameter("personal_code", personalCode);
        return query.getResultList();
    }

    @Override
    public List<Patient> findPatientsByNameAndSurname(String name, String surname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Patient p WHERE name = :name AND surname = :surname");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public List<Patient> findPatientsByNameAndPersonalCode(String name, String personal_code) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Patient p WHERE name = :name AND personal_code = :personal_code");
        query.setParameter("name", name);
        query.setParameter("personal_code", personal_code);
        return query.getResultList();
    }

    @Override
    public List<Patient> findPatientsBySurnameAndPersonalCode(String surname, String personal_code) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Patient p WHERE surname = :surname AND personal_code = :personal_code");
        query.setParameter("surname", surname);
        query.setParameter("personal_code", personal_code);
        return query.getResultList();
    }

    @Override
    public List<Patient> findPatientByNameSurnamePersonalCode(String name, String surname, String personal_code) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM Patient p WHERE name = :name AND surname = :surname AND personal_code = :personal_code");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        query.setParameter("personal_code", personal_code);
        return query.getResultList();
    }
}
