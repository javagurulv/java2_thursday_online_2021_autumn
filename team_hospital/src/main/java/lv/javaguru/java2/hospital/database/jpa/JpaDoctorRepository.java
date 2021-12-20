package lv.javaguru.java2.hospital.database.jpa;

import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public interface JpaDoctorRepository extends JpaRepository<Doctor, Long> {

    @Query(value = "UPDATE Doctor d SET infoToEdit = input WHERE id = doctorId")
    boolean editDoctor(Long doctorId, Enum infoToEdit, String input);

    @Query(value = "SELECT * FROM doctors WHERE id = ?1,", nativeQuery = true)
    List<Doctor> getById(Long id);

    List<Doctor> findByName(String name);

    List<Doctor> findBySurname(String surname);

    List<Doctor> findByNameAndSurname(String name, String surname);

    List<Doctor> findBySpeciality(String speciality);

    List<Doctor> findByNameAndSpeciality(String name, String speciality);

    List<Doctor> findBySurnameAndSpeciality(String surname, String speciality);

    List<Doctor> findByNameAndSurnameAndSpeciality(String name, String surname, String speciality);
}
