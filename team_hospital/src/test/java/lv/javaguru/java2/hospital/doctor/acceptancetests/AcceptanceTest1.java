package lv.javaguru.java2.hospital.doctor.acceptancetests;

import lv.javaguru.java2.hospital.config.HospitalConfiguration;
import lv.javaguru.java2.hospital.database_cleaner.DatabaseCleaner;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.ShowAllDoctorsService;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest1 {

    private ApplicationContext appContext;

    @BeforeEach
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(HospitalConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Ignore
    public void shouldReturnCorrectDoctorList() {
        AddDoctorRequest addDoctorRequest1 = new AddDoctorRequest("NameA", "SurnameA", "SpecialityA");
        getAddDoctorService().execute(addDoctorRequest1);

        AddDoctorRequest addDoctorRequest2 = new AddDoctorRequest("NameAB", "SurnameAB", "SpecialityAB");
        getAddDoctorService().execute(addDoctorRequest2);

        ShowAllDoctorsResponse response = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());
        assertEquals(response.getDoctors().size(), 2);
    }

    private AddDoctorService getAddDoctorService() {
        return appContext.getBean(AddDoctorService.class);
    }

    private ShowAllDoctorsService getShowAllDoctorsService() {
        return appContext.getBean(ShowAllDoctorsService.class);
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleaner.class);
    }
}
