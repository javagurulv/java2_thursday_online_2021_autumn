package lv.javaguru.java2.hospital.visit.acceptance_tests;

import lv.javaguru.java2.hospital.config.HospitalSpringCoreConfiguration;
import lv.javaguru.java2.hospital.database_cleaner.DatabaseCleaner;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.AddDoctorResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.patient.core.requests.AddPatientRequest;
import lv.javaguru.java2.hospital.patient.core.responses.AddPatientResponse;
import lv.javaguru.java2.hospital.patient.core.services.AddPatientService;
import lv.javaguru.java2.hospital.visit.core.requests.AddVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.EditVisitRequest;
import lv.javaguru.java2.hospital.visit.core.requests.SearchVisitRequest;
import lv.javaguru.java2.hospital.visit.core.responses.AddVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.EditVisitResponse;
import lv.javaguru.java2.hospital.visit.core.responses.SearchVisitResponse;
import lv.javaguru.java2.hospital.visit.core.services.AddVisitService;
import lv.javaguru.java2.hospital.visit.core.services.EditVisitService;
import lv.javaguru.java2.hospital.visit.core.services.ShowAllVisitService;
import lv.javaguru.java2.hospital.visit.core.services.search_visit_service.SearchVisitService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HospitalSpringCoreConfiguration.class})
@Sql({"/Schema.sql"})
public class AcceptanceTest3 {

    @Autowired private DatabaseCleaner databaseCleaner;
    @Autowired private AddPatientService addPatientService;
    @Autowired private AddDoctorService addDoctorService;
    @Autowired private AddVisitService addVisitService;
    @Autowired private SearchVisitService searchVisitService;
    @Autowired private ShowAllVisitService showAllVisitService;
    @Autowired private EditVisitService editVisitService;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void shouldCorrectAddVisit() {
        AddPatientRequest addPatientRequest = new AddPatientRequest("name3", "surname3", "55556666777");
        AddPatientResponse addPatientResponse = addPatientService.execute(addPatientRequest);
        AddDoctorRequest addDoctorRequest = new AddDoctorRequest("name3", "surname3", "speciality3");
        AddDoctorResponse addDoctorResponse = addDoctorService.execute(addDoctorRequest);
        AddDoctorRequest addDoctorRequest2 = new AddDoctorRequest("name4", "surname4", "speciality4");
        AddDoctorResponse addDoctorResponse2 = addDoctorService.execute(addDoctorRequest2);

        AddVisitRequest request = new AddVisitRequest(
                addPatientResponse.getPatient().getId().toString(),
                addDoctorResponse.getNewDoctor().getId().toString(),
                "2023-10-10 10:00", "description");
        AddVisitResponse addVisitResponse = addVisitService.execute(request);

        EditVisitRequest editVisitRequest = new EditVisitRequest(
                addVisitResponse.getPatientVisit().getVisitID().toString(), "DOCTOR_ID",
                addDoctorResponse2.getNewDoctor().getId().toString());

        EditVisitResponse editVisitResponse = editVisitService.execute(editVisitRequest);

        SearchVisitRequest searchVisitRequest2 = new SearchVisitRequest(
                null, addDoctorResponse2.getNewDoctor().getId().toString(),
                addPatientResponse.getPatient().getId().toString(),
                "2023-10-10 10:00");

        SearchVisitResponse searchVisitResponse2 = searchVisitService.execute(searchVisitRequest2);

        assertFalse(searchVisitResponse2.getVisits().isEmpty());
    }
}
