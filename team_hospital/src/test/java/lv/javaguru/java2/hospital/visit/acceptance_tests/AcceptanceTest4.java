package lv.javaguru.java2.hospital.visit.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.database_cleaner.DatabaseCleaner;
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
import lv.javaguru.java2.hospital.visit.core.services.ShowAllVisitService;
import lv.javaguru.java2.hospital.visit.core.services.search_visit_service.SearchVisitService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HospitalConfiguration.class})
@Sql({"/schema.sql"})
public class AcceptanceTest4 {

    @Autowired private DatabaseCleaner databaseCleaner;
    @Autowired private AddPatientService addPatientService;
    @Autowired private AddDoctorService addDoctorService;
    @Autowired private AddVisitService addVisitService;
    @Autowired private SearchVisitService searchVisitService;
    @Autowired private ShowAllVisitService showAllVisitService;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void shouldMakeCorrectSearchByID(){
        AddPatientRequest addPatientRequest1 = new AddPatientRequest("name4", "surname4", "66667777888");
        AddPatientResponse addPatientResponse = addPatientService.execute(addPatientRequest1);
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("name5", "surname5", "speciality5");
        AddDoctorResponse addDoctorResponse = addDoctorService.execute(addDoctorRequest);

        AddVisitRequest request = new AddVisitRequest(
                addPatientResponse.getPatient().getId().toString(),
                addDoctorResponse.getNewDoctor().getId().toString(),
                "2022-12-12 12:00", "description");
        AddVisitResponse addVisitResponse = addVisitService.execute(request);

        SearchVisitRequest request1 = new SearchVisitRequest(
                null, addDoctorResponse.getNewDoctor().getId().toString(),
                addPatientResponse.getPatient().getId().toString(),
                "2022-12-12 12:00");
        SearchVisitResponse searchVisitResponse1 = searchVisitService.execute(request1);

        SearchVisitRequest searchVisitRequest2 = new SearchVisitRequest(
                searchVisitResponse1.getVisits().get(0).getVisitID().toString(),
                null,
                null,
                "");
        SearchVisitResponse searchVisitResponse2 = searchVisitService.execute(searchVisitRequest2);
        assertEquals(searchVisitResponse1.getVisits().get(0), searchVisitResponse2.getVisits().get(0));
    }
}
