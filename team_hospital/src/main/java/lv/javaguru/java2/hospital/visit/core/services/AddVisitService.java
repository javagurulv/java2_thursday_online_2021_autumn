package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.DoctorDatabase;
import lv.javaguru.java2.hospital.database.PatientDatabase;
import lv.javaguru.java2.hospital.database.VisitDatabase;
import lv.javaguru.java2.hospital.domain.Doctor;
import lv.javaguru.java2.hospital.domain.Patient;
import lv.javaguru.java2.hospital.domain.Visit;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.CoreError;
import lv.javaguru.java2.hospital.visit.core.services.validators.AddVisitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class AddVisitService {

    @Autowired private VisitDatabase visitDatabase;
    @Autowired private AddVisitValidator validator;
    @Autowired private PatientDatabase patientDatabase;
    @Autowired private DoctorDatabase doctorDatabase;

    public AddVisitResponse execute(AddVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddVisitResponse(errors);
        }

        Patient patient = patientDatabase.findById(Long.valueOf(request.getPatientID())).get(0);
        Doctor doctor = doctorDatabase.findById(Long.valueOf(request.getDoctorsID())).get(0);

        LocalDateTime date = LocalDateTime.from(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").parse(request.getVisitDate()));
        Visit visit = new Visit(doctor, patient, date, request.getDescription());

        visitDatabase.recordVisit(visit);
        return new AddVisitResponse(visit);
    }
}
