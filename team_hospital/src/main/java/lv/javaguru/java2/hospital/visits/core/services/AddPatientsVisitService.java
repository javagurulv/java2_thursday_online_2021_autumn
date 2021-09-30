package lv.javaguru.java2.hospital.visits.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.PatientVisit;
import lv.javaguru.java2.hospital.visits.core.request.AddPatientVisitRequest;
import lv.javaguru.java2.hospital.visits.core.responses.CoreError;
import lv.javaguru.java2.hospital.visits.core.responses.AddPatientVisitResponse;
import lv.javaguru.java2.hospital.visits.core.services.validators.AddPatientVisitValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddPatientsVisitService {
    private final PatientDatabaseImpl patientDatabase;
    private final DoctorDatabaseImpl doctorDatabase;
    private final VisitDatabaseImpl visitDatabase;
    private final AddPatientVisitValidator validator;

    public AddPatientsVisitService(PatientDatabaseImpl patientDatabase, DoctorDatabaseImpl doctorDatabase,
                                   VisitDatabaseImpl visitDatabase, AddPatientVisitValidator validator) {
        this.patientDatabase = patientDatabase;
        this.doctorDatabase = doctorDatabase;
        this.visitDatabase = visitDatabase;
        this.validator = validator;
    }

    public AddPatientVisitResponse execute(AddPatientVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddPatientVisitResponse(errors, null);
        }

        Patient patient = patientDatabase
                .findPatientsByPersonalCode(request.getPatientsPersonalCode()).get(0);
        Doctor doctor = doctorDatabase
                .findByNameAndSurname(request.getDoctorsName(), request.getDoctorsSurname()).get(0);

        String data = request.getVisitDate();
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        PatientVisit patientVisit =
                new PatientVisit(doctor, patient, date);

        visitDatabase.recordVisit(patientVisit);
        return new AddPatientVisitResponse(null, patientVisit);
    }
}
