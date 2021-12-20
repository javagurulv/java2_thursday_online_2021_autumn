package lv.javaguru.java2.hospital.database.jpa;

import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.patient.core.requests.EditPatientEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPatientRepository extends JpaRepository<Patient, Long> {

    @Modifying
    @Query(value = "UPDATE Patient p SET name = :changes WHERE id = :id")
    void editPatientName(@Param("id") Long patientID, @Param("changes") String input);

    @Modifying
    @Query(value = "UPDATE Patient p SET surname = :changes WHERE id = :id")
    void editPatientSurname(@Param("id") Long patientID, @Param("changes") String input);

    @Modifying
    @Query(value = "UPDATE Patient p SET personal_code = :changes WHERE id = :id")
    void editPatientPersonalCode(@Param("id") Long patientID, @Param("changes") String input);

    List<Patient> findByName(String name);

    List<Patient> findBySurname(String surname);

    List<Patient> findByPersonalCode(String personalCode);

    List<Patient> findByNameAndSurname(String name, String surname);

    List<Patient> findByNameAndPersonalCode(String name, String personal_code);

    List<Patient> findBySurnameAndPersonalCode(String surname, String personal_code);

    List<Patient> findByNameAndSurnameAndPersonalCode(String name, String surname, String personal_code);

}
