package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class VisitDatabaseImpl implements VisitDatabase {
    private final List<Visit> visits = new ArrayList<>();
    @Autowired
    PatientDatabase patientDatabase;
    @Autowired
    DoctorDatabase doctorDatabase;

    @Override
    public void recordVisit(Visit visit) {
        visits.add(visit);
    }

    @Override
    public boolean deleteVisit(Long id) {
        return visits.removeIf(p -> p.getVisitID().equals(id));
    }

    @Override
    public List<Visit> showAllVisits() {
        return visits;
    }

    @Override
    public boolean editVisit(Long visitId, EditVisitEnum userInput, String changes) {
        boolean isVisitEdited = false;
        Optional<Visit> visitToEditOpt = visits.stream()
                .filter(patientVisit -> Objects.equals(patientVisit.getVisitID(), visitId))
                .findFirst();
        if (visitToEditOpt.isPresent()) {
            Visit visitToEdit = visitToEditOpt.get();
            if (userInput.equals(EditVisitEnum.DOCTOR)) {
                visitToEdit.setDoctor(doctorDatabase.findById(Long.parseLong(changes)).get(0));
                isVisitEdited = true;
            } else if (userInput.equals(EditVisitEnum.PATIENT)) {
                visitToEdit.setPatient(patientDatabase.findById(Long.parseLong(changes)).get());
                isVisitEdited = true;
            } else {
                try {
                    visitToEdit.setVisitDate(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(changes));
                    isVisitEdited = true;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return isVisitEdited;
    }

    @Override
    public List<Visit> findByVisitId(Long id) {
        return visits.stream()
                .filter(visit -> Objects.equals(visit.getVisitID(), id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByDoctorId(Long id) {
        return visits.stream()
                .filter(visit -> Objects.equals(visit.getDoctor().getId(), id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByPatientId(Long id) {
        return visits.stream()
                .filter(visit -> Objects.equals(visit.getPatient().getId(), id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByDate(Date date) {
        return visits.stream()
                .filter(visit -> visit.getVisitDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByDoctorIdAndPatientId(Long doctorId, Long patientId) {
        return visits.stream()
                .filter(visit -> Objects.equals(visit.getDoctor().getId(), doctorId))
                .filter(visit -> Objects.equals(visit.getPatient().getId(), patientId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByDoctorIdAndDate(Long doctorId, Date date) {
        return visits.stream()
                .filter(visit -> Objects.equals(visit.getDoctor().getId(), doctorId))
                .filter(visit -> visit.getVisitDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByPatientIdAndDate(Long patientId, Date date) {
        return visits.stream()
                .filter(visit -> Objects.equals(visit.getPatient().getId(), patientId))
                .filter(visit -> visit.getVisitDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByDoctorIdAndPatientIdAndDate(Long doctorId, Long patientId, Date date) {
        return visits.stream()
                .filter(visit -> Objects.equals(visit.getDoctor().getId(), doctorId))
                .filter(visit -> Objects.equals(visit.getPatient().getId(), patientId))
                .filter(visit -> visit.getVisitDate().equals(date))
                .collect(Collectors.toList());
    }
}
