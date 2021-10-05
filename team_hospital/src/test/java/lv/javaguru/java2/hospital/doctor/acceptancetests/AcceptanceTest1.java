package lv.javaguru.java2.hospital.doctor.acceptancetests;

import lv.javaguru.java2.hospital.dependency_injection.ApplicationContext;
import lv.javaguru.java2.hospital.dependency_injection.DIApplicationContextBuilder;
import lv.javaguru.java2.hospital.doctor.core.requests.AddDoctorRequest;
import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.AddDoctorService;
import lv.javaguru.java2.hospital.doctor.core.services.ShowAllDoctorsService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest1 {

    private ApplicationContext appContest =
            new DIApplicationContextBuilder().build("lv.javaguru.java2.hospital");

    @Test
    public void shouldReturnCorrectDoctorList() {
        AddDoctorRequest addDoctorRequest1 = new AddDoctorRequest("NameA", "SurnameA", "SpecialityA");
        getAddDoctorService().execute(addDoctorRequest1);

        AddDoctorRequest addDoctorRequest2 = new AddDoctorRequest("NameA", "SurnameA", "SpecialityA");
        getAddDoctorService().execute(addDoctorRequest2);

        ShowAllDoctorsResponse response = getShowAllDoctorsService().execute(new ShowAllDoctorsRequest());
        assertEquals(response.getDoctors().size(), 2);
    }

    private AddDoctorService getAddDoctorService() {
        return appContest.getBean(AddDoctorService.class);
    }

    private ShowAllDoctorsService getShowAllDoctorsService() {
        return appContest.getBean(ShowAllDoctorsService.class);
    }
}
