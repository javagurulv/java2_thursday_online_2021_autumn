package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitEnum;

import java.time.LocalDateTime;
import java.util.List;

public class JDBCVisitDatabaseImpl implements VisitDatabase {

    @Override
    public void recordVisit(Visit visit) {

    }

    @Override
    public boolean deleteVisit(Long id) {
        return false;
    }

    @Override
    public List<Visit> getAllVisits() {
        return null;
    }

    @Override
    public boolean editVisit(Long visitId, EditVisitEnum userInput, String changes) {
        return false;
    }

    @Override
    public List<Visit> findByVisitId(Long id) {
        return null;
    }

    @Override
    public List<Visit> findByDoctorId(Long id) {
        return null;
    }

    @Override
    public List<Visit> findByPatientId(Long id) {
        return null;
    }

    @Override
    public List<Visit> findByDate(LocalDateTime date) {
        return null;
    }

    @Override
    public List<Visit> findByDoctorIdAndPatientId(Long doctorId, Long patientId) {
        return null;
    }

    @Override
    public List<Visit> findByDoctorIdAndDate(Long doctorId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Visit> findByPatientIdAndDate(Long patientId, LocalDateTime date) {
        return null;
    }

    @Override
    public List<Visit> findByDoctorIdAndPatientIdAndDate(Long doctorId, Long patientId, LocalDateTime date) {
        return null;
    }
}
