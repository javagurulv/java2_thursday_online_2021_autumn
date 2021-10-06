package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Visit;

import java.util.Date;
import java.util.List;

public interface VisitsDatabase {

    void recordVisit(Visit visit);

    boolean deleteVisit(Long id);

    List<Visit> showAllVisits();

    boolean editVisit(Long visitId, int userInput, String changes);

    List<Visit> findByVisitId(Long id);

    List<Visit> findByDoctorId(Long id);

    List<Visit> findByPatientId(Long id);

    List<Visit> findByDate(Date date);

    List<Visit> findByDoctorIdAndPatientId(Long doctorId, Long patientId);

    List<Visit> findByDoctorIdAndDate(Long doctorId, Date date);

    List<Visit> findByPatientIdAndDate(Long patientId, Date date);

    List<Visit> findByDoctorIdAndPatientIdAndDate(Long doctorId, Long patientId, Date date);

}
