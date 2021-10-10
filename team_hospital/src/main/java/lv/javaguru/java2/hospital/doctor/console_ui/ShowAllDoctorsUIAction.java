package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.ShowAllDoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowAllDoctorsUIAction implements DoctorUIAction {

    @Autowired private ShowAllDoctorsService showAllDoctors;

    @Override
    public void execute() {
        System.out.println("Doctor list: ");
        ShowAllDoctorsRequest request = new ShowAllDoctorsRequest();
        ShowAllDoctorsResponse response = showAllDoctors.execute(request);
        System.out.println(response.getDoctors());
        System.out.println("Doctor list end.");
    }
}
