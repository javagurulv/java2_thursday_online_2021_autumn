package lv.javaguru.java2.hospital.visit.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.ShowAllVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.ShowAllVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.AddVisitService;
import lv.javaguru.java2.hospital.visit.core.services.ShowAllVisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest1 {

    private ApplicationContext applicationContext;

    @BeforeEach
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
    }

    @Test
    public void shouldCorrectAddVisit() {
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name", "surname", "11112222333");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest1);
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("name", "surname", "speciality");
        AddDoctorResponse addDoctorResponse = getAddDoctorService().execute(addDoctorRequest);

        AddVisitRequest request = new AddVisitRequest(
                addPatientResponse.getPatient().getId().toString(),
                addDoctorResponse.getNewDoctor().getId().toString(),
                "12/12/2022 12:00");
        AddVisitResponse addVisitResponse = getAddVisitService().execute(request);

        ShowAllVisitResponse showAllVisitResponse = getShowAllVisitService().execute(new ShowAllVisitRequest());
        assertEquals(addVisitResponse.getPatientVisit(), showAllVisitResponse.getPatientVisits().get(0));
    }

    private AddVisitService getAddVisitService() {
        return applicationContext.getBean(AddVisitService.class);
    }

    private ShowAllVisitService getShowAllVisitService() {
        return applicationContext.getBean(ShowAllVisitService.class);
    }

    private AddPatientService getAddPatienceService() {
        return applicationContext.getBean(AddPatientService.class);
    }

    private AddDoctorService getAddDoctorService() {
        return applicationContext.getBean(AddDoctorService.class);
    }
}
