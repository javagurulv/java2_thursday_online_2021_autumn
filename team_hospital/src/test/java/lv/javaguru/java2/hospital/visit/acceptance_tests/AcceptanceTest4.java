package lv.javaguru.java2.hospital.visit.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.SearchVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.AddVisitService;
import lv.javaguru.java2.hospital.visit.core.services.SearchVisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest4 {

    private ApplicationContext applicationContext;

    @BeforeEach
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
    }

    @Test
    public void shouldMakeCorrectSearchByID(){
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name4", "surname4", "2222");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest1);
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("name5", "surname5", "speciality5");
        AddDoctorResponse addDoctorResponse = getAddDoctorService().execute(addDoctorRequest);

        AddVisitRequest request = new AddVisitRequest(
                addPatientResponse.getPatient().getPersonalCode(),
                addDoctorResponse.getNewDoctor().getName(),
                addDoctorResponse.getNewDoctor().getSurname(),
                "12/12/2022 12:00");
        AddVisitResponse addVisitResponse = getAddVisitService().execute(request);

        SearchVisitRequest searchVisitRequest = new SearchVisitRequest(
                addVisitResponse.getPatientVisit().getVisitID(),
                null,
                null,
                "");
        SearchVisitResponse searchVisitResponse = getSearchVisitService().execute(searchVisitRequest);
        assertEquals(addVisitResponse.getPatientVisit(), searchVisitResponse.getVisits().get(0));
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

    private SearchVisitService getSearchVisitService(){
        return applicationContext.getBean(SearchVisitService.class);
    }
}
