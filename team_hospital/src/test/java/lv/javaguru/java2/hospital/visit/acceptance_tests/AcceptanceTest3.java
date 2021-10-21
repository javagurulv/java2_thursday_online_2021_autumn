package lv.javaguru.java2.hospital.visit.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.EditVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.AddVisitService;
import lv.javaguru.java2.hospital.visit.core.services.EditVisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcceptanceTest3 {
    private ApplicationContext applicationContext;

    @BeforeEach
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
    }

    @Test
    public void shouldCorrectAddVisit() {
        AddPatientRequest addPatientRequest = new AddPatientRequest("name3", "surname3", "1111");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest);
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("name3", "surname3", "speciality3");
        AddDoctorResponse addDoctorResponse = getAddDoctorService().execute(addDoctorRequest);
        AddDoctorRequest addDoctorRequest2 = new AddDoctorRequest("name4", "surname4", "speciality4");
        AddDoctorResponse addDoctorResponse2 = getAddDoctorService().execute(addDoctorRequest2);

        AddVisitRequest request = new AddVisitRequest(
                addPatientResponse.getPatient().getPersonalCode(),
                addDoctorResponse.getNewDoctor().getName(),
                addDoctorResponse.getNewDoctor().getSurname(),
                "10/10/2023 10:00");
        AddVisitResponse addVisitResponse = getAddVisitService().execute(request);

        EditVisitRequest editVisitRequest = new EditVisitRequest(
                addVisitResponse.getPatientVisit().getVisitID(), "DOCTOR",
                addDoctorResponse2.getNewDoctor().getId().toString());
        EditVisitResponse editVisitResponse = getEditVisitService().execute(editVisitRequest);
        assertTrue(editVisitResponse.isVisitEdited());
    }

    private AddVisitService getAddVisitService() {
        return applicationContext.getBean(AddVisitService.class);
    }

    private AddPatientService getAddPatienceService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private AddDoctorService getAddDoctorService() {
        return applicationContext.getBean(AddDoctorService.class);
    }

    private EditVisitService getEditVisitService(){
        return applicationContext.getBean(EditVisitService.class);
    }
}
