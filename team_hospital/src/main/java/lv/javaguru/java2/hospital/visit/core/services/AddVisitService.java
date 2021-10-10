package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabaseImpl;
import lv.javaguru.java2.hospital.database.PatientDatabaseImpl;
import lv.javaguru.java2.hospital.database.VisitDatabaseImpl;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.AddVisitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class AddVisitService {

    @Autowired private PatientDatabaseImpl patientDatabase;
    @Autowired private DoctorDatabaseImpl doctorDatabase;
    @Autowired private VisitDatabaseImpl visitDatabase;
    @Autowired private AddVisitValidator validator;

    public AddVisitResponse execute(AddVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddVisitResponse(errors);
        }

        Patient patient = getPatient(request);
        Doctor doctor = getDoctor(request);
        Date date = getVisitDate(request);

        Visit visit = new Visit(doctor, patient, date);

        visitDatabase.recordVisit(visit);
        return new AddVisitResponse(visit);
    }

    private Doctor getDoctor(AddVisitRequest request) {
        Doctor doctor = doctorDatabase
                .findByNameAndSurname(request.getDoctorsName(), request.getDoctorsSurname()).get(0);
        return doctor;
    }

    private Patient getPatient(AddVisitRequest request) {
        Patient patient = patientDatabase
                .findPatientsByPersonalCode(request.getPatientsPersonalCode()).get(0);
        return patient;
    }


    private Date getVisitDate(AddVisitRequest request) {
        Date visitDate = null;
        try {
            visitDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(request.getVisitDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return visitDate;
    }
}
