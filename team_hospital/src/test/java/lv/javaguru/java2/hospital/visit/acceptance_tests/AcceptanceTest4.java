package lv.javaguru.java2.hospital.visit.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.requests.SearchDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.responses.SearchDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.SearchDoctorsService;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.requests.SearchPatientsRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.responses.SearchPatientsResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.patient.core.services.search_patient_service.SearchPatientsService;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.SearchVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.AddVisitService;
import lv.javaguru.java2.hospital.visit.core.services.search_visit_service.SearchVisitService;
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
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name4", "surname4", "66667777888");
        AddPatientResponse addPatientResponse = getAddPatienceService().execute(addPatientRequest1);
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("name5", "surname5", "speciality5");
        AddDoctorResponse addDoctorResponse = getAddDoctorService().execute(addDoctorRequest);

        SearchPatientsResponse searchPatientsResponse = getSearchPatientsService()
                .execute(new SearchPatientsRequest("name4", "surname4", "66667777888"));
        SearchDoctorsResponse searchDoctorsResponse = getSearchDoctorsService()
                .execute(new SearchDoctorsRequest(null, "name5", "surname5", "speciality5"));

        AddVisitRequest request = new AddVisitRequest(
                searchPatientsResponse.getPatientList().get(0).getId().toString(),
                searchDoctorsResponse.getDoctors().get(0).getId().toString(),
                "2022-12-12 12:00", "description");
        AddVisitResponse addVisitResponse = getAddVisitService().execute(request);

        SearchVisitRequest request1 = new SearchVisitRequest(
                null, searchDoctorsResponse.getDoctors().get(0).getId().toString(),
                searchPatientsResponse.getPatientList().get(0).getId().toString(),
                "2022-12-12 12:00");
        SearchVisitResponse searchVisitResponse1 = getSearchVisitService().execute(request1);

        SearchVisitRequest searchVisitRequest2 = new SearchVisitRequest(
                searchVisitResponse1.getVisits().get(0).getVisitID().toString(),
                null,
                null,
                "");
        SearchVisitResponse searchVisitResponse2 = getSearchVisitService().execute(searchVisitRequest2);
        assertEquals(searchVisitResponse1.getVisits().get(0), searchVisitResponse2.getVisits().get(0));
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

    private SearchPatientsService getSearchPatientsService() {
        return applicationContext.getBean(SearchPatientsService.class);
    }

    private SearchDoctorsService getSearchDoctorsService(){
        return applicationContext.getBean(SearchDoctorsService.class);
    }

}
