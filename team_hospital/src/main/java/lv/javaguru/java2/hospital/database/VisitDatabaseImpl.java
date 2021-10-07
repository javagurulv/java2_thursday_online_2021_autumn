package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DIComponent
public class VisitDatabaseImpl implements VisitsDatabase {
    private final List<Visit> visits = new ArrayList<>();

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
    public boolean editVisit(Long visitId, int userInput, String changes) {
        boolean isVisitEdited = false;
        Optional<Visit> visitToEditOpt = visits.stream()
                .filter(patientVisit -> patientVisit.getVisitID() == visitId)
                .findFirst();
        if (visitToEditOpt.isPresent()) {
            Visit visitToEdit = visitToEditOpt.get();
            switch (userInput) {
                case 1 -> {
                    visitToEdit.setDoctor(findDoctorById(Long.parseLong(changes)).get(0));
                    isVisitEdited = true;
                }
                case 2 -> {
                    visitToEdit.setPatient(findPatientById(Long.parseLong(changes)).get());
                    isVisitEdited = true;
                }
                case 3 -> {
                    try {
                        visitToEdit.setVisitDate(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(changes));
                        isVisitEdited = true;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return isVisitEdited;
    }

    @Override
    public List<Visit> findByVisitId(Long id) {
        return visits.stream()
                .filter(visit -> visit.getVisitID() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByDoctorId(Long id) {
        return visits.stream()
                .filter(visit -> visit.getDoctor().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByPatientId(Long id) {
        return visits.stream()
                .filter(visit -> visit.getPatient().getId() == id)
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
                .filter(visit -> visit.getDoctor().getId() == doctorId)
                .filter(visit -> visit.getPatient().getId() == patientId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByDoctorIdAndDate(Long doctorId, Date date) {
        return visits.stream()
                .filter(visit -> visit.getDoctor().getId() == doctorId)
                .filter(visit -> visit.getVisitDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByPatientIdAndDate(Long patientId, Date date) {
        return visits.stream()
                .filter(visit -> visit.getPatient().getId() == patientId)
                .filter(visit -> visit.getVisitDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visit> findByDoctorIdAndPatientIdAndDate(Long doctorId, Long patientId, Date date) {
        return visits.stream()
                .filter(visit -> visit.getDoctor().getId() == doctorId)
                .filter(visit -> visit.getPatient().getId() == patientId)
                .filter(visit -> visit.getVisitDate().equals(date))
                .collect(Collectors.toList());
    }

    private List<Doctor> findDoctorById(Long id) {
        DoctorDatabase doctorDatabase = new DoctorDatabaseImpl();
        return doctorDatabase.findById(id);
    }

    private Optional<Patient> findPatientById(Long id) {
        PatientDatabase patientDatabase = new PatientDatabaseImpl();
        return patientDatabase.findById(id);
    }


}
