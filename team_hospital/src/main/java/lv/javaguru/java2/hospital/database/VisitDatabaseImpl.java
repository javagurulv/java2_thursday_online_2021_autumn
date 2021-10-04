package lv.javaguru.java2.hospital.database;

import lv.javaguru.java2.hospital.dependency_injection.DIComponent;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.PatientVisit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class VisitDatabaseImpl implements VisitsDatabase {
    private final List<PatientVisit> patientVisits = new ArrayList<>();

    @Override
    public void recordVisit(PatientVisit patientVisit) {
        patientVisits.add(patientVisit);
    }

    public List<PatientVisit> showAllPatientVisits() {
        return patientVisits;
    }

    @Override
    public boolean editVisit(Long visitId, int userInput, String changes) {
        boolean isVisitEdited = false;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Optional<PatientVisit> visitToEditOpt = patientVisits.stream()
                .filter(patientVisit -> patientVisit.getVisitID() == visitId)
                .findFirst();
        if (visitToEditOpt.isPresent()) {
            PatientVisit visitToEdit = visitToEditOpt.get();
            switch (userInput) {
                case 1 -> visitToEdit.setDoctor(findDoctorById(Long.parseLong(changes)).get(0));
                case 2 -> visitToEdit.setPatient(findPatientById(Long.parseLong(changes)).get());
                case 3 -> {
                    try {
                        visitToEdit.setVisitDate(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(changes));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return false;
    }

    private List<Doctor> findDoctorById(Long id) {
        DoctorDatabase doctorDatabase = new DoctorDatabaseImpl();
        return doctorDatabase.findById(id);
    }

    private Optional<Patient> findPatientById(Long id) {
        PatientDatabase patientDatabase = new PatientDatabaseImpl();
        return patientDatabase.findById(id);
    }

    public boolean DeleteVisit(Long id) {
        return patientVisits.removeIf(p -> p.getVisitID().equals(id));
    }
}
