package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.core.requests.ShowAllDoctorsRequest;
import lv.javaguru.java2.hospital.doctor.core.responses.ShowAllDoctorsResponse;
import lv.javaguru.java2.hospital.doctor.core.services.ShowAllDoctorsService;

public class ShowAllDoctorsUIAction implements DoctorUIActions {

    private final ShowAllDoctorsService showAllDoctors;

    public ShowAllDoctorsUIAction(ShowAllDoctorsService showAllDoctors) {
        this.showAllDoctors = showAllDoctors;
    }

    @Override
    public void execute() {
        System.out.println("Doctor list: ");
        ShowAllDoctorsRequest request = new ShowAllDoctorsRequest();
        ShowAllDoctorsResponse response = showAllDoctors.execute(request);
        System.out.println(response.getDoctors());
        System.out.println("Doctor list end.");
    }
}
