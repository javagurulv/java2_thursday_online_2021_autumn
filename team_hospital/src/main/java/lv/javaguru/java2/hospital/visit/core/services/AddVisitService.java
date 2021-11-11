package lv.javaguru.java2.hospital.visit.core.services;

import lv.javaguru.java2.hospital.database.doctor_repository.DoctorRepository;
import lv.javaguru.java2.hospital.database.patient_repository.PatientRepository;
import lv.javaguru.java2.hospital.database.visit_repository.VisitRepository;
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

    @Autowired private VisitRepository visitRepository;
    @Autowired private AddVisitValidator validator;
    @Autowired private PatientRepository patientRepository;
    @Autowired private DoctorRepository doctorRepository;

    public AddVisitResponse execute(AddVisitRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddVisitResponse(errors);
        }

        Patient patient = patientRepository.findById(Long.valueOf(request.getPatientID())).get(0);
        Doctor doctor = doctorRepository.findById(Long.valueOf(request.getDoctorsID())).get(0);

        LocalDateTime date = LocalDateTime.from(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").parse(request.getVisitDate()));
        Visit visit = new Visit(doctor, patient, date, request.getDescription());

        visitRepository.recordVisit(visit);
        return new AddVisitResponse(visit);
    }
}
