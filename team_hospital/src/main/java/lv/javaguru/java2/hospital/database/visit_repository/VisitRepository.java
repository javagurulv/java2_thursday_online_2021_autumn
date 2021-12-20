package lv.javaguru.java2.hospital.database.visit_repository;

import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitEnum;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitRepository {

    void save(Visit visit);

    boolean deleteById(Long id);

    List<Visit> findAll();

    boolean editVisit(Long visitId, EditVisitEnum userInput, String changes);

    List<Visit> findById(Long id);

    List<Visit> findByDoctorId(Long id);

    List<Visit> findByPatientId(Long id);

    List<Visit> findByDate(LocalDateTime date);

    List<Visit> findByDoctorIdAndPatientId(Long doctorId, Long patientId);

    List<Visit> findByDoctorIdAndDate(Long doctorId, LocalDateTime date);

    List<Visit> findByPatientIdAndDate(Long patientId, LocalDateTime date);

    List<Visit> findByVisitIDAndDate(Long visitID, LocalDateTime date);

    List<Visit> findByDoctorIdAndPatientIdAndDate(Long doctorId, Long patientId, LocalDateTime date);

    List<Visit> findByVisitIdAndPatientId(Long visitID, Long patientID);

    List<Visit> findByVisitIdAndDoctorId(Long visitID, Long doctorID);

    List<Visit> findByVisitIdDoctorIdDate(Long visitID, Long doctorID, LocalDateTime date);

    List<Visit> findByVisitIdPatientIdDate(Long visitID, Long patientID, LocalDateTime date);

    List<Visit> findByVisitIdAndDoctorIdAndPatientId(Long visitID, Long doctorID, Long patientID);

    List<Visit> findByVisitIdAndDoctorIdAndPatientIdAndDate(Long visitID, Long doctorID, Long patientID, LocalDateTime date);

    List<Visit> findPatientForDeleting(Long patientID);
}
