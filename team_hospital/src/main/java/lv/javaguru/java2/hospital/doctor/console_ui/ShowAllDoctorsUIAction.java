package lv.javaguru.java2.hospital.doctor.console_ui;

import lv.javaguru.java2.hospital.doctor.services.ShowAllDoctorsService;

public class ShowAllDoctorsUIAction implements DoctorUIActions {

    private final ShowAllDoctorsService showAllDoctors;

    public ShowAllDoctorsUIAction(ShowAllDoctorsService showAllDoctors) {
        this.showAllDoctors = showAllDoctors;
    }

    @Override
    public void execute() {
        System.out.println(showAllDoctors.execute());
    }
}
